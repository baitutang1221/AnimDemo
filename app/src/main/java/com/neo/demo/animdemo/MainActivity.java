package com.neo.demo.animdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import static android.animation.ValueAnimator.REVERSE;

public class MainActivity extends AppCompatActivity {

    private Button main_button;
    private Button my_btn;
    private Button start_btn;
    private Button end_btn;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_button = (Button) findViewById(R.id.main_button);
        my_btn = (Button) findViewById(R.id.my_btn);
        start_btn = (Button) findViewById(R.id.start_btn);
        end_btn = (Button) findViewById(R.id.end_btn);
        imageView = (ImageView) findViewById(R.id.imageView);

        drawableAnimation();
    }

    /**
     * 计算宽度
     *
     */
    public class WidthsEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startWidth = (int) startValue;
            int endWidth = (int) endValue;
            return (int) (startWidth + fraction * (endWidth - startWidth));
        }
    }


    /**
     * 补间动画
     *
     */
    public void viewAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        main_button.startAnimation(animation);
    }

    /**
     * 属性动画
     *
     */
    public void propertyAnimation(){
        //1，平移操作
//        float translationX = main_button.getTranslationX();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(main_button, "translationX", translationX, 600f, translationX);
//        animator.setDuration(3000);
//        animator.setRepeatCount(2);
//        animator.start();


        //2，缩放动画
//        ObjectAnimator animator = ObjectAnimator.ofFloat(main_button, "scaleX", 1f, 2f, 1f);
//        animator.setDuration(5000);
//        animator.start();

        //3,旋转动画
//        ObjectAnimator animator = ObjectAnimator.ofFloat(main_button, "rotation", 0f, 360f);
//        animator.setDuration(3000);
//        animator.start();

        //4,透明度动画
//        ObjectAnimator animator = ObjectAnimator.ofFloat(main_button, "alpha", 1f, 0f, 1f);
//        animator.setDuration(5000);
//        animator.start();

        //5,颜色动画
//        ObjectAnimator animator = ObjectAnimator.ofInt(main_button, "textColor", Color.BLUE, Color.RED);
//        animator.setEvaluator(new ArgbEvaluator());
//        animator.setDuration(5000);
//        animator.start();
    }

    /**
     * 组合属性动画
     *
     * after(Animator anim) 将现有动画插入到传入的动画之后执行
     * after(long delay) 将现有动画延迟指定毫秒后执行
     *  before(Animator anim) 将现有动画插入到传入的动画之前执行
     *  with(Animator anim) 将现有动画和传入的动画同时执行
     *
     */
    public void propertyAnimationAll(){
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(main_button, "alpha", 1f, 0f, 1f);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(main_button, "rotation", 0f, 360f);

        ObjectAnimator translationx = ObjectAnimator.ofFloat(main_button, "translationX", 600f, 0f);
        ObjectAnimator translationy = ObjectAnimator.ofFloat(main_button, "translationY", 600f, 0f);

        ObjectAnimator scalex = ObjectAnimator.ofFloat(main_button, "scaleX", 1f, 2f, 1f);
        scalex.setRepeatCount(1);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(main_button, "scaleY", 1f, 2f, 1f);
        scaley.setRepeatCount(2);

        //play(alpha).with(rotation) 一起执行
        //after(translationx).after(translationy) 一起执行
        //before(scalex).before(scaley) 一起执行
        animatorSet.play(alpha).with(rotation)
                .after(translationx).after(translationy)
                .before(scalex).before(scaley);

        animatorSet.setDuration(5000);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("Neo","onAnimationStart");
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("Neo","onAnimationEnd");
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        animatorSet.start();
    }

    /**
     *  自定义button-横向可伸缩
     *
     */
    public void dryView(){
        int width = my_btn.getWidth();
        Log.d("Neo","width=="+width);
        width = 300;
        ObjectAnimator animator = ObjectAnimator.ofObject(my_btn, "widths", new WidthsEvaluator(), width, 600);
        animator.setDuration(1000);
        animator.setRepeatCount(1);
        animator.setRepeatMode(REVERSE);
        animator.start();
    }

    /**
     * 自由落体
     *
     */
    public void freeFall(){
        my_btn.animate().x(0).y(500)
                .setDuration(5000)
                .setInterpolator(new BounceInterpolator());
    }

    /**
     * 帧动画
     *
     */
    public void drawableAnimation(){
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.icons);
                AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
        });

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.stop();
            }
        });
    }
}
