package com.e_mobadara.utils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiService {
    @Multipart
    @POST("/upload/")
    Call<ResponseBody> uploadAudio(
            @Part MultipartBody.Part audio, @Part("name") RequestBody name,
            @Part("type") RequestBody type, @Part("langue") RequestBody langue
    );

    @GET("/audiofiles/{i}")
    Call<AudioList> downloadFiles(@Path("i") int nbr);

    @GET("/audiofiles/audiofile/{id}")
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Path("id") int id);

}