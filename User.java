package exam;

public class User {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private boolean loggedIn;

    public User(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.loggedIn = false;
    }

    // ── Getters ──────────────────────────────
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail()    { return email; }
    public boolean isLoggedIn() { return loggedIn; }

    // ── Auth ─────────────────────────────────
    public boolean validatePassword(String input) {
        return this.password.equals(input);
    }

    public void login()  { this.loggedIn = true; }
    public void logout() { this.loggedIn = false; }

    // ── Profile Update ───────────────────────
    public void updateFullName(String name)  { this.fullName = name; }
    public void updateEmail(String email)    { this.email = email; }
    public void updatePassword(String newPw) { this.password = newPw; }
}
