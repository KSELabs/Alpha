package com.kselabs.alpha;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kselabs.alpha.objects.Category;
import com.kselabs.alpha.objects.Item;

import java.util.ArrayList;

//This is the adapter for the Categories in the recycler view found in the Home fragment
public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {
    private Activity activity;
    ArrayList<Category> arrayListGroup;

    public ArrayList<Category> getArrayListGroup() {
        return arrayListGroup;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public GroupAdp(Activity activity, ArrayList<Category> arrayListGroup) {
        this.activity = activity;
        this.arrayListGroup = arrayListGroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_group, parent, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(arrayListGroup.get(position).getStrCatName());

        ArrayList<Item> arrayListMember = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            arrayListMember.add(new Item("Item Description " ,12.00, "Item" + i +" Title"));
        }
        getArrayListGroup().get(position).setItems(arrayListMember);
        MemberAdp adapterMember = new MemberAdp(arrayListMember);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        holder.rvMember.setLayoutManager(linearLayoutManager);

        holder.rvMember.setAdapter(adapterMember);


    }

    @Override
    public int getItemCount() {
        return arrayListGroup.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvTitle;
        RecyclerView rvMember;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTitle = itemView.findViewById(R.id.tv_name2);
            rvMember = itemView.findViewById(R.id.rv_member);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
