package com.ymagis.daf.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.ymagis.daf.request.GameRequest;
import com.ymagis.daf.response.GameResponse;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author daf
 * token post
 * api/start
 * api/test
 */
public class ApiClient {

	private final String baseUrl;
	private final ObjectMapper mapper;
	private final Client client;
	private final String API_KEY;

	private static final String CONTENT_ORDERS = "content_orders";
	private static final String DISTRIBUTION_PACKAGES = "distribution_packages";

	public ApiClient(String baseUrl, String apiKey, int connectionTimeout, int readTimeout)
			throws MalformedURLException {
		this.baseUrl = baseUrl;
		this.mapper = new ObjectMapper();
		client = getClient(connectionTimeout, readTimeout);
		this.API_KEY = apiKey;
	}

	private Client getClient(final int connectionTimeout, final int readTimeout) {
		Client c;
		c = new Client(new URLConnectionClientHandler(new HttpURLConnectionFactory() {
			public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
				return (HttpURLConnection) url.openConnection();
			}
		}));
		c.setConnectTimeout(connectionTimeout);
		c.setReadTimeout(readTimeout);
		return c;
	}

	public GameResponse getGameReponse(GameRequest gameRequest) {
		ClientResponse res = null;
		try {
			res = client.resource(baseUrl).path(CONTENT_ORDERS).queryParams(gameRequest.getParameters())
					.header("X-Api-Key", API_KEY).accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

			System.out.println(res.toString());
			if (res.getStatus() == 200) {
				GameResponse response = mapper.readValue(res.getEntity(String.class), GameResponse.class);
				return response;
			} else {
				throw new Exception("Track receiver plugin failed, code error : " + res.getStatus());
			}
		} catch (Exception e) {
			Logger.getLogger(ApiClient.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			return new GameResponse();
		} finally {
			if (res != null) {
				try {
					res.close();
				} catch (Exception ignored) {
				}
			}
		}
	}
}
