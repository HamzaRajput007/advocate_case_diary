package apps.webscare.digitallawer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.webscare.digitallawer.Models.AdvocateModel;
import apps.webscare.digitallawer.R;

public class AdvocatesAdapter extends RecyclerView.Adapter<AdvocatesAdapter.AdvocateViewHolder> {

    List<AdvocateModel> advocateModelList;
    Context mContext;

    public AdvocatesAdapter(List<AdvocateModel> advocateModelList, Context mContext) {
        this.advocateModelList = advocateModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdvocateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advocate_item_layout, parent, false);
        return new AdvocateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvocateViewHolder holder, int position) {
        holder.name.setText(advocateModelList.get(position).getName());
        holder.email.setText(advocateModelList.get(position).getEmail());
        holder.phone.setText(advocateModelList.get(position).getPhone_number());
    }

    @Override
    public int getItemCount() {
        return advocateModelList.size();
    }

    public class AdvocateViewHolder extends RecyclerView.ViewHolder{

        TextView name , phone , email;
        public AdvocateViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.advocateNameID);
            phone = itemView.findViewById(R.id.advocatePhoneNumberID);
            email = itemView.findViewById(R.id.advocateEmailId);
        }
    }

}
