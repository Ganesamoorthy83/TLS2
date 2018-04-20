package com.mfic.util.netconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Security;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

import com.thoughtworks.xstream.XStream;

public class NetConnectClient {

	private static final Log log = LogFactory.getLog(NetConnectClient.class);

	private NetConnectRequest ncRequest;
	private CreditProfileRequest cRequest;
	private String userID;
	private String password;
	private String SERVER_URL;

	public NetConnectClient() throws IOException {
		intialize();
	}

	private void intialize() throws IOException {
		Properties props = new Properties();
		try {
			props.load(NetConnectClient.class.getClassLoader().getResourceAsStream("netconnect.properties"));
		} catch (IOException e) {
			log.error("unable to read Properties File", e);
			throw e;
		}
		SERVER_URL = props.getProperty("SERVER_URL");
		userID = props.getProperty("userid");
		password = props.getProperty("password");
		ncRequest = new NetConnectRequest();
		ncRequest.setEAI(props.getProperty("EAI"));
		ncRequest.setDBHost(props.getProperty("DBHost"));
		ncRequest.setReferenceId(props.getProperty("ReferenceId"));
		ncRequest.setRequest(new Request());
		cRequest = new CreditProfileRequest();
		cRequest.getSubscriber().setPreamble(props.getProperty("Preamble"));
		cRequest.getSubscriber().setOpInitials(props.getProperty("OpInitials"));
		cRequest.getSubscriber().setSubCode(props.getProperty("SubCode"));
		cRequest.getVendor().setVendorNumber(props.getProperty("VendorNumber"));

		System.setProperty("java.protocol.handler.pkgs",
				"com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	}

	@SuppressWarnings("deprecation")
	public String processRequest(String fname, String lname, String mname, String ssn, String street, String city, String state, String zip) {
		
		cRequest.getPrimaryApplicant().setName(new ApplicantName(fname, mname, lname, ""));
    	cRequest.getPrimaryApplicant().setSSN(ssn);
    	cRequest.getPrimaryApplicant().setCurrentAddress(new Address(street, city, state, zip));
    	
    	ncRequest.getRequest().getProducts().setCreditProfileRequest(cRequest);
    	XStream xstream = new XStream();
    	xstream.alias("CreditProfile", CreditProfileRequest.class);
    	xstream.alias("Subscriber", Subscriber.class);
    	xstream.alias("NetConnectRequest", NetConnectRequest.class);
    	
    	String xml = xstream.toXML(ncRequest);
    	String fileContent = URLEncoder.encode(xml);
		
		String strAuth = getAuthorization();
		String response;

		StringBuffer sbufPost = new StringBuffer();

		log.info("Request:\n" + fileContent);
		sbufPost.append("&NETCONNECT_TRANSACTION=" + fileContent);

		// Create the Http connection
		HttpURLConnection httpConnection = createConnection();

		if (httpConnection == null) {
			System.out.println("Error Creating Http Connection");
			return null;
		}

		// Send the XML request
		try {
			sendRequest(httpConnection, strAuth, sbufPost.toString());
			// Get the response
			response = getResponse(httpConnection);
		} catch (IOException e) {
			log.error("Error getting Net Connect Response", e);
			return null;
		}

		if (response == null) {
			return null;
		}

		String cookies = getCookies(httpConnection);

		httpConnection.disconnect();

		log.debug(cookies + "\n");
		log.info("Response:\n" + response);

		return URLDecoder.decode(response);
	}

	private String getCookies(HttpURLConnection httpConnection) {
		StringBuffer buff = new StringBuffer();
		String headerKey = null;
		String headerVal = null;

		for (int i = 0;; i++) {
			headerKey = httpConnection.getHeaderFieldKey(i);
			headerVal = httpConnection.getHeaderField(i);

			if (headerKey != null || headerVal != null) {
				buff.append("Cookie[" + Integer.toString(i) + "] " + headerKey
						+ " = " + headerVal + "<br>");
			} else {
				break;
			}
		}

		return buff.toString();
	}

	private String getAuthorization() {
		String uidpwd = userID + ":" + password;

		// Encode the user ID and password
		byte[] inputBytes = uidpwd.getBytes();
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
			if (url == null) {
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
			if (connection == null) {
				return null;
			}
		}

		// Configure the Http connection
		HttpURLConnection httpConnection = null;

		httpConnection = (HttpURLConnection) (connection);
		httpConnection.setDoInput(true);
		httpConnection.setDoOutput(true);

		try {
			httpConnection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		return httpConnection;
	}

	private void sendRequest(HttpURLConnection httpConnection,
			String authorization, String request) throws IOException {
		httpConnection.setRequestProperty("Authorization", "BASIC "
				+ authorization);
		httpConnection.setUseCaches(false);
		PrintWriter writer = new PrintWriter(httpConnection.getOutputStream());
		writer.println(request);
		writer.close();
	}

	private String getResponse(HttpURLConnection httpConnection) {
		String response = null;

		try {
			BufferedReader httpReader = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream()));

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

}
