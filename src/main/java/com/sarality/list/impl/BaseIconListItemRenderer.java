package com.sarality.list.impl;

import android.view.View;
import android.widget.CheckBox;

import com.sarality.action.ActionContext;
import com.sarality.action.ViewAction;
import com.sarality.list.R;

/**
 * Abstract Base Class of the List View Item Renderer where each item has a secondary action
 *
 * @author satya (satya puniani)
 */

public abstract class BaseIconListItemRenderer<T, H extends IconListItemViewHolder>
    extends CommonListItemRenderer<T, H> {

  public BaseIconListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.icon_list_item, displayLine2, displayLine3);
  }

  public BaseIconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId, displayLine2, displayLine3);
  }

  @Override
  public void render(final View view, final H viewHolder, final int position, final T data) {
    super.render(view, viewHolder, position, data);

    viewHolder.iconActionCheckBox.setChecked(getIconActionState(position, data));

    int drawableResId = getIconDrawable(position, data);
    if (drawableResId > 0) {
      viewHolder.iconActionCheckBox.setButtonDrawable(drawableResId);
    }

    final ViewAction secondaryAction = setupSecondaryAction(position, data);

    if (secondaryAction != null) {
      View.OnClickListener secondaryActionListener = new View.OnClickListener() {
        @Override
        public void onClick(View clickedView) {
          CheckBox iconView = (CheckBox) clickedView;
          if (!secondaryAction.perform(new ActionContext(iconView))) {
            iconView.toggle();
          }
        }
      };

      viewHolder.iconActionCheckBox.setOnClickListener(secondaryActionListener);

    }

  }

  @Override
  public H createViewHolder(View view) {
    H viewHolder = super.createViewHolder(view);
    viewHolder.iconActionCheckBox = view.findViewById(R.id.list_item_secondary_action);
    return viewHolder;
  }

  protected abstract ViewAction setupSecondaryAction(int position, T data);

  protected abstract boolean getIconActionState(int position, T data);

  protected abstract int getIconDrawable(int position, T data);

}
