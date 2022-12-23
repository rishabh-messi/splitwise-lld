package LLD.SplitWise.Split;

import LLD.SplitWise.Group.Expense;

public class PercentSplit implements SplittingAlgo {
    public boolean validateSplitRequest(Expense exp) {
        float percent = 0;
        for(Split split: exp.getSplits()) {
            percent += split.getPercentAccountable();
        }
        return percent == 100.0;
    }
    public void updateSplit(Expense exp) {
        float expAmount = exp.getAmount();
        for(Split split: exp.getSplits()) {
            float amountOwed = (expAmount * (split.getPercentAccountable()))/100;
            split.amountOwe = amountOwed;
            split.amountSettled = split.amountLent = split.amountPaid - split.amountOwe;
        }
    }
}