package cs9322_coffee_dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import cs9322_coffee_model.Coffee;

public class processingItemDao {
	//private static String ORDER_FILE = "orders.txt";
	private static String PROCESSINGITEM_FILE = "/home/alf/workspace/9322_assignment3/processingitem.txt";
	
	ordersFileDao ofd = new ordersFileDao ();
	
	public String stringConstructor(Coffee c){
		String result = c.getId()+" ";
		result = result + c.getDrink()+" ";
		result = result + c.getSize()+" ";
		result = result + Float.toString(c.getCost())+" ";
		result = result + c.getAddition() + " ";
		result = result + c.getPaytype() + " ";
		if(c.getStatusFlag()==true){
			result += "true";
		}
		else{
			result +="false";
		}
		
		return result;
	}
	
	
	public void addItem(String order_id, Coffee c){
		String toWrite = stringConstructor(c);
		//System.out.println(toWrite);
		try{
			FileWriter theFile = new FileWriter(PROCESSINGITEM_FILE,true);                
			PrintWriter out = new PrintWriter(theFile);
			
			out.print(toWrite + "\n");                                                      
			out.close();
			theFile.close();
			
			//add one into processing, delete one in the orders
			ofd.deleteOrder(order_id);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	
	
	public int deleteItem(String item_id){
		try {
			ArrayList<Coffee> currentItemList = getAllItems();
			FileWriter writer = new FileWriter(PROCESSINGITEM_FILE, false);
			int i=0;
			while(i < currentItemList.size()){
				if(item_id.equals( currentItemList.get(i).getId() )==true ){
					currentItemList.remove(i);
				}
				i++;
			}
			i=0;
			writer.append("id drink size cost addition payType statusFlag\n");
			while(i < currentItemList.size()){
				writer.append( currentItemList.get(i).getId() + " " + currentItemList.get(i).getDrink() + " " + 
						currentItemList.get(i).getSize() + " " + currentItemList.get(i).getCost() + " " +
						currentItemList.get(i).getAddition() + " " + currentItemList.get(i).getPaytype() + " " +
						currentItemList.get(i).getStatusFlag() + "\n");
				i++;
			}
			writer.close();
			return 1;
			
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	public Coffee search4Item(String item_id){
		Coffee c=null;
		ArrayList<Coffee> currentItemList = getAllItems();
		
		int i=0;
		while(i < currentItemList.size()){
			if(item_id.equals( currentItemList.get(i).getId() )==true ){
				c.setId(currentItemList.get(i).getId());
				c.setDrink(currentItemList.get(i).getDrink());
				c.setSize(currentItemList.get(i).getSize());
				c.setCost(currentItemList.get(i).getCost());
				c.setAddition(currentItemList.get(i).getAddition());
				c.setPayType(currentItemList.get(i).getPaytype());
				c.setStatusFlag(currentItemList.get(i).getStatusFlag());
				break;
			}
			i++;
		}
		
		return c;
	}
	
	
	
	public static ArrayList<Coffee> getAllItems() {
		ArrayList<Coffee> itemList = new ArrayList<Coffee>();
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(PROCESSINGITEM_FILE)));
			read.readLine();
			String temp = null;
			//id drink size cost addition payType statusFlag
			while((temp = read.readLine()) != null){
				String[] fields = new String[6];
				fields = temp.split(" ");

				Coffee c = new Coffee();
				c.setId(fields[0]);
				c.setDrink(fields[1]);
				c.setSize(fields[2]);
				c.setCost(Float.parseFloat(fields[3]));
				c.setAddition(fields[4]);
				c.setPayType(fields[5]);
				
				if(fields[6].equals("true")==true){
					c.setStatusFlag(true);
				}
				else{
					c.setStatusFlag(false);
				}
				itemList.add(c);
			}
			read.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return itemList;
	}
	
}
