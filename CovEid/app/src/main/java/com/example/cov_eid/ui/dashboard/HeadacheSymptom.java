package com.example.cov_eid.ui.dashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cov_eid.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class HeadacheSymptom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headache_symptom);
        getSupportActionBar().setTitle("Headache Symptom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
    }
}
