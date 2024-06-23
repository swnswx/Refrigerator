package com.example.refrigerator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class RecipeSearchActivity extends AppCompatActivity {

    private EditText searchQuery;
    private Button searchButton;
    private RecyclerView recipesRecyclerView;
    private RecipesAdapter recipesAdapter;
    private RequestQueue requestQueue;
    private Gson gson;
    private static final String API_KEY = "debb5ac77c1a4f2c80e8"; // 여기에 실제 API 키를 입력하세요
    private static final String SERVICE_ID = "COOKRCP01"; // 여기에 실제 서비스 ID를 입력하세요
    private static final String DATA_TYPE = "json"; // 요청 파일 타입

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);

        searchQuery = findViewById(R.id.search_query);
        searchButton = findViewById(R.id.search_button);
        recipesRecyclerView = findViewById(R.id.recipes_recycler_view);

        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);
        gson = new Gson();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchQuery.getText().toString();
                if (!query.isEmpty()) {
                    searchRecipes(query, 1, 10);
                } else {
                    Toast.makeText(RecipeSearchActivity.this, "키워드를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 빈 어댑터를 설정하여 RecyclerView 초기화
        recipesAdapter = new RecipesAdapter(Collections.emptyList());
        recipesRecyclerView.setAdapter(recipesAdapter);
    }

    public void searchRecipes(String query, int start, int end) {
        // URL 생성
        @SuppressLint("DefaultLocale") String url = String.format("http://openapi.foodsafetykorea.go.kr/api/%s/%s/%s/%d/%d/RCP_NM=%s", API_KEY, SERVICE_ID, DATA_TYPE, start, end, query);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // 응답을 로그로 출력
                            Log.d("API_RESPONSE", response.toString());

                            // "CookRcp01" 키가 있는지 확인
                            if (response.has("COOKRCP01")) {
                                JSONObject cookRcp01 = response.getJSONObject("COOKRCP01");
                                if (cookRcp01.has("row")) {
                                    Type listType = new TypeToken<List<RecipeResponse.Recipe>>() {}.getType();
                                    List<RecipeResponse.Recipe> recipes = gson.fromJson(cookRcp01.getJSONArray("row").toString(), listType);

                                    // 이미지 URL 로그 추가
                                    for (RecipeResponse.Recipe recipe : recipes) {
                                        Log.d("Recipe_Image_URL", recipe.getATT_FILE_NO_MAIN());
                                    }

                                    if (recipes.isEmpty()) {
                                        Toast.makeText(RecipeSearchActivity.this, "검색된 레시피가 없습니다", Toast.LENGTH_SHORT).show();
                                    } else {
                                        recipesAdapter.updateData(recipes);
                                    }
                                } else {
                                    Toast.makeText(RecipeSearchActivity.this, "레시피를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RecipeSearchActivity.this, "레시피를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RecipeSearchActivity.this, "레시피를 찾을 수 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipeSearchActivity.this, "레시피를 불러오는데 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
