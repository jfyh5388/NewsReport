package com.jfyh5388.newsreport;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by nxf31834 on 7/23/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, List<News> news) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, news);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final News currentNews= getItem(position);

        Bitmap bitmap = currentNews.getmBitmap();
        ImageView img = (ImageView) listItemView.findViewById(R.id.image);
        img.setImageBitmap(bitmap);
        if(bitmap == null)
            img.setVisibility(View.GONE);


        String category = currentNews.getmCategory();

        // Find the TextView in the earthquake_list_item.xmlst_item.xml layout with the ID version_name
        TextView categoryView = (TextView) listItemView.findViewById(R.id.category);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        categoryView.setText(category);



        String title  = currentNews.getmTitle();

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(title);

        // 根据点击数创建一个新的 String 对象
        String hit = currentNews.getmHit();

        // 找到视图 ID 为 date 的 TextView
        TextView timeView = (TextView) listItemView.findViewById(R.id.hit);
        timeView.setText(hit);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
