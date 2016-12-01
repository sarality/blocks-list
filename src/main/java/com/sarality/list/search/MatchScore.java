package com.sarality.list.search;

/**
 * The score for how well the search string matched the Data object
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class MatchScore {
  private final boolean isMatch;

  public MatchScore(boolean isMatch) {
    this.isMatch = isMatch;
  }

  public boolean isMatch() {
    return isMatch;
  }
}
