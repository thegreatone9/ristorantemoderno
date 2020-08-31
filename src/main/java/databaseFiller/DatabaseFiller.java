package databaseFiller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ristoranteEntity.Dish;
import ristoranteEntity.Ristorante;

public class DatabaseFiller {
	
	public DatabaseFiller() {
		Connection conn = this.dataBaseFiller();
		List<Dish> dishes = jacksonReader();
		this.insertDishes(conn, dishes);
		
	}
	
	private Connection dataBaseFiller() {
		String url = "jdbc:postgresql://localhost/ristorante";
		String user = "postgres";
		String password = "admin";
		
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
	}
	
	
	public static List<Dish> jacksonReader() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //to ignore unknown fields in json file
		List<Dish> dishes = null;
		try {
			// JSON file to Java object
	         Ristorante ristoranteModerno = mapper.readValue(new File("src/main/resources/db - Copy.json"), Ristorante.class);
	         System.out.println("ristorantedb file found!");
	        dishes = ristoranteModerno.getDishes();
	         System.out.println("The name of the second dish is: " + dishes.get(2).getName());
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return dishes;
		
	}
	
	
	/**
     * insert multiple actors
     */
    public void insertDishes(Connection conn, List<Dish> list) {
        String SQL = "INSERT INTO dish (name, image, category, label, price, featured, description) "
                + "VALUES(?,?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;

            for (Dish dish : list) {
                statement.setString(1, dish.getName());
                statement.setString(2, dish.getImage());
                statement.setString(3, dish.getCategory());
                statement.setString(4, dish.getLabel());
                statement.setDouble(5, dish.getPrice());
                statement.setBoolean(6, dish.getfeatured());
                statement.setString(7, dish.getDescription());

                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == list.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
