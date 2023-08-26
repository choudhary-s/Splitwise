package service;

import entity.*;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(User paidBy, double amount, List<Split> splits, ExpenseType expenseType) throws Exception {
        switch (expenseType){
            case EQUAL:
                int totalPax = splits.size();
                double splitAmount = Math.round(((amount)/totalPax)*100.0)/100.0;
                for(Split s: splits){
                    s.setAmount(splitAmount);
                }
//                System.out.println(splitAmount+(amount-splitAmount*totalPax));
                splits.get(0).setAmount(splitAmount+(amount-splitAmount*totalPax));
                return new EqualExpense(amount, paidBy, splits);
            case PERCENT:
                for(Split s: splits){
                    PercentSplit ps = (PercentSplit) s;
                    ps.setAmount(amount*ps.getPercent()/100.0);
                }
                return new PercentExpense(amount, paidBy, splits);
            case EXACT:
                return new ExactExpense(amount, paidBy, splits);
            default:
                return null;
        }
    }
}
