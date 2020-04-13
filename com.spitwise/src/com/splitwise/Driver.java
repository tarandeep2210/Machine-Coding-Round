package com.splitwise;

import java.util.*;
import com.splitwise.models.*;
import com.splitwise.models.expense.ExpenseType;
import com.splitwise.models.split.EqualSplit;
import com.splitwise.models.split.ExactSplit;
import com.splitwise.models.split.PercentSplit;
import com.splitwise.models.split.Split;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        ExpenseManager expenseManager = new ExpenseManager();
		
		expenseManager.addUser(new User("u1","Taran", "rubal@gmail.com", "8146047857"));
		
		expenseManager.addUser(new User("u2","Yatin", "yatin@gamil.com","9876543210"));
		
		expenseManager.addUser(new User("u3","Machi", "Machi@gamil.com","9876543211"));
		
		expenseManager.addUser(new User("u4","Preet", "Preet@gamil.com","9876543212"));
		
		
		
		Scanner scanner = new Scanner(System.in);
		
		while(true){
			String command = scanner.nextLine();
			String[] commands = command.split(" ");
			String commandType = commands[0];
			
			switch(commandType){
			
				case "SHOW" : 
					if(commands.length==1){
						//call show balances
						expenseManager.showBalances();
						break;
					}
					else{
						//call show balances with user Id;
						expenseManager.showBalance(commands[1]);
						break;
					}
					
				case "EXPENSE" :
					String paidBy = commands[1];
					Double amount = Double.parseDouble(commands[2]);
					
					int noOfUsers = Integer.parseInt(commands[3]);
					
					String expenseType =  commands[4+ noOfUsers];
					
					List<Split> splits = new ArrayList<>();
					
					switch(expenseType){
						
						case "EQUAL" :
							 for (int i = 0; i < noOfUsers; i++) {
	                                splits.add(new EqualSplit(expenseManager.userMap.get(commands[4 + i])));
	                            }
	                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
	                            break;
							
						case "EXACT" :
							 for (int i = 0; i < noOfUsers; i++) {
	                                splits.add(new ExactSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
	                            }
	                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
	                            break;
							
						case "PERCENT" :
							for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentSplit(expenseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                            break;
					}
					
					break;
			}
		}
		
		

	}

}
