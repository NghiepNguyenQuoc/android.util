package com.nghiepnguyen.androidutils.widgets;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;


/**
 * Created by nghiep on 11/17/15.
 */
public class MySpannable extends ClickableSpan {

    private boolean isUnderline = true;
    private Context mContext;

    /**
     * Constructor
     */
    public MySpannable(Context mContext, boolean isUnderline) {
        this.isUnderline = isUnderline;
        this.mContext = mContext;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(isUnderline);
        ds.setColor(Color.GREEN);
    }

    @Override
    public void onClick(View widget) {

    }
}