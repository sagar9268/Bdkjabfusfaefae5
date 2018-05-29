package co.bingleapp.bingle;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    // UI references
    private EditText mSignUpEmail;
    private EditText mSignUpPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Linked elements in layout to Java code
        TextView mSignUpSwitch = findViewById(R.id.SignUp_TxtViewButton);
        mSignUpEmail = findViewById(R.id.Email_Field);
        mSignUpPassword = findViewById(R.id.Password_Field);

        // Keyboard Sign in action
        mSignUpPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if( i == R.integer.login_form_complete || i == EditorInfo.IME_NULL){
                    attemplogin();
                    return true;
                }

                return false;
            }
        });

        //Switch to Sign up activity
        mSignUpSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mSwitchtoSignUp = new Intent(Login.this, SignUp.class);
                startActivity(mSwitchtoSignUp);
            }
        });

        // Instance of Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    public void LoginClick(View view){
        attemplogin();
    }
    private void attemplogin() {
        String email = mSignUpEmail.getText().toString();
        String password = mSignUpPassword.getText().toString();

        if (email.equals("") || password.equals("")) return;

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    showErrorDialog("Invalid email or password");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Log in successful",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
