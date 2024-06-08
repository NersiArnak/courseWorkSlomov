package org.fitmyss.courseworkslomov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PatientActivity extends AppCompatActivity {

    private EditText editFullName, editAge, editHouse, editDiseases, editAllergy, editHabits, editComplaints, editDoc, editDateTime, editReception;
    private DBDataPatient dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient);
        editFullName = findViewById(R.id.editFullName);
        editAge = findViewById(R.id.editAge);
        editHouse = findViewById(R.id.editHouse);
        editDiseases = findViewById(R.id.editDiseases);
        editAllergy = findViewById(R.id.editAllergy);
        editHabits = findViewById(R.id.editHabits);
        editComplaints = findViewById(R.id.editComplaints);
        editDoc = findViewById(R.id.editDoc);
        editDateTime = findViewById(R.id.editDateTime);
        editReception = findViewById(R.id.editReception);

        dbHelper = new DBDataPatient(this);

        Button buttonAdd = findViewById(R.id.btnAddPatient);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = editFullName.getText().toString();
                String age = editAge.getText().toString();
                String house = editHouse.getText().toString();
                String diseases = editDiseases.getText().toString();
                String allergy = editAllergy.getText().toString();
                String habits = editHabits.getText().toString();
                String complaints = editComplaints.getText().toString();
                String doc = editDoc.getText().toString();
                String dateTime = editDateTime.getText().toString();
                String reception = editReception.getText().toString();

                if (fullName.isEmpty() || age.isEmpty() || house.isEmpty() || diseases.isEmpty() || allergy.isEmpty() || habits.isEmpty() || complaints.isEmpty() || doc.isEmpty() || dateTime.isEmpty() || reception.isEmpty()) {
                    Toast.makeText(PatientActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    addPatientDataToDatabase(fullName, age, house, diseases, allergy, habits, complaints, doc, dateTime, reception);
                }
            }
        });

        Button buttonBack = findViewById(R.id.backPatientBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(PatientActivity.this, MainPatientActivity.class);
                startActivity(intentBack);
            }
        });
    }

    private void addPatientDataToDatabase(String fullName, String age, String house, String diseases, String allergy, String habits, String complaints, String doc, String dateTime, String reception) {
        long result = dbHelper.insertPatientData(fullName, Integer.parseInt(age), house, diseases, allergy, habits, complaints, doc, dateTime, reception);
        if (result != -1) {
            Toast.makeText(this, "Данные пациента добавлены успешно!", Toast.LENGTH_SHORT).show();
            editFullName.setText("");
            editAge.setText("");
            editHouse.setText("");
            editDiseases.setText("");
            editAllergy.setText("");
            editHabits.setText("");
            editComplaints.setText("");
            editDoc.setText("");
            editDateTime.setText("");
            editReception.setText("");
        } else {
            Toast.makeText(this, "Ошибка при добавлении данных пациента.", Toast.LENGTH_SHORT).show();
        }
    }
}
