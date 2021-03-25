package apps.webscare.digitallawer.Activities;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import apps.webscare.digitallawer.R;

public class MainActivity extends AppCompatActivity {

    EditText emailET  , passwordET;
    Button btnLogin;
    TextView signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailET = findViewById(R.id.emailEditTextID);
        passwordET = findViewById(R.id.passwordEditTextID);
        signUpBtn = findViewById(R.id.signUpTextViewId);
        btnLogin = findViewById(R.id.loginBtnID);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignup = new Intent(MainActivity.this , SignUp.class);
                startActivity(toSignup);
                finish();
            }
        });


    }
}