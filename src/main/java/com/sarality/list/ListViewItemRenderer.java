package com.sarality.list;

import android.view.View;

/**
 * Interface for all classes that render an Item in a ListView
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListViewItemRenderer<T, H> {

  /**
   * Returns the id of the Layout to be used for the row.
   *
   * @param position Index of the row being rendered
   * @param value Data used to generate the row
   * @return Resource Id for the row layout
   */
  int getRowLayout(int position, T value);

  /**
   * Render the View based on the given data.
   *
   * @param view The View to render.
   * @param viewHolder ViewHolder cache of views inside the row for optimized rendering.
   * @param position Position of the item in the List.
   * @param data The data used to render the view.
   */
  void render(View view, H viewHolder, int position, T data);

  /**
   * Creates a cache of the views inside an Item View for performance purposes.
   * <p/>
   * Can return null for simplicity if the list is small and has static data.
   * @param view View of the List View Item
   * @return ViewHolder cache for the Item View
   */
  H createViewHolder(View view);

  /**
   * Store the ViewHolder on the Item View.
   *
   * @param view View to store the holder for,
   * @param viewHolder ViewHolder that needs to be stored.
   */
  void storeViewHolder(View view, H viewHolder);

  /**
   * Retreives the ViewHolder stored on a View.
   *
   * @param view View where the ViewHolder is stored.
   * @return The stored ViewHolder if any.
   */
  H retrieveViewHolder(View view);

  /**
   * @return Number of types of View rendered by the Renderer.
   */
  int getViewTypeCount();


  /**
   * Returns the type of row being rendered for the given position and data.
   *
   * @param position Index of the row being rendered
   * @param value Data used to generate the row
   * @return integer type for the row.
   */
  int getViewType(int position, T value);
}
