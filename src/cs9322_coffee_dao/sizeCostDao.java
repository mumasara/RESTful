package cs9322_coffee_dao;

import java.util.HashMap;
import java.util.Map;

public enum sizeCostDao {

	instance;
	private Map<String, Float> content = new HashMap<String, Float>();
	
	private sizeCostDao(){
			
		//drinkCost*(1+sizeCost)
		content.put("large", Float.parseFloat("0.3"));
		content.put("medium", Float.parseFloat("0.2"));
		content.put("small", Float.parseFloat("0"));
		
	}
	
    public Map<String, Float> getSizeCost(){
        return content;
    }
	
}
