package com.kselabs.alpha;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {
    private Activity activity;
    ArrayList<String> arrayListGroup;


    public GroupAdp(Activity activity, ArrayList<String> arrayListGroup){
        this.activity = activity;
        this.arrayListGroup = arrayListGroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_group, parent, false);

        return new GroupAdp.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(arrayListGroup.get(position));
        ArrayList<String> arrayListMember = new ArrayList<>();
        for(int i = 1; i<=4; i++){
            arrayListMember.add("Item " + i);
        }
        MemberAdp adapterMember = new MemberAdp(arrayListMember);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        holder.rvMember.setLayoutManager(linearLayoutManager);

        holder.rvMember.setAdapter(adapterMember);

    }

    @Override
    public int getItemCount() {
        return arrayListGroup.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        RecyclerView rvMember;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            rvMember = itemView.findViewById(R.id.rv_member);
        }
    }
}
