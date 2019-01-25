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
  private final boolean moveDown;
  private final ListItemSelector selector;
  private final ListInstance<T, ?> listInstance;

  public MoveListRowAction(boolean moveDown, ListItemSelector selector, ListInstance<T, ?> listInstance) {
    this.moveDown = moveDown;
    this.selector = selector;
    this.listInstance = listInstance;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    int step = -1;
    if (moveDown) {
      step = 1;
    }
    int pos = selector.getSelectedPosition();
    int swapPos = pos + step;
    List<T> currentDataList = listInstance.getDataList();
    if (swapPos < 0 || swapPos >= currentDataList.size()) {
      return true;
    }
    List<T> dataList = new ArrayList<>();
    int ctr = 0;
    for (T currentData : currentDataList) {
      if (ctr == pos) {
        dataList.add(currentDataList.get(swapPos));
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
    return true;
  }
}
