package com.sarality.list;

import com.sarality.form.FormBindings;

/**
 * Renderer for a listviewitem in an editable list.
 *
 * @author Satya@ (Satya Puniani)
 */

public interface EditableListViewItemRenderer<T, H> extends ListViewItemRenderer<T, H> {

  /**
   * Returns the FormBindings for this list row.
   *
   * @return FormBindings
   */

  FormBindings getBindings();

}
