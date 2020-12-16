package com.example.cov_eid.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.cov_eid.MainActivity;
import com.example.cov_eid.MainAdapter;
import com.example.cov_eid.R;
import com.example.cov_eid.RiskStatus;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<HomeModel> homeModel = null;
    MainAdapter mainAdapter;
    MainAdapter.RecyclerViewClickListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate((R.layout.fragment_home), container, false);
        recyclerView = view.findViewById(R.id.home_recycler);

        Integer[] langLogo = {R.drawable.hospital, R.drawable.study, R.drawable.doctor, R.drawable.risk};

        String[] langName = {"Locate \nHealth \nScreening \nFacility", "Digital \nHealth \nCare", "Additional \nResources", "Risk \nStatus"};

        //initialize arraylist
        homeModel = new ArrayList<>();
        for(int i=0; i < langLogo.length; i++){
            HomeModel model = new HomeModel(langLogo[i], langName[i]);
            homeModel.add(model);

        }

        //Design horizontal layout
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initialize mainadapter
        setOnClickListener();
        //mainAdapter  = new MainAdapter(getActivity(), homeModel, listener);
        mainAdapter = new MainAdapter(getActivity(), homeModel, listener);
        //set mainadapter to recycler view
        recyclerView.setAdapter(mainAdapter);
        return view ;
    }

    private void setOnClickListener(){
        listener = new MainAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(homeModel.get(position).getLangName() == "Digital \nHealth \nCare"){
                    Intent intent = new Intent(getActivity(), HealthCareJava.class);
                    startActivity(intent);
                }else if(homeModel.get(position).getLangName() == "Additional \nResources"){
                    Intent intent = new Intent(getActivity(), AdditionalResource.class);
                    startActivity(intent);
                }else if(homeModel.get(position).getLangName() == "Locate \nHealth \nScreening \nFacility"){
                    Intent intent = new Intent(getActivity(), HealthFacility.class);
                    startActivity(intent);
                }else if(homeModel.get(position).getLangName() == "Risk \nStatus") {
                    Intent intent = new Intent(getActivity(), RiskStatus.class);
                    startActivity(intent);
                }

            }
        };
    }

    /*private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/
}