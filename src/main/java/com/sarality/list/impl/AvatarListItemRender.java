package com.sarality.list.impl;

import android.view.View;

import com.sarality.list.R;

/**
 * Implementation of the List View Item Listener where each item has an Avatar.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class AvatarListItemRender<T> extends SimpleListItemRender<T> {

  public AvatarListItemRender(boolean displayLine2, boolean displayLine3) {
    this(R.layout.avatar_list_item, displayLine2, displayLine3);
  }

  public AvatarListItemRender(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    super(itemLayoutId, displayLine2, displayLine3);
  }

  @Override
  public void render(View view, CommonListItemViewHolder viewHolder, int position, T data) {
    super.render(view, viewHolder, position, data);

    String displayChar = getAvatarChar(position, data);
    if (displayChar != null) {
      viewHolder.avatarTextView.setText(displayChar);
    } else {
      viewHolder.avatarTextView.setText("");
    }
    int backgroundResId = getAvatarBackgroundResource(position, data);
    if (backgroundResId > 0) {
      viewHolder.avatarTextView.setBackgroundResource(backgroundResId);
    }
  }

  protected abstract String getAvatarChar(int position, T data);

  protected abstract int getAvatarBackgroundResource(int position, T data);

  @Override
  public CommonListItemViewHolder createViewHolder(View view) {
    CommonListItemViewHolder holder = super.createViewHolder(view);
    holder.avatarTextView = view.findViewById(R.id.list_item_avatar);
    return holder;
  }
}
