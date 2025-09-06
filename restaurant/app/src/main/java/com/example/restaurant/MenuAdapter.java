package com.example.restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuItem> menuList;

    public MenuAdapter(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // هر آیتم منو از لایوت item_menu.xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuList.get(position);
        holder.nameText.setText(item.getName());
        holder.priceText.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    // ViewHolder برای RecyclerView
    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, priceText;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textName);
            priceText = itemView.findViewById(R.id.textPrice);
        }
    }
}
