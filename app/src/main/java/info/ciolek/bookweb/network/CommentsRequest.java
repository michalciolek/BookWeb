package info.ciolek.bookweb.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import info.ciolek.bookweb.models.Comment;

/**
 * Created by michael on 15.04.15.
 */
public class CommentsRequest extends RetrofitSpiceRequest<Comment.List, ApiInterface> {


    public CommentsRequest() {
        super(Comment.List.class, ApiInterface.class);
    }

    @Override
    public Comment.List loadDataFromNetwork() throws Exception {
        return getService().comments();
    }
}
