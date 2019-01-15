package com.sarality.list.action;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Selector when only one item can be selected in a List.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class MultipleItemSelector implements ListItemSelector {

  private final SelectedListItemRenderer selectedListItemRenderer;

  private Set<Integer> selectedPositionList = new HashSet<>();

  public MultipleItemSelector(SelectedListItemRenderer selectedListItemRenderer) {
    this.selectedListItemRenderer = selectedListItemRenderer;
  }

  public MultipleItemSelector() {
    this(null);
  }

  public void toggleSelection(AdapterView<?> adapterView, View view, int pos, long viewId) {
    boolean isSelected = !selectedPositionList.contains(pos);
    if (isSelected) {
      selectedPositionList.add(pos);
      view.setSelected(true);
    } else {
      view.setSelected(false);
      selectedPositionList.remove(pos);
    }
    if (selectedListItemRenderer != null) {
      selectedListItemRenderer.render(isSelected, view, pos, viewId);
    }
  }

  @Override
  public boolean hasSelection() {
    return !selectedPositionList.isEmpty();
  }

  @Override
  public boolean hasMultipleSelections() {
    return true;
  }

  @Override
  public int getSelectedPosition() {
    return -1;
  }

  @Override
  public List<Integer> getSelectedPositions() {
    return new ArrayList<>(selectedPositionList);
  }

  @Override
  public View getSelectedView() {
    return null;
  }
}
