package apps.advocatecasediary.digitallawer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.protobuf.ApiOrBuilder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.advocatecasediary.digitallawer.Models.AdvocateModel;
import apps.advocatecasediary.digitallawer.R;

public class AdvocatesAdapter extends RecyclerView.Adapter<AdvocatesAdapter.AdvocateViewHolder> {

    List<AdvocateModel> advocateModelList;
    Context mContext;
    OnAdvocateClickListener onAdvocateClickListener;

    public AdvocatesAdapter(List<AdvocateModel> advocateModelList, Context mContext , OnAdvocateClickListener onAdvocateClickListener) {
        this.advocateModelList = advocateModelList;
        this.mContext = mContext;
        this.onAdvocateClickListener = onAdvocateClickListener;
    }

    @NonNull
    @Override
    public AdvocateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advocate_item_layout, parent, false);
        return new AdvocateViewHolder(view , onAdvocateClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvocateViewHolder holder, int position) {
        holder.name.setText(advocateModelList.get(position).getName());
        holder.email.setText(advocateModelList.get(position).getEmail());
        holder.phone.setText(advocateModelList.get(position).getPhone_number());
//        holder.profileImge
        Toast.makeText(mContext, advocateModelList.get(position).getImageUrl(), Toast.LENGTH_SHORT).show();
        Glide.with(mContext).load(advocateModelList.get(position).getImageUrl()).into(holder.profileImge);
        // todo image download url is null fix it whe you are back
    }

    @Override
    public int getItemCount() {
        return advocateModelList.size();
    }

    public class AdvocateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name , phone , email;
        de.hdodenhof.circleimageview.CircleImageView profileImge;
        OnAdvocateClickListener onAdvocateClickListener;
        public AdvocateViewHolder(@NonNull View itemView , OnAdvocateClickListener onAdvocateClickListener) {
            super(itemView);
            this.onAdvocateClickListener = onAdvocateClickListener;
            name = itemView.findViewById(R.id.advocateNameID);
            phone = itemView.findViewById(R.id.advocatePhoneNumberID);
            email = itemView.findViewById(R.id.advocateEmailId);
            profileImge = itemView.findViewById(R.id.itemLayoutProfileImageId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAdvocateClickListener.onAdvocateClicked(getAdapterPosition());
        }
    }

    public static interface OnAdvocateClickListener{
        public void onAdvocateClicked(int position);
    }

}
