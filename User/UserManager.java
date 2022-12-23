package LLD.SplitWise.User;

import java.util.HashMap;
import java.util.Map;

import LLD.SplitWise.Stats.Stats;

public class UserManager {
    Map<Integer, User> userMap;
    
    public UserManager() {
        userMap = new HashMap<Integer, User>();
    }

    boolean isUserExist(int id) {
        return (userMap.get(id) == null)? true : false;
    }

    public User createNewUser(String name, String contactNum) {
        User newUser = new User(name, contactNum);
        userMap.put(newUser.id, newUser);
        return newUser;
    }

    public void updateUser(int id, String name, String contactNum) {
        if(!isUserExist(id)) {
            String msg = String.format("No user found with id: %d", id);
            System.out.println(msg);
            return;
        }
        User user = userMap.get(id);
        user.setUserName(name);
        user.setContactNumber(contactNum);
        userMap.put(id, user);
    }

    public void deleteUser(int id) {
        if(!isUserExist(id)) {
            String msg = String.format("No user found with id: %d", id);
            System.out.println(msg);
            return;
        }
        // TBD need to check if this user has any expense not settled in any group
        userMap.remove(id);
    }
// TBD
    public void updateUserStats(int id, Stats stats) {}
}