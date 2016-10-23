package com.rakuten.test.util;

import com.rakuten.src.Util.CountWords;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by chetantonde on 10/22/16.
 */

public class CountWordsTest {
    @Test
    public void countWordsTest() throws IOException {
        String text = "one two two. three three. three";
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("one", 1);
        result.put("two", 2);
        result.put("three", 3);
        assertEquals(result, CountWords.countWords(text));
    }
    
}