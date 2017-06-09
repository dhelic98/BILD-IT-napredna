package zadatciKolekcije;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Zadatak3 {

	public static void main(String[] args) throws IOException {
		try (Scanner uInput = new Scanner(System.in)) {
			System.out.println("Enter name of the file");
			String fileName = uInput.nextLine();
			while (!Files.exists(Paths.get(fileName))) {
				System.out.println("File does not exist");
				System.out.println("Enter name again");
				fileName = uInput.nextLine();

			}
			try (BufferedReader bReader = Files.newBufferedReader(Paths.get("File"))) {

				List<String> lines = new ArrayList<>();

				String line = "";

				while ((line = bReader.readLine()) != null) {
					lines.add(line);
				}

				System.out.println("Enter number of lines you would like to read");
				int numOfLines = uInput.nextInt();

				while (numOfLines > lines.size()) {
					System.out.println("Number of lines is bigger than number of lines in file");
					numOfLines = uInput.nextInt();
				}

				while (numOfLines - 1 >= 0) {
					int rand = (int) (Math.random() * lines.size() - 1);
					System.out.println(lines.get(rand));
					lines.remove(rand);
					numOfLines--;
				}
				uInput.nextLine();

			} catch (InputMismatchException e) {
				System.out.println("Input mismatch");
				main(args);

			} catch (Exception e) {
				System.out.println("Generic exception");

			}

		} catch (Exception e) {
			System.out.println("Outer generic exception");
		}

	}
}
