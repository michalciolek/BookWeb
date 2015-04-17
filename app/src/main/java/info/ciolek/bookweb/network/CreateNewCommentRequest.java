package info.ciolek.bookweb.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import info.ciolek.bookweb.models.Comment;

/**
 * Created by michael on 16.04.15.
 */
public class CreateNewCommentRequest extends RetrofitSpiceRequest<Comment, ApiInterface> {

    private Comment newComment;

    public CreateNewCommentRequest(Comment newComment) {
        super(Comment.class, ApiInterface.class);
        this.newComment = newComment;
    }

    @Override
    public Comment loadDataFromNetwork() throws Exception {
        return getService().createNewComment(newComment);
    }
}
