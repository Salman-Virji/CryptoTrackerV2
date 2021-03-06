package com.salmanvirji.testcryptov2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{

    private ArrayList<Crypto> Crypto;


    //when we create a instance of recycleradapter we have to have new instance of users
    public recyclerAdapter(ArrayList<Crypto> Crypto){
        this.Crypto = Crypto;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;
        private TextView symbolTxt;
        private TextView priceTxt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt= itemView.findViewById(R.id.txtSymbol);
            symbolTxt= itemView.findViewById(R.id.textView3);
            priceTxt= itemView.findViewById(R.id.txtPrice);

        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_item, parent,false);
       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = Crypto.get(position).getName();
        String symbol = Crypto.get(position).getSymbol();
        Double price = Crypto.get(position).getPrice();

        holder.nameTxt.setText(name);
        holder.symbolTxt.setText(symbol);
        holder.priceTxt.setText(price.toString());

    }

    @Override
    public int getItemCount() {
        return Crypto.size();
    }
}
