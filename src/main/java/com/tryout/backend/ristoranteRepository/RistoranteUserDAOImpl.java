package com.tryout.backend.ristoranteRepository;

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
	
	@Value("${spring.datasource.url}")
	private static String url;
	@Value("${spring.datasource.username}")
	private static String user;
	@Value("${spring.datasource.password}")
	private static String password;

	//private final PasswordEncoder passwordEncoder;
	
	//@Autowired
	public RistoranteUserDAOImpl() {
		//this.passwordEncoder = passwordEncoder;
	}
	
	public static Connection connectToDB() {
		try {
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, password);
	        System.out.println("Connected to the PostgreSQL server successfully at: " + url);
	        return conn;
		}
		catch(SQLException e) {
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
	
	private static List<RistoranteUser> getRistoranteUsers() {
		List<RistoranteUser> ristoranteUsers = new ArrayList<RistoranteUser>();
		
        try {
        	Connection conn = connectToDB();
        	ResultSet results = conn.createStatement().executeQuery("select * from customers;");
            while(results.next()) {
            	String email = results.getString("email");
                String  password = results.getString("password");
                ristoranteUsers.add(new RistoranteUser(null, password, email, true, true, true, true));
            }
           
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println(ristoranteUsers);
        
		return ristoranteUsers;
	}
	
	
	public static boolean createNewUser(String username, String password) {
		try {
			Connection conn = connectToDB();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO customers (username, password) VALUES (?, ?)");
			statement.setString(1, username);
			statement.setString(2, password);
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}	
	}

}
