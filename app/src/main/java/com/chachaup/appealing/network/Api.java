package com.chachaup.appealing.network;

import com.chachaup.appealing.models.BookResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //get all books
    @GET("books")
    Call<List<BookResponse>> getBooks();
}
