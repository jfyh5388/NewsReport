package com.jfyh5388.newsreport;

import android.graphics.Bitmap;

/**
 * Created by nxf31834 on 7/23/2017.
 */

public class News {

    //private Bitmap mBitmap;

    /** Time of the earthquake */
    private String mCategory;

    private String mTitle;

    /** Time of the earthquake */
    private String mTime;

    private String mDocurl;

    public News( String fCategory, String fTitle, String fTime, String fUrl)
    {
//        mBitmap = fBitmap;
        mCategory = fCategory;
        mTitle = fTitle;
        mTime = fTime;
        mDocurl = fUrl;;
    }
//    public Bitmap getmImgurl()
//    {
//        return mBitmap;
//    }
    public String getmTitle()
    {
        return mTitle;
    }
    public String getmCategory()
    {
        return mCategory;
    }
    public String getmTime()
    {
        return mTime;
    }
    public String getmUrl()
    {
        return mDocurl;
    }
}
