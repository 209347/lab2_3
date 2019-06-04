package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.Map;

public class SequenceSearcherDoubler implements SequenceSearcher {

    private int numberOfCalls;

    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(int numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    private Map<Integer, SearchResult> map;

    SequenceSearcherDoubler(Map<Integer, SearchResult> map) {
        this.map = map;
    }

    @Override
    public SearchResult search(int key, int[] seq) {
        numberOfCalls++;
        return map.get(key);
    }
}
