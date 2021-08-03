package com.example.androidnetworkinglab;

import android.graphics.Bitmap;

public interface Listener {
        void onImageLoaded(Bitmap bitmap);
        void onError();
    }

