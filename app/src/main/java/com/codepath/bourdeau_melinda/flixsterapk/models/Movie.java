package com.codepath.bourdeau_melinda.flixsterapk.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    int movieId;
    String Back;
    String Poster;
    String title;
    String overview;
    double rating;

    // empty constructor needed by the parceler library
    public Movie() {}

    public Movie(JSONObject jsonObject){
        try {
            Back = jsonObject.getString("backdrop_path");
            Poster = jsonObject.getString("poster_path");
            title = jsonObject.getString("title");
            overview = jsonObject.getString("overview");
            rating = jsonObject.getDouble("vote_average");
            movieId = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPoster() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", Poster);
    }

    public String getBack() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", Back);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }
}
