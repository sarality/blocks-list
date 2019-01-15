package com.sarality.list.action;

import android.view.View;

/**
 * Interface to Render or change the rendering of a Selected List View Item
 *
 * NOTE: If the implementation wants access to the data or the View Holder at the given position is can obtain it by
 * passing in a {@link com.sarality.list.ListInstance} and a {@link com.sarality.list.ListViewItemRenderer}.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface SelectedListItemRenderer {

  void render(boolean isSelected, View view, int pos, long rowId);
}
