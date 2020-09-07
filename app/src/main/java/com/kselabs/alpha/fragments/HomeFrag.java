package com.kselabs.alpha.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kselabs.alpha.GroupAdp;
import com.kselabs.alpha.R;

import java.util.ArrayList;

public class HomeFrag extends Fragment {
    RecyclerView groups;
    ArrayList<String> arrayListGroup;
    LinearLayoutManager layoutManager;
    GroupAdp groupAdp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container,false);
        groups = v.findViewById(R.id.recycleView);
        arrayListGroup = new ArrayList<>();
        for(int i =1; i<=10; i++){
            arrayListGroup.add("Category " + i);
        }

        groupAdp = new GroupAdp((Activity) getContext(), arrayListGroup);

        layoutManager = new LinearLayoutManager(getContext());

        groups.setLayoutManager(layoutManager);

        groups.setAdapter(groupAdp);

        return v;
    }

}
