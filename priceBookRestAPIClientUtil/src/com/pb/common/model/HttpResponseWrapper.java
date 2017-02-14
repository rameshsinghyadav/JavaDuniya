package com.pb.common.model;


/**
 * RESTWrapper class is model class for httpResponse.
 * 
 * @author Ramesh
 *
 */
public class HttpResponseWrapper {
	  private Integer responseCode;
	  private String responseBody;

	  public HttpResponseWrapper(Integer responseCode,String responseBody){
		  this.responseCode = responseCode;
		  this.responseBody = responseBody;
	  }
	  public Integer getResponseCode()
	  {
	    return responseCode;
	  }

	  public void setResponseCode(Integer responseCode)
	  {
	    this.responseCode = responseCode;
	  }

	  public String getResponseBody()
	  {
	    return responseBody;
	  }

	  public void setResponseBody(String responseBody)
	  {
	    this.responseBody = responseBody;
	  }
	 
}
