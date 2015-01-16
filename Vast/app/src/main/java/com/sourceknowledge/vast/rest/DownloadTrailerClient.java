package com.sourceknowledge.vast.rest;

import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.vast.Vast;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by omegatai on 2014-06-17.
 */
public interface DownloadTrailerClient {
    @GET("/Playlist")
    ArrayList<Trailer> downloadTrailer(@Query("format") String format, @Query("num_results") int nResults, @Query("mime") String mime, @Query("res") String res);
}

