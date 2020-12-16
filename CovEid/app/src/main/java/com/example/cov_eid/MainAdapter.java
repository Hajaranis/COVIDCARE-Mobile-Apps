package com.example.cov_eid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<HomeModel> homeModel;
    Context context;
    RecyclerViewClickListener listener;

    public  MainAdapter(){}

    public MainAdapter(Context context, ArrayList<HomeModel> homeModel, RecyclerViewClickListener listener){
        this.context = context;
        this.homeModel = homeModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set logo to image view
        holder.imageView.setImageResource(homeModel.get(position).getLangLogo());
        //set name to textView
        holder.textView.setText(homeModel.get(position).getLangName());

    }

    @Override
    public int getItemCount() {

        return homeModel.size();
    }

    public interface RecyclerViewClickListener{

        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
