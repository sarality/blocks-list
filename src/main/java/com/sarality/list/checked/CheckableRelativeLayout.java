package com.sarality.list.checked;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * A RelativeLayout that is checkable so that it can be used in a Selectable List.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {
  private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
  private boolean mChecked = false;

  public CheckableRelativeLayout(Context context) {
    super(context);
  }

  public CheckableRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void setChecked(boolean b) {
    if (b != mChecked) {
      mChecked = b;
      refreshDrawableState();
    }
  }

  @Override
  public boolean isChecked() {
    return mChecked;
  }

  @Override
  public void toggle() {
    setChecked(!mChecked);
  }

  @Override
  public int[] onCreateDrawableState(int extraSpace) {
    final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
    if (isChecked()) {
      mergeDrawableStates(drawableState, CHECKED_STATE_SET);
    }
    return drawableState;
  }

  @Override
  public boolean performClick() {
    toggle();
    return super.performClick();
  }
}
