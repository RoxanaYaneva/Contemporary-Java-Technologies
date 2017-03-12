/*
 * Copyright 2016 Microprofile.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exercise.sixth;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoviesExplorer {

	public static void main(String[] args) throws Exception {

		// 1) Load the movies
		List<Movie> movies = new ArrayList<>();
		Stream<String> lines = Files.lines(Paths.get("resources", "movies-mpaa.txt"));
		lines.forEach(line -> addMovie(movies, line));

		// 2) Find the number of movies released in 2003
		int numberOfMovies = (int) movies.stream().filter(movie -> movie.getYear() == 2003).count();
		System.out.println(numberOfMovies);

		// 3) Find the first movie that contains Lord of the Rings
		Optional<Movie> fMovie = movies.stream().filter(movie -> movie.getTitle().contains("Lord of the Rings"))
				.findFirst();
		fMovie.ifPresent(System.out::println);
		fMovie.orElseThrow(() -> new IllegalArgumentException());
		fMovie.orElseThrow(IllegalArgumentException::new);

		// 4) Display the films sorted by the release year
		movies.stream().sorted((movie1, movie2) -> movie1.getYear() - movie2.getYear());

		// 5) Find the first and the last year in the statistics
		OptionalInt first = movies.stream().mapToInt(movie -> movie.getYear()).min();
		OptionalInt last = movies.stream().mapToInt(movie -> movie.getYear()).max();
		System.out.println("First year: " + first.getAsInt() + " Last year: " + last.getAsInt());

		// 6) Print the movies grouped by year
		movies.stream().collect(Collectors.groupingBy(Movie::getYear));

		// 7) Extract all the actors
		Set<Actor> actors = movies.stream().flatMap(movie -> movie.getActors().stream()).collect(Collectors.toSet());
		System.out.println(actors);

		// 8) Find all the movies with Kevin Spacey
		Actor KevinSpacey = new Actor("Kevin", "Spacey");
		movies.stream().filter(movie -> movie.getActors().contains(KevinSpacey)).forEach(System.out::println);

		lines.close();
	}

	private static void addMovie(List<Movie> movies, String movieInfo) {
		String[] elements = movieInfo.split("/");
		String title = parseMovieTitle(elements);
		String releaseYear = parseMovieReleaseYear(elements);

		Movie movie = new Movie(title, Integer.valueOf(releaseYear));

		for (int i = 1; i < elements.length; i++) {
			String[] name = elements[i].split(", ");
			String lastName = name[0].trim();
			String firstName = "";
			if (name.length > 1) {
				firstName = name[1].trim();
			}

			Actor actor = new Actor(firstName, lastName);
			movie.addActor(actor);
		}

		movies.add(movie);
	}

	private static String parseMovieTitle(String[] elements) {
		return elements[0].substring(0, elements[0].toString().lastIndexOf("(")).trim();
	}

	private static String parseMovieReleaseYear(String[] elements) {
		String releaseYear = elements[0].substring(elements[0].toString().lastIndexOf("(") + 1,
				elements[0].toString().lastIndexOf(")"));
		if (releaseYear.contains(",")) {
			releaseYear = releaseYear.substring(0, releaseYear.indexOf(","));
		}
		return releaseYear;
	}
}
