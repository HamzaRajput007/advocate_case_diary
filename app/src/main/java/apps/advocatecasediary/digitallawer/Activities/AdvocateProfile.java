package apps.advocatecasediary.digitallawer.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import apps.advocatecasediary.digitallawer.R;

public class AdvocateProfile extends AppCompatActivity {

    TextView emailTv, phoneTv, cityTv, experienceTv, nameTv;
    Button contactMeBtn;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advocate_profile);

        emailTv = findViewById(R.id.profilePageEmailTextViewId);
        phoneTv = findViewById(R.id.advocatePhoneNumberIdProfilePage);
        cityTv = findViewById(R.id.advocateCityTextViewId);
        experienceTv = findViewById(R.id.profilePageExperienceTextViewId);
        nameTv = findViewById(R.id.advocateNameIDProfilePage);
        contactMeBtn = findViewById(R.id.contactMeBtnID);
        profileImage = findViewById(R.id.profileImageId);

        nameTv.setText(getIntent().getStringExtra("name"));
        emailTv.setText(getIntent().getStringExtra("email"));
        phoneTv.setText(getIntent().getStringExtra("phone"));
        experienceTv.setText(getIntent().getStringExtra("experience"));
        cityTv.setText(getIntent().getStringExtra("city"));

        Glide.with(this).load(getIntent().getStringExtra("image_url")).into(profileImage);

    }
}