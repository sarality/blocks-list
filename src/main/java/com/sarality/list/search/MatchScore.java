package com.sarality.list.search;

/**
 * The score for how well the search string matched the Data object
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class MatchScore {
  private final boolean isMatch;
  private final double score;

  public MatchScore(boolean isMatch) {
    this(isMatch, isMatch ? 1.0: 0.0);
  }

  public MatchScore(boolean isMatch, double score) {
    this.isMatch = isMatch;
    this.score = score;
  }

  public boolean isMatch() {
    return isMatch;
  }

  public double getScore() {
    return score;
  }
}
