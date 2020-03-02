package com.worldremit.sousers.api;

import com.worldremit.sousers.api.model.SoResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface StackOverflowApi {

    @GET("2.2/users?pagesize=100&order=desc&sort=reputation&site=stackoverflow")
    Single<SoResponse> getTopUsers();
}
