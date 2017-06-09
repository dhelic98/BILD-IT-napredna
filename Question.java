package zadatciKolekcije;

public class Question {
	private String country;
	private String capital;

	private Question(String country, String capital) {
		this.country = country;
		this.capital = capital;
	}

	public static Question constructQuoestion(String country, String capital) {
		return new Question(country, capital);
	}

	public String getCountry() {
		return country;
	}

	public String getCapital() {
		return capital;
	}

}
