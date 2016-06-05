package com.sarality.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Adapter to display and manage a ListView
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewAdapter<T, H> extends BaseAdapter {

  private final Activity activity;
  private final List<T> dataList;
  private final ListViewItemRenderer<T, H> renderer;

  public ListViewAdapter(Activity activity, List<T> dataList, ListViewItemRenderer<T, H> renderer) {
    this.activity = activity;
    this.dataList = dataList;
    this.renderer = renderer;
  }

  @Override
  public int getCount() {
    return dataList.size();
  }

  @Override
  public T getItem(int position) {
    return dataList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup viewGroup) {
    View rowView = convertView;
    T data = getItem(position);
    if (rowView == null) {
      LayoutInflater inflater = LayoutInflater.from(activity);
      rowView = inflater.inflate(renderer.getRowLayout(position, data), null);

        H holder = renderer.createViewHolder(rowView);
        if (holder != null) {
          renderer.storeViewHolder(rowView, holder);
        }
    }

    H viewHolder = renderer.retrieveViewHolder(rowView);
    renderer.render(rowView, viewHolder, position, data);
    return rowView;
  }
}
