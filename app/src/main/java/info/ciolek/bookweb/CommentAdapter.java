package info.ciolek.bookweb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import info.ciolek.bookweb.models.Comment;

/**
 * Created by michael on 14.04.15.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Comment.List commentList;

    public CommentAdapter() {
        commentList = new Comment.List();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_row, viewGroup, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder commentViewHolder, int i) {
        Comment comment = commentList.get(i);

        commentViewHolder.nameTextView.setText(comment.author);
        commentViewHolder.dateTextView.setText(comment.date);
        commentViewHolder.commentTextView.setText(comment.comment);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView dateTextView;
        public TextView commentTextView;

        public CommentViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            commentTextView = (TextView) itemView.findViewById(R.id.commentTextView);
        }
    }

   public void addNewComments(Comment.List newComments) {
       commentList.clear();
       commentList.addAll(newComments);
       notifyDataSetChanged();
   }
}
