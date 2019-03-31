package edu.iis.mto.similarity;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimilarityFinderTest {

    @Test
    public void jackardSimilarityTest() {
        SequenceSearcherDoubler sequenceSearcherDoubler = new SequenceSearcherDoubler();
        SimilarityFinder similarityFinder = new SimilarityFinder(sequenceSearcherDoubler);

        int[] seq1 = {1, 2, 3};
        int[] seq2 = {4, 2, 5};

        similarityFinder.calculateJackardSimilarity(seq1, seq2);
        //3 + 3 - 1 = 5 => jackardSimilarity = 1.0/5.0 = 0.2
        Assert.assertThat(similarityFinder.calculateJackardSimilarity(seq1, seq2), Matchers.equalTo(0.2));
    }
}