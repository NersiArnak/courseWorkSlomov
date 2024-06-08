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

public class SignInPatientActivity extends AppCompatActivity {

    private Button buttonSignInP;
    private EditText editEmailP;
    private EditText editPasswordP;
    private DBRegPatient dbRegPatient;

    private Button buttonBackPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in_patient);

        dbRegPatient = new DBRegPatient(this);

        buttonSignInP = findViewById(R.id.buttonSignInP);
        editEmailP = findViewById(R.id.editEmailPatient);
        editPasswordP = findViewById(R.id.editPasswordPatient);
        buttonBackPatient = findViewById(R.id.backSignUpPatient); // Initialize buttonBackPatient

        editPasswordP.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        buttonSignInP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmailP.getText().toString().trim();
                String password = editPasswordP.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInPatientActivity.this, "Введите почту и пароль", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbRegPatient.checkUserByEmail(email)) {
                        if (dbRegPatient.checkUserPassword(email, password)) {
                            Intent intent = new Intent(SignInPatientActivity.this, PatientActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInPatientActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignInPatientActivity.this, "Пользователь с такой почтой не найден", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonBackPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBackSignInPatient = new Intent(SignInPatientActivity.this, MainPatientActivity.class);
                startActivity(intentBackSignInPatient);
            }
        });
    }
}
