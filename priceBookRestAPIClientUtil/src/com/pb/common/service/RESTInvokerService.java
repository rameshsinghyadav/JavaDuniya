package com.pb.common.service;

import com.pb.common.model.HttpResponseWrapper;


public interface RESTInvokerService {

	public HttpResponseWrapper invokeGET(String path);
}