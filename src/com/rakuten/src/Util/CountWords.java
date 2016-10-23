package com.rakuten.src.Util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chetantonde on 10/21/16.
 **/

public class CountWords {
    public static Map<String, Integer> countWords(String text) throws IOException {
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        
        // Create BufferedReader so the words can be counted.
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(textBytes));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        
        // Read each line and split on deliminators.
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.toLowerCase().split("[^A-ZÃƒâ€¦Ãƒâ€žÃƒâ€“a-zÃƒÂ¥ÃƒÂ¤ÃƒÂ¶]+");
            for (String word : words) {
                if ("".equals(word)) {
                    continue;
                }
                // Find if word exists if not create with count one else increment count.
                Integer findWord = countMap.get(word);
                if (findWord == null) {
                    countMap.put(word, 1);
                } else countMap.put(word, findWord + 1);
            }
        }
        reader.close();
        return countMap;
    }
}
