package com.sarality.list;

import android.util.SparseArray;
import android.view.View;

/**
 * Generic implementation of a View Holder that caches children of a View for optimal rendering of a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericViewHolder {

  // Cache of ViewId and View
  private final SparseArray<View> viewCache = new SparseArray<>();

  /**
   * Returns the View for the given ViewId.
   * <p>
   * Returns the cached version or performs a lookup if no view is cached.
   *
   * @param rowView The View for the row to lookup elements in.
   * @param viewId Id of the view to lookup.
   * @return The element with the given Id.
   */
  public View getViewById(View rowView, int viewId) {
    return viewCache.get(viewId);
  }

  /**
   * Lookup view in the given row View and cache it.
   *
   * @param rowView The View to lookup the view id.
   * @param viewId The id of the view to lookup.
   */
  public void cacheViewWithId(View rowView, int viewId) {
    View view = rowView.findViewById(viewId);
    if (view != null) {
      viewCache.put(viewId, view);
    }
  }

  @Override
  public String toString() {
    return viewCache.toString();
  }

  @Override
  public int hashCode() {
    return viewCache.hashCode();
  }
}
