package com.sarality.list.impl;

import android.view.View;
import android.widget.CheckBox;

import com.sarality.action.ActionContext;
import com.sarality.action.ViewAction;
import com.sarality.list.R;

import java.util.Locale;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Abstract Base Class of the List View Item Renderer where each item has a secondary action
 *
 * @author satya (satya puniani)
 */

public abstract class BaseIconListItemRenderer<T, H extends IconListItemViewHolder>
    extends CommonListItemRenderer<T, H> {

  private final boolean displayTimeStamp;

  public BaseIconListItemRenderer(boolean displayLine2, boolean displayLine3) {
    this(R.layout.icon_list_item, displayLine2, displayLine3, false);
  }

  public BaseIconListItemRenderer(int itemLayoutId, boolean displayLine2, boolean displayLine3) {
    this(itemLayoutId, displayLine2, displayLine3, false);
  }

  public BaseIconListItemRenderer(int itemLayoutId, boolean displayLine2,
      boolean displayLine3, boolean displayTimeStamp) {
    super(itemLayoutId, displayLine2, displayLine3);
    this.displayTimeStamp = displayTimeStamp;
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

    if (displayTimeStamp) {
      DateTime receivedTime = getReceivedTimeStamp(position, data);
      viewHolder.receivedTimeTextView.setText(formatTimeStamp(receivedTime));
      viewHolder.receivedTimeTextView.setVisibility(View.VISIBLE);
    } else {
      viewHolder.receivedTimeTextView.setVisibility(View.GONE);
    }

  }

  @Override
  public H createViewHolder(View view) {
    H viewHolder = super.createViewHolder(view);
    viewHolder.iconActionCheckBox = view.findViewById(R.id.list_item_secondary_action);
    viewHolder.receivedTimeTextView = view.findViewById(R.id.list_item_received_time);
    return viewHolder;
  }

  private String formatTimeStamp(DateTime timeStamp) {
    if (timeStamp == null) {
      return "-";
    }

    DateTime today = DateTime.today(TimeZone.getDefault());
    DateTime now = DateTime.now(TimeZone.getDefault());
    Integer numDays = timeStamp.numDaysFrom(today);
    if (numDays < 0) {
      numDays = numDays * -1;
      return numDays.toString() + " days later";

    }

    if (numDays == 0) {
      return timeStamp.format("h:mm a", Locale.getDefault());
    }

    if (numDays == 1) {
      DateTime oneLessDay = now.minusDays(1);

      if (oneLessDay.lteq(timeStamp)) {
        Long numSeconds = timeStamp.numSecondsFrom(now);
        if (numSeconds < 3600) {
          return (numSeconds / 60) + " minute(s) ago";
        }
        return (numSeconds / 3600) + " hour(s) ago";
      }

      return "Yesterday";
    }

    return numDays.toString() + " days ago";


  }

  protected abstract DateTime getReceivedTimeStamp(int position, T data);

  protected abstract ViewAction setupSecondaryAction(int position, T data);

  protected abstract boolean getIconActionState(int position, T data);

  protected abstract int getIconDrawable(int position, T data);

}
