package com.sarality.list;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import androidx.cursoradapter.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarality.datasource.ContentProviderSource;

/**
 * Adapter to display and manage a ListView that access data using a Cursor
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewCursorAdapter<T, H> extends CursorAdapter {

  private final Activity activity;
  private final ListViewItemRenderer<T, H> renderer;
  private final ContentProviderSource<T> contentProviderSource;

  public ListViewCursorAdapter(Activity activity, Cursor cursor, ListViewItemRenderer<T, H> renderer,
      ContentProviderSource<T> contentProviderSource) {
    super(activity, cursor, true);
    this.activity = activity;
    this.renderer = renderer;
    this.contentProviderSource = contentProviderSource;
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(activity);
    T data = contentProviderSource.getData(cursor);
    int position = cursor.getPosition();
    View rowView = inflater.inflate(renderer.getRowLayout(position, data), null);

    H holder = renderer.createViewHolder(rowView);
    if (holder != null) {
      renderer.storeViewHolder(rowView, holder);
    }
    return rowView;
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    H viewHolder = renderer.retrieveViewHolder(view);
    T data = contentProviderSource.getData(cursor);
    int position = cursor.getPosition();
    renderer.render(view, viewHolder, position, data);
  }
}
