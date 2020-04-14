package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
final int EDIT_REQUEST_CODE=1 ;
    RecyclerView rv;

  ArrayList<Data> DataList = new ArrayList<Data>();

    DiffUtil.ItemCallback<Data> diffCallback =new DiffUtil.ItemCallback<Data>() {
        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return  oldItem.getNumber().equals(newItem.getNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getNumber().equals(newItem.getNumber());
        }
    };


//    override of method(Clicked) which in interface(OnContactClicked)
    RecyclerViewAdapter.OnContactClicked o = new RecyclerViewAdapter.OnContactClicked() {
    @Override
    public void Clicked(Data data, int position) {
        Intent intent = new Intent(MainActivity.this ,AddDataActivity.class);
        intent.putExtra("Receive" ,data);
        intent.putExtra("pos" ,position);
        startActivityForResult(intent ,EDIT_REQUEST_CODE );
    }

    @Override
    public void delete(Data data) {
DataList.remove(data);
ArrayList<Data> list = new ArrayList<>(DataList);
adapter.submitList(list);
    }


};
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(o);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    void initViews() {
        rv = findViewById(R.id.recyclerView);
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
            DataList.add((Data) data.getSerializableExtra("Receive"));
            ArrayList<Data> list= new ArrayList<>(DataList);
            adapter.submitList(list);
        }
        else if (requestCode == EDIT_REQUEST_CODE &&resultCode ==Activity.RESULT_OK){
            DataList.set(data.getIntExtra("pos", 0),(Data) data.getSerializableExtra("Receive"));
            ArrayList<Data> list= new ArrayList<>(DataList);
            adapter.submitList(list);

        }
    }
}

