package kz.kbtu.catsapp.Network;

import kz.kbtu.catsapp.ResponseCats;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abakh on 25-Jul-17.
 */

public interface ApiService {

    @GET("/api/images/get")
    Call<ResponseCats> getCats(@Query("format") String format, @Query("results_per_page") int numb);

}
