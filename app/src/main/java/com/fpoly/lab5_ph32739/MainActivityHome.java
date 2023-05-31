package com.fpoly.lab5_ph32739;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivityHome extends AppCompatActivity {

    public static ArrayList<Student> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        ListView listView = findViewById(R.id.listview_student);
        String getPlace = getIntent().getStringExtra(MainActivity.KEY_PLACE);
        String getUser = getIntent().getStringExtra(MainActivity.KEY_USERNAME);
        String getAddress = getIntent().getStringExtra(MainActivity.KEY_HOMETOWN);

        Student student = new Student(getPlace, getUser, getAddress);
        if (MainActivity.KEY_SUBMIT.equals("submit")) {
            arrayList.add(student);
        }


        ListStudentAdapter listStudentAdapter = new ListStudentAdapter(this, arrayList);
        listView.setAdapter(listStudentAdapter);

        Button btnAddNew = findViewById(R.id.btn_addTpList);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        String updatePlace = getIntent().getStringExtra("new_place");
        String updateName = getIntent().getStringExtra("new_name");
        String updateHometown = getIntent().getStringExtra("new_hometown");
        Student studentNew = new Student(updatePlace, updateName, updateHometown);
//        if (MainActivity.BUTTON_UPDATE_CLICK.equals("clicked")) {
//            for (int i = 0; i < arrayList.size(); i++) {
//
////                arrayList.set(i, studentNew);
//            }
//        }
    }

}