package org.ionuth.gen.services;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public interface BookService {
	
	List<Pair<String, String>> getBooks();
}
