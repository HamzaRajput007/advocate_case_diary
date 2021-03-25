package apps.webscare.digitallawer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import apps.webscare.digitallawer.R;

public class SignUp extends AppCompatActivity {
    EditText emailET  , passwordET;
    Button btnLogin;
    TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailET = findViewById(R.id.emailEditTextID);
        passwordET = findViewById(R.id.passwordEditTextID);
        loginText = findViewById(R.id.signUpTextViewId);
        btnLogin = findViewById(R.id.loginBtnID);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(SignUp.this , MainActivity.class);
                startActivity(toLogin);
                finish();
            }
        });

    }
}