package com.sarality.list.action;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.sarality.action.ActionContext;
import com.sarality.action.ActionInitializer;
import com.sarality.action.ViewAction;

/**
 * Registers Click Actions on a Non Scrolling List View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class NonScrollingListItemClickActions implements ActionInitializer {

  private Activity activity;
  private int listViewId;
  private ListViewAction listViewAction;
  private ListItemSelector selector;
  // TODO(abhideep): Add support for clicks on sub item

  public NonScrollingListItemClickActions(Activity activity, int listViewId) {
    this(activity, listViewId, new ListItemSelector());
  }

  public NonScrollingListItemClickActions(Activity activity, int listViewId, ListItemSelector selector) {
    this.activity = activity;
    this.listViewId = listViewId;
    this.selector = selector;
  }

  public NonScrollingListItemClickActions register(ListViewAction listViewAction) {
    if (this.listViewAction != null) {
      throw new IllegalStateException("Cannot register multiple default actions for the same List with Id  " +
          activity.getResources().getResourceName(listViewId));
    }
    this.listViewAction = listViewAction;
    return this;
  }

  public NonScrollingListItemClickActions register(ViewAction viewAction) {
    return register(new ViewActionWrapper(viewAction));
  }

  @Override
  public void init() {
    LinearLayout listView = (LinearLayout) activity.findViewById(listViewId);
    int numChildren = listView.getChildCount();
    for (int i = 0; i < numChildren; i++) {
      View child = listView.getChildAt(i);
      child.setOnClickListener(new Performer(listViewAction, i, child.getId(), selector));
    }
  }

  @Override
  public void init(View parentView) {
    LinearLayout listView = (LinearLayout) parentView;
    int numChildren = listView.getChildCount();
    for (int i = 0; i < numChildren; i++) {
      View child = listView.getChildAt(i);
      child.setOnClickListener(new Performer(listViewAction, i, child.getId(), selector));
    }
  }

  public ListItemSelector getSelector() {
    return selector;
  }

  private class ViewActionWrapper implements ListViewAction {

    private final ViewAction action;

    public ViewActionWrapper(ViewAction action) {
      this.action = action;
    }

    @Override
    public boolean perform(AdapterView<?> adapterView, View view, int pos, long viewId) {
      return perform(new ListActionContext(adapterView, view, pos, viewId));
    }

    @Override
    public boolean perform(ActionContext actionContext) {
      return action.perform(actionContext);
    }
  }
  /**
   * Listens to the Click events and triggers the action to be performed.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  private class Performer implements View.OnClickListener {

    private final ListViewAction action;
    private final int position;
    private final long viewId;
    private final ListItemSelector selector;

    public Performer(ListViewAction action, int position, long viewId, ListItemSelector selector) {
      this.action = action;
      this.position = position;
      this.viewId = viewId;
      this.selector = selector;
    }

    @Override
    public void onClick(View view) {
      selector.toggleSelection(view, position, viewId);
      action.perform(null, view, position, viewId);
    }
  }
}
