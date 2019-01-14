package com.sarality.list.action;

import android.view.View;

import java.util.List;

/**
 * Interface for classes that keep track of selected item(s) for a List
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListItemSelector {

  void toggleSelection(View view, int pos, long viewId);

  boolean hasSelection();

  int getSelectedPosition();

  View getSelectedView();
}
