package com.kselabs.alpha.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kselabs.alpha.R;
import com.kselabs.alpha.objects.CategoryItem;
import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

//This is the adapter for the Categories in the recycler view found in the Home fragment
public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {
    private static final String TAG = "GroupAdp";
    private Activity activity;
    private ArrayList<CategoryItem> arrayListGroup;
    private MemberAdp adapterMember;
    private Dialog popupDialog;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemAddClick(int position);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int gPosition) {
        popupDialog = new Dialog(activity);
        holder.tvName.setText(arrayListGroup.get(gPosition).getStrCatName());

        adapterMember = new MemberAdp(arrayListGroup.get(gPosition).getListItems());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        holder.rvMember.setLayoutManager(linearLayoutManager);
        holder.rvMember.setHasFixedSize(false);
        holder.rvMember.setAdapter(adapterMember);

        adapterMember.setOnItemClickListener(new MemberAdp.OnItemClickListener() {
            @Override
            public void onItemEditClick(int position) {
                showEditCategoryPopup(gPosition, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListGroup.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvTitle;
        RecyclerView rvMember;
        FloatingActionButton fabAddItem;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rvMember = itemView.findViewById(R.id.rv_member);
            fabAddItem = itemView.findViewById(R.id.fab_addItem);

            fabAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemAddClick(position);
                        }
                    }
                }
            });
        }
    }

    /**
     * responsible for displaying the popup for Editing items in a category
     *
     * @param gPosition Group position
     * @param position  Item position
     */
    private void showEditCategoryPopup(final int gPosition, final int position) {
        popupDialog.setContentView(R.layout.edit_category_popup);

        ImageView ivClose = popupDialog.findViewById(R.id.iv_close);
        Button bSave = popupDialog.findViewById(R.id.b_save);

        ImageView ivLogo = popupDialog.findViewById(R.id.iv_logo);
        final TextView etItemName = popupDialog.findViewById(R.id.et_item_name);
        final TextView etItemDescription = popupDialog.findViewById(R.id.et_item_description);
        final TextView etPrice = popupDialog.findViewById(R.id.et_price);

        etItemName.setText(arrayListGroup.get(gPosition).getListItems().get(position).getStrTitle());
        etItemDescription.setText(arrayListGroup.get(gPosition).getListItems().get(position).getStrTitle());
        etPrice.setText(String.valueOf(arrayListGroup.get(gPosition).getListItems().get(position).getDblPrice()));

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialog.dismiss();
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListGroup.get(gPosition).getListItems().get(position).setStrTitle(etItemName.getText().toString());
                arrayListGroup.get(gPosition).getListItems().get(position).setStrDescription(etItemDescription.getText().toString());
                arrayListGroup.get(gPosition).getListItems().get(position).setDblPrice(Double.parseDouble(etPrice.getText().toString()));

                Log.d(TAG, "onClick: ");

                adapterMember.notifyItemChanged(position);

                popupDialog.dismiss();
            }
        });

        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();
    }
}
