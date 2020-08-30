package ristoranteEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	private String description;
	
	@Column(name="servetime")
	private String serveTime;
	
	@OneToMany(mappedBy="userId", cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) //refers to the dishId property in the comments table, (i.e. the table which contains the foreign key)
	private List<Comment> comments; //mapped by=userId refers to the dishId field in Comment class. Note that comments is not a column, but refers to the fact that a list of comments of this dish is tracked by the dishId column in the comments table.
	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
			   CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="dish_user", joinColumns=@JoinColumn(name="dishId"), inverseJoinColumns=@JoinColumn(name="userId"))
	private List<User> users;
	
	//constructor
	public Dish() {
		
	}


	public Dish(String name, String image, String category, String label, double price, double feature,
			String description, String serveTime) {
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
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
