package apps.advocatecasediary.digitallawer.Activities;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import apps.advocatecasediary.digitallawer.Constants;
import apps.advocatecasediary.digitallawer.Models.Upload;
import apps.advocatecasediary.digitallawer.R;

public class ClientHome extends AppCompatActivity {
    Button goToAdvocateListBtn, goToScheduleBtn , logOutBtn;
    FirebaseStorage storage;
    LinearLayout scheduleLayout;
    TextView scheduleNameTextView;


    //database reference to get uploads data
    DatabaseReference mDatabaseReference;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        goToAdvocateListBtn = findViewById(R.id.insertAdvocateBtnID);
        goToScheduleBtn = findViewById(R.id.updateSchedule);
        logOutBtn = findViewById(R.id.logoutBtnID);
        scheduleLayout = findViewById(R.id.documentlayoutId);
        scheduleNameTextView = findViewById(R.id.scheleFileNameTextViewId);

        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();

        StorageReference storageRef = storage.getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    if (upload != null) {
                        Toast.makeText(ClientHome.this, "Schedule Downloaded Successfully", Toast.LENGTH_SHORT).show();

                        scheduleLayout.setVisibility(View.VISIBLE);

                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(ClientHome.this, "Failed    " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

// Create a reference with an initial file path and name
        StorageReference pathReference = storageRef.child("uploads/Schedule.pdf");

// Create a reference to a file from a Google Cloud Storage URI
//        StorageReference gsReference = storage.getReferenceFromUrl("gs://bucket/uploads/Schedule.pdf");

// Create a reference from an HTTPS URL
// Note that in the URL, characters are URL escaped!
//        StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/uploads%20Schedule.pdf");

        pathReference = storageRef.child("uploads/Schedule.pdf");

        File localFile = null;
        try {
            localFile = File.createTempFile("Schedule", "pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ClientHome.this, "Schedule File Downloaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(ClientHome.this, "Failed to download Schedule File", Toast.LENGTH_SHORT).show();
            }
        });


        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent toLogin = new Intent(ClientHome.this , MainActivity.class);
                startActivity(toLogin);
                finish();
            }
        });
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