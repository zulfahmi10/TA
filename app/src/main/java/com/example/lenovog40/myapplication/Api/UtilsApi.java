package com.example.lenovog40.myapplication.Api;

public class UtilsApi {
    public static final String BASE_URL_API = "https://fahmi.imukal.com/api/";

    public static BaseApiService getApiService(){
        return AndroidClient.getClient(BASE_URL_API).create(BaseApiService.class);

    }
}
