package com.splitwise.models.expense;

import java.util.List;

import com.splitwise.models.User;
import com.splitwise.models.split.ExactSplit;
import com.splitwise.models.split.PercentSplit;
import com.splitwise.models.split.Split;

public class PercentExpense extends Expense {
	
	 public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
	        super(amount, paidBy, splits, expenseMetadata);
	    }
	 
	 @Override
	 public boolean validate(){
		 for(Split split : getSplits()){
				if(!(split instanceof PercentSplit))
					return false;
			}
			
		 double totalPercent = 100;
		 double sumSplitPercent = 0;
		 
		 for(Split split : getSplits()){
			 PercentSplit  percentSplit= (PercentSplit) split;
			 sumSplitPercent+= percentSplit.getPercent();
		 }
		 
		 if(totalPercent!= sumSplitPercent)
			 return false;
		 
		 return true;
	 }

}
