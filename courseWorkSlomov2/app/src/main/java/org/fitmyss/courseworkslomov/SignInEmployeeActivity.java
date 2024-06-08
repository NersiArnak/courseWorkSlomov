package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignInEmployeeActivity extends AppCompatActivity {
    private Button buttonSignInE;
    private EditText editEmailE;
    private EditText editPasswordE;
    private DBRegEmployee dbRegEmployee;

    private Button buttonBackEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in_employee);

        dbRegEmployee = new DBRegEmployee(this);

        buttonSignInE = findViewById(R.id.buttonSignInE);
        editEmailE = findViewById(R.id.editEmailEmployee);
        editPasswordE = findViewById(R.id.editPasswordEmployee);
        buttonBackEmployee = findViewById(R.id.backSignUpEmployee);

        editPasswordE.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        buttonSignInE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmailE.getText().toString().trim();
                String password = editPasswordE.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInEmployeeActivity.this, "Введите почту и пароль", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbRegEmployee.checkUserByEmail(email)) {
                        if (dbRegEmployee.checkUserPassword(email, password)) {
                            Intent intent = new Intent(SignInEmployeeActivity.this, EmployeeActivity.class);
                            startActivity(intent); // Start the new activity
                        } else {
                            Toast.makeText(SignInEmployeeActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignInEmployeeActivity.this, "Сотрудник с такой почтой не найден", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonBackEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBackSignInEmployee = new Intent(SignInEmployeeActivity.this, MainEmployeeActivity.class);
                startActivity(intentBackSignInEmployee);
            }
        });
    }
}
