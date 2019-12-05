package com.wongnai.interview.movie.external;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
@Component
public class MovieInvertedIndexMemory {
    private Map<String, HashSet<Long>> keywordToMovieID;

    public MovieInvertedIndexMemory(){
        keywordToMovieID = new HashMap<>();
    }

    public void push(String keyword,Long ID){
        keyword = keyword.toLowerCase();
        if (keywordToMovieID.containsKey(keyword)){
            keywordToMovieID.get(keyword).add(ID);
        }
        else {
            HashSet<Long> newSet = new HashSet<Long>();
            newSet.add(ID);
            keywordToMovieID.put(keyword,newSet);
        }
    }

    public HashSet<Long> get(String keyword){
        keyword = keyword.toLowerCase();
        return keywordToMovieID.get(keyword);
    }

    public boolean isContainKey(String keyword){
        keyword = keyword.toLowerCase();
        return keywordToMovieID.containsKey(keyword);
    }

    public void clearData(){
        keywordToMovieID.clear();
    }
}
