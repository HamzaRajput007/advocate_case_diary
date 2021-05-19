package apps.advocatecasediary.digitallawer.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apps.advocatecasediary.digitallawer.Adapters.AdvocatesAdapter;
import apps.advocatecasediary.digitallawer.Models.AdvocateModel;
import apps.advocatecasediary.digitallawer.R;

public class AdvocatesList extends AppCompatActivity implements AdvocatesAdapter.OnAdvocateClickListener {

    FirebaseFirestore mFireStore;
    FirebaseAuth mAuth;
    List<AdvocateModel> advocatesList;
    RecyclerView advocatesRecyclerView;
    AdvocatesAdapter advocatesAdapter;
    int i = 0;
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advocates_list);

        mFireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        advocatesList = new ArrayList<>();

        advocatesRecyclerView = findViewById(R.id.advocatesListRecyclerViewID);
        advocatesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        advocatesRecyclerView.setHasFixedSize(true);
        advocatesAdapter = new AdvocatesAdapter(advocatesList , this , this);
        advocatesRecyclerView.setAdapter(advocatesAdapter);

        mFireStore.collection("Advocates").addSnapshotListener(this , new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                try {
                    for (DocumentChange documentChange : value.getDocumentChanges()){
                        if (documentChange.getType() == DocumentChange.Type.ADDED){
                            String uid = documentChange.getDocument().getId();
                            HashMap<String , Object> recievedData = (HashMap<String, Object>) documentChange.getDocument().getData();
//                            Toast.makeText(AdvocatesList.this, recievedData.get("image_url").toString(), Toast.LENGTH_SHORT).show();
                            AdvocateModel advocate  = documentChange.getDocument().toObject(AdvocateModel.class).withId(uid);
                            advocate.setUserId(uid);
                            advocate.setImageUrl(recievedData.get("image_url").toString());
                            advocatesList.add(advocate);
                            advocatesAdapter.notifyDataSetChanged();
                        }
                    }
                }catch (Exception exception){
                    Log.d("TAG_EXCEPTION", "onEvent: " + error.getMessage());
                    Toast.makeText(AdvocatesList.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onAdvocateClicked(int position) {
        Intent toAdvocateProfile = new Intent(this , AdvocateProfile.class);
        toAdvocateProfile.putExtra("userId" , advocatesList.get(position).getUserId());
        toAdvocateProfile.putExtra("phone" , advocatesList.get(position).getPhone_number());
        toAdvocateProfile.putExtra("name" , advocatesList.get(position).getName());
        toAdvocateProfile.putExtra("email" , advocatesList.get(position).getEmail());
        toAdvocateProfile.putExtra("city" , advocatesList.get(position).getCity());
        toAdvocateProfile.putExtra("experience" , advocatesList.get(position).getExperience());
        toAdvocateProfile.putExtra("image_url" , advocatesList.get(position).getImageUrl());
        startActivity(toAdvocateProfile);

    }
}