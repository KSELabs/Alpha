package com.kselabs.alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

//This is the adapter for the items in the recycler view found in the Home fragment
public class MemberEditAdp extends RecyclerView.Adapter<MemberEditAdp.ViewHolder> {


    ArrayList<ListItem> arrayListMember;

    public MemberEditAdp(ArrayList<ListItem> arrayListMember){
        this.arrayListMember = arrayListMember;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_row_edit_member, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.etItemDescription.setText(arrayListMember.get(position).getStrDescription());
        holder.etPrice.setText(String.valueOf(arrayListMember.get(position).getDblPrice()));
        holder.etTitle.setText(String.valueOf(arrayListMember.get(position).getStrTitle()));

    }

    @Override
    public int getItemCount() {
        return arrayListMember.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText etItemDescription ,etPrice, etTitle;
        ImageView ivLogo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etItemDescription = itemView.findViewById(R.id.et_item_description);
            etPrice = itemView.findViewById(R.id.et_price);
            ivLogo = itemView.findViewById(R.id.iv_logo);
            etTitle = itemView.findViewById(R.id.et_item_title);
        }
    }
}
