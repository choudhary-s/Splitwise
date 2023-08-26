package manager;

import entity.Expense;
import entity.ExpenseType;
import entity.Split;
import entity.User;
import service.ExpenseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    Map<Integer, User> userMap;
    List<Expense> expenseList;
    Map<Integer, Map<Integer, Double>> balanceSheet;

    public ExpenseManager() {
        this.userMap = new HashMap<>();
        this.expenseList = new ArrayList<>();
        this.balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits) throws Exception {
        Expense expense = ExpenseService.createExpense(paidBy, amount, splits, expenseType);
        expenseList.add(expense);
        List<Split> expenseSplit = expense.getSplits();
        for(Split s: expenseSplit){
            User paidTo = s.getUser();
            Map<Integer, Double> balanceOfPaidByUser = balanceSheet.get(paidBy.getId());
            if(!balanceOfPaidByUser.containsKey(paidTo.getId())){
                balanceOfPaidByUser.put(paidTo.getId(), 0.0);
            }
            balanceOfPaidByUser.put(paidTo.getId(), balanceOfPaidByUser.get(paidTo.getId())+s.getAmount());

            Map<Integer, Double> balanceOfPaidToUser = balanceSheet.get(paidTo.getId());
            if(!balanceOfPaidToUser.containsKey(paidBy.getId())){
                balanceOfPaidToUser.put(paidBy.getId(), 0.0);
            }
            balanceOfPaidToUser.put(paidBy.getId(), balanceOfPaidToUser.get(paidBy.getId())-s.getAmount());
        }
    }

    public void showBalance(int user){
        System.out.println("-------------------------------------------");
        boolean flag = true;
        Map<Integer, Double> balance = balanceSheet.get(user);
        for(Integer userId: balance.keySet()){
            if(balance.get(userId)!=0){
                flag = false;
                printBalance(user, userId, balance.get(userId));
            }
        }
        if(flag){
            System.out.println("No balance for "+userMap.get(user).getName());
        }
    }

    public void showBalance() {
        System.out.println("-------------------------------------------");
        boolean flag = true;
        for(Integer paidBy: balanceSheet.keySet()){
            for(Integer paidTo: balanceSheet.get(paidBy).keySet()){
                double amount = balanceSheet.get(paidBy).get(paidTo);
                if(amount!=0){
                    flag = false;
                    printBalance(paidBy, paidTo, amount);
                }
            }
        }
        if(flag){
            System.out.println("No balances.");
        }
    }

    public void printBalance(int u1, int u2, double amount){
        String n1 = userMap.get(u1).getName();
        String n2 = userMap.get(u2).getName();
        if(amount<0){
//            System.out.println(n1+" owes "+n2+": "+(-1*amount));
        }
        else{
            System.out.println(n2+" owes "+n1+": "+amount);
        }
    }

    public Map<Integer, Map<Integer, Double>> getBalanceSheet() {
        return balanceSheet;
    }
}
