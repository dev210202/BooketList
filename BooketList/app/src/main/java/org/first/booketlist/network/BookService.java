package org.first.booketlist.network;

import org.first.booketlist.model.BookData;
import org.first.booketlist.model.BookInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BookService {

    @Headers({"X-Naver-Client-Id: OAVpqgXpExdFQglBCJFc","X-Naver-Client-Secret: KTRwVjuTXS"})
    @GET("book.json")
    Call<BookData> getBooks(@Query("query") String title, @Query("display") int amount);


}
