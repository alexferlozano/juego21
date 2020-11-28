package com.example.loteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> {
    private List<Persona> personasList;
    private LayoutInflater mInflater;
    private Context context;

    public PersonaAdapter (List<Persona> personaList, Context context)
    {
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.personasList=personaList;
    }

    @Override
    public PersonaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=mInflater.inflate(R.layout.lista_personas,null);
        return new PersonaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonaAdapter.ViewHolder holder, final int position)
    {
        holder.bindData(personasList.get(position));
    }

    @Override
    public int getItemCount() {
        return personasList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nom;
        TextView pnt;

        public ViewHolder(View itemview)
        {
            super(itemview);
            nom=itemView.findViewById(R.id.nombre);
            pnt=itemView.findViewById(R.id.pnt);
        }
        public void bindData(final Persona persona)
        {
            nom.setText(persona.getNombre());
            pnt.setText(String.valueOf(persona.getNumero()));
        }
    }
}
