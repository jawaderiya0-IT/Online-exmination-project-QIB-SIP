package exam;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

    private Map<String, User> users = new HashMap<>();

    public UserDatabase() {
        // Pre-loaded users
        users.put("student1", new User("student1", "Riya@123", "Riya jawade",  "riya1@gmail.com"));
        users.put("student2", new User("student2", "Swati@123", "Swati roy",  "swati2@gmail.com"));
        users.put("student3", new User("student3", "Anshu@123", "Anshu jawade",   "anshu3@gmail.com"));
    }

    public User findUser(String username) {
        return users.get(username);
    }
}
