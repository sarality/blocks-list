package com.sarality.list;

import android.view.View;

/**
 * Base implementation of a {@link ListViewItemRenderer} with the same view used for each item.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseListViewItemRenderer<T, H> implements ListViewItemRenderer<T, H> {

  private final int rowLayout;

  public BaseListViewItemRenderer(int rowLayout) {
    this.rowLayout = rowLayout;
  }

  @Override
  public int getRowLayout(int position, T value) {
    return rowLayout;
  }

  @Override
  public void storeViewHolder(View view, H viewHolder) {
    view.setTag(viewHolder);
  }

  @Override @SuppressWarnings("unchecked")
  public H retrieveViewHolder(View view) {
    return (H) view.getTag();
  }

  @Override
  public int getViewTypeCount() {
    return 1;
  }

  @Override
  public int getViewType(int position, T value) {
    return 1;
  }
}
