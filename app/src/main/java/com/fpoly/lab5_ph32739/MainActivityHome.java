package com.fpoly.lab5_ph32739;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public  static  String KEY_STUDENT = "student";
    ArrayList<Student> arrayList = new ArrayList<>();
    ListStudentAdapter listStudentAdapter;
    SearchView searchView;
    Student updateStudent;

    ListView listView;
    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                       Intent intent = result.getData();
                       Bundle bundle = intent.getExtras();
                       String studyAt = bundle.getString(MainActivity.KEY_SEND_PLACE);
                       String name = bundle.getString(MainActivity.KEY_SEND_NAME);
                       String home = bundle.getString(MainActivity.KEY_SEND_HOME);
                       Student student = new Student(studyAt,name,home);
                        arrayList.add(student);
                        setChangeDataInArrayAdt();

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview_student);

        arrayList.add(new Student("Fpoly Hà Nội", "Nguyễn Việt Anh", "Hưng Yên"));
        arrayList.add(new Student("Fpoly Hồ Chí Minh", "Nguyễn Tuấn Anh", "Bạc Liêu"));
        arrayList.add(new Student("Fpoly Tây Nguyên", "Nguyễn Minh Anh", "Gia Lai"));
        arrayList.add(new Student("Fpoly Đà Nẵng", "Nguyễn Bảo Anh", "Đà Nẵng"));
        arrayList.add(new Student("Fpoly Hải Phòng", "Nguyễn Lâm Anh", "Hải Phòng"));
        setChangeDataInArrayAdt();





    }

    private void setChangeDataInArrayAdt() {
        listStudentAdapter = new ListStudentAdapter(this, arrayList);
        listView.setAdapter(listStudentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listStudentAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listStudentAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.add_person) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            getData.launch(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<Intent> update = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();
                        String studyAt = bundle.getString(MainActivity.KEY_SEND_PLACE);
                        String name = bundle.getString(MainActivity.KEY_SEND_NAME);
                        String home = bundle.getString(MainActivity.KEY_SEND_HOME);

                        updateStudent.setNameStd(name);
                        updateStudent.setStudyAt(studyAt);
                        updateStudent.setHometown(home);

                        setChangeDataInArrayAdt();

                    }
                }
            }
    );
    public void setUpdateStudent(int position){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        updateStudent = arrayList.get(position);
       intent.putExtra(KEY_STUDENT,updateStudent);
        update.launch(intent);
    }
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }

        super.onBackPressed();
    }
}