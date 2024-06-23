package com.example.refrigerator;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FridgeItemsAdapter extends RecyclerView.Adapter<FridgeItemsAdapter.ViewHolder> {

    private List<FridgeItem> fridgeItems;
    private OnItemDeleteListener onItemDeleteListener;

    public interface OnItemDeleteListener {
        void onItemDelete(FridgeItem item);
    }

    public FridgeItemsAdapter(List<FridgeItem> fridgeItems, OnItemDeleteListener onItemDeleteListener) {
        this.fridgeItems = fridgeItems;
        this.onItemDeleteListener = onItemDeleteListener;

        // Periodically refresh the adapter
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
                handler.postDelayed(this, 60000); // Refresh every minute
            }
        };
        handler.post(refresh);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fridge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FridgeItem item = fridgeItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.expirationDateTextView.setText("유통기한: " + item.getExpirationDate());
        Glide.with(holder.itemView.getContext()).load(item.getImageUrl()).into(holder.imageView);

        // Calculate and display the days left
        holder.calculateDaysLeft(item.getExpirationDate(), holder.daysLeftTextView);

        holder.deleteButton.setOnClickListener(v -> {
            if (onItemDeleteListener != null) {
                onItemDeleteListener.onItemDelete(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fridgeItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, expirationDateTextView, daysLeftTextView;
        ImageView imageView;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            expirationDateTextView = itemView.findViewById(R.id.item_expiration_date);
            daysLeftTextView = itemView.findViewById(R.id.days_left);
            imageView = itemView.findViewById(R.id.item_image);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

        public void calculateDaysLeft(String expirationDate, TextView daysLeftTextView) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date expDate = sdf.parse(expirationDate);
                Date currentDate = new Date();

                long diff = expDate.getTime() - currentDate.getTime();
                long daysLeft = diff / (1000 * 60 * 60 * 24);

                if (daysLeft > 0) {
                    daysLeftTextView.setText("남은 일수: " + daysLeft + "일");
                } else if (daysLeft == 0) {
                    daysLeftTextView.setText("유통기한이 오늘까지입니다.");
                } else {
                    daysLeftTextView.setText("유통기한이 지났습니다.");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                daysLeftTextView.setText("잘못된 날짜");
            }
        }

    }
}
