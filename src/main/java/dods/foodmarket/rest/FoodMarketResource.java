package dods.foodmarket.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dods.foodmarket.FoodItem;
import dods.foodmarket.SuperMarket;
import dods.foodmarket.model.MarketManager;
import dods.foodmarket.utils.MarketUtils;

@Singleton
@Path("/foodmarket")
public class FoodMarketResource {
	
	private MarketManager marketManager;
	
	public FoodMarketResource() {
		marketManager = MarketUtils.getDefaultMemoryMarketManager();
	}
	
	@GET
	@Path("/supermarkets")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<SuperMarket> getAllSuperMarkets() {
		Set<SuperMarket> marks = marketManager.getAllMarkets();
		LinkedList<SuperMarket> list = new LinkedList<SuperMarket>();
		for(SuperMarket market: marks) {
			list.add(market);
		}
        return list;
    }
	
	@GET
	@Path("/fooditems")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<FoodItem> getAllFoodItems() {
		Set<FoodItem> items = marketManager.getAllFoodItems();
		LinkedList<FoodItem> list = new LinkedList<FoodItem>();
		for(FoodItem item: items) {
			list.add(item);
		}
        return list;
    }
	
	@GET
	@Path("/fooditems/{supermarket}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<FoodItem> getAllFoodItems(@PathParam("supermarket") String superMarketId) {
		Set<FoodItem> items = marketManager.getFoodItemsInSuperMarket(superMarketId);
		LinkedList<FoodItem> list = new LinkedList<FoodItem>();
		for(FoodItem item: items) {
			list.add(item);
		}
        return list;
    }
	
	@GET
	@Path("/fooditems/{supermarket}/{fooditem}")
	@Produces("text/plain")
    public String getIsAvailable(
    		@PathParam("supermarket") String superMarketId, 
    		@PathParam("fooditem") String foodItemId) {
		int quantity = marketManager.getQuantity(superMarketId, foodItemId);
		if(quantity > 0) {
			return "available";
		}
        return "not available";
    }
	
	@POST
	@Path("/addfooditem")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addFoodItem(@FormParam("id") String id,
			@FormParam("type") String type,
			@FormParam("description") String description) {
		FoodItem foodItem = new FoodItem();
		foodItem.setId(id);
		foodItem.setDescription(description);
		foodItem.setType(type);
		
		if(this.marketManager.addFoodItem(foodItem)) {
			return "Food item successfully added";
		}
		return "Food item NOT added!";
	}
	
	@POST
	@Path("/addsupermarket")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addSuperMarket(@FormParam("id") String id,
			@FormParam("description") String description) {
		SuperMarket market = new SuperMarket();
		market.setId(id);
		market.setDescription(description);
		
		if(this.marketManager.addSuperMarket(market)) {
			return "Super market added.";
		}
		return "Super market NOT added!";
	}

	@POST
	@Path("/addQuantity")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addQuantity(@FormParam("marketId") String marketId,
			@FormParam("itemId") String itemId,
			@FormParam("quantity") String quantity) {
		int quantityInt = 0;
		try {
			quantityInt = Integer.parseInt(quantity);
		} catch (Exception ex) {
		}
		if(this.marketManager.addOrUpdate(marketId, itemId, quantityInt)) {
			return "Success";
		}
		return "Failed, bad supermarket or item id";
	}
}
