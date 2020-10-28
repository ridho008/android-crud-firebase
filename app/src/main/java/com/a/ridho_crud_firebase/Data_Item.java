package com.a.ridho_crud_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Data_Item extends RecyclerView.Adapter<Data_Item.MyViewHolder> {
    private List<Mahasiswa> MList;

    public Data_Item(List<Mahasiswa> MList) {
        this.MList = MList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mahasiswa mov = MList.get(position);
        holder.tvnim.setText("Nim : "+mov.getNim());
        holder.tvnama.setText("Nim : "+mov.getNama());
        holder.tvjurusan.setText("Nim : "+mov.getJurusan());
        holder.tvsemester.setText("Nim : "+mov.getSemester());
    }

    @Override
    public int getItemCount() {
        return MList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvnim;
        TextView tvnama;
        TextView tvjurusan;
        TextView tvsemester;
        CardView card_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnim = itemView.findViewById(R.id.tvnim);
            tvnama = itemView.findViewById(R.id.tvnama);
            tvjurusan = itemView.findViewById(R.id.tvjurusan);
            tvsemester = itemView.findViewById(R.id.tvsemester);
        }

    }
}
