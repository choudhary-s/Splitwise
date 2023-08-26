package entity;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(double amount, User paidBy, List<Split> splits) throws Exception {
        super(amount, paidBy, splits);
        if(validate()==false){
            throw new Exception("Invalid expense");
        }
    }

    @Override
    public boolean validate() {
        return true;
    }
}
