package com.sarality.list;

import android.widget.ListView;

/**
 * An instance of the List initialized by the ListViewInitializer
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListInstance<T, H> {
  private final ListView listView;
  private ListViewAdapter<T, H> listViewAdapter;

  public ListInstance(ListView listView) {
    this.listView = listView;
  }

  void setListViewAdapter(ListViewAdapter<T, H> listViewAdapter) {
    this.listViewAdapter = listViewAdapter;
  }

  public ListView getListView() {
    return listView;
  }

  public void filter(String filterText) {
    listViewAdapter.getFilter().filter(filterText);
  }

  public void refresh() {
    listViewAdapter.notifyDataSetChanged();
  }
}
