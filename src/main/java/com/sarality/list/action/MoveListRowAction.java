package com.sarality.list.action;

import com.sarality.action.ActionContext;
import com.sarality.action.ViewAction;
import com.sarality.list.ListInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Moves a Selected Row in a List, Up or Down
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class MoveListRowAction<T> implements ViewAction {
  private final ListInstance<T, ?> listInstance;
  private final ListItemSelector selector;
  private final int stepSize;

  public MoveListRowAction(ListInstance<T, ?> listInstance, ListItemSelector selector, int stepSize) {
    this.listInstance = listInstance;
    this.selector = selector;
    this.stepSize = stepSize;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    // TODO(abhideep): Add Support for Multi-Select
    int pos = selector.getSelectedPosition();
    // If nothing is selected, no need to do anything
    if (pos < 0) {
      return true;
    }
    // What is the position that the selected item is moved to.
    int swapPos = pos + stepSize;
    List<T> currentDataList = listInstance.getDataList();
    if (swapPos < 0) {
      swapPos = 0;
    }
    if (swapPos >= currentDataList.size()) {
      swapPos = currentDataList.size() - 1;
    }
    // Why do extra work if nothing has changed
    if (pos == swapPos) {
      return true;
    }

    int step = 1;
    int startPos = pos;
    int endPos = swapPos - 1;
    if (stepSize < 0) {
      step = -1;
      startPos = swapPos + 1;
      endPos = pos;
    }
    List<T> dataList = new ArrayList<>();
    int ctr = 0;
    for (T currentData : currentDataList) {
      if (ctr >= startPos && ctr <= endPos) {
        dataList.add(currentDataList.get(ctr + step));
      } else if (ctr == swapPos) {
        dataList.add(currentDataList.get(pos));
      } else {
        dataList.add(currentData);
      }
      ctr++;
    }
    listInstance.setDataList(dataList);
    listInstance.refresh();
    listInstance.performItemClick(swapPos);
    listInstance.scrollToPosition(swapPos);
    return true;
  }
}
