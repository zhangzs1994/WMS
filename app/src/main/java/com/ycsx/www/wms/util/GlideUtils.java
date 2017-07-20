package com.ycsx.www.wms.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ycsx.www.wms.R;

import java.io.File;

/**
 * Created by ZZS_PC on 2017/7/13.
 */
public class GlideUtils {

    public static void loadImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).crossFade().placeholder(R.drawable.detail1).error(R.drawable.detail1).into(iv);
    }

    public static void loadGifImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.detail1).error(R.drawable.detail1).into(iv);
    }


    public static void loadCircleImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).placeholder(R.drawable.detail1).error(R.drawable.detail1).transform(new GlideCircleTransform(context)).into(iv);
    }

    public static void loadRoundCornerImage(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).placeholder(R.drawable.detail1).error(R.drawable.detail1).transform(new GlideRoundTransform(context,10)).into(iv);
    }


    public static void loadImage(Context context, final File file, final ImageView imageView) {
        Glide.with(context)
                .load(file)
                .into(imageView);


    }

    public static void loadImage(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .into(imageView);
    }
}
