package com.jfyh5388.newsreport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by nxf31834 on 7/23/2017.
 */

public class NewsLoader  extends AsyncTaskLoader<List<News>> {
    /** 日志消息标签 */
    private static final String LOG_TAG = NewsLoader.class.getName();
    private static Document doc = null;
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";

    /** 查询 URL */
    private String mUrl;
    private String mCategory;
    public NewsLoader(Context context, String url, String category) {
        super(context);
        mUrl = url;
        mCategory = category;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        if(doc == null) {
            Connection conn = Jsoup.connect(mUrl);
            conn.header("User-Agent", userAgent);
            try {
                doc = conn.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 执行网络请求、解析响应和提取地震列表。
        List<News> news = QueryUtils.fetchNews(doc, mUrl, mCategory);
        return news;
    }
}
