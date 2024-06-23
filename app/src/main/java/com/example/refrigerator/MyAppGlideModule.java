// MyAppGlideModule.java
package com.example.refrigerator;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // 전역 설정
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .override(500, 500) // 모든 이미지 크기 조정
                        .fitCenter()
        );
    }
}