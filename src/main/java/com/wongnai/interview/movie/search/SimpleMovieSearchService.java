package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;

import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class
		ArrayList<MovieData> movieDataList = movieDataService.fetchAll();
		queryText = queryText.toLowerCase();
		List<Movie> movieList = new ArrayList<Movie>();
		for (MovieData movie : movieDataList){

			String movieTitle = movie.getTitle().toLowerCase();
				if (movieTitle.contains(" "+queryText+" ") ||
						(movieTitle.contains(" "+queryText) && movieTitle.indexOf(queryText) + queryText.length() == movieTitle.length()) ||
						(movieTitle.contains(queryText+ " ") && movieTitle.indexOf(queryText) == 0)){
					Movie wantedMovie = new Movie(movie.getTitle());
					wantedMovie.getActors().addAll(movie.getCast());

					movieList.add(wantedMovie);
				}

			}

		return movieList;
	}
}
