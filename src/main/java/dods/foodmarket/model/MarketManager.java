package dods.foodmarket.model;

import java.util.List;
import java.util.Set;

import dods.foodmarket.FoodItem;
import dods.foodmarket.SuperMarket;

public interface MarketManager {
	
	public Set<String> getAllFoodTypes();
	
	public Set<FoodItem> getAllFoodItems();
	
	public Set<SuperMarket> getAllMarkets();
	
	public int getQuantity(String marketId, String itemId);
	
	public boolean addFoodItem(FoodItem item);
	
	public boolean addSuperMarket(SuperMarket market);
	
	public boolean addOrUpdate(String marketId, String itemId, int quantity);
	
	public Set<FoodItem> getFoodItems(String type);
	
	public Set<FoodItem> getFoodItemsInSuperMarket(String superMarketId);
}
