package cs9322_coffee_dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import cs9322_coffee_model.Payment;
import cs9322_coffee_model.Coffee;

public enum paymentDao {

	instance;
	private Map<String, Payment> contentPayment = new HashMap<String, Payment>();
	
	private paymentDao(){		
		
		Iterator it = coffeeDao.instance.getOrders().entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Coffee value = (Coffee)entry.getValue();
			//System.out.println("key=" + key + " value=" + value);
			
			//TODO::: CARD or CASH
			if(value.getPayType() == "card"){
				String details = "1234567890";
				Payment temp = new Payment(value.getId(),value.getPayType(),value.getCost(),details,value.getStatusFlag());
				contentPayment.put(value.getId(), temp);
			}
			else{
				Payment temp = new Payment(value.getId(),value.getPayType(),value.getCost(),"cash",value.getStatusFlag());
				contentPayment.put(value.getId(), temp);
			}
		}
		
		
		//Payment = new Payment()
		
	}
	
    public Map<String, Payment> getPayments(){
        return contentPayment;
    }
	
}
