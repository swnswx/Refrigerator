package com.example.refrigerator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShoppingListAdapter adapter;
    private List<ShoppingItem> shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        // 데이터 로드
        loadData();

        recyclerView = findViewById(R.id.shopping_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingListAdapter(shoppingList, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton addItemButton = findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("품목을 입력하세요.");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, findViewById(android.R.id.content), false);
        final EditText inputName = viewInflated.findViewById(R.id.input_name);
        final EditText inputQuantity = viewInflated.findViewById(R.id.input_quantity);

        builder.setView(viewInflated);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String itemName = inputName.getText().toString();
                String itemQuantityStr = inputQuantity.getText().toString();
                if (!itemName.isEmpty() && !itemQuantityStr.isEmpty()) {
                    int itemQuantity = Integer.parseInt(itemQuantityStr);
                    addItem(itemName, itemQuantity);
                } else {
                    Toast.makeText(ShoppingListActivity.this, "품명과 수량을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void addItem(String name, int quantity) {
        ShoppingItem item = new ShoppingItem(name, quantity);
        shoppingList.add(item);
        adapter.notifyItemInserted(shoppingList.size() - 1);
        saveData();  // 데이터 저장
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shopping_list", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(shoppingList);
        editor.putString("shopping_list_items", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shopping_list", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("shopping_list_items", null);
        Type type = new TypeToken<ArrayList<ShoppingItem>>() {}.getType();
        shoppingList = gson.fromJson(json, type);

        if (shoppingList == null) {
            shoppingList = new ArrayList<>();
        }
    }

    public static class ShoppingItem {
        private String name;
        private int quantity;
        private boolean checked;

        public ShoppingItem(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
            this.checked = false;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }

    public static class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

        private List<ShoppingItem> shoppingList;
        private ShoppingListActivity activity;  // Activity 참조

        public ShoppingListAdapter(List<ShoppingItem> shoppingList, ShoppingListActivity activity) {
            this.shoppingList = shoppingList;
            this.activity = activity;  // Activity 참조 저장
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ShoppingItem item = shoppingList.get(position);
            holder.itemName.setText(item.getName() + " (" + item.getQuantity() + ")"); // 품명과 수량 표시
            holder.itemCheckBox.setChecked(item.isChecked());

            // 체크 상태에 따라 텍스트 스타일 변경
            if (item.isChecked()) {
                holder.itemName.setTextSize(18); // 텍스트 크기 변경
                holder.itemName.setTextColor(activity.getResources().getColor(R.color.checkedTextColor)); // 텍스트 색상 변경
            } else {
                holder.itemName.setTextSize(14); // 기본 텍스트 크기
                holder.itemName.setTextColor(activity.getResources().getColor(R.color.defaultTextColor)); // 기본 텍스트 색상
            }

            holder.itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.setChecked(isChecked);
                // 체크 상태에 따라 텍스트 스타일 변경
                if (isChecked) {
                    holder.itemName.setTextSize(18); // 텍스트 크기 변경
                    holder.itemName.setTextColor(activity.getResources().getColor(R.color.checkedTextColor)); // 텍스트 색상 변경
                } else {
                    holder.itemName.setTextSize(14); // 기본 텍스트 크기
                    holder.itemName.setTextColor(activity.getResources().getColor(R.color.defaultTextColor)); // 기본 텍스트 색상
                }
                activity.saveData();  // 데이터 저장
            });

            holder.deleteButton.setOnClickListener(v -> {
                shoppingList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, shoppingList.size());
                activity.saveData();  // 데이터 저장
            });
        }

        @Override
        public int getItemCount() {
            return shoppingList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView itemName;
            public CheckBox itemCheckBox;
            public ImageButton deleteButton;

            public ViewHolder(View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.item_name);
                itemCheckBox = itemView.findViewById(R.id.item_checkbox);
                deleteButton = itemView.findViewById(R.id.item_delete);
            }
        }
    }
}
