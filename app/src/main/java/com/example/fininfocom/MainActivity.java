package com.example.fininfocom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText emailId, mobileNumber;
    Button submit;
    RecyclerView recyclerView1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<ModelClass> aList = new ArrayList<>();
    AdapterClass adapterClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailId = findViewById(R.id.emaiId);
        mobileNumber = findViewById(R.id.mobileNumber);
        submit = findViewById(R.id.submit);
        recyclerView1 = findViewById(R.id.recyclerView1);
        loadData();
        buildRecyclerView();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobileNumber.getText().toString().length() == 0 || emailId.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Mobile or EmailID", Toast.LENGTH_SHORT).show();
                } else {

                    boolean exists = false;
                    for (int i = 0; i < aList.size(); i++) {


                            if (aList.get(i).getMobileNumber().equalsIgnoreCase(mobileNumber.getText().toString())||aList.get(i).getEmailId().equalsIgnoreCase(emailId.getText().toString())) {
                                exists = true;
                                break;
                            }

                    }

                    if (exists) {
                        Toast.makeText(MainActivity.this, "Mobile or Email Already Exists", Toast.LENGTH_SHORT).show();
                    } else {


                        aList.add(new ModelClass(emailId.getText().toString(), mobileNumber.getText().toString()));
                        adapterClass.notifyItemInserted(aList.size());
                        SharedPreferences sharedPreferences = getSharedPreferences("t3", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(aList);
                        editor.putString("task list", json);
                        editor.apply();
                    }

                }
            }
        });
    }

    private void buildRecyclerView() {

        adapterClass = new AdapterClass(aList);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView1.setAdapter(adapterClass);

    }

    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("t3", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {
        }.getType();
        aList = gson.fromJson(json, type);

        if (aList == null) {
            aList = new ArrayList<>();
        }


    }
}