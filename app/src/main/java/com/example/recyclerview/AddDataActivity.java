package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddDataActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);
        initView();
    }

    void initView() {
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = CreatData(etName.getText().toString(), etNumber.getText().toString());
                if (data != null) {
                    Intent intent = new Intent();
                    intent.putExtra("Data", data);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
        }
        });

}
    private Data CreatData(String name, String number) {
        boolean invalid = false;
        if (name == null || name.isEmpty()) {
            invalid = true;
            etName.setError("لا يمكن اضافه مستحدم بدون اسم");
        }
        if (number == null || number.isEmpty()) {
            invalid = true;
            etNumber.setError("لا يمكن اضافه مستحدم بدون رقم ");
        }
        if (invalid) return null;
          else   return new Data(name, number);

        }

    }


