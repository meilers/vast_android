package com.sourceknowledge.vast.rest;

import com.sourceknowledge.vast.models.vast.Vast;

import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

/**
 * Created by omegatai on 2014-06-17.
 */
public interface DownloadVastTagUriClient {
    @POST("/bid.php")
    Vast downloadVastClientUri(@Body TypedInput payload);
}

