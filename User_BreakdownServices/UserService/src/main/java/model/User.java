package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
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

	public String insertUser(String username, String password, String accountNumber, String address, String NIC, String phone, String reset_code) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user (`userID`,`username`,`password`,`accountNumber`,`address`,`NIC`,`phone`, `reset_code`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, password);
			preparedStmt.setInt(4, Integer.parseInt(accountNumber));
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, NIC);
			preparedStmt.setString(7, phone);
			preparedStmt.setString(8, reset_code);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "User Account Created Successfully!!!";
		} catch (Exception e) {
			output = "Error while creating the user account";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Username</th><th>Account Number</th>" + "<th>Address</th>" + "<th>NIC</th>" + "<th>Phone</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String username = rs.getString("username");
				String address = rs.getString("address");
				String accountNumber = Integer.toString(rs.getInt("accountNumber"));
				String NIC = rs.getString("NIC");
				String phone = rs.getString("phone");
				// Add into the html table
				output += "<tr><td>" + username + "</td>";
				output += "<td>" + accountNumber + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + phone + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + userID + "'>" + "</form></td></tr>";
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

	public String updateUser(String userID, String username, String password, String accountNumber, String address, String NIC, String phone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE user SET username=?,password=?,accountNumber=?,address=?,NIC=?,phone=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.setInt(3, Integer.parseInt(accountNumber));
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, NIC);
			preparedStmt.setString(6, phone);
			preparedStmt.setInt(7, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "User Account Updated Successfully!";
		} catch (Exception e) {
			output = "Error while updating the user account.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String userID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "User Account Deleted Successfully";
		} catch (Exception e) {
			output = "Error while deleting the user account.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}