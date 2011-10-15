package cs9322_coffee_model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Payment {

	private String pID;
	private String payType;
	private float amount;
	private String details;
	private boolean statusFlag;
	
	public Payment(){
		
	}
	
	
	//constructor for paying BY CARD
	public Payment(String pID, String payType, float amount, String details, boolean statusFlag){
		this.pID = pID;
		this.payType = payType;
		this.amount = amount;
		this.details = details;
		this.statusFlag = statusFlag;
	}
	
	public String getPID(){
		return pID;
	}
	
	public void setPID(String pID){
		this.pID = pID;
	}
	
	public String getPayType(){
		return payType;
	}
	
	public void setPayType(String payType){
		this.payType = payType;
	}
	
	public float getAmount(){
		return amount;
	}
	
	public void setAmount(float amount){
		this.amount = amount;
	}
	
	public String getDetails(){
		return details;
	}
	
	public void setDetails(String details){
		if (details.equals("card") == true){
			this.details = details;
		}
		else if (details.equals("cash") == true){
			this.details = "cash";
		}
	}
	
	public boolean getStatusFlag(){
		return statusFlag;
	}
	
	public void setStatusFlag(boolean statusFlag){
		this.statusFlag = statusFlag;
	}
	
}
