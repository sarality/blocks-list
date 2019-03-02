package com.sarality.list;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.sarality.datasource.ContentProviderSource;
import com.sarality.db.query.Query;

/**
 * Creates the loader to asynchronously load data from a ContentProvider.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewCursorInitializer<T> implements LoaderManager.LoaderCallbacks<Cursor>{

  private final Activity context;
  private final ContentProviderSource<T> dataSource;
  private final ListViewInitializer<T, ?> initializer;

  ListViewCursorInitializer(Activity context, ContentProviderSource<T> dataSource,
      ListViewInitializer<T, ?> initializer){
    this.context = context;
    this.dataSource = dataSource;
    this.initializer = initializer;
  }

  @Override
  public @NonNull Loader<Cursor> onCreateLoader(int id, Bundle args) {
    Query query = dataSource.getQuery();
    return new CursorLoader(context, dataSource.getContentProviderUri(),
        null, query.getWhereClause(), query.getWhereClauseArguments(), query.getOrderByClause());
  }

  @Override
  public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
    initializer.setCursor(cursor);
  }

  @Override
  public void onLoaderReset(@NonNull Loader<Cursor> loader) {
    initializer.setCursor(null);
  }
}
