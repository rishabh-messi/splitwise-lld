package LLD.SplitWise.Group;

import LLD.SplitWise.Split.Split;
import LLD.SplitWise.Split.SplittingAlgo;

import java.util.List;

public class Expense {
    static int cnt = 0;
    public int id;
    float amount;
    String details;
    SplittingAlgo algo;
    List<Split> splits;
    boolean isNegative;

    public Expense(float amount, String details, SplittingAlgo algo, List<Split> splits) {
        id = ++cnt;
        this.amount = amount;
        this.details = details;
        this.algo = algo;
        this.splits = splits;
        isNegative = false;
    }

    public void updateSplit() {
        algo.updateSplit(this);
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setSplittingAlgo(SplittingAlgo sAlgo) {
        algo = sAlgo;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public void setStatus(boolean status) {
        isNegative = status;
    }

    public boolean getStatus() {
        return isNegative;
    }

    public float getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }

    public List<Split> getSplits() {
        return splits;
    }
}