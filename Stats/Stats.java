package LLD.SplitWise.Stats;

import java.util.Map;
import java.util.HashMap;

public class Stats {
    public int expenseCount;
    public float totalAmountOwed;
    public float totalAmountLent;
    public float totalAmountSpent;
    // user_id, amountOwe/Lent
    public Map<Integer, Float> debtMap;

    public Stats() {
        expenseCount = 0;
        totalAmountOwed = 0;
        totalAmountLent = 0;
        totalAmountSpent = 0;
        debtMap = new HashMap<Integer, Float>();
    }

    public boolean isSettled() {
        return totalAmountLent == 0 && totalAmountOwed == 0;
    }

    public void show() {
        System.out.println(
                String.format("Totalnum of expenses: %d\nTotalAmtOwed: %f\nTotalAmtLent: %f\nTotalAmtSpent: %f\n",
                        expenseCount, totalAmountOwed, totalAmountLent, totalAmountSpent));
    }
}
