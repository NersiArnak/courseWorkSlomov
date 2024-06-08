package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationPatientActivity extends AppCompatActivity {

    DBRegPatient dbRegPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_patient);

        dbRegPatient = new DBRegPatient(this);

        Button btnAddPatient = findViewById(R.id.buttonAddPatient);
        Button btnBackPatient = findViewById(R.id.backSignUpPatient);

        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editLogin = findViewById(R.id.loginIDPatient);
                EditText editPassword = findViewById(R.id.passwordIdPatient);

                String login = editLogin.getText().toString();
                String password = editPassword.getText().toString();

                if (!login.isEmpty() && !password.isEmpty()) {
                    if (!dbRegPatient.checkUserByEmail(login)) {
                        Data objDataAdd = new Data(login, password);
                        dbRegPatient.addOne(objDataAdd);
                        Toast.makeText(RegistrationPatientActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistrationPatientActivity.this, "Аккаунт уже зарегистрирован", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationPatientActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChoice = new Intent(RegistrationPatientActivity.this, MainPatientActivity.class);
                startActivity(intentChoice);
            }
        });
    }
}
