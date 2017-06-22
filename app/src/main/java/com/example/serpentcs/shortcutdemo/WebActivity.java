package com.example.serpentcs.shortcutdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.serpentcs.shortcutdemo.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {
    /**
     * this activity used when we can create static intent and when we define class name in
     * xml/shortcuts file. so it will redirect this class and perform the static shortcut action
     */

    ActivityWebBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        binding.web.getSettings().setJavaScriptEnabled(true);
        binding.web.getSettings().setDomStorageEnabled(true);
        binding.web.getSettings().setUseWideViewPort(true);
        binding.web.getSettings().setAppCacheEnabled(true);
        binding.web.getSettings().setLoadsImagesAutomatically(true);
        binding.web.loadUrl("https://github.com/hy-1710");
    }

}
