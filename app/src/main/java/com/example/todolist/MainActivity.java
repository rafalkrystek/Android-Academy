package com.example.todolist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.add_button);
        listView = findViewById(R.id.list1);
        itemList = FileHelper.readData(this);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);
        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(view -> {

            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            arrayAdapter.notifyDataSetChanged();

        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            FragmentDialog dialog = new FragmentDialog();
            dialog.setListener(() -> {
                itemList.remove(i);
                arrayAdapter.notifyDataSetChanged();
            });
            dialog.show(getSupportFragmentManager(), "Delete fragment");
        });
    }
}


