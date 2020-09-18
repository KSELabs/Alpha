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
    private static final String TAG = "HomeFrag";
    private RecyclerView groups;
    private ArrayList<CategoryItem> arrayListGroup;
    private LinearLayoutManager layoutManager;
    private GroupAdp groupAdp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        groups = v.findViewById(R.id.recycleView);
        arrayListGroup = new ArrayList<>();


        //Initialising Arraylist for each category
        for (int i = 1; i <= 2; i++) {
            ArrayList<ListItem> arrayListMember = new ArrayList<>();
            //Initialising Arraylist for each item
            for (int a = 1; a <= 1; a++) {
                arrayListMember.add(new ListItem("ListItem " + a + " Title", "ListItem Description ", 12.00));
            }
            arrayListGroup.add(new CategoryItem("CategoryItem " + i, arrayListMember));
        }

        groupAdp = new GroupAdp((Activity) getContext(), arrayListGroup);

        layoutManager = new LinearLayoutManager(getContext());

        groups.setLayoutManager(layoutManager);

        groups.setAdapter(groupAdp);
        return v;
    }
}
