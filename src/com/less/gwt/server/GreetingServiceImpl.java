package com.less.gwt.server;

import com.less.gwt.client.GreetingService;
import com.less.gwt.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {


	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public String greetServer(String input, boolean status) throws IllegalArgumentException {
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 1 characters long");
		}


		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		return reverseString(input, status);
	}
	private String reverseString(String string, boolean check){
		String result="";
		if (check){
			String[] parts=string.split(" ");
			for (String str:parts){
				String reverse=new StringBuffer(str).reverse().toString();
				result=result+reverse+" ";
			}
		}else{
			result=new StringBuffer(string).reverse().toString();
		}
		return result.trim();
		
	}
}
