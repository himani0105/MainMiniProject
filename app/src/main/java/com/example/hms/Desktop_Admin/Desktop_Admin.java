package com.example.hms.Desktop_Admin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hms.DatabaseHelper;
import com.example.hms.Personal_Info;
import com.example.hms.R;


public class Desktop_Admin extends AppCompatActivity {
    String username,password,user_type;
    DatabaseHelper dbh;
    TextView daname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop_admin);

        dbh = new DatabaseHelper(this);
        daname = (TextView) findViewById(R.id.tv_da_name);


        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");

        Cursor y = dbh.checkduplicates_in_user_credentials(username, password,getResources().getString(R.string.user_credentials));

        if (y.moveToFirst()) {
            String name = y.getString(1);
            daname.setText(name);
        }
    }

    public void onClick(View view){

        Intent i;
        Bundle b = new Bundle();
        b.putString("username",username);
        b.putString("password",password);
        b.putString("user_type",user_type);

        switch (view.getId())
        {
            case R.id.b_da_info:
                i = new Intent(Desktop_Admin.this, Personal_Info.class);
                break;
            case R.id.b_da_patient_appointment:
                i = new Intent(Desktop_Admin.this, Grant_appointment.class);
                break;
            case R.id.b_da_remaining_bills:
                i = new Intent(Desktop_Admin.this, Remaining_patient_bills.class);
                break;
            case R.id.b_da_assign:
                i = new Intent(Desktop_Admin.this, Assign_Staff.class);
                break;
            default:
                i = new Intent(Desktop_Admin.this, Delete_Users.class);
                break;
        }

        i.putExtras(b);
        startActivity(i);
    }
}


