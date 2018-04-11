package com.sarality.list.impl;

import android.view.View;

import com.sarality.list.R;

/**
 * Implementation of the List View Item Renderer where each item has a secondary action
 *
 * @author satya (satya puniani)
 */

public abstract class SecondaryActionListitemRender<T> extends SimpleListItemRender<T> {

  public SecondaryActionListitemRender(boolean displayLine2, boolean displayLine3) {
    this(R.layout.secondary_action_list_item, displayLine2, displayLine3);
  }

  public SecondaryActionListitemRender(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId, displayLine2, displayLine3);
  }

  @Override
  public void render(View view, CommonListItemViewHolder viewHolder, int position, T data) {
    super.render(view, viewHolder, position, data);

    viewHolder.secondaryActionCheckbox.setChecked(getSecondaryActionState(position, data));

    int drawableResId = getSecondaryActionDrawable(position, data);
    if (drawableResId > 0) {
      viewHolder.secondaryActionCheckbox.setButtonDrawable(drawableResId);
    }

    View.OnClickListener secondaryActionListener = getSecondaryActionListener(position, data);
    if (secondaryActionListener != null) {
      viewHolder.secondaryActionCheckbox.setOnClickListener(secondaryActionListener);
    }
  }

  @Override
  public CommonListItemViewHolder createViewHolder(View view) {
    CommonListItemViewHolder viewHolder = super.createViewHolder(view);
    viewHolder.secondaryActionCheckbox = view.findViewById(R.id.list_item_secondary_action);
    return viewHolder;
  }

  protected abstract View.OnClickListener getSecondaryActionListener(int position, T data);

  protected abstract boolean getSecondaryActionState(int position, T data);

  protected abstract int getSecondaryActionDrawable(int position, T data);
}
