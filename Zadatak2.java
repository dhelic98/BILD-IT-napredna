package zadatciKolekcije;

import java.util.*;

public class Zadatak2 {

	public static void main(String[] args) {
		System.out.println("Welcome to Dzeno's sthoperica");

		List<Integer> aList = new ArrayList<>();
		List<Integer> lList = new LinkedList<>();

		System.out.printf("Time for adding 5 million ints to arrayList is %,.2f seconds",
				(getTimeNeeded(aList) / 1000.0));
		System.out.printf("\nTime for adding 5 million whole numbers to linked list is: %,.2f seconds \n",
				(getTimeNeeded(lList) / 1000.0));

		System.out.println("Iteration without iterator");
		System.out.printf("Time for arrayList: %,.2f seconds\n", (getTimeNeededForIteration(aList) / 1000.0));
		System.out.printf("Time for linkedList: %,.2f seconds\n", (getTimeNeededForIteration(lList) / 1000.0));

		System.out.println("Iteration with iterator");

		System.out.printf("Time for array list: %.2f seconds\n", (getTimeNeededIterator(aList) / 1000.0));
		System.out.printf("Time for linked list: %.2f seconds\n", (getTimeNeededIterator(lList) / 1000.0));

	}

	public static long getTimeNeeded(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			list.add(i);
		}

		long endTime = System.currentTimeMillis();

		return (long) endTime - startTime;
	}

	public static long getTimeNeededIterator(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			int i = iterator.next();
		}

		long endTime = System.currentTimeMillis();

		return (long) endTime - startTime;

	}

	public static long getTimeNeededForIteration(List<Integer> list) {

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5000000; i++) {
			int l = list.get(i);
		}

		long endTime = System.currentTimeMillis();

		return (long) endTime - startTime;

	}

}
