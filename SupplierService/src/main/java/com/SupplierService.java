package com;

import model.Supplier; 

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*; 

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

	@Path("/Supplier") 

public class SupplierService {
	
		Supplier supplierObj = new Supplier(); 
	
	  @GET 
	  @Path("/") 
	  @Produces(MediaType.TEXT_HTML) 
	  public String readSuppliers() 
	  { 
	    return supplierObj.readSuppliers(); 
	  }
	  
	  @POST 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String insertSupplier(@FormParam("name") String name,     
	        @FormParam("address") String address, 
	        @FormParam("NIC") String NIC,      
	        @FormParam("phone") String phone) 
	  { 
	    String output = supplierObj.insertSupplier(name, address, NIC, phone); 
	    return output; 
	  }
	  
	  @PUT 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_JSON) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String updateSupplier(String supplierData) 
	  { 
	    //Convert the input string to a JSON object 
	    JsonObject supplierObject = new JsonParser().parse(supplierData).getAsJsonObject(); 
	   
	    //Read the values from the JSON object 
	    String powerSupplierID = supplierObject.get("powerSupplierID").getAsString(); 
	    String name = supplierObject.get("name").getAsString(); 
	    String address = supplierObject.get("address").getAsString(); 
	    String NIC = supplierObject.get("NIC").getAsString(); 
	    String phone = supplierObject.get("phone").getAsString(); 
	   
	         String output = supplierObj.updateSupplier(powerSupplierID, name, address, NIC, phone); 
	   
	    return output; 
	  } 
	  
	  @DELETE 
	  @Path("/") 
	  @Consumes(MediaType.APPLICATION_XML) 
	  @Produces(MediaType.TEXT_PLAIN) 
	  public String deleteSupplier(String supplierData) 
	  { 
	    //Convert the input string to an XML document 
	    Document doc = Jsoup.parse(supplierData, "", Parser.xmlParser()); 
	       
	    //Read the value from the element <itemID> 
	    String powerSupplierID = doc.select("powerSupplierID").text(); 
	   
	    String output = supplierObj.deleteSupplier(powerSupplierID); 
	   
	    return output; 
	  }

}
