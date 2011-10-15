package cs9322_coffee_dao;

import java.util.HashMap;
import java.util.Map;

import cs9322_coffee_model.Coffee;

public enum drinkCostDao {

	instance;
	private Map<String, Float> content = new HashMap<String, Float>();
	
	private drinkCostDao(){
		
		//Coffee c = new Coffee("1","chai","large",3.5,"syrup",false);
		//contentOrder.put("1", c);
		
		content.put("chai", Float.parseFloat("3.5"));
		content.put("cuppccino", Float.parseFloat("3.5"));
		content.put("latte", Float.parseFloat("3.3"));
		content.put("mocca", Float.parseFloat("3.3"));
		content.put("longblack", Float.parseFloat("3.0"));
		content.put("flatwhite", Float.parseFloat("3.0"));
	}
	
    public Map<String, Float> getDrinkCost(){
        return content;
    }
	
}
