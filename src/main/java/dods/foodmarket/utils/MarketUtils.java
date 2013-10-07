package dods.foodmarket.utils;

import dods.foodmarket.FoodItem;
import dods.foodmarket.SuperMarket;
import dods.foodmarket.model.MemoryMarketManager;

public class MarketUtils {
	
	public static final String MARKET_TESCO = "TSCO";
	public static final String MARKET_ASDA = "ASDA";
	
	public static final String FOOD_ID_APPLE = "APPLE";
	public static final String FOOD_ID_ORANGE = "ORANGE";
	public static final String FOOD_ID_CHICKEN = "CHICKEN";
	
	public static final String FOOD_TYPE_FRUIT = "FRUIT";
	public static final String FOOD_TYPE_MEAT = "MEAT";
	
	public static MemoryMarketManager getDefaultMemoryMarketManager() {
		 MemoryMarketManager memoryMarketManager = new MemoryMarketManager();
		 
		 memoryMarketManager.addFoodItem(getFoodItem(FOOD_ID_APPLE, "Apples", FOOD_TYPE_FRUIT));
		 memoryMarketManager.addFoodItem(getFoodItem(FOOD_ID_ORANGE, "Oranges", FOOD_TYPE_FRUIT));
		 memoryMarketManager.addFoodItem(getFoodItem(FOOD_ID_CHICKEN, "Chicken", FOOD_TYPE_MEAT));
		 
		 memoryMarketManager.addSuperMarket(getSuperMarket(MARKET_TESCO, "Tesco super market"));
		 memoryMarketManager.addSuperMarket(getSuperMarket(MARKET_ASDA, "ASDA super market"));
		 
		 memoryMarketManager.addOrUpdate(MARKET_TESCO, FOOD_ID_APPLE, 10);
		 memoryMarketManager.addOrUpdate(MARKET_TESCO, FOOD_ID_ORANGE, 10);
		 
		 memoryMarketManager.addOrUpdate(MARKET_ASDA, FOOD_ID_APPLE, 20);
		 memoryMarketManager.addOrUpdate(MARKET_ASDA, FOOD_ID_CHICKEN, 20);
		 
		 return memoryMarketManager;
	}

	private static SuperMarket getSuperMarket(String id, String description) {
		SuperMarket market = new SuperMarket();
		market.setId(id);
		market.setDescription(description);
		return market;
	}

	private static FoodItem getFoodItem(String id, String description, String type) {
		FoodItem item = new FoodItem();
		item.setId(id);
		item.setDescription(description);
		item.setType(type);
		return item;
	}

}
