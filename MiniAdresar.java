package zadatciKolekcije;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MiniAdresar {

	private static Map<String, Contact> contacts = new TreeMap<>(getContactComparator());

	public static void main(String[] args) {
		readFromFile(contacts);
		System.out.println("Welcome to mini contact book");
		try (Scanner uInput = new Scanner(System.in)) {
			while (true) {
				try {
					System.out.println("Enter your option\n1.Add new contact\n2.View contacts\n0.Exit");
					int option = Integer.parseInt(uInput.nextLine());

					if (badOption(option)) {
						continue;
					}
					optionSwitch(option, uInput);

				} catch (NumberFormatException e) {
					System.out.println("Number format exception");

				} catch (IOException e) {
					System.out.println("IO Exception");
				}
			}
		}

	}

	private static void optionSwitch(int option, Scanner uInput) throws IOException {
		if (option == 1) {
			addContact(uInput);

		} else if (option == 2) {
			viewContacts();

		} else {
			System.out.println("Bye!");
			System.exit(0);
		}

	}

	private static boolean badOption(int option) {
		if (option > 2 || option < 0) {
			return true;

		}
		return false;
	}

	private static void addContact(Scanner uInput) throws IOException {
		String firstName = getFirstName(uInput);
		String lastName = getLastName(uInput);
		String phoneNumber = getPhoneNumber(uInput);
		if (isConcatcInfoValid(firstName, lastName, phoneNumber)) {
			Contact contact = Contact.constructContact(firstName, lastName, phoneNumber);
			putIntoMap(contact);

			writeToFile();
		} else {
			System.out.println("Wrong contact info");
			addContact(uInput);
		}
	}

	private static void putIntoMap(Contact contact) {
		String key = contact.getFirstName() + contact.getLastName();
		if (contacts.containsKey(key)) {
			key = key + contact.getPhoneNumber();

		}
		contacts.put(key, contact);

	}

	private static String getFirstName(Scanner uInput) {
		System.out.println("Enter first name");
		return getString(uInput);
	}

	private static String getLastName(Scanner uInput) {
		System.out.println("Enter last name");
		return getString(uInput);

	}

	private static String getPhoneNumber(Scanner uInput) {
		System.out.println("Enter phone number");
		return getString(uInput);

	}

	private static boolean isConcatcInfoValid(String name, String last, String phoneNum) {
		if (name.isEmpty() || last.isEmpty() || phoneNum.isEmpty()) {
			return false;
		}
		return true;
	}

	private static String getString(Scanner uInput) {
		return uInput.nextLine();
	}

	private static Comparator<String> getContactComparator() {
		return new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				for (int i = 0; i < ((int) (Math.min(o1.length(), o2.length()))); i++) {
					char c1 = o1.charAt(i);
					char c2 = o2.charAt(i);
					if (c1 > c2) {
						return 1;
					} else if (c1 < c2) {
						return -1;
					}
				}

				return 0;
			}

		};

	}

	private static void viewContacts() {
		contacts.forEach(
				(k, v) -> System.out.println(v.getFirstName() + " " + v.getLastName() + "::" + v.getPhoneNumber()));
	}

	private static void readFromFile(Map<String, Contact> contacts) {

		Path path = Paths.get("contacts.txt");

		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				insertIntoMap(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void insertIntoMap(String info) {
		String[] array = info.split("-");
		if (array.length < 3) {
			return;
		}
		Contact contact = Contact.constructContact(array[0], array[1], array[2]);
		contacts.put((contact.getFirstName() + contact.getLastName()), contact);

	}

	private static void writeToFile() throws IOException {
		Path path = Paths.get("contacts.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			contacts.forEach((key, value) -> {
				try {
					writer.write(value.getFirstName() + "-" + value.getLastName() + "-" + value.getPhoneNumber());
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

	}
}
