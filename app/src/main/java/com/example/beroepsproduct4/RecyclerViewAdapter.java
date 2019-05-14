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
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerViewAdapter";
    private static int type_personen = 1;
    private static int type_evenementen = 2;

    private ArrayList<Persoon> persoonList = new ArrayList<>();
    private ArrayList<String> stringlistfull = new ArrayList<>();
    private ArrayList<Evenement> evenementList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Persoon> persoonList, ArrayList<Evenement> evenementList) {
        if(persoonList != null) {
            this.persoonList = persoonList;
            for(Persoon p : persoonList)
            {
                stringlistfull.add(p.getPersoonnaam());
            }
            this.context = context;
        }
        if(evenementList!= null){
            this.evenementList = evenementList;
            for(Evenement e : evenementList)
            {
                stringlistfull.add(e.getEvenementnaam());
            }
            this.context = context;

        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(i == type_personen)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
            return new personenViewHolder(view);
        }
        else
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
            return  new evenementenViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        if (getItemViewType(i) == type_personen) {
            ((personenViewHolder) viewHolder).setPersonenInfo(persoonList.get(i));

            ((personenViewHolder)viewHolder).recyclerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent s = new Intent(v.getContext(), ReadInfoOverAnderen.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("persoonsnaam", persoonList.get(i).getPersoonnaam());
                    s.putExtras(bundle);
                    v.getContext().startActivity(s);
                }
            });


        }
        else {

            ((evenementenViewHolder) viewHolder).setEvenementInfo(evenementList.get(i));

            ((evenementenViewHolder) viewHolder).recyclerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //intent voor evenement info na click.
                }
            });
        }




    }

    @Override
    public int getItemViewType(int i)
    {
        if (persoonList!= null)
        {
                 return type_personen;

        }
        else

                return  type_evenementen;

    }

    @Override
    public int getItemCount() {
        return persoonList.size();
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
                filteredlijst.addAll(stringlistfull);
            }

            else
            {
                String filterpattern = constraint.toString().toLowerCase().trim();

                for(String string : stringlistfull)
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
            persoonList.clear();
            persoonList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

     class personenViewHolder extends RecyclerView.ViewHolder{

        TextView persoonsNaam;
        ConstraintLayout recyclerLayout;

        public personenViewHolder(View itemView){
            super(itemView);
            persoonsNaam = itemView.findViewById(R.id.persoonsnaam);
            recyclerLayout = itemView.findViewById(R.id.recycler_Layout);
        }

         public void setPersonenInfo(Persoon persoon)
         {
             persoonsNaam.setText(persoon.getPersoonnaam());
         }

    }

    class evenementenViewHolder extends RecyclerView.ViewHolder{
         TextView evenementNaam;
         TextView evenementLocatie;
         ImageView evenementFoto;
         ConstraintLayout recyclerLayout;

        public evenementenViewHolder(View itemView){
            super(itemView);

        }

        public void setEvenementInfo(Evenement evenement)
        {
            evenementNaam.setText(evenement.getEvenementnaam());
            evenementLocatie.setText(evenement.getEvenementlocatie());
            //foto nog toevoegen
        }


    }




}
