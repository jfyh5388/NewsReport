package com.jfyh5388.newsreport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by nxf31834 on 7/23/2017.
 */

public class NewsLoader  extends AsyncTaskLoader<List<News>> {
    /** 日志消息标签 */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** 查询 URL */
    private String mUrl;
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
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
        // 执行网络请求、解析响应和提取地震列表。
        List<News> earthquakes = QueryUtils.fetchNewsData(mUrl);
        return earthquakes;
    }
}
