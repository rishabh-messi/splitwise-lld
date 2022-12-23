package LLD.SplitWise.Group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LLD.SplitWise.Split.Split;
import LLD.SplitWise.Split.SplittingAlgo;
// import LLD.SplitWise.Stats.Stats;
import LLD.SplitWise.User.User;

public class Group {
    static int cnt = 0;
    public int id;
    String groupName;
    ExpenseHandler expHandler;
    // Stats stats;
    Map<Integer, User> userMap;

    Group(String groupName) {
        id = ++cnt;
        expHandler = new ExpenseHandler();
        userMap = new HashMap<Integer, User>();
        this.groupName = groupName;
        // stats = new Stats();
    }

    public Expense createNewExpense(float amount, String details, SplittingAlgo algo, List<Split> splits) {
        float totalAmountPaid = 0;
        for (Split split : splits) {
            totalAmountPaid += split.getAmountPaid();
        }
        if (totalAmountPaid == amount) {
            return new Expense(amount, details, algo, splits);
        }
        return null;
    }

    public void addExpense(Expense expense) {
        expHandler.addExpense(expense);
    }

    public void deleteExpense(Expense expense) {
        expHandler.deleteExpense(expense.id);
    };

    public void updateExpense(Expense expense) {
        expHandler.updateExpense(expense.id, expense);
    };

    public void addUser(User user) {
        if (userMap.get(user.id) == null) {
            userMap.put(user.id, user);
        } else {
            String msg = String.format("UserID: %d already exists in the groupID: %d", user.id, this.id);
            System.out.println(msg);
        }
    };

    private boolean isSafeToDeleteUser(int id) {
        if (userMap.get(id) == null) {
            String msg = String.format("UserID: %d does not exist in the groupID: %d", id, this.id);
            System.out.println(msg);
        }
        // Check if we can delete the user from this group or not
        return true;
    }

    public void deleteUser(User user) {
        String msg;
        if (isSafeToDeleteUser(user.id)) {
            userMap.remove(user.id);
            msg = "User successfully deleted from the group!";
        } else {
            msg = "User can't be deleted, as there are some unsettled expenses for the user";
        }
        System.out.println(msg);
    };

    // public String showStats() {
    // return stats.show();
    // }
}