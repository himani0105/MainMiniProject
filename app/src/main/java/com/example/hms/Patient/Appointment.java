package com.example.hms.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hms.Doctor.Specialization;
import com.example.hms.Personal_Info;
import com.example.hms.R;


public class Appointment extends AppCompatActivity {

    String username,password,user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);

        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");
    }

    public void onClick(View view){

        Intent i;
        Bundle b = new Bundle();
        b.putString("username",username);
        b.putString("password",password);
        b.putString("user_type",user_type);

        switch (view.getId())
        {
            case R.id.b_new:
                i = new Intent(Appointment.this, New_Appointment.class);
                break;
           /* case R.id.b_wait:
                i = new Intent(Appointment.this, Wait_Appointment.class);
                break;*/
            default:
                i = new Intent(Appointment.this, Confirmed_Appointment.class);
                break;
        }
        i.putExtras(b);
        startActivity(i);
    }

}
