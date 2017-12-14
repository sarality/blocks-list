package com.sarality.list.impl;

import android.view.View;

import com.sarality.list.BaseListViewItemRenderer;
import com.sarality.list.R;

/**
 * Implementation of the List View Item Renderer for a Simple Layout with one to three text lines.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class SimpleListItemRender<T> extends BaseListViewItemRenderer<T, CommonListItemViewHolder> {

  private final boolean displayLine2;
  private final boolean displayLine3;

  public SimpleListItemRender(boolean displayLine2, boolean displayLine3) {
    this(R.layout.simple_list_item, displayLine2, displayLine3);
  }

  SimpleListItemRender(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId);
    this.displayLine2 = displayLine2;
    this.displayLine3 = displayLine3;
  }

  @Override
  public void render(View view, CommonListItemViewHolder viewHolder, int position, T data) {
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
  }

  protected abstract String getTitle(int position, T data);

  protected abstract String getLine2(int position, T data);

  protected abstract String getLine3(int position, T data);

  @Override
  public CommonListItemViewHolder createViewHolder(View view) {
    CommonListItemViewHolder holder = new CommonListItemViewHolder();
    holder.titleTextView = view.findViewById(R.id.list_item_title);
    holder.line2TextView = view.findViewById(R.id.list_item_line2);
    holder.line3TextView = view.findViewById(R.id.list_item_line3);
    return holder;
  }
}
