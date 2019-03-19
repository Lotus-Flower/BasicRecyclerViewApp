package meehan.matthew.androidcodingchallenge.Network;

import com.google.gson.JsonArray;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkInterface {

    @GET("books.json")
    Observable<JsonArray> getBooks();

}
