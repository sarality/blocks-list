package com.sarality.list.impl;

import android.content.res.Resources;
import android.view.View;

import com.sarality.list.BaseListViewItemRenderer;
import com.sarality.list.R;

import java.util.HashMap;

/**
 * Common implementation of the List View Item Renderer with one to three text lines.
 *
 * @author abhideep@ (Abhideep Singh)
 */
abstract class CommonListItemRenderer<T, H extends SimpleListItemViewHolder>
    extends BaseListViewItemRenderer<T, H> {

  private final boolean displayLine2;
  private final boolean displayLine3;
  private View contextView;

  CommonListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.simple_list_item, displayLine2, displayLine3);
  }

  CommonListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId);
    this.displayLine2 = displayLine2;
    this.displayLine3 = displayLine3;
  }

  private void setContextView(View view) {
    this.contextView = view;
  }

  protected Resources getResources() {
    return contextView.getResources();
  }

  @Override
  public void render(View view, H viewHolder, int position, T data) {
    setContextView(view);
    viewHolder.titleTextView.setText(getTitle(position, data));

    if (displayLine2) {
      String line2 = getLine2(position, data);
      if (line2 != null) {
        viewHolder.line2TextView.setText(line2);
      } else {
        viewHolder.line2TextView.setText("");
      }
    } else {
      viewHolder.line2TextView.setVisibility(View.GONE);
    }
    if (displayLine3) {
      String line3 = getLine3(position, data);
      if (line3 != null) {
        viewHolder.line3TextView.setText(line3);
      } else {
        viewHolder.line3TextView.setText("");
      }
    } else {
      viewHolder.line3TextView.setVisibility(View.GONE);
    }

    HashMap<Integer, Object> tagsMap = getTags(position, data);
    if (tagsMap != null) {
      for (Integer resourceId : tagsMap.keySet()) {
        view.setTag(resourceId, tagsMap.get(resourceId));
      }
    }
  }

  protected abstract HashMap<Integer, Object> getTags(int position, T data);

  protected abstract String getTitle(int position, T data);

  protected abstract String getLine2(int position, T data);

  protected abstract String getLine3(int position, T data);

  @Override
  public H createViewHolder(View view) {
    H holder = newViewHolder();
    holder.titleTextView = view.findViewById(R.id.list_item_title);
    holder.line2TextView = view.findViewById(R.id.list_item_line2);
    holder.line3TextView = view.findViewById(R.id.list_item_line3);
    return holder;
  }

  protected abstract H newViewHolder();
}
