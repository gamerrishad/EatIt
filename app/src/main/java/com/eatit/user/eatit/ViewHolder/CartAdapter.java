package com.eatit.user.eatit.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.eatit.user.eatit.Model.Orders;
import com.eatit.user.eatit.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView cartItemName, cartItemPrice;
    public ImageView imageCart;

    public CartViewHolder(View itemView) {
        super(itemView);

        cartItemName = itemView.findViewById(R.id.cartItemName);
        cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
        imageCart = itemView.findViewById(R.id.imgCart);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Orders> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Orders> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false); // Bujhi nai
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        // Bujhi Nai
        TextDrawable drawable = TextDrawable.builder().buildRound(listData.get(position).getQuantity(), Color.RED);
        holder.imageCart.setImageDrawable(drawable);
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.cartItemPrice.setText(fmt.format(price));
        holder.cartItemName.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
