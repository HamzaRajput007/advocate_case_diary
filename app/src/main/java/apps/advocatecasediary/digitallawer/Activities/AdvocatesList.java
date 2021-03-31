package apps.advocatecasediary.digitallawer.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import apps.advocatecasediary.digitallawer.Adapters.AdvocatesAdapter;
import apps.advocatecasediary.digitallawer.Models.AdvocateModel;
import apps.advocatecasediary.digitallawer.R;

public class AdvocatesList extends AppCompatActivity {

    FirebaseFirestore mFireStore;
    FirebaseAuth mAuth;
    List<AdvocateModel> advocatesList;
    RecyclerView advocatesRecyclerView;
    AdvocatesAdapter advocatesAdapter;
    @Override
    protected void onStart() {
        super.onStart();

        mFireStore.collection("Advocates").addSnapshotListener(this , new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange documentChange : value.getDocumentChanges()){
                    if (documentChange.getType() == DocumentChange.Type.ADDED){
                        AdvocateModel advocate  = documentChange.getDocument().toObject(AdvocateModel.class);
                        advocatesList.add(advocate);
                        advocatesAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
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
        advocatesAdapter = new AdvocatesAdapter(advocatesList , this);
        advocatesRecyclerView.setAdapter(advocatesAdapter);

    }
}