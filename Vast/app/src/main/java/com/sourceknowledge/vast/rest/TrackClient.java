package com.sourceknowledge.vast.rest;

import com.sourceknowledge.vast.models.vast.Vast;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by omegatai on 2014-06-17.
 */
public interface TrackClient {
    @GET("/")
    Response trackAdProgress();
}

