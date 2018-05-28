package co.bingleapp.bingle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Linked elements in layout to Java code
        TextView mLoginSwitch = findViewById(R.id.Login_TextViewButton);

        // Switch to Login activity
        mLoginSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mSwitchtoLogin = new Intent(SignUp.this,Login.class);
                startActivity(mSwitchtoLogin);
            }
        });
    }
}
