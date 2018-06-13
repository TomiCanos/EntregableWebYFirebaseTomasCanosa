package com.example.dh.entregableservicioswebyfirebase.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by DH on 30/5/2018.
 */

public class AdapterPaint extends RecyclerView.Adapter {
    private List<Paint> paintsAMostrar;
    private NotificableDelClickEnPaint notificableDelClickEnPaint;


    public AdapterPaint(List<Paint> paintsAMostrar, NotificableDelClickEnPaint notificableDelClickEnPaint) {
        this.paintsAMostrar = paintsAMostrar;
        this.notificableDelClickEnPaint = notificableDelClickEnPaint;
    }

    public AdapterPaint(List<Paint> paintsAMostrar) {
        this.paintsAMostrar = paintsAMostrar;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_recycler_paints, parent, false);
        ViewHolderPaint viewHolderPaint = new ViewHolderPaint(viewDeLaCelda);

        return viewHolderPaint;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Paint paintAMostrar = paintsAMostrar.get(position);
        ViewHolderPaint viewHolderPaint = (ViewHolderPaint) holder;
        viewHolderPaint.BindPaint(paintAMostrar);

    }

    @Override
    public int getItemCount() {
        return paintsAMostrar.size();
    }

    private class ViewHolderPaint extends RecyclerView.ViewHolder {
        private TextView nombrePaint;
        private String imageURL;
        private ImageView imagePaint;

        public ViewHolderPaint(View itemView) {
            super(itemView);
            nombrePaint = itemView.findViewById(R.id.txtview_nombre_paint);

            nombrePaint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notificableDelClickEnPaint.abrirDetallePaintClickeada(paintsAMostrar, getAdapterPosition());
                }
            });
        }

        public void BindPaint (Paint paint){

            nombrePaint.setText(paint.getName());
            imageURL = (paint.getImage());
            //Glide.with(context).using(new FirebaseI);

        }
    }

    public interface NotificableDelClickEnPaint {
       public void abrirDetallePaintClickeada(List<Paint> listaPaints, Integer positionPaint);
    }
}
