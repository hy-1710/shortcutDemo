package com.example.serpentcs.shortcutdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.serpentcs.shortcutdemo.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    /**
     * when we define multiple shortcuts in dynamic way you can use ShortcutManager
     */
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ShortcutManager manager = (ShortcutManager) getSystemService(Context.SHORTCUT_SERVICE);
        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                .setShortLabel("Web site")
                .setLongLabel("Open the web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_launch_pink_200_24dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/hy-1710")))
                .build();

        ShortcutInfo shortcut1 = new ShortcutInfo.Builder(this, "id2")
                .setShortLabel("Contact")
                .setLongLabel("call")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_face_light_green_800_24dp))
                .setIntent(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+91 8460773135")))
                .build();

        manager.setDynamicShortcuts(Arrays.asList(shortcut, shortcut1));


    }
}
