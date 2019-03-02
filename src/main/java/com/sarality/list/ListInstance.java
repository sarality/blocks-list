package com.sarality.list;

import android.widget.ListView;

import java.util.List;

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

  public void filter(String filterText) {
    listViewAdapter.getFilter().filter(filterText);
  }

  public void refresh() {
    listViewAdapter.notifyDataSetChanged();
  }

  public List<T> getDataList() {
    if (listViewAdapter == null) {
      return null;
    }
    return listViewAdapter.getDataList();
  }

  public void setDataList(List<T> sourceList) {
    listViewAdapter.setDataList(sourceList);
  }

  public int getDisplayCount() {
    return listViewAdapter.getCount();
  }

  public List<T> getFilteredList() {
    return listViewAdapter.getFilteredList();
  }


  public void performItemClick(int pos) {
    listView.performItemClick(listViewAdapter.getView(pos, null, null),
        pos, listViewAdapter.getItemId(pos));
  }
}
