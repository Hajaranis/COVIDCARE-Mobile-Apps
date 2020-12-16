package com.example.cov_eid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdditionalResource extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_resources);
        getSupportActionBar().setTitle("Additional Resources");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout call = findViewById(R.id.card_call);
        LinearLayout smoke = findViewById(R.id.card_smoke);
        LinearLayout sop = findViewById(R.id.card_sop);

        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+60388810200"));
                startActivity(intent);
            }
        });

        smoke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jomquit.com"));
                startActivity(intent);
            }
        });

        sop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mysejahtera.malaysia.gov.my/register/sop"));
                startActivity(intent);;
            }
        });
    }


}
