package com.pb.common.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.pb.common.model.HttpResponseWrapper;
import com.pb.common.service.RESTInvokerService;


/**
 * The Class RESTInvoker.
 */

public class RESTInvokerServiceImpl implements RESTInvokerService {
	
	private class HttpMethodType {
		public static final String HTTP_GET = "GET";
		public static final String HTTP_POST = "POST";
		public static final String HTTP_PUT = "PUT";
		public static final String HTTP_DELETE = "DELETE";
	}

	public HttpResponseWrapper invokeGET(String path) {
		// TODO Auto-generated method stub
		return invokeHttpMethod(HttpMethodType.HTTP_GET, path, "");
	}
	
	private HttpResponseWrapper invokeHttpMethod(String httpMethod, String path, String data) {
		HttpResponseWrapper returnResponse = null;
		if (HttpMethodType.HTTP_GET.equalsIgnoreCase(httpMethod) || HttpMethodType.HTTP_POST.equalsIgnoreCase(httpMethod) || HttpMethodType.HTTP_PUT.equalsIgnoreCase(httpMethod)
				|| HttpMethodType.HTTP_DELETE.equalsIgnoreCase(httpMethod)) {

			HttpClient httpClient = new DefaultHttpClient();
			HttpRequestBase requestObject = null;
			requestObject = getRequestObject(httpMethod, path, data);

			if (requestObject != null) {
				// set request parameters
//				setHeaders(requestObject);
				try {
					HttpResponse response = null;

					response = httpClient.execute(requestObject);
					int statusCode = response.getStatusLine().getStatusCode();
					if (response.getEntity() != null) {						
						BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
						StringBuilder sb = null;
						sb = new StringBuilder();
						String line = "";
						while ((line = rd.readLine()) != null) {
							sb.append(line);
						}
						String content = sb.toString();
						returnResponse = new HttpResponseWrapper(statusCode, content);
					} else {
						returnResponse = new HttpResponseWrapper(statusCode, null);
					}

				} catch (Exception e) {
				} finally {
					requestObject.abort();
				}
			}
		}
		return returnResponse;
	}
	
	/*private void setHeaders(HttpRequestBase baseRequest) {

		if (authorization != null && !"".equalsIgnoreCase(authorization))
			baseRequest.addHeader(Iconstant.AUTHORIZATION, authorization);

		if (contentType != null && !"".equalsIgnoreCase(contentType))
			baseRequest.addHeader(Iconstant.CONTENT_TYPE, contentType);

	}*/
	
	private HttpRequestBase getRequestObject(String httpMethodType, String uri, String data) {

		// data is only used for POST and PUT requests
		HttpRequestBase httpRequest = null;
		if (httpMethodType.equalsIgnoreCase(HttpMethodType.HTTP_GET))
			httpRequest = new HttpGet(uri);
		if (httpMethodType.equalsIgnoreCase(HttpMethodType.HTTP_POST)) {
			HttpPost postRequest = new HttpPost(uri);
			StringEntity input;
			try {
				input = new StringEntity(data);
				postRequest.setEntity(input);
				httpRequest = postRequest;
			} catch (UnsupportedEncodingException e) {
			}
		}
		if (httpMethodType.equalsIgnoreCase(HttpMethodType.HTTP_PUT)) {
			HttpPut putRequest = new HttpPut(uri);
			StringEntity input;
			try {
				input = new StringEntity(data);
				putRequest.setEntity(input);
				httpRequest = putRequest;
			} catch (UnsupportedEncodingException e) {
			}
		}
		if (httpMethodType.equalsIgnoreCase(HttpMethodType.HTTP_DELETE))
			httpRequest = new HttpDelete(uri);

		return httpRequest;
	}
	
}
