package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    public interface OnItemDeleteListener {
        void onItemDeleted(int position);
    }

    private final Context context;
    private final List<String> items;
    private OnItemDeleteListener onItemDeleteListener;

    public ItemAdapter(@NonNull Context context, @NonNull List<String> items) {

        this.context = context;
        this.items = items;
    }

    public void setOnItemDeleteListener(@NonNull OnItemDeleteListener listener) {
        onItemDeleteListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_to_do, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(items.get(position));
        holder.delete.setOnClickListener(view -> {
            if (onItemDeleteListener != null) {
                onItemDeleteListener.onItemDeleted(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView delete;

        public ViewHolder(@NonNull View view) {

            super(view);
            name = view.findViewById(R.id.name);
            delete = view.findViewById(R.id.delete);
        }

    }

}
