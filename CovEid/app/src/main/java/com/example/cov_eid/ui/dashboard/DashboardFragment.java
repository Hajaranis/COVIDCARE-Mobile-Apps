package com.example.cov_eid.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cov_eid.AdditionalResource;
import com.example.cov_eid.HealthCareJava;
import com.example.cov_eid.HealthFacility;
import com.example.cov_eid.HomeModel;
import com.example.cov_eid.MainAdapter;
import com.example.cov_eid.R;
import com.example.cov_eid.preventModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<HomeModel> preventModels;
    MainAdapter mainAdapter;
    MainAdapter.RecyclerViewClickListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate((R.layout.fragment_dashboard), container, false);
        //return inflater.inflate((R.layout.fragment_dashboard), container, false);

        recyclerView = view.findViewById(R.id.home_recycler);

        LinearLayout washCard = view.findViewById(R.id.wash_card);
        LinearLayout faceMaskCard = view.findViewById(R.id.face_mask_card);
        LinearLayout crowdedPlaceCard = view.findViewById(R.id.crowded_place_card);

        Integer[] langLogo = {R.drawable.fever, R.drawable.caugh, R.drawable.headache};

        String[] langName = {"Fever", "Cough", "Headache"};

        //initialize arraylist
        preventModels = new ArrayList<>();
        for(int i=0; i < langLogo.length; i++){
            HomeModel model = new HomeModel(langLogo[i], langName[i]);
            preventModels.add(model);

        }


        //Design horizontal layout
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setOnClickListener();

        washCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WashHand.class);
                startActivity(intent);

            }
        });

        faceMaskCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FaceMask.class);
                startActivity(intent);

            }
        });

        crowdedPlaceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CrowdedPlace.class);
                startActivity(intent);

            }
        });

        //initialize mainadapter
        //mainAdapter  = new MainAdapter(getActivity(), homeModel);
        mainAdapter = new MainAdapter(getActivity(), preventModels, listener);
        //set mainadapter to recycler view
        recyclerView.setAdapter(mainAdapter);
        return view ;
    }

    private void setOnClickListener(){
        listener = new MainAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(preventModels.get(position).getLangName() == "Fever"){
                    Intent intent = new Intent(getActivity(), FeverSymptom.class);
                    startActivity(intent);
                }else if(preventModels.get(position).getLangName() == "Cough"){
                    Intent intent = new Intent(getActivity(), CoughSymptom.class);
                    startActivity(intent);
                }else if(preventModels.get(position).getLangName() == "Headache"){
                    Intent intent = new Intent(getActivity(), HeadacheSymptom.class);
                    startActivity(intent);
                }

            }
        };
    }

    /*private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/

}