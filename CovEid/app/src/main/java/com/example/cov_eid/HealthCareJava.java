package com.example.cov_eid;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HealthCareJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_care);
        getSupportActionBar().setTitle("Digital Health Care");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout doc2 = findViewById(R.id.health_doc2);
        LinearLayout docCall = findViewById(R.id.health_docCall);
        LinearLayout bookDoc = findViewById(R.id.health_bookDoc);
        LinearLayout encoremed = findViewById(R.id.health_encoremed);
        LinearLayout qualitasMed = findViewById(R.id.health_qualitasMed);
        LinearLayout queMed = findViewById(R.id.health_queMed);
        LinearLayout sunwayMed = findViewById(R.id.health_sunwayMed);


        doc2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.doc2us.com/covid-19-advisory"));
                startActivity(intent);
            }
        });

        docCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.doctoroncall.com.my/coronavirus"));
                startActivity(intent);
            }
        });

        bookDoc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bookdoc.com/search/?keyword=klinik"));
                startActivity(intent);
            }
        });

        encoremed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kkm.encoremed.io/#home/institutes"));
                startActivity(intent);
            }
        });

        qualitasMed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://qualitas.com.my"));
                startActivity(intent);
            }
        });

        queMed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://qmed.asia/booking"));
                startActivity(intent);
            }
        });

        sunwayMed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sunmedvelocity.com.my/en/"));
                startActivity(intent);
            }
        });

    }
    
}
