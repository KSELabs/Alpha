package com.kselabs.alpha.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Canvas;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.kselabs.alpha.adapters.GroupAdp;
import com.kselabs.alpha.R;
import com.kselabs.alpha.objects.CategoryItem;
import com.kselabs.alpha.objects.ListItem;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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
                arrayListMember.add(new ListItem("List Item " + a + " Title", "ListItem Description ", 12.00));
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(groups);

        return v;
    }

    private CategoryItem deletedCatItem = null;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            deletedCatItem = arrayListGroup.get(position);
            arrayListGroup.remove(position);
            groupAdp.notifyItemRemoved(position);
            Snackbar.make(groups, deletedCatItem.getStrCatName() + " deleted", BaseTransientBottomBar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            arrayListGroup.add(position, deletedCatItem);
                            groupAdp.notifyItemInserted(position);

                        }
                    }).show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getActivity(), R.color.Red))
                    .addSwipeLeftActionIcon(R.drawable.delete_icon)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    /**
     * responsible for displaying the popup for adding new items in a category
     *
     * @param position group position
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
