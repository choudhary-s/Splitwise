package entity;

import java.util.List;

public class ExactExpense extends Expense{

    public ExactExpense(double amount, User paidBy, List<Split> splits) throws Exception{
        super(amount, paidBy, splits);
        if(!validate()){
            throw new Exception("Invalid Exact Expense");
        }
    }

    @Override
    public boolean validate() {
        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for(Split s: getSplits()){
            sumSplitAmount += s.getAmount();
        }
        return totalAmount==sumSplitAmount;
    }
}
