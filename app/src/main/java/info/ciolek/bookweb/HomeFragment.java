package info.ciolek.bookweb;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import info.ciolek.bookweb.models.Comment;
import info.ciolek.bookweb.network.CommentsRequest;
import info.ciolek.bookweb.network.InternetFragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends InternetFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        CommentsRequest commentsRequest = new CommentsRequest();

        spiceManager.execute(commentsRequest, new RequestListener<Comment.List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getActivity(), "Jest błąd!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestSuccess(Comment.List comments) {
                commentAdapter.addNewComments(comments);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentAdapter = new CommentAdapter();

        recyclerView.setAdapter(commentAdapter);

        return rootView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((HomeActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}