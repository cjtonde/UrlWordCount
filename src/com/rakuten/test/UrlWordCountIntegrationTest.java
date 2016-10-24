package com.rakuten.test;

import com.rakuten.src.Util.CountWords;
import com.rakuten.src.dao.data.impl.RdbDaoImpl;
import com.rakuten.src.dao.data.impl.RunsDaoImpl;
import com.rakuten.src.dao.data.impl.WordCountDaoImpl;
import com.rakuten.src.dao.data.model.RdbDao;
import com.rakuten.src.dao.data.model.RunsDao;
import com.rakuten.src.dao.data.model.WordCountDao;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

public class UrlWordCountIntegrationTest {
    public static void main(String[] args) throws SQLException, IOException, BoilerpipeProcessingException {
        /* Stored in code for now but should be stored in a separate config file or passed as parameter from CLI. */
        String user = "root";
        String password = "QNLM5k3m";
        String jdbcUrl = "jdbc:mysql://localhost:3306/rakuten";
        RdbDao dao = new RdbDaoImpl(user, password, jdbcUrl);
        
        /* Grad the page contents. */
        String scrapeUrl = "https://en.wikipedia.org/wiki/Main_Page";
        final URL url = new URL(scrapeUrl);
        System.out.println("Downloading..." + url.toString());
        String html = ArticleExtractor.INSTANCE.getText(url);
        
        /* Store runs in runs table. */
        RunsDao runDao = new RunsDaoImpl();
        int urlId = runDao.createRun(dao, scrapeUrl);
        
        /* Count words from the test.*/
        Map<String, Integer> countWords = CountWords.countWords(html);
        
        /* Store word counts in the wordcount table. */
        WordCountDao wordCountDao = new WordCountDaoImpl();
        wordCountDao.createWordCount(dao, urlId, countWords);
        System.out.println("Done!");
    }
}