package com.example.fininfocom;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<ModelClass> modelClasses;

    public AdapterClass(ArrayList<ModelClass> modelClasses) {
        this.modelClasses = modelClasses;
    }

    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {


        holder.emailRecycler.setText(modelClasses.get(position).getEmailId());
        holder.mobileRecycler.setText(modelClasses.get(position).getMobileNumber());
    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView emailRecycler,mobileRecycler;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emailRecycler = itemView.findViewById(R.id.emailRecycler);
            mobileRecycler = itemView.findViewById(R.id.mobileRecycler);
        }
    }
}

