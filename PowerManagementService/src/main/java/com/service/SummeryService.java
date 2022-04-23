package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Model.PowerSummery;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/getSummery")
public class SummeryService {
	PowerSummery Obj = new PowerSummery();
	
	//reads
	@GET
	@Path("/monthly/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getSummery(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String capID = itemObject.get("key").getAsString();

		

		
		String[] output = Obj.getSummeryArray(capID); 
		return output[1];
	}
	
	
	//reads
	@GET
	@Path("/Annual/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getYearSummery(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String capID = itemObject.get("key").getAsString();

		

		
		String[] output = Obj.getAnnualSummeryTable(capID); 
		return output[1];
	}
}
