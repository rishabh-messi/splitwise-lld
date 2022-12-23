package LLD.SplitWise;

import java.util.ArrayList;
import java.util.List;

import LLD.SplitWise.Group.Expense;
import LLD.SplitWise.Group.Group;
import LLD.SplitWise.Group.GroupHandler;
import LLD.SplitWise.Split.SplitAlgoFactory;
import LLD.SplitWise.Split.Split;
import LLD.SplitWise.Split.SplittingAlgo;
import LLD.SplitWise.Split.SplitAlgoEnum;
import LLD.SplitWise.User.User;
import LLD.SplitWise.User.UserManager;

public class SplitWise {
    UserManager userManager;
    GroupHandler groupHandler;

    public SplitWise() {
        userManager = new UserManager();
        groupHandler = new GroupHandler();
    }

    public void run() {
        // creating 2 user Richa & Rishabh
        User richa = userManager.createNewUser("Richa", "735539954");
        User rishabh = userManager.createNewUser("Rishabh", "8896143680");

        // creating 1 group PondicherryTrip
        Group pondicherry = groupHandler.createNewGroup(rishabh, "PondicherryTrip");

        // Adding richa to pondicherry
        groupHandler.addUserToGroup(pondicherry, richa);

        // Adding travel expense to pondi - equal
        SplittingAlgo splitAlgo = SplitAlgoFactory.getSplittingAlgo(SplitAlgoEnum.EQUAL);
        List<Split> splits = new ArrayList<Split>();
        Split rishabh_split = new Split(rishabh).setAmountPaid(3000);
        Split richa_split = new Split(richa);
        splits.add(rishabh_split);
        splits.add(richa_split);
        Expense pondi = groupHandler.createAndAddExpToGroup(pondicherry, 3000, "Transport", splitAlgo, splits);

        // Adding food expense to pondi - percent
        splitAlgo = SplitAlgoFactory.getSplittingAlgo(SplitAlgoEnum.PERCENT);
        splits.clear();
        rishabh_split = new Split(rishabh).setAmountPaid(700).setPercentAccountable(50);
        richa_split = new Split(richa).setAmountPaid(300).setPercentAccountable(50);
        splits.add(richa_split);
        splits.add(rishabh_split);
        groupHandler.createAndAddExpToGroup(pondicherry, 1000, "Food", splitAlgo, splits);

        // Adding waffle expense to pondi - unequal
        splitAlgo = SplitAlgoFactory.getSplittingAlgo(SplitAlgoEnum.UNEQUAL);
        splits.clear();
        richa_split = new Split(richa).setAmountPaid(100).setAmountOwed(70);
        rishabh_split = new Split(rishabh).setAmountOwed(30);
        splits.add(richa_split);
        splits.add(rishabh_split);
        groupHandler.createAndAddExpToGroup(pondicherry, 100, "Food", splitAlgo, splits);

        // Show rishabh's stats
        rishabh.showStats();

        // Show richa's stats
        richa.showStats();

        groupHandler.deleteExpenseFromGroup(pondicherry, pondi);

        rishabh.showStats();
        richa.showStats();

        // Show pondi stats
        // pondicherry.showStats();
    }

    public static void main(String[] args) {
        SplitWise sw = new SplitWise();
        sw.run();
    }
}