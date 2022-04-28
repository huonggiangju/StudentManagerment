package com.example.studentmanagerment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name, course, email, url;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        //actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Student");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        name = (EditText) findViewById(R.id.etName);
        course = (EditText) findViewById(R.id.etCourse);
        email = (EditText) findViewById(R.id.etEmail);
        url = (EditText) findViewById(R.id.etImg);

        addBtn =  (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //insert data
    private void insertData(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("course", course.getText().toString());
        map.put("email", email.getText().toString());
        map.put("surl", url.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("student").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data insert successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Failed insertion ", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //clear text view
    private void clearAll(){
        name.setText("");
        course.setText("");
        email.setText("");
        url.setText("");
    }
}