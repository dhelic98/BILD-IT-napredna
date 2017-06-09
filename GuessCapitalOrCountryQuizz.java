package zadatciKolekcije;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessCapitalOrCountryQuizz {

	private static List<Question> questions = new ArrayList<>();

	private static Scanner uInput = new Scanner(System.in);
	private static String correctAnswer = "";

	public static void main(String[] args) throws IOException {
		readQuestions();

		System.out.println("Welcome to the QUIZZZZZZ");
		try {

			while (true) {

				int numberOfQuestions = getNumberOfQuestions(uInput);

				startQuizz(numberOfQuestions, uInput);

				break;

			}
		} catch (Exception e) {
			System.out.println("Exception in main");
			uInput.nextLine();
			main(args);

		}

	}

	private static void startQuizz(int numberOfQuestions, Scanner uInput) {
		int points = 0;
		while (numberOfQuestions > 0) {
			int rand = getRandom();
			printQuestion(rand);
			String answer = getAnswer(uInput);
			if (isCorrect(answer)) {
				System.out.println("Correct");
				points++;
			} else {
				System.out.println("Correct answer is:" + correctAnswer);
			}

			numberOfQuestions--;
		}
		System.out.println("Game over, number of points: " + points);
	}

	private static boolean isCorrect(String answer) {
		return answer.equals(correctAnswer);

	}

	private static void printQuestion(int rand) {

		int capOrCount = (int) (Math.random() * 2);
		if (capOrCount == 1) {
			printCapitalQ(rand);
		} else {
			printCountryQ(rand);
		}

	}

	private static int getRandom() {
		return (int) (Math.random() * (questions.size() - 1));
	}

	private static String getAnswer(Scanner uInput) {
		String answer = uInput.nextLine();
		while (answer.isEmpty()) {
			System.out.println("Your answer is empty enter again");
			getAnswer(uInput);
		}

		return answer;
	}

	private static void printCapitalQ(int rand) {
		System.out.println("Guess the country with capital of: " + questions.get(rand).getCapital());
		correctAnswer = questions.get(rand).getCountry();
	}

	private static void printCountryQ(int rand) {
		System.out.println("What is the capital city of: " + questions.get(rand).getCountry());
		correctAnswer = questions.get(rand).getCapital();
	}

	private static int getNumberOfQuestions(Scanner uInput) {
		System.out.println("Enter number of questions you would like to answer");
		return Integer.parseInt(uInput.nextLine());
	}

	private static void readQuestions() throws IOException {
		Path path = Paths.get("listOfCountries");
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				insertIntoList(line);
			}
		} catch (Exception e) {
			System.out.println("Exception in readQuestion()");
		}

	}

	private static void insertIntoList(String line) {
		String[] s = line.split(",");
		if (s.length != 2) {
			return;
		}
		Question question = Question.constructQuoestion(s[0], s[1]);
		questions.add(question);
	}

}
