package exam;

import java.util.Scanner;

public class ExamApp {

    private static final int EXAM_TIME_SECONDS = 120; // 2 minutes for demo
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    private UserDatabase db;
    private Scanner scanner;
    private User currentUser;
    private ExamSession session;

    public ExamApp() {
        this.db = new UserDatabase();
        this.scanner = new Scanner(System.in);
    }

    // ════════════════════════════════════════════
    //  START
    // ════════════════════════════════════════════
    public void start() {
        printBanner();

        while (true) {
            if (login()) {
                showDashboard();
            }
            System.out.print("\n  Login again? (yes/no): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) break;
        }

        System.out.println("\n  Thank you for using the Online Exam System. Goodbye!\n");
        scanner.close();
    }

    // ════════════════════════════════════════════
    //  LOGIN
    // ════════════════════════════════════════════
    private boolean login() {
        System.out.println("\n  ┌─────────────────────────────┐");
        System.out.println("  │           LOGIN             │");
        System.out.println("  └─────────────────────────────┘");

        System.out.print("  Username: ");
        String username = scanner.nextLine().trim();

        User user = db.findUser(username);
        if (user == null) {
            System.out.println("  ✗ Username not found.");
            return false;
        }

        for (int attempt = 1; attempt <= MAX_LOGIN_ATTEMPTS; attempt++) {
            System.out.print("  Password: ");
            String password = scanner.nextLine().trim();

            if (user.validatePassword(password)) {
                user.login();
                currentUser = user;
                System.out.println("  ✓ Login successful! Welcome, " + currentUser.getFullName() + ".");
                return true;
            } else {
                int left = MAX_LOGIN_ATTEMPTS - attempt;
                if (left > 0)
                    System.out.println("  ✗ Wrong password. " + left + " attempt(s) remaining.");
                else
                    System.out.println("  ✗ Account locked. Too many failed attempts.");
            }
        }
        return false;
    }

    // ════════════════════════════════════════════
    //  DASHBOARD
    // ════════════════════════════════════════════
    private void showDashboard() {
        while (true) {
            System.out.println();
            System.out.println("  ╔══════════════════════════════════╗");
            System.out.println("  ║         STUDENT DASHBOARD        ║");
            System.out.println("  ╠══════════════════════════════════╣");
            System.out.println("  ║  1. Start Exam                   ║");
            System.out.println("  ║  2. Update Profile               ║");
            System.out.println("  ║  3. Change Password              ║");
            System.out.println("  ║  4. Logout                       ║");
            System.out.println("  ╚══════════════════════════════════╝");
            System.out.print("  Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": startExam();       break;
                case "2": updateProfile();   break;
                case "3": changePassword();  break;
                case "4":
                    logout();
                    return;
                default:
                    System.out.println("  ✗ Invalid option.");
            }
        }
    }

    // ════════════════════════════════════════════
    //  UPDATE PROFILE
    // ════════════════════════════════════════════
    private void updateProfile() {
        System.out.println("\n  ── Update Profile ──────────────────");
        System.out.println("  Current Name  : " + currentUser.getFullName());
        System.out.println("  Current Email : " + currentUser.getEmail());

        System.out.print("  New Full Name  (Enter to skip): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) currentUser.updateFullName(name);

        System.out.print("  New Email      (Enter to skip): ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) currentUser.updateEmail(email);

        System.out.println("  ✓ Profile updated successfully.");
        System.out.println("  Updated Name  : " + currentUser.getFullName());
        System.out.println("  Updated Email : " + currentUser.getEmail());
    }

    // ════════════════════════════════════════════
    //  CHANGE PASSWORD
    // ════════════════════════════════════════════
    private void changePassword() {
        System.out.println("\n  ── Change Password ─────────────────");
        System.out.print("  Current Password: ");
        String current = scanner.nextLine().trim();

        if (!currentUser.validatePassword(current)) {
            System.out.println("  ✗ Incorrect current password.");
            return;
        }

        System.out.print("  New Password    : ");
        String newPw = scanner.nextLine().trim();
        if (newPw.length() < 4) {
            System.out.println("  ✗ Password too short (min 4 characters).");
            return;
        }

        System.out.print("  Confirm Password: ");
        String confirm = scanner.nextLine().trim();
        if (!newPw.equals(confirm)) {
            System.out.println("  ✗ Passwords do not match.");
            return;
        }

        currentUser.updatePassword(newPw);
        System.out.println("  ✓ Password changed successfully.");
    }

    // ════════════════════════════════════════════
    //  START EXAM
    // ════════════════════════════════════════════
    private void startExam() {
        System.out.println("\n  ── Exam Instructions ───────────────────────────────");
        System.out.println("  • Total Questions : 10 MCQs");
        System.out.printf ("  • Time Limit      : %d minutes%n", EXAM_TIME_SECONDS / 60);
        System.out.println("  • Each question carries 1 mark");
        System.out.println("  • Exam auto-submits when time runs out");
        System.out.println("  ────────────────────────────────────────────────────");
        System.out.print("  Press ENTER to begin the exam...");
        scanner.nextLine();

        session = new ExamSession(QuestionBank.getQuestions(), EXAM_TIME_SECONDS);
        runExam();
    }

    // ════════════════════════════════════════════
    //  RUN EXAM (navigate questions)
    // ════════════════════════════════════════════
    private void runExam() {
        int current = 0;
        int total = session.getTotalQuestions();
        Question[] questions = session.getQuestions();

        while (!session.isSubmitted()) {

            // ── Auto-submit check ────────────────
            if (session.isTimeUp()) {
                System.out.println("\n\n  ⏰ Time is up! Exam auto-submitted.");
                session.submit();
                break;
            }

            clearScreen();
            printExamHeader(current + 1, total);
            printQuestion(current, questions[current]);
            printExamMenu(current, total);

            System.out.print("  Choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1": case "2": case "3": case "4":
                    int opt = Integer.parseInt(input);
                    session.selectAnswer(current, opt);
                    System.out.println("  ✓ Answer saved.");
                    pause(600);
                    if (current < total - 1) current++;
                    break;
                case "N": case "n":
                    if (current < total - 1) current++;
                    else System.out.println("  Already on last question.");
                    break;
                case "P": case "p":
                    if (current > 0) current--;
                    else System.out.println("  Already on first question.");
                    break;
                case "S": case "s":
                    if (confirmSubmit()) {
                        session.submit();
                    }
                    break;
                default:
                    System.out.println("  ✗ Invalid input.");
                    pause(500);
            }
        }

        showResult();
    }

    // ════════════════════════════════════════════
    //  PRINT QUESTION
    // ════════════════════════════════════════════
    private void printQuestion(int index, Question q) {
        int saved = session.getAnswer(index);
        System.out.println();
        System.out.println("  Q" + (index + 1) + ": " + q.getQuestionText());
        System.out.println();
        String[] opts = q.getOptions();
        for (int i = 0; i < opts.length; i++) {
            String marker = (saved == i + 1) ? " ◀ (selected)" : "";
            System.out.printf("      %d. %s%s%n", i + 1, opts[i], marker);
        }
        System.out.println();
    }

    private void printExamHeader(int current, int total) {
        System.out.println("  ╔════════════════════════════════════════════════════╗");
        System.out.printf ("  ║  ONLINE EXAM  │  Q %02d / %02d  │  Time: %s remaining  ║%n",
                current, total, session.getFormattedTimeRemaining());
        System.out.printf ("  ║  Student: %-20s  Attempted: %02d/%02d   ║%n",
                currentUser.getFullName(), session.getAttempted(), total);
        System.out.println("  ╚════════════════════════════════════════════════════╝");
    }

    private void printExamMenu(int current, int total) {
        System.out.println("  ─────────────────────────────────────────────────────");
        System.out.println("  Enter 1-4 to select answer  │  N=Next  P=Prev  S=Submit");
        System.out.println("  ─────────────────────────────────────────────────────");
    }

    // ════════════════════════════════════════════
    //  CONFIRM SUBMIT
    // ════════════════════════════════════════════
    private boolean confirmSubmit() {
        int attempted = session.getAttempted();
        int total = session.getTotalQuestions();
        int skipped = total - attempted;

        System.out.println("\n  ── Confirm Submission ───────────────");
        System.out.println("  Attempted : " + attempted + " / " + total);
        if (skipped > 0)
            System.out.println("  Warning   : " + skipped + " question(s) not answered!");
        System.out.print("  Submit exam? (yes/no): ");
        return scanner.nextLine().trim().equalsIgnoreCase("yes");
    }

    // ════════════════════════════════════════════
    //  SHOW RESULT
    // ════════════════════════════════════════════
    private void showResult() {
        int score    = session.calculateScore();
        int total    = session.getTotalQuestions();
        int wrong    = session.getAttempted() - score;
        int skipped  = total - session.getAttempted();
        int timeTaken = Math.min(session.getElapsedSeconds(), session.getTimeLimitSeconds());
        double pct   = (score * 100.0) / total;
        String grade = pct >= 80 ? "A" : pct >= 60 ? "B" : pct >= 40 ? "C" : "F";

        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║              EXAM RESULT                 ║");
        System.out.println("  ╠══════════════════════════════════════════╣");
        System.out.printf ("  ║  Student  : %-29s║%n", currentUser.getFullName());
        System.out.printf ("  ║  Score    : %d / %d  (%.1f%%)%21s║%n", score, total, pct, "");
        System.out.printf ("  ║  Correct  : %-29s║%n", score);
        System.out.printf ("  ║  Wrong    : %-29s║%n", wrong);
        System.out.printf ("  ║  Skipped  : %-29s║%n", skipped);
        System.out.printf ("  ║  Time     : %d sec taken%20s║%n", timeTaken, "");
        System.out.printf ("  ║  Grade    : %-29s║%n", grade);
        System.out.println("  ╚══════════════════════════════════════════╝");

        System.out.println("\n  ── Answer Review ───────────────────────────────────");
        Question[] questions = session.getQuestions();
        int[] answers = session.getAnswers();
        for (int i = 0; i < total; i++) {
            String status;
            if (answers[i] == 0) {
                status = "SKIPPED";
            } else if (questions[i].isCorrect(answers[i])) {
                status = "CORRECT";
            } else {
                status = "WRONG   (Correct: " + questions[i].getCorrectOption() + ")";
            }
            System.out.printf("  Q%-2d  %-8s  %s%n", i + 1, status,
                    questions[i].getQuestionText().substring(0,
                            Math.min(40, questions[i].getQuestionText().length())) + "...");
        }
        System.out.println("  ────────────────────────────────────────────────────");

        System.out.print("\n  Press ENTER to return to dashboard...");
        scanner.nextLine();
        session = null;
    }

    // ════════════════════════════════════════════
    //  LOGOUT
    // ════════════════════════════════════════════
    private void logout() {
        System.out.println("\n  ✓ Session closed. " + currentUser.getFullName() + " logged out.");
        currentUser.logout();
        currentUser = null;
        session = null;
    }

    // ════════════════════════════════════════════
    //  HELPERS
    // ════════════════════════════════════════════
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    private void printBanner() {
        System.out.println();
        System.out.println("  ╔═══════════════════════════════════════════╗");
        System.out.println("  ║        ONLINE EXAMINATION SYSTEM          ║");
        System.out.println("  ║           Core Java Project               ║");
        System.out.println("  ╠═══════════════════════════════════════════╣");
        System.out.println("  ║  Demo Accounts:                           ║");
        System.out.println("  ║  Username: student1  Password: Ved@123    ║");
        System.out.println("  ║  Username: student2  Password: Om@123     ║");
        System.out.println("  ║  Username: student3  Password: Gargi@123  ║");
        System.out.println("  ╚═══════════════════════════════════════════╝");
    }
}
