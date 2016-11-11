package com.nghiepnguyen.androidutils.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by nghiep on 5/20/16.
 */
public class MyURLSpan extends ClickableSpan {

    private Context mContext = null;
    private String mUrl = "";
    private String mTitle = "";

    public MyURLSpan(Context context, String url, String title) {
        this.mContext = context;
        this.mUrl = url;
        this.mTitle = title;
    }

    @Override
    public void onClick(View widget) {
        if (mUrl.startsWith("tel")) {// if url is a phone number
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(mUrl));
            mContext.startActivity(intent);
        }
        if (mUrl.startsWith("mailto")) {// if url is a "mailto"
            String mailId = "";
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", mailId, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            mContext.startActivity(Intent.createChooser(emailIntent, mTitle));

        } else {// if url is a hyperlink
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
            mContext.startActivity(browserIntent);
        }

    }
}
