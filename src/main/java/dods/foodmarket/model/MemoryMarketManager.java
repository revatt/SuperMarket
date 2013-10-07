package dods.foodmarket.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import dods.foodmarket.FoodItem;
import dods.foodmarket.SuperMarket;
import dods.foodmarket.SuperMarketItem;

public class MemoryMarketManager implements MarketManager {
	
	private ConcurrentHashMap<String, FoodItem> foodItems = new ConcurrentHashMap<String, FoodItem>();
	
	private ConcurrentHashMap<String, SuperMarket> markets = new ConcurrentHashMap<String, SuperMarket>();
	
	private ConcurrentHashMap<String, SuperMarketItem> marketItems = new ConcurrentHashMap<String, SuperMarketItem>();

	public Set<String> getAllFoodTypes() {
		HashSet<String> allFoodTypes = new HashSet<String>();
		for(FoodItem item: foodItems.values()) {
			allFoodTypes.add(item.getType());
		}
		return allFoodTypes;
	}
	
	public Set<SuperMarket> getAllMarkets() {
		HashSet<SuperMarket> allSuperMarkets = new HashSet<SuperMarket>();
		for(SuperMarket market: markets.values()) {
			allSuperMarkets.add(market);
		}
		return allSuperMarkets;
	}

	private String getMarketItemKey(String market, String item) {
		return market + "^" + item;
	}
	
	public int getQuantity(String market, String item) {
		if(market == null || item == null) {
			return -2;
		}
		String marketItemKey = getMarketItemKey(market, item);
		SuperMarketItem marketItem = this.marketItems.get(marketItemKey);
		if(marketItem == null) {
			return -1;
		}
		return marketItem.getQuantity();
	}
	
	public boolean addOrUpdate(String marketId, String itemId, int quantity) {
		if(marketId == null || itemId == null) {
			return false;
		}
		if(!markets.containsKey(marketId) 
				|| !foodItems.containsKey(itemId)) {
			return false;
		}
		String marketItemKey = getMarketItemKey(marketId, itemId);
		SuperMarketItem marketItem = new SuperMarketItem();
		marketItem.setItemId(itemId);
		marketItem.setSuperMarketId(marketId);
		marketItem.setQuantity(quantity);
		this.marketItems.put(marketItemKey, marketItem);
		return true;
	}
	
	public Set<FoodItem> getFoodItems(String type) {
		HashSet<FoodItem> items = new HashSet<FoodItem>();
		for(FoodItem item: foodItems.values()) {
			if(item.getType().equals(type)) {
				items.add(item);
			}
		}
		return items;
	}
	
	public Set<FoodItem> getFoodItemsInSuperMarket(String superMarketId) {
		HashSet<FoodItem> allFoodItems = new HashSet<FoodItem>();
		for(SuperMarketItem item: this.marketItems.values()) {
			if(item.getSuperMarketId().equals(superMarketId)) {
				FoodItem foodItem = this.foodItems.get(item.getItemId());
				if(foodItem != null) {
					allFoodItems.add(foodItem);
				}
			}
		}
		return allFoodItems;
	}

	public boolean addFoodItem(FoodItem item) {
		if(item == null) {
			return false;
		}
		this.foodItems.put(item.getId(), item);
		return true;
	}

	public boolean addSuperMarket(SuperMarket market) {
		if(market == null) {
			return false;
		}
		this.markets.put(market.getId(), market);
		return true;
	}

	@Override
	public Set<FoodItem> getAllFoodItems() {
		HashSet<FoodItem> allFoodItems = new HashSet<FoodItem>();
		for(FoodItem item: this.foodItems.values()) {
			FoodItem foodItem = this.foodItems.get(item.getId());
			if(foodItem != null) {
				allFoodItems.add(foodItem);
			}
		}
		return allFoodItems;
	}
}
