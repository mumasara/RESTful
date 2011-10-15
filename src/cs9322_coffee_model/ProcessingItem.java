package cs9322_coffee_model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import cs9322_coffee_dao.ordersFileDao;

@XmlRootElement
public class ProcessingItem {
	
	private Coffee c;
	
	public ProcessingItem(){
		
	}
	
	public ProcessingItem(Coffee c){
		this.c = c;
	}
	
	public Coffee getProcessingItem(String order_id){
		ordersFileDao ofd = new ordersFileDao();
		ArrayList<Coffee> temp = new ArrayList<Coffee>();
		int i=0;
		temp = ofd.getAllOrders();
		while(i<temp.size()){
			if (temp.get(i).getId().equals(order_id) == true ){
				Coffee c = new Coffee();
				c = temp.get(i);
				return c;
			}
			
		}
		return null;
	}
	
	public void setProcessingItem(Coffee c){
		this.c = c;
	}
	
}
