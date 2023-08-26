package entity;

import java.util.List;

public class PercentExpense extends Expense{

    public PercentExpense(double amount, User paidBy, List<Split> splits)throws Exception {
        super(amount, paidBy, splits);
        if(!validate()){
            throw new Exception("Invalid Percent Exception");
        }
    }

    @Override
    public boolean validate() {
        double totalAmount = getAmount();
        double sumSplitPercent = 0;
        for(Split s: getSplits()){
            PercentSplit ps = (PercentSplit) s;
            sumSplitPercent += ps.getPercent();
        }
        return sumSplitPercent==100;
    }
}
