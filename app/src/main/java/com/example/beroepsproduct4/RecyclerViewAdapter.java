package com.example.beroepsproduct4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String>  persoonsnamen = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> persoonsnamen) {
        this.persoonsnamen = persoonsnamen;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.persoonsNaam.setText(persoonsnamen.get(i));

        viewHolder.recyclerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent open informatie over de persoon
            }
        });

    }

    @Override
    public int getItemCount() {
        return persoonsnamen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView persoonsNaam;
        ConstraintLayout recyclerLayout;

        public ViewHolder(View itemView){
            super(itemView);
            persoonsNaam = itemView.findViewById(R.id.persoonsnaam);
            recyclerLayout = itemView.findViewById(R.id.recycler_Layout);
        }

    }
}
