package com.sarality.list.action;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Selector when only one item can be selected in a List.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SingleItemSelector implements ListItemSelector {

  private final int highlightResId;

  private boolean hasSelection = false;
  private int selectedPosition = -1;
  private View selectedView = null;

  public SingleItemSelector(int highlightResId) {
    this.highlightResId = highlightResId;
  }

  public SingleItemSelector() {
    this.highlightResId = 0;
    // We used to use {@code android.R.drawable.list_selector_background} but we now rely on the layout itself
    // to change the highlighting while this class only takes care of tracking the selected positions.
  }

  public void toggleSelection(AdapterView<?> adapterView, View view, int pos, long viewId) {
    if (selectedView != null) {
      selectedView.setSelected(false);
    }
    if (pos == selectedPosition) {
      if (hasSelection) {
        unhighlightRow(selectedView);
        hasSelection = false;
        selectedView = null;
        selectedPosition = -1;
      } else {
        hasSelection = true;
        selectedView = view;
        selectedView.setSelected(true);
        selectedPosition = pos;
        highlightRow(selectedView);
      }
    } else {
      unhighlightRow(selectedView);
      hasSelection = true;
      selectedView = view;
      selectedView.setSelected(true);
      selectedPosition = pos;
      highlightRow(selectedView);
    }
  }

  @Override
  public boolean hasSelection() {
    return hasSelection;
  }

  @Override
  public boolean hasMultipleSelections() {
    return false;
  }

  @Override
  public int getSelectedPosition() {
    return selectedPosition;
  }

  @Override
  public List<Integer> getSelectedPositions() {
    List<Integer> positionList = new ArrayList<>();
    if (hasSelection) {
      positionList.add(selectedPosition);
    }
    return positionList;
  }

  @Override
  public View getSelectedView() {
    return selectedView;
  }

  private void highlightRow(View view) {
    if (view != null && highlightResId > 0) {
      view.setBackgroundResource(highlightResId);
    }
  }

  private void unhighlightRow(View view) {
    if (view != null && highlightResId > 0) {
      view.setBackgroundResource(android.R.color.transparent);
    }
  }
}
