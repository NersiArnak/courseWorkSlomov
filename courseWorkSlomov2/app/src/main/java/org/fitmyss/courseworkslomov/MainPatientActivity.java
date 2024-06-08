package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_patient);

        Button buttonBackMainPatient = findViewById(R.id.buttonBackPatient);
        Button buttonRegSignUpPatient = findViewById(R.id.buttonSignUpPatient);
        Button buttonSignItPatient = findViewById(R.id.buttonSignInPatient);

        buttonBackMainPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBackMain = new Intent(MainPatientActivity.this, ChoiceActivity.class);
                startActivity(intentBackMain);
            }
        });

        buttonRegSignUpPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegSignUpPatient = new Intent(MainPatientActivity.this, RegistrationPatientActivity.class);
                startActivity(intentRegSignUpPatient);
            }
        });

        buttonSignItPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignInPatient = new Intent(MainPatientActivity.this, SignInPatientActivity.class);
                startActivity(intentSignInPatient);
            }
        });

    }
}