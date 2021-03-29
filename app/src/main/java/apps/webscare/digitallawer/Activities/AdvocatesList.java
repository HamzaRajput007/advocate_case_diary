package apps.webscare.digitallawer.Activities;

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
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

import apps.webscare.digitallawer.Adapters.AdvocatesAdapter;
import apps.webscare.digitallawer.Models.AdvocateModel;
import apps.webscare.digitallawer.R;

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
      // TODO implemet recycler view


            /*  @Override
            public void onBindViewHolder(AdvocateViewHolder holder, int position, AdvocatesList model) {

                holder.name.setText(model.);

            }

            @Override
            public AdvocateViewHolder onCreateViewHolder(ViewGroup group, int i) {
                // Using a custom layout called R.layout.message for each item, we create a new instance of the viewholder
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.advocate_item_layout, group, false);

                return new AdvocateViewHolder(view);
            }*/
    }


       /* mFireStore.collection("Advocates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                }
            }
        });

        mFireStore.collection("Advocates").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            }
        });*/
}