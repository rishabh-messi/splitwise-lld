package LLD.SplitWise.Split;

import java.util.List;

import LLD.SplitWise.Group.Expense;

public class EqualSplit implements SplittingAlgo {
    public boolean validateSplitRequest(Expense exp) {
        return true;
    }
    public void updateSplit(Expense exp) {
        List<Split> splits = exp.getSplits();
        int totalMembers = splits.size();
        float percentAccountable = 100/(float)totalMembers;
        for(Split split: splits) {
            split.amountOwe = exp.getAmount() / totalMembers;
            split.percentAccountable = percentAccountable;
            split.amountSettled = split.amountLent = split.amountPaid - split.amountOwe;
        }
    }
}