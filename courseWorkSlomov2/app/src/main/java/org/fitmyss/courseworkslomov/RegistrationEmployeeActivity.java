package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationEmployeeActivity extends AppCompatActivity {
    DBRegEmployee dbRegEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_employee);

        dbRegEmployee = new DBRegEmployee(this);

        Button btnAddEmployee = findViewById(R.id.buttonAddEmployee);
        Button btnBackEmployee = findViewById(R.id.backSignUpEmployee);

        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editLogin = findViewById(R.id.loginIdEmployee);
                EditText editPassword = findViewById(R.id.passwordIdEmployee);

                String login = editLogin.getText().toString();
                String password = editPassword.getText().toString();

                if (!login.isEmpty() && !password.isEmpty()) {
                    if (!dbRegEmployee.checkUserByEmail(login)) {
                        Data objDataAdd = new Data(login, password);
                        dbRegEmployee.addOne(objDataAdd);
                        Toast.makeText(RegistrationEmployeeActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistrationEmployeeActivity.this, "Аккаунт уже зарегистрирован", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationEmployeeActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChoice = new Intent(RegistrationEmployeeActivity.this, MainEmployeeActivity.class);
                startActivity(intentChoice);
            }
        });
    }
}

