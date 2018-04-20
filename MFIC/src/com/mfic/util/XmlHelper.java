package com.mfic.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlHelper {
	private static final Logger logger = Logger.getLogger(XmlHelper.class);

	/**
	 * Formats given XML String.
	 * @param xmlString
	 * @return formated xmlString
	 */
	public static String format(String xmlString) {
		String formatedXmlString = null;
		try {
			final Document document = parseXmlString(xmlString);
			Writer out = new StringWriter();
			formatedXmlString = format(document, out);
		} catch (Exception e) {
			logger.error(e);
			formatedXmlString = xmlString; // This case should not occur. But to be in the safer side. If an exception occurs when formating, just return the xmlString as it is.
		}
		return formatedXmlString;
	}

	private static String format(Document doc, Writer out) throws Exception {

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer serializer;
		try {
			serializer = tfactory.newTransformer();
			// Setup indenting to "pretty print"
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");

			serializer.transform(new DOMSource(doc), new StreamResult(out));
			return out.toString();
		} catch (TransformerException e) {
			// this is fatal, just dump the stack and throw a runtime exception
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	private static Document parseXmlString(String in) throws Exception {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
