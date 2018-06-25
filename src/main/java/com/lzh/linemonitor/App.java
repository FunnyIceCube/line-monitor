package com.lzh.linemonitor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Document doc = Jsoup.connect("http://bangumi.tv/anime/browser/tv/?sort=date&page=1").get();
            Elements titles = doc.select("div.inner a");
            for (Element element : titles) {
                System.out.println(element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
