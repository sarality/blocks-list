package com.sarality.list.action;

import android.app.Activity;
import android.widget.Button;

import com.sarality.action.ActionContext;
import com.sarality.action.ViewAction;

/**
 * Toggles the Enable / Disable state of a Button depending of whether an item is selected
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EnableListViewButtonAction implements ViewAction {

  private final Button button;
  private final ListItemSelector selector;

  public EnableListViewButtonAction(Button button, ListItemSelector selector) {
    this.button = button;
    this.selector = selector;
  }

  public EnableListViewButtonAction(Activity activity, int buttonViewId, ListItemSelector selector) {
    this((Button) activity.findViewById(buttonViewId), selector);
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    if (selector.hasSelection()) {
      button.setEnabled(true);
    } else {
      button.setEnabled(false);
    }
    return true;
  }
}
