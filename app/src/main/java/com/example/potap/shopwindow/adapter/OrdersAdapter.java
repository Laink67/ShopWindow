package com.example.potap.shopwindow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.dbObjects.Orders;

import java.util.Collections;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    class OrdersViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, price, size, color, quantity;
        private final ImageView image;
        private final MaterialButton deletMaterialButton;

        private OrdersViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.basket_product_name);
            image = itemView.findViewById(R.id.basket_product_image);
            price = itemView.findViewById(R.id.basket_product_price);
            color = itemView.findViewById(R.id.basket_product_color);
            size = itemView.findViewById(R.id.basket_product_size);
            quantity = itemView.findViewById(R.id.basket_product_number);
            deletMaterialButton = itemView.findViewById(R.id.delete_order_button);


            deletMaterialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeOrder(getAdapterPosition());
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Orders> mOrders = Collections.emptyList(); // Cached copy of words

    public OrdersAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrdersAdapter.OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.basket, parent, false);
        return new OrdersAdapter.OrdersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.OrdersViewHolder holder, int position) {
        final Context context = mInflater.getContext();
        final Orders current = mOrders.get(position);
        Glide.with(context).asBitmap().load(current.getImage()).into(holder.image);
        holder.name.setText(current.getName());
        holder.price.setText("Цена: " + String.valueOf(current.getPrice()));
        holder.color.setText("Цвет: " + current.getColor());
        holder.size.setText("Размер: " + current.getSize());
        holder.quantity.setText("Количество: " + current.getQuantity());
    }


    public void setOrders(List<Orders> Orders) {
        mOrders = Orders;
        notifyDataSetChanged();
    }

    private void removeOrder(int position) {
        mOrders.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }
}
