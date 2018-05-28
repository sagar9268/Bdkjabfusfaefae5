package co.bingleapp.bingle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Linked elements in layout to Java code
        TextView mSignupSwitch =  findViewById(R.id.SignUp_TxtViewButton);

        //Switch to Sign up activity
        mSignupSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mSwitchtoSignUp = new Intent(Login.this,SignUp.class);
                startActivity(mSwitchtoSignUp);
            }
        });

    }
}
