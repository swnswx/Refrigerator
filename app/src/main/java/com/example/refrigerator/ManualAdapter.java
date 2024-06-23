package com.example.refrigerator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ManualAdapter extends RecyclerView.Adapter<ManualAdapter.ManualViewHolder> {

    private List<Manual> manuals;

    public ManualAdapter(List<Manual> manuals) {
        this.manuals = manuals;
    }

    @NonNull
    @Override
    public ManualViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manual, parent, false);
        return new ManualViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManualViewHolder holder, int position) {
        Manual manual = manuals.get(position);
        holder.manualStep.setText(manual.getStep());
        Glide.with(holder.itemView.getContext()).load(manual.getImageUrl()).into(holder.manualImage);
    }

    @Override
    public int getItemCount() {
        return manuals.size();
    }

    public static class Manual {
        private String step;
        private String imageUrl;

        public Manual(String step, String imageUrl) {
            this.step = step;
            this.imageUrl = imageUrl;
        }

        public String getStep() {
            return step;
        }

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public static class ManualViewHolder extends RecyclerView.ViewHolder {
        public TextView manualStep;
        public ImageView manualImage;

        public ManualViewHolder(View itemView) {
            super(itemView);
            manualStep = itemView.findViewById(R.id.manual_step);
            manualImage = itemView.findViewById(R.id.manual_image);
        }
    }
}
