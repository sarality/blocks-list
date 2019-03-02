package com.sarality.list.action.intent;

import android.view.View;

import com.sarality.action.intent.ViewValueExtractor;
import com.sarality.list.ListInstance;
import com.sarality.list.action.ListItemSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Extracts the selected values from a List of values.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SelectedListValuesExtractor<T> implements ViewValueExtractor<List<T>>{
  private final ListInstance<T, ?> listInstance;
  private final ListItemSelector selector;

  public SelectedListValuesExtractor(ListInstance<T, ?> listInstance, ListItemSelector selector) {
    this.listInstance = listInstance;
    this.selector = selector;
  }

  @Override
  public List<T> extract(View view) {
    List<T> dataList = listInstance.getDataList();
    List<T> valueList = new ArrayList<>();
    for (Integer position : selector.getSelectedPositions()) {
      valueList.add(dataList.get(position));
    }
    return valueList;
  }
}
