package com.jfyh5388.newsreport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.LoaderManager;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    /**
     * 地震 loader ID 的常量值。我们可选择任意整数。
     * 仅当使用多个 loader 时该设置才起作用。
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;
    public static final String LOG_TAG = MainActivity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL = "http://wangyi.butterfly.mopaasapp.com/news/api";
    //private static final String USGS_REQUEST_URL =
    //        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";
    /** Adapter for the list of earthquakes */
    private NewsAdapter mAdapter;
    /** 列表为空时显示的 TextView */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 在布局中查找 {@link ListView} 的引用
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // 创建新适配器，将空地震列表作为输入
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // 在 {@link ListView} 上设置适配器
        // 以便可以在用户界面中填充列表
        earthquakeListView.setAdapter(mAdapter);

        // 在 ListView 上设置项目单击监听器，该监听器会向 Web 浏览器发送 intent，
        // 打开包含有关所选地震详细信息的网站。
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 查找单击的当前地震
                News currentEarthquake = mAdapter.getItem(position);

                // 将字符串 URL 转换成 URI 对象（传递到 Intent 构造函数中）
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmUrl());

                // 创建新 intent 以查看地震 URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // 发送 intent 以启动新活动
                startActivity(websiteIntent);
            }
        });
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            // 引用 LoaderManager，以便与 loader 进行交互。
            LoaderManager loaderManager = getLoaderManager();

            // 初始化 loader。传递上面定义的整数 ID 常量并为为捆绑
            // 传递 null。为 LoaderCallbacks 参数（由于
            // 此活动实现了 LoaderCallbacks 接口而有效）传递此活动。
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
        else
        {
            View bar =  findViewById(R.id.loading_spinner);
            bar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String categoryBy = sharedPrefs.getString(
                getString(R.string.settings_category_by_key),
                getString(R.string.settings_category_by_default)
        );

//        String minMagnitude = sharedPrefs.getString(
//                getString(R.string.settings_min_magnitude_key),
//                getString(R.string.settings_min_magnitude_default));
//
//        String maxMagnitude = sharedPrefs.getString(
//                getString(R.string.settings_max_magnitude_key),
//                getString(R.string.settings_max_magnitude_default));
//
//        String itemNumber = sharedPrefs.getString(
//                getString(R.string.settings_item_number_key),
//                getString(R.string.settings_item_number_default));
//

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        //uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("type", categoryBy);
        uriBuilder.appendQueryParameter("page", "1");
//        uriBuilder.appendQueryParameter("orderby", orderBy);
//        uriBuilder.appendQueryParameter("minlatitude", "3");
//        uriBuilder.appendQueryParameter("maxlatitude", "53");
//        uriBuilder.appendQueryParameter("minlongitude", "73");
//        uriBuilder.appendQueryParameter("maxlongitude", "135");

        return new NewsLoader(this, uriBuilder.toString());
    }
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> earthquakes) {

        View bar =  findViewById(R.id.loading_spinner);
        bar.setVisibility(View.GONE);
        // 清除之前地震数据的适配器
        mAdapter.clear();
        // 将空状态文本设置为显示“未发现地震。(No earthquakes found.)”"
        mEmptyStateTextView.setText(R.string.no_news);
        // 如果存在 {@link Earthquake} 的有效列表，则将其添加到适配器的
        // 数据集。这将触发 ListView 执行更新。
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }

    }
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // 重置 Loader，以便能够清除现有数据。
        mAdapter.clear();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
