package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

public class Breakdown {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBreakdown(String region, String description, String phone, String user) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into breakdown (`breakdownID`,`region`,`description`,`phone`,`user`,`date`,`status`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			String strDate = dateFormat.format(date);
			Integer status = 0;
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, region);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, phone);
			preparedStmt.setInt(5, Integer.parseInt(user));
			preparedStmt.setString(6, strDate);
			preparedStmt.setInt(7, status);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Breakdown Reported Successfully!!!";
		} catch (Exception e) {
			output = "Error while reporting the breakdown";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBreakdowns() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Region</th><th>Description</th>" + "<th>Mobile Number</th>" + "<th>User</th>" + "<th>Date</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from breakdown";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String breakdownID = Integer.toString(rs.getInt("breakdownID"));
				String region = rs.getString("region");
				String description = rs.getString("description");
				String phone = rs.getString("phone");
				String user = Integer.toString(rs.getInt("user"));
				String date = rs.getString("date");
				// Add into the html table
				output += "<tr><td>" + region + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + user + "</td>";
				output += "<td>" + date + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='breakdownID' type='hidden' value='" + breakdownID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBreakdown(String breakdownID, String region, String description, String phone, String user) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE breakdown SET region=?,description=?,phone=?,user=? WHERE breakdownID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, region);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, phone);
			preparedStmt.setInt(4, Integer.parseInt(user));
			preparedStmt.setInt(5, Integer.parseInt(breakdownID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Breakdown Info Updated Successfully!";
		} catch (Exception e) {
			output = "Error while updating Breakdown Info.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBreakdown(String breakdownID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from breakdown where breakdownID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(breakdownID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Breakdown Complain Deleted Successfully";
		} catch (Exception e) {
			output = "Error while deleting the user account.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
