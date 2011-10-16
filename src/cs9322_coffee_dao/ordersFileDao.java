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

public class ordersFileDao {

	private static String ORDER_FILE = "/home/alf/workspace/9322_assignment3/orders.txt";
	//ArrayList<Coffee> orders = getAllOrders();
	
	
	public String stringConstructor(Coffee c){
		String result = c.getId()+" ";
		result = result + c.getDrink()+" ";
		result = result + c.getSize()+" ";
		result = result + Float.toString(c.getCost())+" ";
		result = result + c.getAddition() + " ";
		result = result + c.getPayType() + " ";
		if(c.getStatusFlag()==true){
			result += "true";
		}
		else{
			result +="false";
		}
		
		return result;
	}
	
	public void addOrder(String order_id, Coffee c){
		String toWrite = stringConstructor(c);
		//System.out.println(toWrite);
		try{
			FileWriter theFile = new FileWriter(ORDER_FILE,true);                
			PrintWriter out = new PrintWriter(theFile);
			
			out.print(toWrite + "\n");                                                      
			out.close();
			theFile.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public int deleteOrder(String order_id){
		try {
			ArrayList<Coffee> currentOrderList = getAllOrders();
			FileWriter writer = new FileWriter(ORDER_FILE, false);
			int i=0;
			while(i < currentOrderList.size()){
				if(order_id.equals( currentOrderList.get(i).getId() )==true ){
					currentOrderList.remove(i);
				}
				i++;
			}
			i=0;
			writer.append("id drink size cost addition payType statusFlag\n");
			while(i < currentOrderList.size()){
				writer.append( currentOrderList.get(i).getId() + " " + currentOrderList.get(i).getDrink() + " " + 
						currentOrderList.get(i).getSize() + " " + currentOrderList.get(i).getCost() + " " +
						currentOrderList.get(i).getAddition() + " " + currentOrderList.get(i).getPayType() + " " +
						currentOrderList.get(i).getStatusFlag() + "\n");
				i++;
			}
			writer.close();
			return 1;
			
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int updateAnOrder(String order_id, Coffee c){
		try{
			ArrayList<Coffee> currentOrderList = getAllOrders();
			FileWriter writer = new FileWriter(ORDER_FILE, false);
			
			int i=0;
			while(i < currentOrderList.size()){
				if(order_id.equals( currentOrderList.get(i).getId() )==true ){
					currentOrderList.get(i).setId(c.getId());
					currentOrderList.get(i).setDrink(c.getDrink());
					currentOrderList.get(i).setSize(c.getSize());
					currentOrderList.get(i).setCost(c.getCost());
					currentOrderList.get(i).setAddition(c.getAddition());
					currentOrderList.get(i).setPayType(c.getPayType());
					currentOrderList.get(i).setStatusFlag(c.getStatusFlag());
				}
				i++;
			}
			i=0;
			writer.append("id drink size cost addition payType statusFlag\n");
			while(i < currentOrderList.size()){
				writer.append( currentOrderList.get(i).getId() + " " + currentOrderList.get(i).getDrink() + " " + 
						currentOrderList.get(i).getSize() + " " + currentOrderList.get(i).getCost() + " " +
						currentOrderList.get(i).getAddition() + " " + currentOrderList.get(i).getPayType() + " " +
						currentOrderList.get(i).getStatusFlag() + "\n");
				i++;
			}
			writer.close();
			return 1;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		//return currentOrderList;
	}
	
	//set statusFlag to be true
	public int payOrder(String order_id){
		try{
			ArrayList<Coffee> currentOrderList = getAllOrders();
			FileWriter writer = new FileWriter(ORDER_FILE, false);
			paymentFileDao pdf = new paymentFileDao();
			Payment p = new Payment();
			
			Coffee temp = new Coffee();
			
			int i=0;
			while(i < currentOrderList.size()){
				if(order_id.equals( currentOrderList.get(i).getId() )==true ){
					temp = currentOrderList.get(i);
					currentOrderList.get(i).setStatusFlag(true);
				}
				i++;
			}
			i=0;
			writer.append("id drink size cost addition payType statusFlag\n");
			while(i < currentOrderList.size()){
				writer.append( currentOrderList.get(i).getId() + " " + currentOrderList.get(i).getDrink() + " " + 
						currentOrderList.get(i).getSize() + " " + currentOrderList.get(i).getCost() + " " +
						currentOrderList.get(i).getAddition() + " " + currentOrderList.get(i).getPayType() + " " +
						currentOrderList.get(i).getStatusFlag() + "\n");
				i++;
			}
			writer.close();
			
			//not deleting an order when it is paid
			//add as a payment into the payment file
			p.setPID(order_id);
			p.setPayType(temp.getPayType());
			p.setAmount(temp.getCost());
			p.setDetails(temp.getPayType());
			p.setStatusFlag(true);
			
			pdf.addPayment(order_id, p);
			
			return 1;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public void setStatusToBeTrue(String id){
		Coffee c = search4Order(id);
		if(c == null){
			System.out.println("order is null");
		}
		else{
			c.setStatusFlag(true);
			updateAnOrder(id,c);
			
		}
	}
	
	
	
	public Coffee search4Order(String order_id){
		Coffee c= new Coffee();
		ArrayList<Coffee> currentOrderList = getAllOrders();
		
		int i=0;
		while(i < currentOrderList.size()){
			if(order_id.equals( currentOrderList.get(i).getId() )==true ){
				c.setId(currentOrderList.get(i).getId());
				c.setDrink(currentOrderList.get(i).getDrink());
				c.setSize(currentOrderList.get(i).getSize());
				c.setCost(currentOrderList.get(i).getCost());
				c.setAddition(currentOrderList.get(i).getAddition());
				c.setPayType(currentOrderList.get(i).getPayType());
				c.setStatusFlag(currentOrderList.get(i).getStatusFlag());
				break;
			}
			i++;
		}
		
		return c;
	}
	
	
	public static ArrayList<Coffee> getAllOrders() {
		ArrayList<Coffee> orderList = new ArrayList<Coffee>();
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(ORDER_FILE)));
			read.readLine();
			String temp = null;
			//id drink size cost addition payType statusFlag
			while((temp = read.readLine()) != null){
				String[] fields = new String[6];
				fields = temp.split(" ");
//				System.out.println("line is:: "+temp);

				Coffee c = new Coffee();
				c.setId(fields[0]);
				c.setDrink(fields[1]);
				c.setSize(fields[2]);
				c.setCost(Float.parseFloat(fields[3]));
				c.setAddition(fields[4]);
				c.setPayType(fields[5]);
				
				System.out.println("in ordersFileDao:: "+c.getPayType());
				
				if(fields[6].equals("true")==true){
					c.setStatusFlag(true);
				}
				else{
					c.setStatusFlag(false);
				}
				//System.out.println("in ordersFileDao:: "+c.getStatusFlag());
				
				orderList.add(c);
			}
			read.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return orderList;
	}
	
	
}
