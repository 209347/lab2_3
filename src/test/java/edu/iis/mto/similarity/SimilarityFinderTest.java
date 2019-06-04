package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class SimilarityFinderTest {

    private SequenceSearcherDoubler sequenceSearcherDoubler;

    private SimilarityFinder similarityFinder;

    private HashMap<Integer, SearchResult> map;

    @Before
    public void setup(){
        map = new HashMap<>();
        sequenceSearcherDoubler = new SequenceSearcherDoubler(map);
        similarityFinder = new SimilarityFinder(sequenceSearcherDoubler);
    }

    private void addValueToMap(Integer value, boolean isFound) {
        map.put(value, SearchResult.builder().withFound(isFound).build());
    }

    @Test
    public void jackardSimilarityTest() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {4, 2, 5};

        addValueToMap(1, false);
        addValueToMap(2, true);
        addValueToMap(3, false);

        //3 + 3 - 1 = 5 => jackardSimilarity = 1.0/5.0 = 0.2
        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2), Matchers.equalTo(0.2));
    }

    @Test
    public void jackardSimilarityShouldBeZeroIfSequencesAreDifferentTest() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = {4, 5, 6};

        addValueToMap(1, false);
        addValueToMap(2, false);
        addValueToMap(3, false);

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2), Matchers.equalTo(0.0));
    }

    @Test
    public void jackardSimilarityShouldBeOneIfSequencesAreEqualTest() {
        int[] seq = {1, 2, 3};

        addValueToMap(1, true);
        addValueToMap(2, true);
        addValueToMap(3, true);

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq, seq), Matchers.equalTo(1.0));
    }

    @Test
    public void jackardSimilarityShouldBeOneIfSequencesAreEmptyTest() {
        int[] seq = {};

        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq, seq), Matchers.equalTo(1.0));
    }

    @Test(expected = NullPointerException.class)
    public void jackardSimilarityShouldThrowNullPointerExceptionIfSequenceIsNotInitializedTest() {
        int[] seq1 = {1, 2, 3};
        int[] seq2 = null;

        similarityFinder.calculateJackardSimilarity(seq1, seq2);
    }

    @Test
    public void jackardSimilarityShouldBeCalledForEveryElementInTheFirstSequenceTest() {
        int[] seq1 = {1, 2, 3, 4, 5};
        int[] seq2 = {1, 2, 3};

        addValueToMap(1, true);
        addValueToMap(2, true);
        addValueToMap(3, true);
        addValueToMap(4, false);
        addValueToMap(5, false);

        sequenceSearcherDoubler.setNumberOfCalls(0);
        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        Assert.assertThat(sequenceSearcherDoubler.getNumberOfCalls(), Matchers.equalTo(seq1.length));
    }
}