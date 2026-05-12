package exam;

public class ExamSession {

    private Question[] questions;
    private int[] answers;           // 0 = not attempted
    private int totalQuestions;
    private long startTimeMillis;
    private int timeLimitSeconds;    // e.g. 120 seconds for demo
    private boolean submitted;

    public ExamSession(Question[] questions, int timeLimitSeconds) {
        this.questions = questions;
        this.totalQuestions = questions.length;
        this.answers = new int[totalQuestions];
        this.timeLimitSeconds = timeLimitSeconds;
        this.submitted = false;
        this.startTimeMillis = System.currentTimeMillis();
    }

    // ── Timer ────────────────────────────────────────────────
    public int getElapsedSeconds() {
        return (int) ((System.currentTimeMillis() - startTimeMillis) / 1000);
    }

    public int getRemainingSeconds() {
        return Math.max(0, timeLimitSeconds - getElapsedSeconds());
    }

    public boolean isTimeUp() {
        return getElapsedSeconds() >= timeLimitSeconds;
    }

    public String getFormattedTimeRemaining() {
        int remaining = getRemainingSeconds();
        int mins = remaining / 60;
        int secs = remaining % 60;
        return String.format("%02d:%02d", mins, secs);
    }

    // ── Answer Selection ─────────────────────────────────────
    public boolean selectAnswer(int questionIndex, int option) {
        if (questionIndex < 0 || questionIndex >= totalQuestions) return false;
        if (option < 1 || option > 4) return false;
        answers[questionIndex] = option;
        return true;
    }

    public int getAnswer(int questionIndex) {
        return answers[questionIndex];
    }

    // ── Submit ───────────────────────────────────────────────
    public void submit() {
        this.submitted = true;
    }

    public boolean isSubmitted() { return submitted; }

    // ── Results ──────────────────────────────────────────────
    public int calculateScore() {
        int score = 0;
        for (int i = 0; i < totalQuestions; i++) {
            if (answers[i] != 0 && questions[i].isCorrect(answers[i])) {
                score++;
            }
        }
        return score;
    }

    public int getAttempted() {
        int count = 0;
        for (int a : answers) if (a != 0) count++;
        return count;
    }

    public int getTotalQuestions() { return totalQuestions; }
    public Question[] getQuestions() { return questions; }
    public int[] getAnswers() { return answers; }
    public int getTimeLimitSeconds() { return timeLimitSeconds; }
}
