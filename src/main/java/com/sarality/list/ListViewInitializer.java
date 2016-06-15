package com.sarality.list;

import android.app.Activity;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

import com.sarality.datasource.ContentProviderSource;
import com.sarality.datasource.DataSource;

import java.util.List;

/**
 * Class used to initialize a ListView
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewInitializer<T, H> {

  private final Activity activity;
  private final ListView listView;
  private final ListViewItemRenderer<T, H> renderer;

  // Optional fields
  private ListViewCursorAdapter<T, H> cursorAdapter;

  private View emptyListView = null;

  public ListViewInitializer(Activity activity, int listViewId, ListViewItemRenderer<T, H> renderer) {
    this(activity, (ListView) activity.findViewById(listViewId), renderer);
  }

  public ListViewInitializer(Activity activity, ListView listView, ListViewItemRenderer<T, H> renderer) {
    this.activity = activity;
    this.listView = listView;
    this.renderer = renderer;
  }

  public ListViewInitializer<T, H> withEmptyList(int viewId) {
    emptyListView = activity.findViewById(viewId);
    return this;
  }

  public void init(DataSource<List<T>> dataSource) {
    dataSource.load();
    render(dataSource.getData());
  }

  public void initAsync(FragmentActivity activity, int loaderId, DataSource<List<T>> dataSource) {
    ListViewDataInitializer<T> dataInitializer = new ListViewDataInitializer<>(activity, dataSource, this);
    activity.getSupportLoaderManager().initLoader(loaderId, null, dataInitializer).forceLoad();
  }

  public void initAsync(FragmentActivity activity, int loaderId, ContentProviderSource<T> contentProviderSource) {
    cursorAdapter = new ListViewCursorAdapter<>(activity, null, renderer, contentProviderSource);
    listView.setAdapter(cursorAdapter);

    if (emptyListView != null) {
      listView.setEmptyView(emptyListView);
    }

    ListViewCursorInitializer<T> dataInitializer = new ListViewCursorInitializer<>(activity, contentProviderSource, this);
    activity.getSupportLoaderManager().initLoader(loaderId, null, dataInitializer).forceLoad();
  }

  void setCursor(Cursor cursor) {
    cursorAdapter.swapCursor(cursor);
  }

  void render(List<T> dataList) {
    ListViewAdapter<T, H> listViewAdapter = new ListViewAdapter<>(activity, dataList, renderer);
    listView.setAdapter(listViewAdapter);

    if (emptyListView != null) {
      listView.setEmptyView(emptyListView);
    }
  }
}
