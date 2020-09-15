package com.kselabs.alpha;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kselabs.alpha.objects.CategoryItem;
import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

//This is the adapter for the Categories in the recycler view found in the Home fragment
public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {
    private Activity activity;
    ArrayList<CategoryItem> arrayListGroup;

    public ArrayList<CategoryItem> getArrayListGroup() {
        return arrayListGroup;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public GroupAdp(Activity activity, ArrayList<CategoryItem> arrayListGroup) {
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

        ArrayList<ListItem> arrayListMember = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            arrayListMember.add(new ListItem("ListItem" + i + " Title", "ListItem Description ", 12.00));
        }
        getArrayListGroup().get(position).setListItems(arrayListMember);
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
        public ImageView iv_edit;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rvMember = itemView.findViewById(R.id.rv_member);
            iv_edit = itemView.findViewById(R.id.iv_edit);

            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
