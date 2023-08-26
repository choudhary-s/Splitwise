import entity.ExpenseType;
import entity.PercentSplit;
import entity.Split;
import entity.User;
import manager.ExpenseManager;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        User user1 = new User(1, "Alice", "a@b.com");
        User user2 = new User(2, "Bob", "a@b.com");
        User user3 = new User(3, "Steve", "a@b.com");
        User user4 = new User(4, "John", "a@b.com");

        ExpenseManager em = new ExpenseManager();
        em.addUser(user1);
        em.addUser(user2);
        em.addUser(user3);
        em.addUser(user4);

        try {
            Split s1 = new Split(user1);
            Split s2 = new Split(user2);
            Split s3 = new Split(user3);
            em.addExpense(ExpenseType.EQUAL, 1000, user1, Arrays.asList(s1, s2, s3));
            em.showBalance();
            System.out.println(em.getBalanceSheet());

            Split s4 = new Split(user1,500);
            Split s5 = new Split(user3, 400);
            em.addExpense(ExpenseType.EXACT, 900, user4, Arrays.asList(s4, s5));
            em.showBalance();

            Split s6 = new PercentSplit(user2, 20);
            Split s7 = new PercentSplit(user3, 30);
            Split s8 = new PercentSplit(user4, 50);
            em.addExpense(ExpenseType.PERCENT, 1500, user2, Arrays.asList(s6,s7,s8));
            em.showBalance();

            Split s9 = new Split(user2,500);
            Split s10 = new Split(user4, 400);
            em.addExpense(ExpenseType.EXACT, 900, user3, Arrays.asList(s9,s10));
            em.showBalance();

            Split s11 = new Split(user2,500);
            Split s12 = new Split(user4, 400);
            em.addExpense(ExpenseType.EXACT, 9000, user3, Arrays.asList(s11,s12));
            em.showBalance();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
