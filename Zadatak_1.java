package zadatciKolekcije;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Zadatak_1 {

	public static void main(String[] args) {
		try (BufferedReader buffReader = Files.newBufferedReader(Paths.get("File"))) {
			String line = "";
			List<String> list = new ArrayList<>();
			while ((line = buffReader.readLine()) != null) {
				line = line.replaceAll("[^\\w&\\s]", "");
				String[] lineSplit = line.split(" ");
				list.addAll(Arrays.asList(lineSplit));

			}

			Collections.sort(list, getStringComparator());

			list.forEach(word -> System.out.println(word));
			
			
			
			
			
			

		} catch (Exception e) {
			System.out.println("Exception");
		}
	}

	public static Comparator<String> getStringComparator() {
		return new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				o1 = o1.toLowerCase();
				o2 = o2.toLowerCase();
				char[] c1 = o1.toCharArray();
				char[] c2 = o2.toCharArray();
				for (int i = 0; i < Math.min(c1.length, c2.length); i++) {
					if (c1[i] > c2[i]) {
						return 1;
					} else if (c1[i] < c2[i]) {
						return -1;
					}

				}
				return 0;
			}

		};

	}
}
