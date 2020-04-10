package com.example.recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Data>  DataList = new ArrayList<Data>();
    RecyclerViewAdapter adapter = new RecyclerViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_data);
        adapter.MyData = DataList;
    }

        void initViews() {
            rv =findViewById(R.id.recyclerView);
            rv.setAdapter(adapter);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, AddDataActivity.class);
                    startActivityForResult(i, 2);
                }
            });
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            DataList.add((Data) data.getSerializableExtra("contact"));
            adapter.notifyDataSetChanged();
        }
    }
    }

