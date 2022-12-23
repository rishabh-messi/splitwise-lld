package LLD.SplitWise.User;

import LLD.SplitWise.Stats.Stats;

public class User {
    static int count = 0;
    public int id;
    public Stats stats;
    String name;
    String contactNumber;

    User(String name, String contactNumber) {
        id = ++count;
        this.name = name;
        this.contactNumber = contactNumber;
        stats = new Stats();
    }

    public String getUserName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setUserName(String newName) {
        name = newName;
    }

    public void setContactNumber(String newContactNumber) {
        contactNumber = newContactNumber;
    }

    public void showStats() {
        stats.show();
    }
}