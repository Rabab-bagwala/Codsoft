import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private String question;
    private String[] options;
    private char correctAnswer;

    public Quiz(String question, String[] options, char correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }
}

class QuizApp {
    private Quiz[] quizzes;
    private int score;
    private int currentQuestionIndex;
    private boolean timeUp;
    private Scanner scanner;

    public QuizApp(Quiz[] quizzes) {
        this.quizzes = quizzes;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        for (currentQuestionIndex = 0; currentQuestionIndex < quizzes.length; currentQuestionIndex++) {
            timeUp = false;
            System.out.println(
                    "\nQuestion " + (currentQuestionIndex + 1) + ": " + quizzes[currentQuestionIndex].getQuestion());
            String[] options = quizzes[currentQuestionIndex].getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((char) ('A' + i) + ": " + options[i]);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                }
            }, 10000); // 10 seconds timer

            char userAnswer = getUserAnswer();
            timer.cancel();

            if (timeUp) {
                System.out.println("Time's up! Moving to the next question.");
            } else if (userAnswer == quizzes[currentQuestionIndex].getCorrectAnswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println(
                        "Incorrect. The correct answer was " + quizzes[currentQuestionIndex].getCorrectAnswer());
            }
        }

        displayResult();
    }

    private char getUserAnswer() {
        char answer = ' ';
        while (!timeUp) {
            System.out.print("Enter your answer (A/B/C/D): ");
            answer = Character.toUpperCase(scanner.next().charAt(0));
            if (answer >= 'A' && answer <= 'D') {
                break;
            } else {
                System.out.println("Invalid answer. Please enter A, B, C, or D.");
            }
        }
        return answer;
    }

    private void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + quizzes.length);
        System.out.println("Summary:");
        for (int i = 0; i < quizzes.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + quizzes[i].getQuestion());
            System.out.println("Correct answer: " + quizzes[i].getCorrectAnswer());
        }
    }

    public static void main(String[] args) {
        Quiz[] quizzes = {
                new Quiz("Which type of fish is Nemo from Finding Nemo?",
                        new String[] { "GoldFish", "ClownFish", "AngelFish", "SwordFish" }, 'B'),
                new Quiz("Milky Way is a named celestial object of what sort?",
                        new String[] { " Supernova", " Comet", "Constellation", " Galaxy" }, 'D'),
                new Quiz("Who wrote 'To Kill a Mockingbird'?",
                        new String[] { "Harper Lee", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald" }, 'A'),
                new Quiz("What is the smallest planet in our solar system?",
                        new String[] { "Venus", "Mars", "Mercury", "Earth" }, 'C')
        };

        QuizApp quizApp = new QuizApp(quizzes);
        quizApp.startQuiz();
    }
}
