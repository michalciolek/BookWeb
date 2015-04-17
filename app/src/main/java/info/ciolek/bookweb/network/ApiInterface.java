package info.ciolek.bookweb.network;

import info.ciolek.bookweb.model.Book;
import info.ciolek.bookweb.model.Comment;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by michael on 10.04.15.
 */
public interface ApiInterface {

    @GET("/comments")
    Comment.List comments();

    @GET("/comments/{id}")
    Comment comment(@Path("id") long id);

    @GET("/comments")
    Comment.List comments(@Query("q") String bookId);

    @POST("/comments")
    Comment createComment(@Body Comment comment);

    @GET("/volumes")
    Book.Items volumes(@Query("q") String searchText);

    @GET("/volumes/{id}")
    Book volume(@Path("id") String bookId);
}
