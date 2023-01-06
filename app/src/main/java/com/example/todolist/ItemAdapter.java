package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    public interface OnItemDeleteListener {
        void onItemDeleted(int position);
    }

    private final List<String> urls = Arrays.asList("https://www.wp.pl", "https://www.onet.pl", "https://www.telepolis.pl/images/2022/06/android-13-beta-3-debiutuje.jpg");
    private final Context context;
    private final List<String> items;
    private OnItemDeleteListener onItemDeleteListener;
    private final Random random = new Random();

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

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(items.get(position));
        holder.delete.setOnClickListener(view -> {
            if (onItemDeleteListener != null) {
                onItemDeleteListener.onItemDeleted(position);
            }
        });

        if (position % 2 == 0) {
            holder.webView.setVisibility(View.VISIBLE);
            holder.webView.loadUrl(holder.url);
            holder.webView.setWebViewClient(new WebViewClient());
            holder.webView.getSettings().setJavaScriptEnabled(true);
        } else {
            holder.webView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView delete;
        private final WebView webView;
        private final String url;

        public ViewHolder(@NonNull View view) {

            super(view);
            name = view.findViewById(R.id.name);
            delete = view.findViewById(R.id.delete);
            webView = view.findViewById(R.id.webview);
            url = urls.get(random.nextInt(urls.size()));
        }

    }

}
