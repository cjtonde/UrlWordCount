package com.rakuten.test;

import com.rakuten.src.Util.CountWords;
import com.rakuten.src.dao.data.model.RunsDao;
import com.rakuten.src.dao.data.model.WordCountDao;
import com.rakuten.src.dao.data.impl.RunsDaoImpl;
import com.rakuten.src.dao.data.impl.WordCountDaoImpl;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

public class UrlWordCountIntegrationTest {
    public static void main(String[] args) throws SQLException, IOException, BoilerpipeProcessingException {
        String user = "root";
        String password = "QNLM5k3m";
        String jdbcUrl = "jdbc:mysql://localhost:3306/rakuten";
        String scrapeUrl = "https://en.wikipedia.org/wiki/Main_Page";
        
        final URL url = new URL(scrapeUrl);
        System.out.println("Downloading..." + url.toString());
        String html = ArticleExtractor.INSTANCE.getText(url);
        
        //  Store runs in runs table.
        RunsDao runDao = new RunsDaoImpl(user, password, jdbcUrl);
        int urlId = runDao.createRun(scrapeUrl);
        
        // Count words
        Map<String, Integer> countWords = CountWords.countWords(html);
        
        // Store word counts in wordcount table.
        WordCountDao wordCountDao = new WordCountDaoImpl(user, password, jdbcUrl);
        wordCountDao.createWordCount(urlId, countWords);
        System.out.println("Done!");
    }
}