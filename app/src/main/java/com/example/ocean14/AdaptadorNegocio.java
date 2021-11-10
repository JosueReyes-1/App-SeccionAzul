package com.example.ocean14;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorNegocio extends RecyclerView.Adapter<AdaptadorNegocio.NegocioViewHolder> {
    Context context;
    List<Negocio>listaNegocio;
    int posicion=0;

    public AdaptadorNegocio(Context context, List<Negocio> listaNegocio ) {
        this.context = context;
        this.listaNegocio = listaNegocio;
    }



    @NonNull
    @Override
    public NegocioViewHolder onCreateViewHolder(@NonNull  ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_negocio,viewGroup,false);
        return new NegocioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  NegocioViewHolder negocioViewHolder, int i) {
        negocioViewHolder.cnegocio.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        negocioViewHolder.tvnombre.setText(listaNegocio.get(i).getNombre());
        Glide.with(context)
                .load(listaNegocio.get(i).getImagen())
                .into(negocioViewHolder.imgnegocio);
        //negocioViewHolder.tvservicio.setText(listaNegocio.get(i).getServicio());
        negocioViewHolder.tvtelefono.setText(listaNegocio.get(i).getTelefono());
        negocioViewHolder.tvdomicilio.setText(listaNegocio.get(i).getDomicilio());
        negocioViewHolder.tvhora.setText(listaNegocio.get(i).getHora());
        //negocioViewHolder.tvfacebook.setText(listaNegocio.get(i).getFacebook());
         final int pos=i;
        negocioViewHolder.cnegocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,detalles.class);
                intent.putExtra("nombre",listaNegocio.get(i).getNombre());
                intent.putExtra("servicio",listaNegocio.get(i).getServicio());
                intent.putExtra("telefono",listaNegocio.get(i).getTelefono());
                intent.putExtra("domicilio",listaNegocio.get(i).getDomicilio());
                intent.putExtra("hora",listaNegocio.get(i).getHora());
                intent.putExtra("facebook",listaNegocio.get(i).getFacebook());
                intent.putExtra("telefono2",listaNegocio.get(i).getTelefono2());
                intent.putExtra("whatsapp",listaNegocio.get(i).getWhatsapp());
                intent.putExtra("correo",listaNegocio.get(i).getCorreo_electronico());
                intent.putExtra("instagram",listaNegocio.get(i).getInstagram());
                intent.putExtra("youtube", listaNegocio.get(i).getYoutube());
                intent.putExtra("twitter", listaNegocio.get(i).getTwitter());
                intent.putExtra("imagen",listaNegocio.get(i).getImagen());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaNegocio.size();
    }

    public class NegocioViewHolder extends RecyclerView.ViewHolder {
        TextView tvnombre,tvtelefono,tvdomicilio,tvhora,facebook;
        CardView cnegocio;
        ImageView imgnegocio;

        public NegocioViewHolder(@NonNull  View itemView) {
            super(itemView);
            facebook=itemView.findViewById(R.id.facebook);
            imgnegocio=itemView.findViewById(R.id.imgnegocio);
            cnegocio=itemView.findViewById(R.id.cnegocio);
            tvnombre=itemView.findViewById(R.id.tvnombre);
            cnegocio=itemView.findViewById(R.id.cnegocio);

            tvtelefono=itemView.findViewById(R.id.tvtelefono);
            tvdomicilio=itemView.findViewById(R.id.tvdomicilio);
            tvhora=itemView.findViewById(R.id.tvhora);

        }
    }
    public void filtrar(ArrayList<Negocio>filtrarnegocio){
        this.listaNegocio=filtrarnegocio;
        notifyDataSetChanged();
    }
}
