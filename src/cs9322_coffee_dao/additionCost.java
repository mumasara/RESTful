package cs9322_coffee_dao;

import java.util.HashMap;
import java.util.Map;

public enum additionCost {
	
	instance;
	private Map<String, Float> content = new HashMap<String, Float>();
	
	private additionCost(){
		
		content.put("sugar", Float.parseFloat("0.1"));
		content.put("syrup", Float.parseFloat("0.5"));
		content.put("milk", Float.parseFloat("0.3"));
		content.put("choc", Float.parseFloat("0.3"));
	}
	
    public Map<String, Float> getAdditionCost(){
        return content;
    }
	
}
