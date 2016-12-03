package com.sarality.list.search;

import java.util.Comparator;

/**
 * Comparator to sort data based on MatchScore of a List of matching objects.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ScoredListItemComparator<T> implements Comparator<ScoredListItem<T>> {
  private final Comparator<T> defaultComparator;

  ScoredListItemComparator(Comparator<T> defaultComparator) {
    this.defaultComparator = defaultComparator;
  }

  @Override
  public int compare(ScoredListItem<T> lhs, ScoredListItem<T> rhs) {
    double lhsScore = lhs.getMatchScore().getScore();
    double rhsScore = rhs.getMatchScore().getScore();

    if (lhsScore == rhsScore) {
      if (defaultComparator != null) {
        return defaultComparator.compare(lhs.getItem(), rhs.getItem());
      } else {
        return 0;
      }
    } else if (lhsScore < rhsScore) {
      return -1;
    } else {
      return 1;
    }
  }
}
