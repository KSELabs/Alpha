package com.kselabs.alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

//This is the adapter for the items in the recycler view found in the Home fragment
public class MemberAdp extends RecyclerView.Adapter<MemberAdp.ViewHolder> {

    ArrayList<ListItem> arrayListMember;

    public MemberAdp(ArrayList<ListItem> arrayListMember) {
        this.arrayListMember = arrayListMember;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_row_member, parent, false);
        return new MemberAdp.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(arrayListMember.get(position).getStrTitle());
        holder.tvDescription.setText(arrayListMember.get(position).getStrDescription());
        holder.tvPrice.setText(String.valueOf(arrayListMember.get(position).getDblPrice()));

    }

    @Override
    public int getItemCount() {
        return arrayListMember.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDescription, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
