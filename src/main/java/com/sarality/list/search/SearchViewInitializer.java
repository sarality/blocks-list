package com.sarality.list.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;

import com.sarality.list.ListInstance;

/**
 * Initializer for a SearchView in a Menu Bar
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SearchViewInitializer {

  private final Activity activity;
  private final int searchViewId;
  private final ListInstance listInstance;

  public SearchViewInitializer(Activity activity, int searchViewId, ListInstance listInstance) {
    this.activity = activity;
    this.searchViewId = searchViewId;
    this.listInstance = listInstance;
  }

  public void init(Menu menu) {
    initSearchView((SearchView) menu.findItem(searchViewId).getActionView());
  }

  public void init() {
    initSearchView((SearchView) activity.findViewById(searchViewId));
  }

  private void initSearchView(SearchView searchView) {
    SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
    searchView.setOnQueryTextListener(new QueryTextListener());
  }

  private class QueryTextListener implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextSubmit(String query) {
      return true; // Already handled by Instant Results
    }

    @Override
    public boolean onQueryTextChange(String newText) {
      listInstance.filter(newText);
      return true;
    }
  }

}
