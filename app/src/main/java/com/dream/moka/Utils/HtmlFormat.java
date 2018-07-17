package com.dream.moka.Utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2016/11/10.
 *要注意包不要导错了
 */

public class HtmlFormat {

    public static String getNewContent(String htmltext){
        Document doc= Jsoup.parse(htmltext);
        Elements elements=doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width","100%").attr("height","auto");
        }
        Log.e("tag","doc="+doc.toString());
        return doc.toString();
    }
}
