package LLD.SplitWise.Split;

import LLD.SplitWise.Group.Expense;

public interface SplittingAlgo {
    public boolean validateSplitRequest(Expense exp);
    public void updateSplit(Expense exp);
}