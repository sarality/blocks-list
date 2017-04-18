package com.sarality.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sarality.datasource.DataSource;
import com.sarality.form.FormBindings;

import java.util.ArrayList;
import java.util.List;

/**
 * Initializer for an Editable List based on a LinearLayout that displays all rows
 *
 * @author satya@ (Satya Puniani)
 */
public class EditableListViewInitializer<T, H> {

  private final Activity activity;
  private final LinearLayout listView;
  private final EditableListViewItemRenderer<T, H> renderer;
  private List<FormBindings> bindingsList = new ArrayList<>();

  private View emptyListView = null;

  //TODO(Satya) - We need to make changes to this so it uses a ListView instead of a LinearLayout - will require work
  // on saving/loading data from formdata when views are recycled.

  public EditableListViewInitializer(Activity activity, int listViewId, EditableListViewItemRenderer<T, H> renderer) {
    this(activity, (LinearLayout) activity.findViewById(listViewId), renderer);
  }

  public EditableListViewInitializer(Activity activity, LinearLayout listView,
      EditableListViewItemRenderer<T, H> renderer) {
    this.activity = activity;
    this.listView = listView;
    this.renderer = renderer;
  }

  public EditableListViewInitializer<T, H> withEmptyList(int viewId) {
    emptyListView = activity.findViewById(viewId);
    return this;
  }

  public void init(DataSource<List<T>> dataSource) {
    dataSource.load();
    render(dataSource.getData());
  }

  public List<FormBindings> getBindingsList() {
    List<FormBindings> list = new ArrayList<>();
    list.addAll(bindingsList);
    return list;
  }

  void render(List<T> dataList) {
    if (dataList == null || dataList.isEmpty()) {
      emptyListView.setVisibility(View.VISIBLE);
      listView.setVisibility(View.GONE);
      return;
    }
    emptyListView.setVisibility(View.GONE);
    listView.setVisibility(View.VISIBLE);

    LayoutInflater inflater = LayoutInflater.from(activity);
    int position = 0;
    listView.removeAllViews();
    bindingsList.clear();

    for (T data : dataList) {
      View rowView = inflater.inflate(renderer.getRowLayout(position, data), null);
      H viewHolder = renderer.createViewHolder(rowView);
      renderer.render(rowView, viewHolder, position, data);

      listView.addView(rowView);
      bindingsList.add(renderer.getBindings());
      position++;
    }
  }
}
