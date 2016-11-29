package com.sarality.list.action;

import android.view.View;

import com.sarality.action.intent.ActionViewProvider;

/**
 * Provides the View that was selected to perform the action on.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SelectedViewProvider implements ActionViewProvider {

  private final ListItemSelector selector;

  public SelectedViewProvider(ListItemSelector selector) {
    this.selector = selector;
  }

  @Override
  public View getView(View triggerView) {
    return selector.getSelectedView();
  }
}
