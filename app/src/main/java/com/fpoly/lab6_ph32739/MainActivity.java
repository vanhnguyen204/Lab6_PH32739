package com.fpoly.lab6_ph32739;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fpoly.lab5_ph32739.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String KEY_SEND_STUDENT= "SEND_DATA";
    public static String KEY_SEND_NAME= "SEND_NAME";
    public static String KEY_SEND_HOME= "SEND_HOME";
    public static String KEY_SEND_PLACE= "SEND_PLACE";

    String place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spn = findViewById(R.id.spinner_list);
        ArrayList<PlacesModel> arrayList = new ArrayList<>();
        arrayList.add(new PlacesModel(R.mipmap.img1, "Fpoly Hà Nội"));
        arrayList.add(new PlacesModel(R.mipmap.img2, "Fpoly Đà Nẵng"));
        arrayList.add(new PlacesModel(R.mipmap.img4, "Fpoly Tây Nguyên"));
        arrayList.add(new PlacesModel(R.mipmap.img3, "Fpoly Hồ Chí Minh"));
        arrayList.add(new PlacesModel(R.mipmap.img5, "Fpoly Cần Thơ"));

        ChoosePlaces choosePlaces = new ChoosePlaces(this, arrayList);
        spn.setAdapter(choosePlaces);


        EditText edtUsername = findViewById(R.id.edt_name);
        EditText edtAddress = findViewById(R.id.edt_address);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = arrayList.get(position).getNamePlace();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = arrayList.get(position).getNamePlace();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getUser = edtUsername.getText().toString();
                String getAddress = edtAddress.getText().toString();

                if (getUser.length() == 0 || getUser.trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn phải nhập tên để thêm !", Toast.LENGTH_SHORT).show();
                } else if (getAddress.length() == 0 || getAddress.trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn phải nhập quê quán để thêm !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                   bundle.putString(KEY_SEND_NAME,getUser);
                   bundle.putString(KEY_SEND_HOME,getAddress);
                   bundle.putString(KEY_SEND_PLACE,place);
                    intent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
        Student updatePositionStudent =  (Student) getIntent().getSerializableExtra(MainActivityHome.KEY_STUDENT);
        if (updatePositionStudent!= null){

            edtUsername.setText(updatePositionStudent.getNameStd());
            edtAddress.setText(updatePositionStudent.getHometown());
            int position = arrayList.indexOf(updatePositionStudent.getStudyAt());
            spn.setSelection(position);
        }



    }


}