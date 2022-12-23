package LLD.SplitWise.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import LLD.SplitWise.Split.Split;
import LLD.SplitWise.Split.SplittingAlgo;
import LLD.SplitWise.User.User;

public class GroupHandler {
    HashMap<Group, ArrayList<User>> groups;

    public GroupHandler() {
        groups = new HashMap<Group, ArrayList<User>>();
    }

    public Group createNewGroup(User groupCreator, String groupName) {
        Group newGroup = new Group(groupName);
        groups.put(newGroup, new ArrayList<User>());
        addUserToGroup(newGroup, groupCreator);
        return newGroup;
    }

    private boolean isGroupDeletable(Group group) {
        // Check if expenses are settled among group members.
        return false;
    }

    public void deleteGroup(Group group) {
        String msg;
        if (!isGroupDeletable(group)) {
            msg = String.format("GroupID: %d is not deletable, as the expenses are not settled", group.id);
        } else {
            groups.remove(group);
            msg = String.format("GroupID: %d deleted successfully", group.id);
        }
        System.out.println(msg);
    }

    public void addUserToGroup(Group group, User user) {
        String msg;
        if (groups.get(group) != null) {
            group.addUser(user);
            msg = String.format("UserID: %d added successfully to groupID: %d", user.id, group.id);
        } else {
            msg = String.format("GroupID: %d does not exist in the system", group.id);
        }
        System.out.println(msg);
    }

    public void deleteUserFromGroup(Group group, User user) {
        try {
            group.deleteUser(user);
        } catch (Exception e) {
            System.out.println("Some error occurred while deleting user");
        }
    }

    public Expense createAndAddExpToGroup(Group group, float amount, String details, SplittingAlgo algo,
            List<Split> splits) {
        Expense newExpense = group.createNewExpense(amount, details, algo, splits);
        String msg;
        if (newExpense != null && algo.validateSplitRequest(newExpense)) {
            addExpenseToGroup(group, newExpense);
            msg = "Expense added successfully!!";
        } else {
            msg = "Bad expense request";
        }
        System.out.println(msg);
        return newExpense;
    }

    public void addExpenseToGroup(Group group, Expense expense) {
        group.addExpense(expense);
    }

    public void updateExpenseInGroup(Group group, Expense expense) {
        group.updateExpense(expense);
    }

    public void deleteExpenseFromGroup(Group group, Expense expense) {
        group.deleteExpense(expense);
    }
}
