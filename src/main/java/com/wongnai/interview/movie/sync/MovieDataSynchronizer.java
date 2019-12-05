package com.wongnai.interview.movie.sync;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieInvertedIndexMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieInvertedIndexMemory moviesKeywordToID;
	private boolean isSync = false;

	@Transactional
	public void forceSync() {
        //TODO: implement this to sync movie into repository
            movieRepository.deleteAll();
            moviesKeywordToID.clearData();
            List<MovieData> movieDatas = movieDataService.fetchAll();
            for (MovieData data : movieDatas) {
                Movie movie = new Movie(data.getTitle());
                movie.getActors().addAll(data.getCast());
                movieRepository.save(movie);

                for (String keyword : data.getTitle().split(" ")) {
                    moviesKeywordToID.push(keyword, movie.getId());
                }

            }
        }
}
