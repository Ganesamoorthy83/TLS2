package com.mfic.util.netconnect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.Security;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import sun.misc.BASE64Encoder;

import com.thoughtworks.xstream.XStream;

public class NCClient {
    private String requestFile;
    private String userID;
    private String password;
    private final static String SERVER_URL = "https://stg1-ss1.experian.com/netconnect2_0Demo/servlets/NetConnectServlet";
        
    
    public NCClient(String requestFile, String userID, String password) {
        this.requestFile = requestFile;
        this.userID = userID;
        this.password = password;
    }
    
    public static void main(String[] args) throws Exception {
        if(args.length != 3) {
            System.out.println("Usage: NCClient <request_file> <user_id> <password>");
        }
        
        System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
        Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider());
        
        NCClient client = new NCClient(args[0], args[1], args[2]);
            
        client.processRequest();
    }
    
    public String parseXML(){
    	XStream xstream = new XStream();
    	xstream.alias("CreditProfile", CreditProfileRequest.class);
    	xstream.alias("Subscriber", Subscriber.class);
    	xstream.alias("NetConnectRequest", NetConnectRequest.class);
    	
    	CreditProfileRequest crequest = new CreditProfileRequest();
    	crequest.getSubscriber().setPreamble("TBD3");
    	crequest.getSubscriber().setOpInitials("TN");
    	crequest.getSubscriber().setSubCode("1599880");
    	crequest.getPrimaryApplicant().setName(new ApplicantName("Jonathan", "Quincy", "Consumer", "Jr"));
    	crequest.getPrimaryApplicant().setSSN("999999990");
    	crequest.getPrimaryApplicant().setCurrentAddress(new Address("10655 NorthBirch Street", "Burbank", "CA", "91502"));
    	
    	NetConnectRequest ncRequest = new NetConnectRequest();
    	ncRequest.setEAI("NV46D3WZ");
    	ncRequest.setDBHost("STAR");
    	ncRequest.setReferenceId("TEST");
    	ncRequest.setRequest(new Request());
    	ncRequest.getRequest().getProducts().setCreditProfileRequest(crequest);
    	
    	String xml = xstream.toXML(ncRequest);
    	
    	System.out.println(xml); 
    	return xml;
    }
        
    @SuppressWarnings("deprecation")
	public void processRequest() throws Exception {
        String strAuth = getAuthorization();
        String response;
        
        StringBuffer sbufPost = new StringBuffer();
        //String fileContent = URLEncoder.encode(readXML(requestFile));
        String fileContent = URLEncoder.encode(parseXML());
        System.out.println("Request:\n" + fileContent);
        sbufPost.append("&NETCONNECT_TRANSACTION=" + fileContent);
        
        // Create the Http connection
        HttpURLConnection httpConnection = createConnection();

        if(httpConnection == null) {
            System.out.println("Error Creating Http Connection");
            return;
        }
        
        // Send the XML request
        try {
            sendRequest(httpConnection, strAuth, sbufPost.toString());
            // Get the response
            response = getResponse(httpConnection);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

       
        if(response == null) {
            return;
        }
        
        String cookies = getCookies(httpConnection);
        
        httpConnection.disconnect();
        
        System.out.println(cookies + "\n");
        System.out.println("Response:\n" + response);
    }
    
    public String readXML(String filename) {
        BufferedReader in = null;
        File file = null;
        FileInputStream finput = null;
        String inputLine = null;
        StringBuffer fileContent = new StringBuffer();
        
        try {
            file = new File("C:\\kumar\\mfic\\src\\com\\mfic\\util\\" + filename);
                       
            finput = new FileInputStream(file);
            in = new BufferedReader(new InputStreamReader(finput));
            while ((inputLine = in.readLine()) != null) {
                fileContent.append(inputLine + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
        return fileContent.toString();
    }
    
    private String getAuthorization() {
        String uidpwd = userID + ":" + password;
        
        // Encode the user ID and password
        byte[]  inputBytes = uidpwd.getBytes();
        BASE64Encoder encoder = new BASE64Encoder();
        
        return encoder.encode(inputBytes);
    }
    
    private HttpURLConnection createConnection() {
        // Create the URL class
        URL url = null;

        try {
            url = new URL(SERVER_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(url == null) {
                return null;
            }
        }
        
        // Create the connection the server
        URLConnection connection = null;
        
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection == null) {
                return null;
            }
        }
        
        // Configure the Http connection
        HttpURLConnection httpConnection = null;
        
        httpConnection = (HttpURLConnection)(connection);
        httpConnection.setDoInput(true);
        httpConnection.setDoOutput(true);

        try{
            httpConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        
        return httpConnection;
    }
    
    private void sendRequest(HttpURLConnection httpConnection
                             , String authorization
                             , String request) throws IOException {
        httpConnection.setRequestProperty("Authorization","BASIC " + authorization);
        httpConnection.setUseCaches(false);
        PrintWriter writer = new PrintWriter(httpConnection.getOutputStream());
        writer.println(request);
        writer.close();
    }
    
    private String getResponse(HttpURLConnection httpConnection) {
        String response = null;

        try {
            BufferedReader httpReader = new BufferedReader(new InputStreamReader(
                                        httpConnection.getInputStream()));

            String inputLine = null;
            StringBuffer buff = new StringBuffer();
            
            while ((inputLine = httpReader.readLine()) != null) {
                buff.append(inputLine);
            }

            response = buff.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return response;
    }
    
    private String getCookies(HttpURLConnection httpConnection) {
        StringBuffer buff = new StringBuffer();
        String headerKey = null;
        String headerVal = null;
        
        for (int i=0;;i++){
            headerKey = httpConnection.getHeaderFieldKey(i);
            headerVal = httpConnection.getHeaderField(i);

            if (headerKey!=null || headerVal!=null) {
                buff.append("Cookie[" + Integer.toString(i) + "] " + headerKey + " = "+headerVal +"<br>");
            } else {
                break;
            }
        }
        
        return buff.toString();
    }

}