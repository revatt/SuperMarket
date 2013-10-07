package dods.foodmarket;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SuperMarketItem {

	private String superMarketId;
	
	private String itemId;
	
	private int quantity;

	public String getSuperMarketId() {
		return superMarketId;
	}

	public void setSuperMarketId(String superMarketId) {
		this.superMarketId = superMarketId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
