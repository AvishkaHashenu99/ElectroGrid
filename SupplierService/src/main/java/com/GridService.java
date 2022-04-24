package com;

import model.Grid; 

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*; 

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Grid") 

public class GridService {
	
	Grid gridObj = new Grid(); 
	
	  @GET 
	  @Path("/") 
	  @Produces(MediaType.TEXT_HTML) 
	  public String readGrid() 
	  { 
	    return gridObj.readGrid(); 
	  }
	  
	  @POST 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String insertGrid(@FormParam("name") String name,     
	        @FormParam("resourceType") String resourceType, 
	        @FormParam("totalCapacity") String totalCapacity,      
	        @FormParam("address") String address,
	        @FormParam("phone") String phone,
	  		@FormParam("powerSupplierID") String powerSupplierID) 
	  { 
	    String output = gridObj.insertGrid(name, resourceType, totalCapacity, address, phone, powerSupplierID); 
	    return output; 
	  }
	  
	  @PUT 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String updateGrid(String gridData) 
	  { 
	    //Convert the input string to a JSON object 
	    JsonObject gridObject = new JsonParser().parse(gridData).getAsJsonObject(); 
	   
	    //Read the values from the JSON object 
	    String gridID = gridObject.get("gridID").getAsString(); 
	    String name = gridObject.get("name").getAsString(); 
	    String resourceType = gridObject.get("resourceType").getAsString(); 
	    String totalCapacity = gridObject.get("totalCapacity").getAsString(); 
	    String address = gridObject.get("address").getAsString();
	    String phone = gridObject.get("phone").getAsString(); 
	    String powerSupplierID = gridObject.get("powerSupplierID").getAsString();
	   
	    	String output = gridObj.updateGrid(gridID, name, resourceType, totalCapacity, address, phone, powerSupplierID); 
	   
	    return output; 
	  }  
	  
	  @DELETE 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_XML) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String deleteGrid(String gridData) 
	  { 
	    //Convert the input string to an XML document 
	    Document doc = Jsoup.parse(gridData, "", Parser.xmlParser()); 
	       
	    //Read the value from the element <itemID> 
	    String gridID = doc.select("gridID").text(); 
	   
	    String output = gridObj.deleteGrid(gridID); 
	   
	    return output; 
	  }
	  
	  @GET
	  @Path("/gridByID")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.TEXT_PLAIN)
	  public String readGridByID(String gridIDData)
	  {
	  	//Convert the input string to a JSON object 
	  	JsonObject gridObject = new JsonParser().parse(gridIDData).getAsJsonObject();
	  	//Read the values from the JSON object
	  	String gridId = gridObject.get("gridID").getAsString();
	  	return gridObj.readGridByID(gridId);				  
	  }

}
