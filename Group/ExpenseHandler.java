package LLD.SplitWise.Group;

import java.util.Map;

import LLD.SplitWise.Split.Split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseHandler {
    int id;
    Map<Integer, Expense> expenseMap;
    HashMap<Integer, HashMap<Integer, Float>> grpDebtMap;

    ExpenseHandler() {
        expenseMap = new HashMap<Integer, Expense>();
        grpDebtMap = new HashMap<Integer, HashMap<Integer, Float>>();
    }

    void updateDebtMap(Expense exp) {
        exp.updateSplit();
        List<Split> splits = exp.getSplits(), surplusSplits = new ArrayList<Split>(),
                lackSplits = new ArrayList<Split>();
        for (Split split : splits) {
            if (split.getAmountLent() > 0) {
                surplusSplits.add(split);
            } else {
                lackSplits.add(split);
            }
        }
        int surplus = surplusSplits.size(), lack = lackSplits.size(), i = 0, j = 0;
        while (i < surplus && j < lack) {
            Split surp = surplusSplits.get(i);
            Split lk = lackSplits.get(j);
            if (surp.amountSettled == 0) {
                ++i;
                continue;
            }
            float settledAmt;
            if (surp.amountSettled > Math.abs(lk.amountSettled)) {
                settledAmt = Math.abs(lk.amountSettled);
                surp.amountSettled -= settledAmt;
                lk.amountSettled = 0;
                ++j;
            } else {
                settledAmt = surp.amountSettled;
                surp.amountSettled = 0;
                ++i;
                lk.amountSettled += settledAmt;
            }
            if (grpDebtMap.get(lk.user.id) == null) {
                grpDebtMap.put(lk.user.id, new HashMap<Integer, Float>());
            }
            grpDebtMap.get(lk.user.id).put(surp.user.id, settledAmt);
            surp.user.stats.debtMap.put(lk.user.id, settledAmt);
            lk.user.stats.debtMap.put(surp.user.id, -settledAmt);
        }
    }

    void addExpense(Expense expense) {
        expenseMap.put(expense.id, expense);
        updateDebtMap(expense);
        updateUserStats(expense);
    }

    void updateExpense(int id, Expense expense) {
        if (expenseMap.get(id) != null) {
            deleteExpense(id);
            addExpense(expense);
            return;
        }
        System.out.println(String.format("No expense found in the group with id: %d", id));
    }

    void updateUserStats(Expense expense) {
        int count = (expense.getStatus())?-1:1;
        for(Split split: expense.getSplits()) {
            split.user.stats.expenseCount += count;
            split.user.stats.totalAmountOwed += split.getAmountOwed();
            split.user.stats.totalAmountLent += split.getAmountLent();
            split.user.stats.totalAmountSpent += split.getAmountOwed();
        }
    }

    private Expense getNegativExpense(int id) {
        Expense exp = expenseMap.get(id);
        List<Split> splits = exp.getSplits();
        for (Split split : splits) {
            split.setAmountPaid(-split.getAmountPaid());
            split.setAmountOwed(-split.getAmountOwed());
        }
        Expense negExp = new Expense(-exp.amount, exp.details, exp.algo, splits);
        negExp.updateSplit();
        return negExp;
    }

    void deleteExpense(int id) {
        if (expenseMap.get(id) == null) {
            System.out.println(String.format("No expense found in the group with id: %d", id));
            return;
        }
        Expense negExp = getNegativExpense(id);
        updateDebtMap(negExp);
        updateUserStats(negExp);
        expenseMap.remove(id);
    }
}