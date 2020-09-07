package com.tryout.backend.ristoranteRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.tryout.backend.ristoranteEntity.RistoranteUser;

@Repository("ristoranteModernoDAO")
public class RistoranteUserDAOImpl{

	/*private static String url = "jdbc:postgresql://localhost:5432/ristorante2";
	private static String user = "postgres";
	private static String password = "admin";*/

	private static URI dbUri;
	private static String user;
	private static String password;
	private static String url;

	public RistoranteUserDAOImpl()  {

	}

	public static void constInitializer() throws URISyntaxException{

		try {
			dbUri = new URI(System.getenv("DATABASE_URL"));
			user = dbUri.getUserInfo().split(":")[0];
			password = dbUri.getUserInfo().split(":")[1];
			url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		}
		catch(URISyntaxException e){
			e.printStackTrace();
		}
	}

	public static Connection connectToDB() {

		try {
			constInitializer();
			Connection conn = null;
			System.out.println(url + " " + user + " " + " " + password);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully at: " + url);
			return conn;
		}
		catch(SQLException | URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static Optional<RistoranteUser> selectRistoranteUserByUsername(String email) {
		Optional<RistoranteUser> user = getRistoranteUsers()
				.stream()
				.filter(ristoranteUser -> email.equals(ristoranteUser.getUsername()))
				.findFirst();

		System.out.println(user.toString());
		return user;

	}

	public static boolean checkIfUserExists(String email) {
		try {
			constInitializer();
			System.out.println(email);
			Connection conn = connectToDB();
			PreparedStatement st = conn.prepareStatement("SELECT 1 FROM customers WHERE email = ?");
			st.setString(1, email);
			ResultSet results = st.executeQuery();
			System.out.println("Resultset created successfully while checking for existing accounts.");

			if (!results.next()) {
				System.out.println("Database check successful. No existing account with that email exists.");
				return false;
			}

			else {
				while(results.next()) System.out.println("Database found: " + results.getString(1));
			}
		}

		catch(SQLException | URISyntaxException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}

		System.out.println("Signup error: Account with name already exists!");
		return true;
	}

	private static List<RistoranteUser> getRistoranteUsers() {
		List<RistoranteUser> ristoranteUsers = new ArrayList<RistoranteUser>();

		try {
			constInitializer();
			Connection conn = connectToDB();
			ResultSet results = conn.createStatement().executeQuery("select * from customers;");
			while(results.next()) {
				String email = results.getString("email");
				String  password = results.getString("password");
				int customerid = results.getInt("id");
				ristoranteUsers.add(new RistoranteUser(customerid, null, password, email, true, true, true, true));
			}


		} catch (SQLException | URISyntaxException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(ristoranteUsers);

		return ristoranteUsers;
	}


	public static boolean createNewUser(String email, String password, String firstname, String lastname) {
		try {
			constInitializer();
			Connection conn = connectToDB();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO customers (email, password, first_name, last_name) VALUES (?, ?, ?, ?)");
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, firstname);
			statement.setString(4, lastname);
			statement.execute();
			System.out.println("Connected to DB -> Created account -> Now returning.");
			return true;
		}
		catch(SQLException | URISyntaxException e){
			e.printStackTrace();
			return false;
		}
	}

}
