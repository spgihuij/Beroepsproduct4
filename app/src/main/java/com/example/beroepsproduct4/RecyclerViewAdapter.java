package com.example.beroepsproduct4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.internal.service.Common;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String>  persoonsnamen = new ArrayList<>();
    private ArrayList<String> persoonsnamenfull;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> persoonsnamen) {
        this.persoonsnamen = persoonsnamen;
        persoonsnamenfull = new ArrayList<>(persoonsnamen);
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.persoonsNaam.setText(persoonsnamen.get(i));

        viewHolder.recyclerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(v.getContext(),ReadInfoOverAnderen.class);
                Bundle bundle = new Bundle();
                bundle.putString("persoonsnaam", persoonsnamen.get(i));
                s.putExtras(bundle);
                v.getContext().startActivity(s);





            }
        });

    }

    @Override
    public int getItemCount() {
        return persoonsnamen.size();
    }

    @Override
    public Filter getFilter() {
        return lijstfilter;
    }

    public Filter lijstfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredlijst = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                filteredlijst.addAll(persoonsnamenfull);
            }

            else
            {
                String filterpattern = constraint.toString().toLowerCase().trim();

                for(String string : persoonsnamenfull)
                {
                    if (string.toLowerCase().contains(filterpattern))
                    {
                        filteredlijst.add(string);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlijst;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            persoonsnamen.clear();
            persoonsnamen.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

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
