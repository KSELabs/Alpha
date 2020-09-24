package com.kselabs.alpha.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.app.Activity.RESULT_OK;
import static com.kselabs.alpha.util.Util.IMAGE_PICK_CODE;
import static com.kselabs.alpha.util.Util.PERMISSION_CODE;

public class HomeFrag extends Fragment {
    private Dialog popupDialog;
    private static final String TAG = "HomeFrag";
    private RecyclerView groups;
    private ArrayList<CategoryItem> arrayListGroup;
    private LinearLayoutManager layoutManager;
    private GroupAdp groupAdp;

    private Uri tempImage = null;
    private ListItem tempItem;

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
                arrayListMember.add(new ListItem(tempImage, "List Item " + a + " Title", "ListItem Description ", 12.00,
                        arrayListMember.size() - 1));
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
                showNewItemPopup(position);
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
    private void showNewItemPopup(final int position) {
        popupDialog.setContentView(R.layout.edit_category_popup);

        ImageView ivClose = popupDialog.findViewById(R.id.iv_close);
        Button bSave = popupDialog.findViewById(R.id.b_save);

        ImageView ivLogo = popupDialog.findViewById(R.id.iv_logo);
        final TextView etItemName = popupDialog.findViewById(R.id.et_item_name);
        final TextView etItemDescription = popupDialog.findViewById(R.id.et_item_description);
        final TextView etPrice = popupDialog.findViewById(R.id.et_price);

        if (tempImage != null) {
            ivLogo.setImageURI(tempImage);
        }

        if (tempItem != null) {
            etItemName.setText(tempItem.getStrTitle());
            etItemDescription.setText(tempItem.getStrDescription());
            etPrice.setText(String.valueOf(tempItem.getDblPrice()));
        }

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialog.dismiss();
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryItem categoryItem = arrayListGroup.get(position);
                String itemName = " ";
                String itemDescription = " ";
                double itemPrice = 0.0;

                if (!etItemName.getText().toString().isEmpty()) {
                    itemName = etItemName.getText().toString();
                }
                if (!etItemDescription.getText().toString().isEmpty()) {
                    itemDescription = etItemDescription.getText().toString();
                }
                if (!etPrice.getText().toString().isEmpty()) {
                    itemPrice = Double.parseDouble(etPrice.getText().toString());
                }
                categoryItem.getListItems().add(new ListItem(tempImage, itemName, itemDescription, itemPrice, position));
                arrayListGroup.get(position).getMemberAdp().notifyItemInserted(arrayListGroup.get(position).getListItems().size());
                tempImage = null;
                popupDialog.dismiss();
            }
        });

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = " ";
                String itemDescription = " ";
                double itemPrice = 0.0;

                if (!etItemName.getText().toString().isEmpty()) {
                    itemName = etItemName.getText().toString();
                }
                if (!etItemDescription.getText().toString().isEmpty()) {
                    itemDescription = etItemDescription.getText().toString();
                }
                if (!etPrice.getText().toString().isEmpty()) {
                    itemPrice = Double.parseDouble(etPrice.getText().toString());
                }

                tempItem = new ListItem(tempImage, itemName, itemDescription, itemPrice, position);
                //Check runtime permissions
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //Permissions not granted
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions, PERMISSION_CODE);


                    } else { //Permissions already granted

                        PickImageFromGallery();

                    }
                } else {     //os < Marshmallow
                    PickImageFromGallery();
                }
            }
        });

        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();
    }

    /**
     * Open intent to pick image from gallery
     */
    private void PickImageFromGallery() {
/*        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);*/
        CropImage.activity()
                .setAspectRatio(1, 1)
                .setRequestedSize(200, 250)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                PickImageFromGallery();
            } else {
                Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult: Called");
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            Log.d(TAG, "onActivityResult: Image found");
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                tempImage = result.getUri();
                showNewItemPopup(tempItem.getIntPosition());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

                Log.d(TAG, "onActivityResult: " + error);
            }
        }
    }
}
