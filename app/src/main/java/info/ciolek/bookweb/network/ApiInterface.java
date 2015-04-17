package info.ciolek.bookweb.network;

import info.ciolek.bookweb.models.Comment;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by michael on 15.04.15.
 */
public interface ApiInterface {
    @GET("/comments")
    Comment.List comments();

    @POST("/comments")
    Comment createNewComment(@Body Comment newComment);
}
