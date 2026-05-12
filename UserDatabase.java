package exam;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

    private Map<String, User> users = new HashMap<>();

    public UserDatabase() {
        // Pre-loaded users
        users.put("student1", new User("student1", "Ved@123", "Vedant Raut",  "ved123@gmail.com"));
        users.put("student2", new User("student2", "Om@123", "Om bure",  "om456@gmail.com"));
        users.put("student3", new User("student3", "Gargi@123", "Gargi Raut",   "gargi789@gmail.com"));
    }

    public User findUser(String username) {
        return users.get(username);
    }
}
