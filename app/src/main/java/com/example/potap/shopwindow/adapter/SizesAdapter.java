package com.example.potap.shopwindow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.potap.shopwindow.R;
import com.example.potap.shopwindow.dbObjects.Sizes;

import java.util.Collections;
import java.util.List;

public class SizesAdapter extends RecyclerView.Adapter<SizesAdapter.SizesViewHolder> {

    class SizesViewHolder extends RecyclerView.ViewHolder {
        private final RadioButton radioButton;

        private SizesViewHolder(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.sizes_radiobutton);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    size = Double.valueOf(radioButton.getText().toString());
                    notifyDataSetChanged();

                }
            });
        }
    }

    public double size;
    private int lastSelectedPosition = -1;
    private final LayoutInflater mInflater;
    private List<Sizes> sizesList = Collections.emptyList(); // Cached copy of words

    public SizesAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SizesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.sizes_recylerview_item, viewGroup, false);
        return new SizesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SizesViewHolder holder, int position) {
        final Context context = mInflater.getContext();
        final Sizes current = sizesList.get(position);
        holder.radioButton.setText(String.valueOf(current.getSize()));
        holder.radioButton.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return sizesList.size();
    }

    public void setSizes(List<Sizes> sizes) {
        this.sizesList = sizes;
        notifyDataSetChanged();
    }
}
