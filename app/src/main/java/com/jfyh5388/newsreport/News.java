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
    private String mHit;

    private String mDocurl;

    public News(Bitmap fBitmap, String fCategory, String fTitle, String fHit, String fUrl)
    {
        mBitmap = fBitmap;
        mCategory = fCategory;
        mTitle = fTitle;
        mHit = fHit;
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
            case "news":
                catChinese = "新闻";
                break;
            case "ent":
                catChinese = "娱乐";
                break;
            case "sports":
                catChinese = "体育";
                break;
            case "money":
                catChinese = "财经";
                break;
            case "tech":
                catChinese = "科技";
                break;
            case "auto":
                catChinese = "汽车";
                break;
            case "lady":
                catChinese = "女人";
                break;
            case "travel":
                catChinese = "房产";
                break;
            case "edu":
                catChinese = "教育";
                break;
            case "video":
                catChinese = "视频";
                break;
            default:
                break;
        }
        return catChinese;
    }
    public String getmHit()
    {
        return mHit;
    }
    public String getmUrl()
    {
        return mDocurl;
    }
}
