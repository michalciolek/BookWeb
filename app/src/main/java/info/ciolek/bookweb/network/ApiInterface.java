package info.ciolek.bookweb.network;

import info.ciolek.bookweb.models.Comment;
import retrofit.http.GET;

/**
 * Created by michael on 15.04.15.
 */
public interface ApiInterface {
    @GET("/comments")
    Comment.List comments();
}
