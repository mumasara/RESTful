package cs9322_coffee_dao;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cs9322_coffee_model.Coffee;

public enum coffeeDao {

	instance;
	private Map<String, Coffee> contentOrder = new HashMap<String, Coffee>();
	
	private coffeeDao(){
		
//		Coffee c = new Coffee("1","chai","large",(float) 3.5,"syrup","cash",false);
//		contentOrder.put("1", c);
//		
//		c = new Coffee("2","latte","medium",(float) 5.5,"syrup","card",false);
//		contentOrder.put("2", c);
	}
	
    public Map<String, Coffee> getOrders(){
        return contentOrder;
    }
	
}

