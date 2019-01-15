package com.sarality.list.action;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Interface for classes that keep track of selected item(s) for a List.
 *
 * NOTE: An implementation can take in an instance of the {@link com.sarality.list.ListViewItemRenderer} and
 * {@link com.sarality.list.ListInstance} if it needs access to the Data or View Holder for the position.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListItemSelector {

  void toggleSelection(AdapterView<?> adapterView, View view, int pos, long viewId);

  boolean hasSelection();

  int getSelectedPosition();

  View getSelectedView();

  boolean hasMultipleSelections();

  List<Integer> getSelectedPositions();
}
