package com.sarality.list.search;

/**
 * A Pair of an Item and its MatchScore
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ScoredListItem<T> {
  private final T item;
  private final MatchScore score;

  ScoredListItem(T item, MatchScore score) {
    this.item = item;
    this.score = score;
  }

  public T getItem() {
    return item;
  }

  MatchScore getMatchScore() {
    return score;
  }
}
