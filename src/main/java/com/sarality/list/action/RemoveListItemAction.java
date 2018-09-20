package com.sarality.list.action;

import android.view.View;
import android.view.ViewGroup;

import com.sarality.action.ActionContext;
import com.sarality.action.ViewAction;

/**
 * Action that removes a row from an existing List.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class RemoveListItemAction implements ViewAction {

  private final View itemView;

  public RemoveListItemAction(View itemView) {
    this.itemView = itemView;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    ((ViewGroup) itemView.getParent()).removeView(itemView);
    return true;
  }
}
