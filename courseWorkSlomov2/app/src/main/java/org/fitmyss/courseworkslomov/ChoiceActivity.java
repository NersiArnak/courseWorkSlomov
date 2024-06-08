package org.fitmyss.courseworkslomov;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choice);

        Button buttonChoicePatient = findViewById(R.id.buttonPatient);
        Button buttonChoiceEmplyee = findViewById(R.id.buttonEmployee);

        buttonChoicePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChoice = new Intent(ChoiceActivity.this, MainPatientActivity.class);
                startActivity(intentChoice);
            }
        });

        buttonChoiceEmplyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChoice = new Intent(ChoiceActivity.this, MainEmployeeActivity.class);
                startActivity(intentChoice);
            }
        });

    }
}

