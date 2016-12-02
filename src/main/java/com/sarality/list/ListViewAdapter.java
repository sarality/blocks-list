package com.sarality.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.sarality.list.search.DataMatcher;
import com.sarality.list.search.MatchScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to display and manage a ListView
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewAdapter<T, H> extends BaseAdapter implements Filterable {

  private final Activity activity;
  private final List<T> dataList;
  private final List<T> displayList = new ArrayList<>();
  private final ListViewItemRenderer<T, H> renderer;
  private final Filter resultsFilter;

  ListViewAdapter(Activity activity, List<T> dataList, ListViewItemRenderer<T, H> renderer,
      DataMatcher<T> dataMatcher) {
    this.activity = activity;
    this.dataList = dataList;
    this.displayList.addAll(dataList);
    this.renderer = renderer;
    if (dataMatcher != null) {
      this.resultsFilter = new DataFilter(dataMatcher);
    } else {
      this.resultsFilter = null;
    }
  }

  @Override
  public int getCount() {
    return displayList.size();
  }

  @Override
  public T getItem(int position) {
    return displayList.get(position);
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

  @Override
  public Filter getFilter() {
    return resultsFilter;
  }

  public List<T> getDataList() {
    return dataList;
  }

  private class DataFilter extends Filter {
    private final DataMatcher<T> matcher;

    private DataFilter(DataMatcher<T> matcher) {
      this.matcher = matcher;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
      FilterResults results = new FilterResults();
      List<T> filteredDataList = new ArrayList<>();
      String searchText = charSequence.toString().toUpperCase();
      for (T data : dataList) {
        MatchScore score = matcher.matches(data, searchText);
        if (score != null && score.isMatch()) {
          filteredDataList.add(data);
        }
      }
      results.values = filteredDataList;
      results.count = filteredDataList.size();

      return results;
    }

    @Override @SuppressWarnings("unchecked")
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
      List<T> newDataList = (List<T>) filterResults.values;
      displayList.clear();
      displayList.addAll(newDataList);
      notifyDataSetChanged();
    }
  }
}
