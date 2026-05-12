package exam;

public class QuestionBank {

    public static Question[] getQuestions() {
        return new Question[] {

            new Question(
                "Which keyword is used to define a class in Java?",
                new String[]{"define", "class", "struct", "object"},
                2
            ),
            new Question(
                "What is the size of an int in Java?",
                new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on OS"},
                2
            ),
            new Question(
                "Which of these is NOT a primitive data type in Java?",
                new String[]{"int", "float", "String", "boolean"},
                3
            ),
            new Question(
                "What does JVM stand for?",
                new String[]{"Java Virtual Machine", "Java Visual Memory", "Java Variable Method", "Java Verified Module"},
                1
            ),
            new Question(
                "Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"},
                3
            ),
            new Question(
                "Which collection uses key-value pairs in Java?",
                new String[]{"ArrayList", "LinkedList", "HashMap", "HashSet"},
                3
            ),
            new Question(
                "What is the default value of a boolean variable in Java?",
                new String[]{"true", "false", "0", "null"},
                2
            ),
            new Question(
                "Which operator is used to create an object in Java?",
                new String[]{"create", "new", "object", "alloc"},
                2
            ),
            new Question(
                "Which of the following is used for single-line comments in Java?",
                new String[]{"/* */", "<!-- -->", "//", "#"},
                3
            ),
            new Question(
                "What is the parent class of all classes in Java?",
                new String[]{"Base", "Super", "Object", "Root"},
                3
            )
        };
    }
}
