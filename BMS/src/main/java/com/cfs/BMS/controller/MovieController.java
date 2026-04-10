package com.cfs.BMS.controller;

import com.cfs.BMS.entity.Movie;
import com.cfs.BMS.service.MovieService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/movieTitle")
    public ResponseEntity<List<Movie>> searchByTitle(@RequestParam String movieTitle){
        return ResponseEntity.ok(movieService.searchByTitle(movieTitle));
    }

    @GetMapping("/movieLanguage")
    public ResponseEntity<List<Movie>> searchByLanguage(@PathVariable String movieLanguage){
        return ResponseEntity.ok(movieService.searchByLanguage(movieLanguage));
    }

    @GetMapping("/genre/movieGenre")
    public ResponseEntity<List<Movie>> searchByGenre(@PathVariable String movieGenre){
        return ResponseEntity.ok(movieService.searchByGenre(movieGenre));
    }
}
