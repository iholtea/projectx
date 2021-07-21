package org.ionuth.gen.services.app;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.ionuth.gen.services.BookService;
import org.ionuth.gen.services.CityService;
import org.ionuth.gen.services.FirstNameService;
import org.ionuth.gen.services.LastNameService;
import org.ionuth.gen.services.StreetService;
import org.ionuth.gen.services.impl.json.BookServiceJson;
import org.ionuth.gen.services.impl.json.CityServiceJson;
import org.ionuth.gen.services.impl.json.FirstNameServiceJson;
import org.ionuth.gen.services.impl.json.LastNameServiceJson;
import org.ionuth.gen.services.impl.json.StreetServiceJson;

public class AppTest {
	
	private static void testNameServiceJson() {
		FirstNameService fns = new FirstNameServiceJson();
		LastNameService lns = new LastNameServiceJson();
		
		List<String> firstNames = fns.getNames();
		System.out.println("firstNames(0): " + firstNames.get(0));
		System.out.println("firstNames(last): " + firstNames.get(firstNames.size()-1));
		
		System.out.println("");
		List<String> lastNames = lns.getNames();
		System.out.println("lastNames(0): " + lastNames.get(0));
		System.out.println("lastNames(last): " + lastNames.get(lastNames.size()-1));
	}
	
	private static void testCityServiceJson() {
		CityService cs = new CityServiceJson();
		List<Pair<String, String>> cities = cs.getCities();
		System.out.println();
		System.out.println("cities(0): " + cities.get(0).getLeft() + " " + cities.get(0).getRight()  );
		System.out.println("cities(last): " + cities.get(cities.size()-1).getLeft() + 
				" " + cities.get(cities.size()-1).getRight()  );
	}
	
	private static void testStreetServiceJson() {
		StreetService ss = new StreetServiceJson();
		List<String> streets = ss.getStreets();
		System.out.println();
		System.out.println("streets(0): " + streets.get(0));
		System.out.println("streets(last): " + streets.get(streets.size()-1));
	}
	
	private static void testBooksServiceJson() {
		BookService bs = new BookServiceJson();
		List<Pair<String,String>> books = bs.getBooks();
		System.out.println();
		System.out.println("books(0): " + books.get(0).getLeft() + " " + books.get(0).getRight()  );
		System.out.println("books(last): " + books.get(books.size()-1).getLeft() + 
				" " + books.get(books.size()-1).getRight()  );
	}
	
	public static void main(String[] args) {
		
		testNameServiceJson();
		System.out.println("\n-----------------------\n");
		
		testCityServiceJson();
		System.out.println("\n-----------------------\n");
		
		testStreetServiceJson();
		System.out.println("\n-----------------------\n");
		
		testBooksServiceJson();
		
		
		
		

	}
	
	
}
