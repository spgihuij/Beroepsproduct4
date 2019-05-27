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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerViewAdapter";
    private static int type_personen = 1;
    private static int type_evenementen = 2;

    private ArrayList<Persoon> persoonList = null;
    private ArrayList<Persoon> persoonsListFull = new ArrayList<>();
    private ArrayList<Evenement> evenementList = null;
    private ArrayList<Evenement> evenementListFull = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Persoon> persoonList, ArrayList<Evenement> evenementList) {
        if(persoonList != null) {
            this.persoonList = persoonList;

            persoonsListFull.addAll(persoonList);

            this.context = context;
        }
        if(evenementList!= null){
            this.evenementList = evenementList;
            evenementListFull.addAll(evenementList);
            this.context = context;

        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(i == type_personen)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_personen,viewGroup,false);
            return new personenViewHolder(view);
        }


        else
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_evenementen,viewGroup,false);
            return  new evenementenViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        if (getItemViewType(i) == type_personen) {
            ((personenViewHolder) viewHolder).setPersonenInfo(persoonList.get(i));


            String uri = persoonList.get(i).getPersoonprofielfoto();

            Picasso.get()
                    .load(uri)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(((personenViewHolder) viewHolder).imageView);





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
        else if (getItemViewType(i) == type_evenementen){
            ((evenementenViewHolder) viewHolder).setEvenementInfo(evenementList.get(i));

            String uri = evenementList.get(i).getEvenementfoto();
            Picasso.get()
                    .load(uri)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(((evenementenViewHolder) viewHolder).evenementFoto);


            ((evenementenViewHolder) viewHolder).recyclerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent s = new Intent(v.getContext(), InfoOverEvenementPlaceholder.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("evenementennaam", evenementList.get(i).getEvenementnaam());
                    s.putExtras(bundle);
                    v.getContext().startActivity(s);
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
        if(persoonList!= null) {
            return persoonList.size();
        }
        else
        {
            return evenementList.size();
        }
    }

    @Override
    public Filter getFilter() {
        return lijstfilter;
    }

    public Filter lijstfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Persoon> filteredlijstPersoon = new ArrayList<>();
            List<Evenement> filteredlijstEvenement = new ArrayList<>();
            if (persoonList != null) {

                if (constraint == null || constraint.length() == 0) {
                    filteredlijstPersoon.addAll(persoonsListFull);
                } else {
                    String filterpattern = constraint.toString().toLowerCase().trim();

                    for (Persoon persoon : persoonsListFull) {
                        if (persoon.getPersoonnaam().toLowerCase().contains(filterpattern)) {
                            filteredlijstPersoon.add(persoon);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredlijstPersoon;
                return results;
            }

            else
            {
                if (constraint == null || constraint.length() == 0) {
                    filteredlijstEvenement.addAll(evenementListFull);
                } else {
                    String filterpattern = constraint.toString().toLowerCase().trim();

                    for (Evenement evenement : evenementListFull) {
                        if (evenement.getEvenementnaam().toLowerCase().contains(filterpattern)) {
                            filteredlijstEvenement.add(evenement);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredlijstEvenement;
                return results;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (persoonList != null ) {
                persoonList.clear();
                persoonList.addAll((List) results.values);
            }

            else
            {
                evenementList.clear();
                evenementList.addAll((List) results.values);
            }
            notifyDataSetChanged();

        }
    };

    class personenViewHolder extends RecyclerView.ViewHolder{

        TextView persoonsNaam;
        ConstraintLayout recyclerLayout;
        ImageView imageView;

        public personenViewHolder(View itemView){
            super(itemView);
            persoonsNaam = itemView.findViewById(R.id.persoonsnaam);
            recyclerLayout = itemView.findViewById(R.id.recycler_Layout_personen);
            imageView = itemView.findViewById(R.id.imageViewPersoon);
        }

        public void setPersonenInfo(Persoon persoon)
        {
            persoonsNaam.setText(persoon.getPersoonnaam());




        }

    }

    class evenementenViewHolder extends RecyclerView.ViewHolder{
        TextView evenementNaam;
        TextView evenementDatum;
        TextView evenementBeschrijving;
        ImageView evenementFoto;
        ConstraintLayout recyclerLayout;

        public evenementenViewHolder(View itemView){
            super(itemView);
            evenementNaam = itemView.findViewById(R.id.evenementNaam);
            evenementFoto = itemView.findViewById(R.id.evenementImage);
            evenementDatum = itemView.findViewById(R.id.evenementDatum);
            evenementBeschrijving  = itemView.findViewById(R.id.evenementBeschrijving);
            recyclerLayout = itemView.findViewById(R.id.recycler_Layout_evenement);

        }

        public void setEvenementInfo(Evenement evenement)
        {
            evenementNaam.setText(evenement.getEvenementnaam());
            evenementDatum.setText(evenement.getEvenementdatum());
            evenementBeschrijving.setText(evenement.getEvenementbeschrijving());


        }


    }




}
