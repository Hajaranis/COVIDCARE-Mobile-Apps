package com.example.cov_eid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class RiskStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.risk_status);
        getSupportActionBar().setTitle("Risk Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button start = findViewById(R.id.btn_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RiskQuestionnaire.class);
                startActivity(intent);
            }
        });
    }
}
