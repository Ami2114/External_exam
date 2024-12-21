package com.example.qusetion_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        box = findViewById(R.id.box);

        Button upButton = findViewById(R.id.btnUp);
        Button downButton = findViewById(R.id.btnDown);
        Button leftButton = findViewById(R.id.btnLeft);
        Button rightButton = findViewById(R.id.btnRight);

        // Set onClickListeners for buttons
        upButton.setOnClickListener(v -> applyAnimation("up"));
        downButton.setOnClickListener(v -> applyAnimation("down"));
        leftButton.setOnClickListener(v -> applyAnimation("left"));
        rightButton.setOnClickListener(v -> applyAnimation("right"));
    }

    private void applyAnimation(String direction) {
        Animation animation = null;

        switch (direction) {
            case "up":
                animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
                break;
            case "down":
                animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                break;
            case "left":
                animation = AnimationUtils.loadAnimation(this, R.anim.slide_left);
                break;
            case "right":
                animation = AnimationUtils.loadAnimation(this, R.anim.slide_right);
                break;
        }

        if (animation != null) {
            box.startAnimation(animation);
        }
    }
}