package com.kselabs.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditActivity extends AppCompatActivity {
//This is the activity we want to call upon clicking on any of the items in the recycler view of the Home fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }
}
