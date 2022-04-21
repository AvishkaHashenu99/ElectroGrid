package com.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.Model.Power_Consumption;

@Path("/powerConsumption")
public class ConsumptionService {
	Power_Consumption Obj = new Power_Consumption();
	
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems(){
		
		return Obj.readItems();
	}
	
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(String itemData)
	{

		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		String ActNo = itemObject.get("AccountNo").getAsString();
		String currentReading = itemObject.get("currentReading").getAsString();
		String date = itemObject.get("date").getAsString();
		String type = itemObject.get("type").getAsString();
		String readerID = itemObject.get("readerID").getAsString();
		String userID = itemObject.get("userID").getAsString();
	
		
		String output = Obj.insertItem(ActNo, currentReading, date, type, readerID, userID); 
		return output;
	}
	
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object  
		String recID = itemObject.get("recordID").getAsString();
		String ActNo = itemObject.get("AccountNo").getAsString();
		String currentReading = itemObject.get("currentReading").getAsString();
		String date = itemObject.get("date").getAsString();
		String type = itemObject.get("type").getAsString();
		String readerID = itemObject.get("readerID").getAsString();
		String userID = itemObject.get("userID").getAsString();
		
		

		
		String output = Obj.updateItem(recID, ActNo, currentReading, date, type, readerID, userID); 
		return output;
	}
	
	
	//delete items
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		
		String recID = itemObject.get("recordID").getAsString();
		String output = Obj.deleteItem(recID);
		return output;
	}
}
