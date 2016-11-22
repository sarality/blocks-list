package com.sarality.list;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sarality.datasource.DataSource;

import java.util.List;

/**
 * Initializer for a List based on a LinearLayout that displays all rows
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class NonScrollingListViewInitializer<T, H> {

  private final Activity activity;
  private final LinearLayout listView;
  private final ListViewItemRenderer<T, H> renderer;

  private View emptyListView = null;

  public NonScrollingListViewInitializer(Activity activity, int listViewId, ListViewItemRenderer<T, H> renderer) {
    this(activity, (LinearLayout) activity.findViewById(listViewId), renderer);
  }

  public NonScrollingListViewInitializer(Activity activity, LinearLayout listView,
      ListViewItemRenderer<T, H> renderer) {
    this.activity = activity;
    this.listView = listView;
    this.renderer = renderer;
  }

  public NonScrollingListViewInitializer<T, H> withEmptyList(int viewId) {
    emptyListView = activity.findViewById(viewId);
    return this;
  }

  public void init(DataSource<List<T>> dataSource) {
    dataSource.load();
    render(dataSource.getData());
  }

  public void initAsync(FragmentActivity activity, int loaderId, DataSource<List<T>> dataSource) {
    NonScrollingListViewDataInitializer<T> dataInitializer =
        new NonScrollingListViewDataInitializer<>(activity, dataSource, this);
    activity.getSupportLoaderManager().initLoader(loaderId, null, dataInitializer).forceLoad();
  }

  void render(List<T> dataList) {
    if (dataList == null || dataList.isEmpty()) {
      emptyListView.setVisibility(View.VISIBLE);
      listView.setVisibility(View.GONE);
      return;
    }
    emptyListView.setVisibility(View.GONE);
    listView.setVisibility(View.VISIBLE);

    LayoutInflater inflater = LayoutInflater.from(activity);
    int position = 0;
    for (T data : dataList) {
      View rowView = inflater.inflate(renderer.getRowLayout(position, data), null);
      H viewHolder = renderer.createViewHolder(rowView);
      renderer.render(rowView, viewHolder, position, data);

      listView.addView(rowView);
      position++;
    }
  }
}
