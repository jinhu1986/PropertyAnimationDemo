package com.bwie.test.propertyanimationdemo;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class NextActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView next_image;
    private Button shanshuo;
    private Button doudong;
    private Button bianse;
    private Button quxian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void parabola() {

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                /**x方向200px/s ，则y方向0.5 * 200 * t**/
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                next_image.setX(point.x);
                next_image.setY(point.y);

            }
        });
    }

    private void initView() {
        next_image = (ImageView) findViewById(R.id.next_image);
        shanshuo = (Button) findViewById(R.id.shanshuo);
        doudong = (Button) findViewById(R.id.doudong);
        bianse = (Button) findViewById(R.id.bianse);

        quxian = (Button) findViewById(R.id.quxian);

        shanshuo.setOnClickListener(this);
        doudong.setOnClickListener(this);
        bianse.setOnClickListener(this);

        quxian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shanshuo:
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(next_image, "alpha", 0f, 1f);
                objectAnimator2.setDuration(500);
                objectAnimator2.setRepeatCount(3);
                objectAnimator2.start();
                break;
            case R.id.doudong:
                ObjectAnimator objectAnimatorTranslate3 = ObjectAnimator.ofFloat(next_image, "translationX", -50f, 50f);
                objectAnimatorTranslate3.setDuration(500);
                objectAnimatorTranslate3.setRepeatCount(3);
                objectAnimatorTranslate3.start();
                break;
            case R.id.bianse:
                ObjectAnimator objectAnimatorBg = ObjectAnimator.ofInt(bianse, "backgroundColor", Color.BLUE, Color.YELLOW, Color.RED);
                objectAnimatorBg.setDuration(3000);
                objectAnimatorBg.start();
                break;

            case R.id.quxian:
                parabola();
                break;
        }
    }
}
