package com.jfyh5388.newsreport;

import android.graphics.Bitmap;

/**
 * Created by nxf31834 on 7/23/2017.
 */

public class News {

    private Bitmap mBitmap;

    /** Time of the earthquake */
    private String mCategory;

    private String mTitle;

    /** Time of the earthquake */
    private String mTime;

    private String mDocurl;

    public News(Bitmap fBitmap, String fCategory, String fTitle, String fTime, String fUrl)
    {
        mBitmap = fBitmap;
        mCategory = fCategory;
        mTitle = fTitle;
        mTime = fTime;
        mDocurl = fUrl;;
    }
    public Bitmap getmBitmap()
    {
        return mBitmap;
    }
    public String getmTitle()
    {
        return mTitle;
    }
    public String getmCategory()
    {
        String catChinese = "";
        switch (mCategory) {
            case "war":
                catChinese = "战争";
                break;
            case "sport":
                catChinese = "休育";
                break;
            case "tech":
                catChinese = "科技";
                break;
            case "edu":
                catChinese = "教育";
                break;
            case "ent":
                catChinese = "娱乐";
                break;
            case "money":
                catChinese = "金融";
                break;
            case "gupiao":
                catChinese = "股票";
                break;
            case "travel":
                catChinese = "旅游";
                break;
            case "lady":
                catChinese = "女人";
                break;
            default:
                break;
        }
        return catChinese;
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
