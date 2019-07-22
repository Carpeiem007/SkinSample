package com.gg.skinlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import com.gg.skinlib.R;
import com.gg.skinlib.core.SkinManager;
import com.gg.skinlib.model.AttrModel;

public class SkinTextView extends android.support.v7.widget.AppCompatTextView implements ViewsMatch{

    private AttrModel attrModel;
    private boolean isSkin ;

    public SkinTextView(Context context) {
        this(context,null);
    }

    public SkinTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);

    }

    public SkinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrModel = new AttrModel();
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SkinnableTextView,defStyleAttr,0);
        attrModel.saveViewResource(typedArray,R.styleable.SkinnableTextView);
        typedArray.recycle();
    }

    @Override
    public void updateSkin() {
            if(attrModel!=null){
             int background = attrModel.getViewResource(R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_android_background]);
             background = isSkin?getResources().getColor(background):SkinManager.getInstance().getSkinRes(background);
             this.setBackgroundColor(background);
             int textColor = attrModel.getViewResource(R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_android_textColor]);
             textColor = isSkin?getResources().getColor(textColor):SkinManager.getInstance().getSkinRes(textColor);
             this.setTextColor(textColor);
             isSkin = !isSkin;
            }
    }
}
