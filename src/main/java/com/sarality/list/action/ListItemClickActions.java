package com.sarality.list.action;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sarality.action.ActionContext;
import com.sarality.action.ActionInitializer;
import com.sarality.action.ViewAction;

/**
 * Registers Click Actions on a View or children of a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListItemClickActions implements ActionInitializer {

  private Activity activity;
  private int listViewId;
  private ListViewAction listViewAction;
  private ListItemSelector selector;
  // TODO(abhideep): Add support for clicks on sub item

  public ListItemClickActions(Activity activity, int listViewId, ListItemSelector selector) {
    this.activity = activity;
    this.listViewId = listViewId;
    this.selector = selector;
  }

  public ListItemClickActions(Activity activity, int listViewId) {
    this(activity, listViewId, null);
  }

  public ListItemClickActions register(ListViewAction listViewAction) {
    if (this.listViewAction != null) {
      throw new IllegalStateException("Cannot register multiple default actions for the same List with Id  " +
          activity.getResources().getResourceName(listViewId));
    }
    this.listViewAction = listViewAction;
    return this;
  }

  public ListItemClickActions register(ViewAction viewAction) {
    return register(new ViewActionWrapper(viewAction));
  }

  @Override
  public void init() {
    ListView listView = (ListView) activity.findViewById(listViewId);
    listView.setOnItemClickListener(new Performer(listViewAction, selector));
  }

  @Override
  public void init(View parentView) {
    ListView listView = (ListView) parentView;
    listView.setOnItemClickListener(new Performer(listViewAction, selector));
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
  private class Performer implements ListView.OnItemClickListener {

    private final ListViewAction action;
    private final ListItemSelector selector;

    public Performer(ListViewAction action, ListItemSelector selector) {
      this.action = action;
      this.selector = selector;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long viewId) {
      if (selector != null) {
        selector.toggleSelection(view, pos, viewId);
      }
      action.perform(adapterView, view, pos, viewId);
    }
  }
}
