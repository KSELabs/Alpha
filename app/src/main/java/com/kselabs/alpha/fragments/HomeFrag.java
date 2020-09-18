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

import com.kselabs.alpha.adapters.GroupAdp;
import com.kselabs.alpha.R;
import com.kselabs.alpha.objects.CategoryItem;
import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

public class HomeFrag extends Fragment {
    private Dialog popupDialog;
    private static final String TAG = "HomeFrag";
    private RecyclerView groups;
    private ArrayList<CategoryItem> arrayListGroup;
    private LinearLayoutManager layoutManager;
    private GroupAdp groupAdp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        popupDialog = new Dialog(getActivity());
        groups = v.findViewById(R.id.recycleView);
        arrayListGroup = new ArrayList<>();


        //Initialising ArrayList for each category
        for (int i = 1; i <= 2; i++) {
            ArrayList<ListItem> arrayListMember = new ArrayList<>();
            //Initialising ArrayList for each item
            for (int a = 1; a <= 3; a++) {
                arrayListMember.add(new ListItem("ListItem " + a + " Title", "ListItem Description ", 12.00));
            }
            arrayListGroup.add(new CategoryItem("CategoryItem " + i, arrayListMember));
        }

        groupAdp = new GroupAdp((Activity) getContext(), arrayListGroup);

        layoutManager = new LinearLayoutManager(getContext());

        groups.setLayoutManager(layoutManager);

        groups.setAdapter(groupAdp);

        groupAdp.setOnItemClickListener(new GroupAdp.OnItemClickListener() {
            @Override
            public void onItemAddClick(int position) {
                showEditCategoryPopup(position);
            }
        });
        return v;
    }

    /**
     * responsible for displaying the popup for adding new items in a category
     *
     * @param position  group position
     */
    private void showEditCategoryPopup(final int position) {
        popupDialog.setContentView(R.layout.edit_category_popup);

        ImageView ivClose = popupDialog.findViewById(R.id.iv_close);
        Button bSave = popupDialog.findViewById(R.id.b_save);

        ImageView ivLogo = popupDialog.findViewById(R.id.iv_logo);
        final TextView etItemName = popupDialog.findViewById(R.id.et_item_name);
        final TextView etItemDescription = popupDialog.findViewById(R.id.et_item_description);
        final TextView etPrice = popupDialog.findViewById(R.id.et_price);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialog.dismiss();
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListGroup.get(position).getListItems().add(new ListItem(etItemName.getText().toString(),
                        etItemDescription.getText().toString(), Double.parseDouble(etPrice.getText().toString())));
                groupAdp.notifyItemInserted(arrayListGroup.get(position).getListItems().size());
                popupDialog.dismiss();
            }
        });

        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();
    }
}
