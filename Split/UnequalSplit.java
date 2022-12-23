package LLD.SplitWise.Split;

import LLD.SplitWise.Group.Expense;

public class UnequalSplit implements SplittingAlgo {
    public boolean validateSplitRequest(Expense exp) {
        float totalAmountOwed = 0;
        for (Split split : exp.getSplits()) {
            totalAmountOwed += split.getAmountOwed();
        }
        return totalAmountOwed == exp.getAmount();
    }

    public void updateSplit(Expense exp) {
        for (Split split : exp.getSplits()) {
            split.amountSettled = split.amountLent = split.amountPaid - split.amountOwe;
        }
    }
}