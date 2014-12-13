package com.sourceknowledge.vast.rest;

import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

/**
 * Created by omegatai on 2014-06-17.
 */
public interface DownloadVastTagClient {
    @POST("/bid.php")
    Integer downloadVastClient(@Body TypedInput payload);
}

