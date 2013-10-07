package dods.foodmarket;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SuperMarket {

	private String id;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
