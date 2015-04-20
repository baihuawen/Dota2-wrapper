package com.github.kaisle.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

import com.google.gson.JsonParseException;

/**
 * This class is responsible for extracting XML-data from a URL.
 * @author Anders Borum
 *
 */
public class Query {

	private SAXBuilder builder;
	private HttpURLConnection con;
	public static Object connectionLock = new Object(); // Lock to prevent multiple threads from querying the API simultaneously and breaching the API call limit specified by Valve

	/**
	 * Initiate a new Query-object with a dedicated SAX-builder.
	 */
	public Query() {
		LoggingStopWatch sax = new LoggingStopWatch("SAX");
		builder = new SAXBuilder();
		sax.stop();
	}

	/**
	 * Query the webAPI with respect to the given parameters and build an XML-document containing the response. The default format is JSON so to use this method, please make sure to include a parameter which specifies that the format should be XML. 
	 * For JSON, use queryAPIForJSON. If the server is too busy to handle the request, the method will call the server
	 * again until a response is received. The method has a built-in mechanism
	 * which makes sure that the API call limit of 1 call per second is always satisfied. 
	 * @param request
	 *            The request-string
	 * @param parameters
	 *            A mapped representation of query parameters used when calling
	 *            the API, eg. [format, xml] to make sure the response is in
	 *            XML-format.
	 * @return A JDOM document containing the response
	 */
	public Document queryAPI(String request, HashMap<String, String> parameters) {
		Document response = new Document();
		try {
			URL requestURL = new URL(appendParameters(request, parameters));
			con = (HttpURLConnection) (requestURL).openConnection();
			prepareConnection(con);
			StopWatch overall = new LoggingStopWatch("Overall");
			StopWatch connect = new LoggingStopWatch("Connecting");
			synchronized (connectionLock) { // Prevent multiple threads from accessing the API simultaneously. 
				long startTime = System.currentTimeMillis();
				con.connect();
				connect.stop();
				StopWatch fetch = new LoggingStopWatch("Fetching data-stream");
				InputStream inputStream = con.getInputStream();
				fetch.stop();
				StopWatch building = new LoggingStopWatch("Building");
				response = builder.build(inputStream);
				building.stop();
				overall.stop();
				inputStream.close();
				while (System.currentTimeMillis() < startTime + 1000) {
					// wait 1 second to respect the API call limit 
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Server too busy.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return queryAPI(request, parameters); // API too busy, call it again
		} 
		catch (JDOMParseException e) {
			System.out.println("Body of response not XML.");
		}
		catch (JDOMException e) {
			e.printStackTrace();
		}
		
		finally {
			con.disconnect();
		}
		return response;
	} 
	
	/**
	 * Append parameters to a request-URL
	 * 
	 * @param request
	 *            Request
	 * @param parameters
	 *            Parametes
	 * @return Modified URL
	 */
	public String appendParameters(String request,
			HashMap<String, String> parameters) {

		String requestURLString = new String(request + "?key=" + Defines.apiKey); // Add mandatory key param
		if (parameters != null) {
			for (String parameter : parameters.keySet()) // Append other parameters to URL
			{
				requestURLString = requestURLString + "&" + parameter + "="
						+ parameters.get(parameter);
			}
		}

		return requestURLString;
	}

	/**
	 * Prepare connection for webAPI.
	 * @param con
	 *            The connection to prepare.
	 */
	public void prepareConnection(HttpURLConnection con)
			throws ProtocolException {
		con.setRequestMethod("GET");
		con.setDoInput(true);
		con.setUseCaches(false);
		con.setRequestProperty("Connection", "close");
	}

	/**
	 * Recursively iterate through available matches and
	 * add them to a document. The server will usually return 100 results per
	 * query. The iteration is done by taking the last match id in the set of
	 * 100 returned matches and performing a new query with that match id as a
	 * start_at_match_id parameter (effectively returning the last match from
	 * the previous query and 99 new entries).
	 * Note that only a player's last 500 matches can be retrieved due to
	 * Valve constraints. Also note that a root element must be specified for
	 * the document before populating it with matches.
	 * 
	 * @param document
	 *            The document which the data is added to. Must contain a root
	 *            element.
	 * @param parameters
	 *            The parameters to use in the search.
	 * @param firstQuery
	 *            Boolean specifying whether this is the first step in the
	 *            iteration. If so, the first match entry is not deleted.
	 */
	public void getSequenceOfMatches(Document document,
			HashMap<String, String> parameters, boolean firstQuery) {

		Document sequenceDoc = queryAPI(Defines.matchHistoryURL, parameters); // Get document of matches
		Element sequenceRoot = sequenceDoc.getRootElement(); // Get root element of document

		Element sequenceMatches = sequenceRoot.getChild("matches");
		List<Element> matches = sequenceMatches.getChildren("match"); // Get a list of matches

		if (!firstQuery) {
			matches.remove(0);
		} // Remove the first match since it was already added to the document in the previous method-call
		document.getRootElement().addContent(sequenceRoot.clone()); // Add modified document of matches to the main document

		System.out.println("Matches retrieved: " + matches.size());
		if (new Integer(sequenceRoot.getChild("results_remaining").getText()) > 0) { // Call this method recursively until no matches left
			Element lastMatchId = matches.get(matches.size() - 1).getChild(
					"match_id"); // Get the match-ID of the last match
			parameters.put("start_at_match_id", lastMatchId.getText());
			getSequenceOfMatches(document, parameters, false);
		}
	}

	
	/**
	 * Query the API and return the response as a JSONObject. 
	 * @param request The request to be used in the query.
	 * @param parameters The parameters to be used in the query. 
	 * @return A JSONObject containing the response. 
	 */
	public JSONObject queryAPIForJSON(String request,
			HashMap<String, String> parameters) {
		JSONObject response = new JSONObject();
		try {
			URL requestURL = new URL(appendParameters(request, parameters));
			con = (HttpURLConnection) (requestURL).openConnection();
			prepareConnection(con);
			StopWatch overall = new LoggingStopWatch("Overall JSON");
			StopWatch connect = new LoggingStopWatch("Connecting");
			synchronized (connectionLock) { // Prevent multiple threads from accessing the API simultaneously. 
				long startTime = System.currentTimeMillis();
				con.connect();
				connect.stop();
				StopWatch fetch = new LoggingStopWatch("Fetching data-stream");
				InputStream inputStream = con.getInputStream();
				BufferedReader streamReader = new BufferedReader(
						new InputStreamReader(inputStream, "UTF-8"));
				StringBuilder responseStrBuilder = new StringBuilder();
				fetch.stop();
				StopWatch building = new LoggingStopWatch("Building");
				String inputStr;
				while ((inputStr = streamReader.readLine()) != null)
					responseStrBuilder.append(inputStr);
				response = new JSONObject(responseStrBuilder.toString());
				building.stop();
				overall.stop();
				inputStream.close();
				while (System.currentTimeMillis() < startTime + 1000) {
					// wait 1 second to respect the API call limit 
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Server too busy.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return queryAPIForJSON(request, parameters); // API too busy, call it again
		} 
		catch (JsonParseException e) {
			System.out.println("Response body not JSON.");
		}
		
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return response;
	}

}
