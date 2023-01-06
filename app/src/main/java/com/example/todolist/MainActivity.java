package com.example.todolist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    RecyclerView recyclerView;
    ArrayList<String> itemList = new ArrayList<>();
    com.example.todolist.ItemAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.list1);
        itemList = FileHelper.readData(this);
        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add.setOnClickListener(view -> {

            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            adapter.notifyItemInserted(itemList.size() - 1);

        });
        adapter.setOnItemDeleteListener(position -> {
            FragmentDialog dialog = new FragmentDialog();
            dialog.setListener(() -> {
                itemList.remove(position);
                adapter.notifyItemRemoved(position);
                FileHelper.writeData(itemList, getApplicationContext());
            });
            dialog.show(getSupportFragmentManager(), "Delete fragment");
        });
    }
}



