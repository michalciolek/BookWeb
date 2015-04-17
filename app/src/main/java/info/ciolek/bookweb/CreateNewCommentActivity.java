package info.ciolek.bookweb;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import info.ciolek.bookweb.models.Comment;
import info.ciolek.bookweb.network.CreateNewCommentRequest;
import info.ciolek.bookweb.network.InternetActivity;


public class CreateNewCommentActivity extends InternetActivity {

    @Email(message = "To pole musi być emajlem!")
    private EditText authorEditText;

    @NotEmpty(message = "Musisz podać komentarz!!")
    private EditText commentEditText;

    Validator validator = new Validator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_comment);

        authorEditText = (EditText) findViewById(R.id.authorEditText);
        commentEditText = (EditText) findViewById(R.id.commentEditText);

        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                Comment newComment = new Comment(0, "sdfg", "", authorEditText.getText().toString(), commentEditText.getText().toString());


                CreateNewCommentRequest createNewCommentRequest = new CreateNewCommentRequest(newComment);

                spiceManager.execute(createNewCommentRequest, new RequestListener<Comment>() {
                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        Toast.makeText(CreateNewCommentActivity.this, "Jest błąd", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRequestSuccess(Comment comment) {
                        finish();
                    }
                });
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view = error.getView();
                    String message = error.getCollatedErrorMessage(CreateNewCommentActivity.this);

                    // Display error messages ;)
                    if (view instanceof EditText) {
                        ((EditText) view).setError(message);
                    } else {
                        Toast.makeText(CreateNewCommentActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSendClick(View view) {
        validator.validate();


    }
}
