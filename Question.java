package exam;

public class Question {

    private String questionText;
    private String[] options;      // Always 4 options
    private int correctOption;     // 1-based index

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() { return questionText; }
    public String[] getOptions()    { return options; }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }

    public int getCorrectOption() { return correctOption; }
}
