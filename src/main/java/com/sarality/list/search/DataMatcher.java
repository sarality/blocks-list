package com.sarality.list.search;

/**
 * Interface for classes that indicate whether a data object matches a given search String.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface DataMatcher<T> {

  /**
   * Performs the match between the Data object and the searchText
   * @param data Data to match against
   * @param searchText Search Query as a String
   * @return Score associated with the match.
   */
  MatchScore matches(T data, String searchText);
}
