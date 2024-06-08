package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainEmployeeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_employee);

        Button buttonEmployee = findViewById(R.id.buttonBackEmployee);
        Button buttonRegSignUpEmployee = findViewById(R.id.buttonSignUpEmployee);
        Button buttonSignItEmployee = findViewById(R.id.buttonSignInEmployee);

        buttonEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChoice = new Intent(MainEmployeeActivity.this, ChoiceActivity.class);
                startActivity(intentChoice);
            }
        });

        buttonRegSignUpEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegSignUpEmployee = new Intent(MainEmployeeActivity.this, RegistrationEmployeeActivity.class);
                startActivity(intentRegSignUpEmployee);
            }
        });

        buttonSignItEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignInEmployee = new Intent(MainEmployeeActivity.this, SignInEmployeeActivity.class);
                startActivity(intentSignInEmployee);
            }
        });

    }
}

