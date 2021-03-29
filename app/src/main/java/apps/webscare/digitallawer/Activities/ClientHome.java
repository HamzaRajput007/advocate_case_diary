package apps.webscare.digitallawer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import apps.webscare.digitallawer.R;

public class ClientHome extends AppCompatActivity {
    Button goToAdvocateListBtn, goToScheduleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);


        goToAdvocateListBtn = findViewById(R.id.insertAdvocateBtnID);
        goToScheduleBtn = findViewById(R.id.updateSchedule);

        goToAdvocateListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInsertAdvocate = new Intent(ClientHome.this , AdvocatesList.class);
                startActivity(toInsertAdvocate);
            }
        });

        goToScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}