package com.kselabs.alpha.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kselabs.alpha.GroupAdp;
import com.kselabs.alpha.MemberEditAdpt;
import com.kselabs.alpha.R;
import com.kselabs.alpha.objects.CategoryItem;
import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

public class HomeFrag extends Fragment {
    private RecyclerView groups;
    private ArrayList<CategoryItem> arrayListGroup;
    private ArrayList<ListItem> itemsArrayListGroup;
    private LinearLayoutManager layoutManager;
    private GroupAdp groupAdp;
    private Dialog popupDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        popupDialog = new Dialog(getActivity());

        groups = v.findViewById(R.id.recycleView);
        arrayListGroup = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            arrayListGroup.add(new CategoryItem("CategoryItem " + i));
        }

        groupAdp = new GroupAdp((Activity) getContext(), arrayListGroup);

        layoutManager = new LinearLayoutManager(getContext());

        groups.setLayoutManager(layoutManager);

        groups.setAdapter(groupAdp);

        groupAdp.setOnItemClickListener(new GroupAdp.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showEditCategoryPopup(position);
            }
        });

        return v;
    }

    /**
     * responsible for displaying the popup for Editing items in a category
     *
     * @param position
     */
    private void showEditCategoryPopup(int position) {
        popupDialog.setContentView(R.layout.edit_category_popup);
        ImageView ivClose = popupDialog.findViewById(R.id.iv_close);
        TextView tvName = popupDialog.findViewById(R.id.tv_name);
        RecyclerView items = popupDialog.findViewById(R.id.rv_member);
        Button bSave = popupDialog.findViewById(R.id.b_save);
        FloatingActionButton fabAddItem = popupDialog.findViewById(R.id.fab_AddItem);

        LinearLayoutManager itemsLayoutManager;
        final MemberEditAdpt itemsAdp;
        itemsArrayListGroup = new ArrayList<>();

        tvName.setText(arrayListGroup.get(position).getStrCatName());
        for (int i = 1; i <= 10; i++) {
            itemsArrayListGroup = arrayListGroup.get(position).getListItems();     //Add all items from the category into the popup items
        }

        itemsAdp = new MemberEditAdpt(itemsArrayListGroup);
        itemsLayoutManager = new LinearLayoutManager(getContext());
        items.setLayoutManager(itemsLayoutManager);
        items.setAdapter(itemsAdp);

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewItem(new ListItem("", "", 0.0), itemsAdp);
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialog.dismiss();
            }
        });
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialog.dismiss();
            }
        });
        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();

    }

    private void AddNewItem(ListItem listItem, MemberEditAdpt itemsAdp) {
        itemsArrayListGroup.add(listItem);
        itemsAdp.notifyItemInserted(itemsArrayListGroup.size());
    }

}
