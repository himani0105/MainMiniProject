package com.example.hms;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hms.Doctor.D_Slot;
import com.example.hms.Doctor.Doctor_Patient.Report_Upload;
import com.example.hms.Doctor.Leaves.Leaves;
import com.example.hms.Doctor.Specialization;
import com.example.hms.Doctor.View_Assigned_Staff;

import java.util.ArrayList;


public class Feedback extends AppCompatActivity {

    ListView lv_feed;
    EditText et_feed;
    String username, password, user_type, tmp;
    ArrayList<String> feedback = new ArrayList<>();

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");

        lv_feed = (ListView) findViewById(R.id.lv_feedback);
        et_feed = (EditText) findViewById(R.id.et_feedback);


        Cursor y = db.checkduplicates_in_user_credentials(username, password, "FEEDBACK");

        if (y.moveToFirst()) {
            while (true) {
                feedback.add(y.getString(2));

                if (y.isLast())
                    break;
                y.moveToNext();
            }

            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feedback);
            lv_feed.setAdapter(adapter);
        }

    }

    public void onClick(View view) {
        tmp = et_feed.getText().toString();
        if (tmp.length() == 0) {
            Message.message(Feedback.this, "Please type your feedback");
        } else {
            boolean b = db.insert_feedback(username, password, tmp);

            if (b) {
                Message.message(Feedback.this, "Feedback Submitted");
                finish();
            } else {
                Message.message(Feedback.this, "Feedback cannot be inserted");
            }
        }
    }
}