package cs9322_coffee_model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coffee {

	private String id;
	private String drink;
	private String size;
	private float cost;
	private String addition;
	private String payType;
	private boolean statusFlag;
	
    public Coffee(){

    }
    
    public Coffee(String id,String drink,String size,float cost,String addition,String payType,boolean status){
    	this.id = id;
    	this.drink = drink;
    	this.size = size;
    	this.cost = cost;
    	this.addition = addition;
    	this.payType = payType;
    	this.statusFlag = status;
    	
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDrink() {
        return drink;
    }
    public void setDrink(String drink) {
        this.drink = drink;
    }
    
    public String getSize(){
    	return size;
    }
    
    public void setSize(String size){
    	this.size = size;
    }
    
    public float getCost(){
    	return cost;
    }
    
    public void setCost(float cost){
    	this.cost = cost;
    }
    
    
    public String getAddition(){
    	return addition;
    }
    
    public void setAddition(String addition){
    	this.addition = addition;
    }
    
	public String getPayType(){
		return payType;
	}
    public void setPayType(String payType){
    	this.payType = payType;
    }
    


    
    
	public boolean getStatusFlag(){
		return statusFlag;
	}
	
	public void setStatusFlag(boolean statusFlag){
		this.statusFlag = statusFlag;
	}
    
    
}
