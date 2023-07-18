package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.jsoup.Jsoup.connect;

// Shift을(를) 두 번 눌러 전체 검색 대화상자를 열고 'show whitespaces'를 입력한 다음,
// Enter를 누르세요. 그러면 코드 내에서 공백 문자를 확인할 수 있습니다.
public class Main {
    public static void main(String[] args) {

        System.out.println("=====그신 합 : 225 (79/146)");
        testApi2();

    }

    public static void testApi2() {
        String URL = "https://git-scm.com/book/en/v2/GitHub-Maintaining-a-Project";
        Document doc;
        try {
            doc = connect(URL).get();
            Document parseDoc = Jsoup.parse(doc.select("#main").html());
//            System.out.println(doc.select(elem));
//            System.out.println(elem.prepend());

            Iterator<Element> docIter = parseDoc.getAllElements().iterator();

            while (docIter.hasNext()){
                Element el = docIter.next();
                System.out.println(el);
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void testApi() {
        String URL = "https://git-scm.com/book/en/v2/GitHub-Maintaining-a-Project";
        Document doc;
        try {
            doc = connect(URL).get();

            Elements elem = doc.select("#main");
            Elements elements = doc.getAllElements();
//            Iterator<Element> eleIter = elem.iterator();
            Iterator<Element> eleIter = elements.iterator();

            String allText = "";
            while (eleIter.hasNext()) {
                Element ele = eleIter.next();
                System.out.println(ele.attr("id"));
                allText += ele.text();
            }

            //2번뛰어쓰기 제거
//            allText = allText.replace("  ", " ");

            System.out.println(allText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void callUrl() {
//        String URL = "https://finance.naver.com/item/main.nhn?code=005930";
        String URL = "https://git-scm.com/book/en/v2/GitHub-Maintaining-a-Project";
        Document doc;
        try {
            doc = connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Elements elem = doc.select("#main");
//        Elements elements = doc.getAllElements();
//        Iterator<Element> eleIter = elem.iterator();

        ArrayList<String> arrExceptClass = new ArrayList<>();
        arrExceptClass.add("listingblock");
        arrExceptClass.add("imageblock");
        arrExceptClass.add("language-console");

        Elements elements = doc.select("#main .paragraph");
        Iterator<Element> eleIter = elements.iterator();
//        System.out.println(doc.select("#main .paragraph"));

        List<String> arrWord = new ArrayList<>();

        String allText = "";
        String temp = "";
        while(eleIter.hasNext()) {
            Element ele = eleIter.next();
            temp = ele.text();
            temp = temp.replaceAll("[.,:\"]", "");
            allText += temp+"\r\n";

            List<String> words = Arrays.asList(temp.split(" "));
            Iterator iter = words.iterator();
            while (iter.hasNext()) {
                try {
                    String tempWord = (String) iter.next();
                    System.out.println("tempWord:" + tempWord);
//                    if( tempWord.indexOf("’") > 0 ) {
                    if( tempWord.contains("’") ) {
                        iter.remove();
                    }
                } catch (Exception e) {
                    System.out.println("=============");
                    System.out.println(e);
                }
            }

//            arrWord.addAll(Arrays.asList(words.stream().toString()));
        }

//        System.out.println(arrWord.size());
//        List<String> newList = arrWord.stream().distinct().collect(Collectors.toList());
//        System.out.println(newList.size());
//        System.out.println(newList);

        //2번뛰어쓰기 제거
        allText = allText.replace("  ", " ");
//        System.out.println(allText);
    }

    public static void getCrawingText() {
        String URL = "https://git-scm.com/book/en/v2/GitHub-Maintaining-a-Project";
        Document doc;
        try {
            doc = connect(URL).get();

            Elements elem = doc.select("#main");
            Elements elements = doc.getAllElements();
            Iterator<Element> eleIter = elem.iterator();

            String allText = "";
            while (eleIter.hasNext()) {
                Element ele = eleIter.next();
                allText += ele.text();
            }

            //2번뛰어쓰기 제거
            allText = allText.replace("  ", " ");
            System.out.println(allText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
