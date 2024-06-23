package com.example.refrigerator;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FridgeItemsActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private RecyclerView fridgeItemsRecyclerView;
    private FridgeItemsAdapter fridgeItemsAdapter;
    private List<FridgeItem> fridgeItemsList;
    private AppDatabase db;
    private Uri imageUri;
    private ImageView selectedImageView;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_items);

        fridgeItemsRecyclerView = findViewById(R.id.item_recycler_view);
        fridgeItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.add_item_button).setOnClickListener(v -> showAddItemDialog());

        db = AppDatabase.getInstance(this);

        fridgeItemsList = new ArrayList<>();
        fridgeItemsAdapter = new FridgeItemsAdapter(fridgeItemsList, this::deleteItemFromFridge);
        fridgeItemsRecyclerView.setAdapter(fridgeItemsAdapter);

        loadFridgeItems();
    }

    private void loadFridgeItems() {
        new Thread(() -> {
            fridgeItemsList.clear();
            fridgeItemsList.addAll(db.fridgeItemDao().getAll());
            runOnUiThread(() -> fridgeItemsAdapter.notifyDataSetChanged());
        }).start();
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("새 항목 추가");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.add_db_item, findViewById(android.R.id.content), false);

        final EditText inputName = viewInflated.findViewById(R.id.input_name);
        final EditText inputExpirationDate = viewInflated.findViewById(R.id.input_expiration_date);
        selectedImageView = viewInflated.findViewById(R.id.item_image);
        Button selectImageButton = viewInflated.findViewById(R.id.select_image_button);

        selectImageButton.setOnClickListener(v -> openFileChooser());

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            String name = inputName.getText().toString();
            String expirationDate = inputExpirationDate.getText().toString();
            if (imageUri != null) {
                imageUrl = imageUri.toString();
                addItemToFridge(name, expirationDate, imageUrl);
            } else {
                Toast.makeText(this, "이미지를 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            selectedImageView.setImageURI(imageUri);
        }
    }

    private void addItemToFridge(String name, String expirationDate, String imageUrl) {
        new Thread(() -> {
            FridgeItem newItem = new FridgeItem(name, expirationDate, imageUrl);
            db.fridgeItemDao().insert(newItem);
            fridgeItemsList.add(newItem);
            runOnUiThread(() -> fridgeItemsAdapter.notifyDataSetChanged());
        }).start();
    }

    private void deleteItemFromFridge(FridgeItem item) {
        new Thread(() -> {
            db.fridgeItemDao().delete(item);
            fridgeItemsList.remove(item);
            runOnUiThread(() -> fridgeItemsAdapter.notifyDataSetChanged());
        }).start();
    }

}
