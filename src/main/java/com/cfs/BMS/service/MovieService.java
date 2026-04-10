package com.cfs.BMS.service;

import com.cfs.BMS.entity.Movie;
import com.cfs.BMS.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;


    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);

    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id).orElseThrow(()-> new RuntimeException("Movie not found by id" + id));
    }
    //for search Title

    public List<Movie> searchByTitle(String  title){
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Movie> searchByGenre(String  genre){
        return movieRepository.findByGenre(genre);
    }
    public List<Movie> searchByLanguage(String language){
        return movieRepository.findByLanguage(language);
    }

    public Movie updateMovie(Long id,Movie newMovie){
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new RuntimeException("Movie not found by Id"));

        movie.setTitle(newMovie.getTitle());
        return  movieRepository.save(movie);
    }


    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }


}
