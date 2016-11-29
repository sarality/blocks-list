package com.sarality.list.action;

import android.view.View;

/**
 * Keeps track of the selection for a List
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListItemSelector {

  private final int highlightResId;

  private boolean isSelected = false;
  private int selectedPosition = -1;
  private View selectedView = null;

  public ListItemSelector(int highlightResId) {
    this.highlightResId = highlightResId;
  }

  public ListItemSelector() {
    this.highlightResId = android.R.drawable.list_selector_background;
  }

  public boolean toggleSelection(View view, int pos, long viewId) {
    if (selectedView != null) {
      selectedView.setSelected(false);
    }
    if (pos == selectedPosition) {
      if (isSelected) {
        unhighlightRow(selectedView);
        isSelected = false;
        selectedView = null;
        selectedPosition = -1;
      } else {
        isSelected = true;
        selectedView = view;
        selectedView.setSelected(true);
        selectedPosition = pos;
        highlightRow(selectedView);
      }
    } else {
      unhighlightRow(selectedView);
      isSelected = true;
      selectedView = view;
      selectedView.setSelected(true);
      selectedPosition = pos;
      highlightRow(selectedView);
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

  public void highlightRow(View view) {
    if (view != null) {
      view.setBackgroundResource(highlightResId);
    }
  }

  private void unhighlightRow(View view) {
    if (view != null) {
      view.setBackgroundResource(android.R.color.transparent);
    }
  }
}
