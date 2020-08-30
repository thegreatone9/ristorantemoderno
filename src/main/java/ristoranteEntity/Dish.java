package ristoranteEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dish")
public class Dish {
	
	//create field vars
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="category")
	private String category;
	
	@Column(name="label")
	private String label;
	
	@Column(name="price")
	private double price;
	
	@Column(name="feature")
	private double feature;
	
	@Column(name="description")
	private double description;
	
	@Column(name="servetime")
	private String serveTime;
	
	//constructor
	public Dish() {
		
	}


	public Dish(String name, String image, String category, String label, double price, double feature,
			double description, String serveTime) {
		this.name = name;
		this.image = image;
		this.category = category;
		this.label = label;
		this.price = price;
		this.feature = feature;
		this.description = description;
		this.serveTime = serveTime;
	}

	
	//getters and setters
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	public double getFeature() {
		return feature;
	}


	public void setFeature(double feature) {
		this.feature = feature;
	}


	public double getDescription() {
		return description;
	}


	public void setDescription(double description) {
		this.description = description;
	}


	public String getServeTime() {
		return serveTime;
	}

	public void setServeTime(String serveTime) {
		this.serveTime = serveTime;
	}

	//toString
	

	@Override
	public String toString() {
		return "Dishes [id=" + id + ", name=" + name + ", image=" + image + ", category=" + category + ", label="
				+ label + ", price=" + price + ", feature=" + feature + ", description=" + description + ", serveTime="
				+ serveTime + "]";
	}
	
}
