package com.sarality.list.action;

import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Keeps track of the selection for a List
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListItemSelector {

  public static final Logger logger = LoggerFactory.getLogger(ListItemSelector.class);

  private boolean isSelected = false;
  private int selectedPosition = -1;
  private View selectedView = null;

  public boolean toggleSelection(View view, int pos, long viewId) {
    if (selectedView != null) {
      selectedView.setSelected(false);
    }
    if (pos == selectedPosition) {
      if (isSelected) {
        isSelected = false;
        selectedView = null;
        selectedPosition = -1;
      } else {
        isSelected = true;
        selectedView = view;
        selectedView.setSelected(true);
        selectedPosition = pos;
      }
    } else {
      isSelected = true;
      selectedView = view;
      selectedView.setSelected(true);
      selectedPosition = pos;
    }
    return true;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public int getSelectedPosition() {
    return selectedPosition;
  }

  public View getSelectedView() {
    return selectedView;
  }
}
