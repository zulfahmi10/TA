package com.example.lenovog40.myapplication.Api;

import okhttp3.ResponseBody;
import  retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface BaseApiService {
    //LOGIN
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest (@Field("username") String username,
                                     @Field("password") String password);
    //REGISTER
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest (@Field("username") String username,
                                        @Field("password")String password,
                                        @Field("nama_user_android") String nama_user_android,
                                        @Field("alamat") String alamat,
                                        @Field("telpon") String telpon,
                                        @Field("email") String email);

    //IkutEvent
    @FormUrlEncoded
    @POST("ikutEvent.php")
    Call<ResponseBody> ikutevent(@Field("id_register") String id_register,
                                 @Field("id_event")String id_event,
                                 @Field("nama_register")String nama_register,
                                 @Field("alamat")String alamat,
                                 @Field("telpon")String telpon,
                                 @Field("email")String email);

    //LISTEVENT

    @GET("listAllEvent.php")
    Call<ResponseBody> daftarRequest();

}
