package com.home.serega2593.music;

/**
 * Created by serega2593 on 9/2/14.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class GrandAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mList;
    private int mLayout;

    public GrandAdapter(Context context, List<T> list, int layout) {
        mContext = context;
        mList = list;
        mLayout = layout;
    }

    public int getCount() {
        if (mList!=null) {
            return mList.size();
        }
        return 0;
    }

    public T getItem(int position) {
        return position >= 0 && position < mList.size() ? mList.get(position) : null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getLayout() {
        LayoutInflater inf = LayoutInflater.from(mContext);
        return inf.inflate(mLayout, null);
    }

    public Context getContext() {
        return mContext;
    }

    public Drawable getDrawable(int id) {
        return mContext.getResources().getDrawable(id);
    }

    public int getColor(int id) {
        return mContext.getResources().getColor(id);
    }

}
