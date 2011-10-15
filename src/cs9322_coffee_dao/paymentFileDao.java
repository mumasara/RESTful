package cs9322_coffee_dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import cs9322_coffee_model.Coffee;
import cs9322_coffee_model.Payment;

public class paymentFileDao {
	
	private static String ORDER_FILE = "/home/alf/workspace/9322_assignment3/orders.txt";
	private static String PAYMENT_FILE = "/home/alf/workspace/9322_assignment3/payments.txt";
	processingItemDao pid = new processingItemDao();
	ordersFileDao ofd = new ordersFileDao();
	
	public String stringConstructor(Payment p){
		String result = p.getPID() + " ";
		result = result + p.getPayType() + " ";
		result = result + p.getAmount() +" ";
		result = result + p.getDetails() + " ";
		result = result + p.getStatusFlag();
		
		//System.out.println("string is :: "+result);
		
		return result;
	}
	
	//content appended to the end of file
	public int addPayment(String payment_id, Payment p){
		String toWrite = stringConstructor(p);
		//System.out.println("String to write "+toWrite);
		try{
			Payment temp = new Payment();
			temp = getPayment(payment_id);
			
			//System.out.println("payment id:"+temp.getPID());
			
			if(temp.getPID() == null){
			
				FileWriter theFile = new FileWriter(PAYMENT_FILE,true);                
				PrintWriter out = new PrintWriter(theFile);
				
				out.print(toWrite + "\n");                                                      
				out.close();
				theFile.close();
				
				//add one into payment, delete one in the processingitem
				//pid.deleteItem(payment_id);
				
				//set the statusFlag of an order as TRUE, TO display it normally on the Cashier 
				//ofd.payOrder(payment_id);
				
				ofd.setStatusToBeTrue(payment_id);
			}
			else{
				System.out.println("payment already exists!");
			}
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
	
	public int deletePayment(String payment_id){
		try {
			ArrayList<Payment> currentPaymentList = getAllPayments();
			FileWriter writer = new FileWriter(PAYMENT_FILE, false);
			int i=0;
			while(i < currentPaymentList.size()){
				if(payment_id.equals( currentPaymentList.get(i).getPID() )==true ){
					currentPaymentList.remove(i);
				}
				i++;
			}
			i=0;
			writer.append("pID payType amount details statusFlag\n");
			while(i < currentPaymentList.size()){
				writer.append( currentPaymentList.get(i).getPID() + " " + currentPaymentList.get(i).getPayType() + " " + 
						currentPaymentList.get(i).getAmount() + " " + currentPaymentList.get(i).getDetails() + " " + 
						currentPaymentList.get(i).getStatusFlag() + "\n");
				i++;
			}
			writer.close();
			return 1;
			
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
//	public int updateAPayment(String payment_id, Payment p){
//		try{
//			ArrayList<Payment> currentPaymentList = getAllPayments();
//			FileWriter writer = new FileWriter(PAYMENT_FILE, false);
//			
//			int i=0;
//			while(i < currentPaymentList.size()){
//				if(payment_id.equals( currentPaymentList.get(i).getPID() )==true ){
//					currentPaymentList.get(i).setPID(p.getPID());
//					currentPaymentList.get(i).setPayType(p.getPayType());
//					currentPaymentList.get(i).setAmount(p.getAmount());
//					currentPaymentList.get(i).setDetails(p.getDetails());
//					currentPaymentList.get(i).setStatusFlag(p.getStatusFlag());
//				}
//				i++;
//			}
//			i=0;
//			writer.append("pID payType amount details statusFlag\n");
//			while(i < currentPaymentList.size()){
//				writer.append( currentPaymentList.get(i).getPID() + " " + currentPaymentList.get(i).getPayType() + " " + 
//						currentPaymentList.get(i).getAmount() + " " + currentPaymentList.get(i).getDetails() + " " + 
//						currentPaymentList.get(i).getStatusFlag() +  "\n");
//				i++;
//			}
//			writer.close();
//			return 1;
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return 0;
//		}
//	}
	
	
	public float getCurrentBalance(String pID){
		float sum=0;
		int it=0;
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		paymentList = getAllPayments();
		try{
			while(it<paymentList.size()){
				Payment temp = new Payment();
				temp = paymentList.get(it);
				//payment found
				if (temp.getPID().equals(pID) == true){
					//if the detail is not CASH
					if( temp.getDetails().equals("cash") == false ){
						//System.out.println(temp.getDetails());
						sum = Float.parseFloat( temp.getDetails() );
						return sum;
					}
				}
				it++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sum;
	}
	
	
	public float getCost(String pID){
		float cost=0;
		int it=0;
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		paymentList = getAllPayments();
		try{
			while(it<paymentList.size()){
				Payment temp = new Payment();
				temp = paymentList.get(it);
				//payment found
				if (temp.getPID().equals(pID) == true){
					cost = temp.getAmount();
					return cost;
				}
				it++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return cost;
	}
	
	
	public Payment getPayment(String pID){
		Payment p = new Payment();
		ArrayList<Payment> currentPaymentList = getAllPayments();
		int i=0;
		while(i < currentPaymentList.size()){
			if(pID.equals( currentPaymentList.get(i).getPID() )==true ){
				p.setPID(pID);
				p.setPayType(currentPaymentList.get(i).getPayType());
				p.setAmount(currentPaymentList.get(i).getAmount());
				p.setDetails(currentPaymentList.get(i).getDetails());
				p.setStatusFlag(currentPaymentList.get(i).getStatusFlag());
				break;
			}
			i++;
		}
		
		return p;
	}
	
	
	
	
	public static ArrayList<Payment> getAllPayments() {
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(PAYMENT_FILE)));
			read.readLine();
			String temp = null;
			//id drink size cost addition payType statusFlag
			while((temp = read.readLine()) != null){
				String[] fields = new String[5];
				fields = temp.split(" ");

				Payment p = new Payment();
				p.setPID(fields[0]);
				p.setPayType(fields[1]);
				p.setAmount( Float.parseFloat(fields[2]) );
				p.setDetails(fields[3]);
				if(fields[4].equals("true")==true){
					p.setStatusFlag(true);
				}
				else{
					p.setStatusFlag(false);
				}
				
				paymentList.add(p);
			}
			read.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return paymentList;
	}
	
}
