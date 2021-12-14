package org.genadidharma.jjjyuk.networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIBuilder {

    private final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jjyuk-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    public final APIService service = retrofit.create(APIService.class);
}
