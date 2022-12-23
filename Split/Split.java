package LLD.SplitWise.Split;

import LLD.SplitWise.User.User;

public class Split {
    public User user;
    float amountPaid;
    float amountOwe;
    float amountLent;
    public float amountSettled;
    float percentAccountable;

    public Split(User user) {
        this.user = user;
        amountOwe = 0;
        amountPaid = 0;
        amountLent = 0;
        amountSettled = 0;
        percentAccountable = 0;
    }

    public void clear() {
        user = null;
        amountOwe = 0;
        amountPaid = 0;
        percentAccountable = 0;
    }

    public Split setUser(User user) {
        this.user = user;
        return this;
    }

    public Split setAmountPaid(float amount) {
        amountPaid = amount;
        return this;
    }

    public Split setAmountOwed(float amount) {
        amountOwe = amount;
        return this;
    }

    public Split setPercentAccountable(float percent) {
        percentAccountable = percent;
        return this;
    }

    public void setAmountLent(float amount) {
        amountLent = amount;
    }

    public void setAmountSettled(float amount) {
        amountSettled = amount;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public float getAmountOwed() {
        return amountOwe;
    }

    public float getAmountLent() {
        return amountLent;
    }

    public float getPercentAccountable() {
        return percentAccountable;
    }
}