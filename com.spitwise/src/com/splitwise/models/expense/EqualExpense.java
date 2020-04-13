package com.splitwise.models.expense;

import java.util.List;

import com.splitwise.models.User;
import com.splitwise.models.split.EqualSplit;
import com.splitwise.models.split.Split;

public class EqualExpense extends Expense {
	
	public EqualExpense(double amount , User paidBy , List<Split> splits , ExpenseMetadata metadata){
		super(amount , paidBy , splits , metadata);
	}
	
	@Override
	public boolean validate(){
		for(Split split : getSplits()){
			if(!(split instanceof EqualSplit))
				return false;
		}
		return true;
	}

}
