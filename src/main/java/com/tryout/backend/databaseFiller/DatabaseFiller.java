package com.tryout.backend.databaseFiller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryout.backend.ristoranteEntity.Comment;
import com.tryout.backend.ristoranteEntity.Dish;
import com.tryout.backend.ristoranteEntity.Feedback;
import com.tryout.backend.ristoranteEntity.Leader;
import com.tryout.backend.ristoranteEntity.Promotion;
import com.tryout.backend.ristoranteEntity.Ristorante;

@JsonIgnoreProperties(ignoreUnknown = true) //to ignore unknown fields during json parsing
public class DatabaseFiller {
	
	public DatabaseFiller() {
		//Connection conn = this.dataBaseFiller();
		//Ristorante ristoranteModerno = jacksonReader();
		//this.insertDishes(conn, ristoranteModerno.getDishes());
		//this.insertComments(conn, ristoranteModerno.getComments());
		//this.insertLeaders(conn, ristoranteModerno.getLeaders());
		//this.insertFeedbacks(conn, ristoranteModerno.getFeedback());
		//this.insertPromotions(conn, ristoranteModerno.getPromotions());
		
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
	
	
	public static Ristorante jacksonReader() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //to ignore unknown fields in json file
		Ristorante ristoranteModerno = null;
		try {
			// JSON file to Java object
	         ristoranteModerno = mapper.readValue(new File("src/main/resources/db - Copy.json"), Ristorante.class);
	         System.out.println("ristorantedb file found!");
	         List<Dish>dishes = ristoranteModerno.getDishes();
	         System.out.println("The name of the second dish is: " + dishes.get(2).getName());
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return ristoranteModerno;
		
	}
	
	
	/**
     * insert multiple dishes
     */
    public void insertDishes(Connection conn, List<Dish> list) {
        String SQL = "INSERT INTO dish (name, image, category, label, price, featured, description, servetime) "
                + "VALUES(?,?,?,?,?,?,?,?)";
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
                statement.setString(8, dish.getServeTime());

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
    
    /**
     * insert multiple dishes
     */
    public void insertComments(Connection conn, List<Comment> list) {
        String SQL = "INSERT INTO comment (dishid, rating, comment, author, date) "
                + "VALUES(?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;

            for (Comment comment : list) {
                statement.setInt(1, comment.getDishId()+1);
                statement.setDouble(2, comment.getRating());
                statement.setString(3, comment.getComment());
                statement.setString(4, comment.getauthor());
                statement.setString(5, comment.getDate());

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
    
    /**
     * insert multiple dishes
     */
    public void insertLeaders(Connection conn, List<Leader> list) {
        String SQL = "INSERT INTO leader (name, image, designation, abbr, featured, description) "
                + "VALUES(?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;

            for (Leader leader : list) {
                statement.setString(1, leader.getName());
                statement.setString(2, leader.getImage());
                statement.setString(3, leader.getDesignation());
                statement.setString(4, leader.getAbbr());
                statement.setBoolean(5, leader.isFeatured());
                statement.setString(6, leader.getDescription());

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
    
    
    
    /**
     * insert multiple dishes
     */
    public void insertPromotions(Connection conn, List<Promotion> list) {
        String SQL = "INSERT INTO promotion (name, image, label, price, featured, description) "
                + "VALUES(?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;

            for (Promotion promotion : list) {
                statement.setString(1, promotion.getName());
                statement.setString(2, promotion.getImage());
                statement.setString(3, promotion.getLabel());
                statement.setDouble(4, promotion.getPrice());
                statement.setBoolean(5, promotion.isFeatured());
                statement.setString(6, promotion.getDescription());

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
    
    
    
    /**
     * insert multiple dishes
     */
    public void insertFeedbacks(Connection conn, List<Feedback> list) {
    	
    	System.out.println("Feedback size is: " + list.size());
    	System.out.println("First feedback message is: " + list.get(0).getMessage());
    	
        String SQL = "INSERT INTO feedback (firstname, lastname, telnum, email, agree, contactType, message, date) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try (
            PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;

            for (Feedback feedback : list) {
            	statement.setString(1, feedback.getFirstname());
            	statement.setString(2, feedback.getLastname());
                statement.setInt(3, feedback.getTelnum());
                statement.setString(4, feedback.getEmail());
                statement.setBoolean(5, feedback.getAgree());
                statement.setString(6, feedback.getContactType());
                statement.setString(7, feedback.getMessage());
                statement.setString(8, feedback.getDate());
                

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
