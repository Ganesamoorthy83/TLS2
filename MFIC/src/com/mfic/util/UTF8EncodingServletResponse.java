package com.mfic.util;

import javax.servlet.http.HttpServletResponse;

public class UTF8EncodingServletResponse extends
		javax.servlet.http.HttpServletResponseWrapper {
	private boolean encodingSpecified = false;

	public UTF8EncodingServletResponse(HttpServletResponse response) {
		super(response);
	}

	public void setContentType(String type) {
		String explicitType = type;
		// If a specific encoding has not already been set by the app,
		// let's see if this is a call to specify it. If the content
		// type doesn't explicitly set an encoding, make it UTF-8.
		if (!encodingSpecified) {
			String lowerType = type.toLowerCase();
			// See if this is a call to explicitly set the character encoding.
			if (lowerType.indexOf("charset") < 0) {
				// If no character encoding is specified, we still need to
				// ensure the app is specifying text content.
				if (lowerType.startsWith("text/")) {
					// App is sending a text response, but no encoding
					// is specified, so we'll force it to UTF-8.
					explicitType = type + "; charset=UTF-8";
				}
			} else {
				// App picked a specific encoding, so let's make
				// sure we don't override it.
				encodingSpecified = true;
			}
		}
		// Delegate to supertype to record encoding.
		super.setContentType(explicitType);
	}
}
