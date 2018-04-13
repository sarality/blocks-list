package com.sarality.list.impl;

import com.sarality.list.R;

/**
 * Implementation of the List View Item Renderer for a Simple Layout with one to three text lines.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class SimpleListItemRenderer<T> extends CommonListItemRenderer<T, SimpleListItemViewHolder> {

  public SimpleListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.simple_list_item, displayLine2, displayLine3);
  }

  public SimpleListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId, displayLine2, displayLine3);
  }

  @Override
  protected final SimpleListItemViewHolder newViewHolder() {
    return new SimpleListItemViewHolder();
  }
}
