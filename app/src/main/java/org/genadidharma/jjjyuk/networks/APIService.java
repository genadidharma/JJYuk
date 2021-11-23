package org.genadidharma.jjjyuk.networks;


import org.genadidharma.jjjyuk.data.model.DestinationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("/")
    Call<DestinationResponse> getDestinations();

    @GET("/")
    Call<DestinationResponse> searchDestinations(
            @Query("id") int id
    );
}
