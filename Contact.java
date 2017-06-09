package zadatciKolekcije;

import java.util.Comparator;

public class Contact {
	private String firstName = "";
	private String lastName = "";
	private String phoneNumber = "";

	private Contact(String first, String last, String number) {
		this.firstName = first;
		this.lastName = last;
		this.phoneNumber = number;

	}

	public static Contact constructContact(String firstName, String lastName, String number) {
		number = number.replace("[^0-9+]", "");
		return new Contact(firstName, lastName, number);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String toString() {
		return this.firstName + ":" + this.lastName + ":" + this.phoneNumber;
	}
}
