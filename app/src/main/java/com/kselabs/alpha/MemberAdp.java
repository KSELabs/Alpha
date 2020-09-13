package com.kselabs.alpha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kselabs.alpha.objects.Item;

import java.util.ArrayList;
//This is the adapter for the items in the recycler view found in the Home fragment
public class MemberAdp extends RecyclerView.Adapter<MemberAdp.ViewHolder> {


    ArrayList<Item> arrayListMember;

    public MemberAdp(ArrayList<Item> arrayListMember){
        this.arrayListMember = arrayListMember;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_row_member, parent,false);
        return new MemberAdp.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(arrayListMember.get(position).getStrDescription());
        holder.tvName.setText(arrayListMember.get(position).getStrDescription());

    }

    @Override
    public int getItemCount() {
        return arrayListMember.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTitle = itemView.findViewById(R.id.tv_name);
        }
    }
}
