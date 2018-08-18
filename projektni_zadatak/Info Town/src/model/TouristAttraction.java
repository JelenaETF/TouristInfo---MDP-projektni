package model;

public class TouristAttraction {

	private String name;
	private String imageURL;
	private String description;
	
	public TouristAttraction() {}

	public TouristAttraction(String name, String imageURL, String description) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}