package com.sarality.list.action;

import android.view.View;
import android.widget.AdapterView;

import com.sarality.action.ViewAction;

/**
 * Interface for all actions on a ListView
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListViewAction extends ViewAction {

  boolean perform(AdapterView<?> adapterView, View view, int pos, long viewId);
}
