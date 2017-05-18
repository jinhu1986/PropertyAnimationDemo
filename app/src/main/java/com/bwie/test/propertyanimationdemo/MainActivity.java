package com.bwie.test.propertyanimationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button alpha;
    private Button scale;
    private Button rotate;
    private Button translate;
    private Button animatorset;
    private ImageView image;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        alpha = (Button) findViewById(R.id.alpha);
        alpha.setOnClickListener(this);
        scale = (Button) findViewById(R.id.scale);
        scale.setOnClickListener(this);
        rotate = (Button) findViewById(R.id.rotate);
        rotate.setOnClickListener(this);
        translate = (Button) findViewById(R.id.translate);
        translate.setOnClickListener(this);
        animatorset = (Button) findViewById(R.id.animatorset);
        animatorset.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(this);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:
                ObjectAnimator animator = ObjectAnimator.ofFloat(image, "alpha", 0f, 1f);
                animator.setDuration(2000);
                animator.start();
                break;
            case R.id.scale:
                PropertyValuesHolder objectAnimatorScaleX = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
                PropertyValuesHolder objectAnimatorScaleY = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
                /**同时播放两个动画**/
                ObjectAnimator.ofPropertyValuesHolder(image, objectAnimatorScaleX, objectAnimatorScaleY)
                        .setDuration(2000)
                        .start();
                break;
            case R.id.rotate:
                ObjectAnimator objectAnimatorScale = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
                objectAnimatorScale.setDuration(2000);
                objectAnimatorScale.start();
                break;
            case R.id.translate:
                PropertyValuesHolder translateX = PropertyValuesHolder.ofFloat("translationX", 0f, 500f);
                PropertyValuesHolder translateY = PropertyValuesHolder.ofFloat("translationY", 0f, 0f);
                /**同时播放两个动画**/
                ObjectAnimator.ofPropertyValuesHolder(image, translateX, translateY).setDuration(2000).start();
                break;
            case R.id.animatorset:
                AnimatorSet animatorSetGroup2 = new AnimatorSet();
                ObjectAnimator objectAnimatorTranslate2 = ObjectAnimator.ofFloat(image, "translationX", 0f, 500f);
                ObjectAnimator objectAnimatorRotateX2 = ObjectAnimator.ofFloat(image, "rotationX", 0f, 360f);
                ObjectAnimator objectAnimatorRotateY2 = ObjectAnimator.ofFloat(image, "rotationY", 0f, 360f);
                animatorSetGroup2.setDuration(2000);
                animatorSetGroup2.play(objectAnimatorTranslate2).after(objectAnimatorRotateX2)
                        .after(objectAnimatorRotateY2);
                animatorSetGroup2.start();
                break;
            case R.id.next:
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
                break;
        }
    }
}
