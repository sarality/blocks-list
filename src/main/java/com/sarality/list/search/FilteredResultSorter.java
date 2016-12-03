package com.sarality.list.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sorts the filtered List based on MatchScore
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FilteredResultSorter<T> {
  private final List<ScoredListItem<T>> scoredDataList = new ArrayList<>();
  private final ScoredListItemComparator<T> comparator;

  private List<T> dataList;

  public FilteredResultSorter(Comparator<T> defaultComparator) {
    this.comparator = new ScoredListItemComparator<>(defaultComparator);
  }

  public void addItem(MatchScore score, T item) {
    scoredDataList.add(new ScoredListItem<T>(item, score));
  }

  public List<T> sort() {
    Collections.sort(scoredDataList, comparator);
    dataList = new ArrayList<>();
    for (ScoredListItem<T> scoredListItem: scoredDataList) {
      dataList.add(scoredListItem.getItem());
    }
    return dataList;
  }

  public List<T> getDataList() {
    return dataList;
  }
}
