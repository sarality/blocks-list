package com.sarality.list.impl;

import android.view.View;
import android.widget.CheckBox;

import com.sarality.list.R;

/**
 * Implementation of the List View Item Renderer where each item has a secondary action
 *
 * @author satya (satya puniani)
 */

public abstract class IconListItemRenderer<T> extends CommonListItemRenderer<T, IconListItemViewHolder> {

  public IconListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.icon_list_item, displayLine2, displayLine3);
  }

  public IconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId, displayLine2, displayLine3);
  }

  @Override
  public void render(final View view, final IconListItemViewHolder viewHolder, final int position, final T data) {
    super.render(view, viewHolder, position, data);

    viewHolder.iconActionCheckBox.setChecked(getIconActionState(position, data));

    int drawableResId = getIconDrawable(position, data);
    if (drawableResId > 0) {
      viewHolder.iconActionCheckBox.setButtonDrawable(drawableResId);
    }

    View.OnClickListener secondaryActionListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CheckBox iconView = (CheckBox) view;
        if (!performIconAction(view, position, data)) {
          iconView.toggle();
        }
      }
    };

    viewHolder.iconActionCheckBox.setOnClickListener(secondaryActionListener);
  }


  @Override
  public IconListItemViewHolder createViewHolder(View view) {
    IconListItemViewHolder viewHolder = super.createViewHolder(view);
    viewHolder.iconActionCheckBox = view.findViewById(R.id.list_item_secondary_action);
    return viewHolder;
  }

  @Override
  protected IconListItemViewHolder newViewHolder() {
    return new IconListItemViewHolder();
  }

  protected abstract boolean performIconAction(View view, int position, T data);

  protected abstract boolean getIconActionState(int position, T data);

  protected abstract int getIconDrawable(int position, T data);

}
