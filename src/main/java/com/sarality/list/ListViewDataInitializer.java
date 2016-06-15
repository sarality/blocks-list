package com.sarality.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.sarality.datasource.DataSource;
import com.sarality.datasource.DataSourceLoader;

import java.util.List;

/**
 * Creates the loader to load data from a DataSource and then initializes the ListView with the data once
 * the data has been loaded. Used to asynchronously load data
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewDataInitializer<T> implements LoaderManager.LoaderCallbacks<List<T>>{

  private final DataSource<List<T>> dataSource;
  private final Activity context;
  private final ListViewInitializer<T, ?> initializer;

  public ListViewDataInitializer(Activity context, DataSource<List<T>> dataSource,
      ListViewInitializer<T, ?> initializer){
    this.context = context;
    this.dataSource = dataSource;
    this.initializer = initializer;
  }

  @Override
  public Loader<List<T>> onCreateLoader(int id, Bundle args) {
    return new DataSourceLoader<>(context, dataSource);
  }

  @Override
  public void onLoadFinished(Loader<List<T>> loader, List<T> data) {
    initializer.render(data);
  }

  @Override
  public void onLoaderReset(Loader<List<T>> data) {
    // No need to reset the loader here since the cursor is never handled here.
  }
}
