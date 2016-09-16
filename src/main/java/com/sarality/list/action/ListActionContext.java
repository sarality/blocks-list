package com.sarality.list.action;

import android.view.View;
import android.widget.AdapterView;

import com.sarality.action.ActionContext;

/**
 * Action for an action of a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListActionContext extends ActionContext {

  private final AdapterView<?> adapterView;
  private final int position;
  private final long rowId;

  public ListActionContext(AdapterView<?> adapterView, View view, int position, long rowId) {
    super(view);
    this.adapterView = adapterView;
    this.position = position;
    this.rowId = rowId;
  }

  public AdapterView<?> getAdapterView() {
    return adapterView;
  }

  public int getPosition() {
    return position;
  }

  public long getRowId() {
    return rowId;
  }
}
