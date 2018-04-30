package com.sarality.list.impl;

import com.sarality.list.R;

/**
 * Implementation of the List View Item Renderer where each item has a secondary action
 *
 * @author satya (satya puniani)
 */

public abstract class IconListItemRenderer<T>
    extends BaseIconListItemRenderer<T, IconListItemViewHolder> {


  public IconListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.icon_list_item, displayLine2, displayLine3, false);
  }

  public IconListItemRenderer(boolean displayLine2, boolean displayLine3, boolean displayTimeStamp) {
    this(R.layout.icon_list_item, displayLine2, displayLine3, displayTimeStamp);
  }


  public IconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    this(itemLayoutId, displayLine2, displayLine3, false);
  }

  public IconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3, boolean displayTimeStamp) {
    super(itemLayoutId, displayLine2, displayLine3, displayTimeStamp);
  }
  
  @Override
  protected IconListItemViewHolder newViewHolder() {
    return new IconListItemViewHolder();
  }
}
