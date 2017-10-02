package com.example.brilho.maisibta.dao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Brilho on 10/05/2017.
 */

public interface NoticesService {

    public static final String BASE_URL = "http://appibta.herokuapp.com";
    @GET("/notices")
    Call<ResponseBody> listaNoticias();

    @FormUrlEncoded
    @POST("/contacts")
    Call<ResponseBody> enviarForm(@Field("contact[email]") String email,@Field("contact[message]") String message,
                                  @Field("contact[subject]")String subject,@Field("contact[phone]") String phone);

}
