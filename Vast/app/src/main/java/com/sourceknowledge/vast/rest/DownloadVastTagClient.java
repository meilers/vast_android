package com.sourceknowledge.vast.rest;

import com.sourceknowledge.vast.models.vast.Vast;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedInput;

/**
 * Created by omegatai on 2014-06-17.
 */
public interface DownloadVastTagClient {
    @GET("/{path}")
    Vast downloadVastClient(@Path("path") String path);
}

