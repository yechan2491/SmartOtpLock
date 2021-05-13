package com.cookandroid.smartotplock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHub {
    // GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
    @GET("/repos/{owner}/{repo}/contributors")
    // JSON Array를 리턴하므로 List<>가 되었다
    Call<List<Contributor>> contributors(
            // param 값으로 들어가는 것들이다
            @Path("owner") String owner,
            @Path("repo") String repo);
}