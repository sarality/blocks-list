package com.sarality.list.action;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarality.action.ActionContext;
import com.sarality.action.ViewAction;
import com.sarality.list.ListViewItemRenderer;

/**
 * Action that adds a row to an existing Row.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class AddListItemAction<T, H> implements ViewAction {
  private final Activity activity;
  private final int listViewId;
  private final ListViewItemRenderer<T, H> renderer;
  private final T itemData;

  public AddListItemAction(Activity activity, int listViewId, ListViewItemRenderer<T, H> renderer, T itemData) {
    this.activity = activity;
    this.listViewId = listViewId;
    this.renderer = renderer;
    this.itemData = itemData;
  }

  @Override
  public boolean perform(ActionContext actionContext) {
    ViewGroup listView = activity.findViewById(listViewId);
    int position = listView.getChildCount();
    LayoutInflater inflater = LayoutInflater.from(activity);

    View rowView = inflater.inflate(renderer.getRowLayout(position, itemData), null);
    H viewHolder = renderer.createViewHolder(rowView);
    renderer.render(rowView, viewHolder, position, itemData);

    listView.addView(rowView);
    return false;
  }
}
