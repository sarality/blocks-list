package com.sarality.list.impl;

import android.view.View;

import com.sarality.list.R;

/**
 * Implementation of the List View Item Listener where each item has an Avatar and a secondary action.
 *
 * @author satya@ (Satya Puniani)
 */
public abstract class AvatarWithIconListItemRenderer<T>
    extends BaseIconListItemRenderer<T, AvatarWithIconListItemViewHolder> {

  public AvatarWithIconListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.avatar_secondary_action_list_item, displayLine2, displayLine3, false);
  }

  public AvatarWithIconListItemRenderer(boolean displayLine2, boolean displayLine3, boolean displayTimeStamp) {
    this(R.layout.avatar_secondary_action_list_item, displayLine2, displayLine3, displayTimeStamp);
  }

  public AvatarWithIconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    this(itemLayoutId, displayLine2, displayLine3, false);
  }

  public AvatarWithIconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3,
      boolean displayTimeStamp) {
    super(R.layout.avatar_secondary_action_list_item, displayLine2, displayLine3, displayTimeStamp);
  }

  @Override
  public void render(View view, AvatarWithIconListItemViewHolder viewHolder, int position, T data) {
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
  public AvatarWithIconListItemViewHolder createViewHolder(View view) {
    AvatarWithIconListItemViewHolder holder = super.createViewHolder(view);
    holder.avatarTextView = view.findViewById(R.id.list_item_avatar);
    return holder;
  }

  @Override
  protected AvatarWithIconListItemViewHolder newViewHolder() {
    return new AvatarWithIconListItemViewHolder();
  }
}
