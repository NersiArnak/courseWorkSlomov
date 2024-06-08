package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private DBDataPatient dbDataPatient;
    private ListView patientListView;
    private EditText editTextId, editTextReception;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        dbDataPatient = new DBDataPatient(this);
        patientListView = findViewById(R.id.patientListView);
        editTextId = findViewById(R.id.editTextId);
        editTextReception = findViewById(R.id.editTextReception);

        Button buttonOpen = findViewById(R.id.button_open);
        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPatientData();
            }
        });

        Button buttonUpdate = findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReception();
            }
        });

        Button buttonBackEM = findViewById(R.id.backBackEM);
        buttonBackEM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBackEM = new Intent(EmployeeActivity.this, MainEmployeeActivity.class);
                startActivity(intentBackEM);

            }
        });
    }

    private void loadPatientData() {
        List<String> patients = dbDataPatient.getAllPatients();
        if (patients.isEmpty()) {
            Toast.makeText(this, "Нет данных для отображения", Toast.LENGTH_SHORT).show();
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patients);
            patientListView.setAdapter(adapter);
        }
    }

    private void updateReception() {
        String idStr = editTextId.getText().toString().trim();
        String newReception = editTextReception.getText().toString().trim();

        if (idStr.isEmpty() || newReception.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните оба поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);
        boolean result = dbDataPatient.updateReceptionById(id, newReception);

        if (result) {
            Toast.makeText(this, "Обновление успешно", Toast.LENGTH_SHORT).show();
            loadPatientData(); // Перезагрузка данных для отображения обновлений
        } else {
            Toast.makeText(this, "Ошибка обновления", Toast.LENGTH_SHORT).show();
        }
    }


}
