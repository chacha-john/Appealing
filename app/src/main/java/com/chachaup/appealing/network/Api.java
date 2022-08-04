package com.chachaup.appealing.network;

import com.chachaup.appealing.models.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //get all books
    @GET("books")
    Call<BookResponse> getBooks();
}
