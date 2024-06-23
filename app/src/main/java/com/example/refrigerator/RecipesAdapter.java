package com.example.refrigerator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<RecipeResponse.Recipe> recipes;

    public RecipesAdapter(List<RecipeResponse.Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeResponse.Recipe recipe = recipes.get(position);
        holder.title.setText(recipe.getRCP_NM());
        holder.ingredients.setText(recipe.getRCP_PARTS_DTLS());
        // Glide 설정 추가: 모든 이미지에 동일한 크기 조정 적용
        RequestOptions requestOptions = new RequestOptions()
                .override(500, 500) // 모든 이미지 크기 조정
                .fitCenter(); // 이미지 크기에 맞추기

        Glide.with(holder.itemView.getContext())
                .load(recipe.getATT_FILE_NO_MAIN())
                .apply(requestOptions) // 모든 이미지에 크기 조정 적용
                .into(holder.image);

        // 매뉴얼 정보 설정
        List<ManualAdapter.Manual> manuals = new ArrayList<>();
        if (recipe.getMANUAL01() != null && !recipe.getMANUAL01().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL01(), recipe.getMANUAL_IMG01()));
        }
        if (recipe.getMANUAL02() != null && !recipe.getMANUAL02().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL02(), recipe.getMANUAL_IMG02()));
        }
        if (recipe.getMANUAL03() != null && !recipe.getMANUAL03().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL03(), recipe.getMANUAL_IMG03()));
        }
        if (recipe.getMANUAL04() != null && !recipe.getMANUAL04().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL04(), recipe.getMANUAL_IMG04()));
        }
        if (recipe.getMANUAL05() != null && !recipe.getMANUAL05().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL05(), recipe.getMANUAL_IMG05()));
        }
        if (recipe.getMANUAL06() != null && !recipe.getMANUAL06().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL06(), recipe.getMANUAL_IMG06()));
        }
        if (recipe.getMANUAL07() != null && !recipe.getMANUAL07().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL07(), recipe.getMANUAL_IMG07()));
        }
        if (recipe.getMANUAL08() != null && !recipe.getMANUAL08().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL08(), recipe.getMANUAL_IMG08()));
        }
        if (recipe.getMANUAL09() != null && !recipe.getMANUAL09().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL09(), recipe.getMANUAL_IMG09()));
        }
        if (recipe.getMANUAL10() != null && !recipe.getMANUAL10().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL10(), recipe.getMANUAL_IMG10()));
        }
        if (recipe.getMANUAL11() != null && !recipe.getMANUAL11().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL11(), recipe.getMANUAL_IMG11()));
        }
        if (recipe.getMANUAL12() != null && !recipe.getMANUAL12().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL12(), recipe.getMANUAL_IMG12()));
        }
        if (recipe.getMANUAL13() != null && !recipe.getMANUAL13().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL13(), recipe.getMANUAL_IMG13()));
        }
        if (recipe.getMANUAL14() != null && !recipe.getMANUAL14().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL14(), recipe.getMANUAL_IMG14()));
        }
        if (recipe.getMANUAL15() != null && !recipe.getMANUAL15().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL15(), recipe.getMANUAL_IMG15()));
        }
        if (recipe.getMANUAL16() != null && !recipe.getMANUAL16().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL16(), recipe.getMANUAL_IMG16()));
        }
        if (recipe.getMANUAL17() != null && !recipe.getMANUAL17().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL17(), recipe.getMANUAL_IMG17()));
        }
        if (recipe.getMANUAL18() != null && !recipe.getMANUAL18().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL18(), recipe.getMANUAL_IMG18()));
        }
        if (recipe.getMANUAL19() != null && !recipe.getMANUAL19().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL19(), recipe.getMANUAL_IMG19()));
        }
        if (recipe.getMANUAL20() != null && !recipe.getMANUAL20().isEmpty()) {
            manuals.add(new ManualAdapter.Manual(recipe.getMANUAL20(), recipe.getMANUAL_IMG20()));
        }

        ManualAdapter manualAdapter = new ManualAdapter(manuals);
        holder.manualRecyclerView.setAdapter(manualAdapter);
        holder.manualRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void updateData(List<RecipeResponse.Recipe> newRecipes) {
        this.recipes = newRecipes;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView ingredients;
        public ImageView image;
        public RecyclerView manualRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipe_title);
            ingredients = itemView.findViewById(R.id.recipe_ingredients);
            image = itemView.findViewById(R.id.recipe_image);
            manualRecyclerView = itemView.findViewById(R.id.manual_recycler_view);
        }
    }
}
