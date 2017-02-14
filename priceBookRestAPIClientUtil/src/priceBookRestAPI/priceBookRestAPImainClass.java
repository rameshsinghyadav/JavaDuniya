package priceBookRestAPI;

import java.math.BigDecimal;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.pb.common.model.HttpResponseWrapper;
import com.pb.common.service.RESTInvokerService;
import com.pb.common.service.impl.RESTInvokerServiceImpl;

public class priceBookRestAPImainClass {
	
	public static void main(String[] args) {
		
		RESTInvokerService  restinvokerService = new RESTInvokerServiceImpl();
		HttpResponseWrapper httpResponseWrapper =restinvokerService.invokeGET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyAotbxtH0sBfenPZkXNK2upjkyMiuiRsYo");

		if (httpResponseWrapper != null) {
	      Integer statusCode = httpResponseWrapper.getResponseCode();
	      String content = httpResponseWrapper.getResponseBody();
	      JSONParser jsonParser = new JSONParser();
	      if (statusCode.toString().equalsIgnoreCase("200")) {
	       try {
	        JSONObject json = (JSONObject) jsonParser.parse(content);
	        System.out.println("json   "+json.keySet());
	        Set<String> keyset = json.keySet();
	        
	        for (String string : keyset) {
	        	 System.out.println("key: "+string +"   value: "+json.get(string));				
			}	        
	       	} catch (ParseException e) {
	       }		
	      }
		}
	}
}
