package com.kselabs.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditActivity extends AppCompatActivity {
//This is the activity we want to callupon clicking on any of the items in the recycler view of the menu fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }
}
