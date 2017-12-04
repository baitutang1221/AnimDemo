package com.neo.demo.animdemo;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017\12\1 0001.
 */

public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getWidths(){
        return getLayoutParams().width;
    }

    public void setWidths(int width){
        getLayoutParams().width = width;
        requestLayout();
    }

//    public int getHeights(){
//        return getLayoutParams().height;
//    }
//
//    public void setHeights(int height){
//        getLayoutParams().height = height;
//        requestLayout();
//    }

}
