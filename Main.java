import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.security.Key;
import java.util.*;

public class Main {

    static ArrayList<String> links = new ArrayList<>();
    static Set<String> unique_tags = new HashSet<>();
    static ArrayList<String> tags = new ArrayList<>();
    static ArrayList<Tag> tagArrayList = new ArrayList<>();

    static ArrayList<String> words = new ArrayList<>();
    static Set<String> unique_words = new HashSet<>();
    static ArrayList<Keyword> keywordArrayList = new ArrayList<>();

    static ArrayList<Integer> prices = new ArrayList<>();

    public static void main(String[] args) {


        /** Add ETSY Product links which are similar to your product in "links" array */
        /** links.add(""); */




        /**----------------------------------------------------------------------------------------------------------*/

        if(links.size() > 0){
            handleLink(links,0);
        }

        for(String tag: unique_tags){
            tagArrayList.add(new Tag(tag,getCount(tags,tag)));
        }
        Collections.sort(tagArrayList);
        System.out.println("TAGS");
        for(Tag tag: tagArrayList){
            System.out.println(tag.getCount() + " - " + tag.getTag());
        }
        System.out.println("--------------------------------------");

        for(String word: unique_words){
            keywordArrayList.add(new Keyword(word,getCount(words,word)));
        }
        Collections.sort(keywordArrayList);
        System.out.println("KEYWORDS");
        for(Keyword keyword : keywordArrayList){
            System.out.println(keyword.getCount() + " - " + keyword.getKeyword());
        }
        System.out.println("--------------------------------------");
        Collections.sort(prices);
        System.out.println("PRICES");
        for(int price: prices){
            System.out.println(price);
        }
        System.out.println("--------------------------------------");

    }

    private static void handleLink(ArrayList<String> links,int index){
        try{
            System.out.print(index + " ");
            Document document = Jsoup.connect(links.get(index)).get();
            Elements elements = document.select("h3.tag-card-title");

            Element h1Element = document.select("h1.wt-text-body-01").first();
            String text = h1Element.text();
            String text1 = text.replace(",","");
            String text2 = text1.replace("|","");
            words.addAll(List.of(text2.split(" ")));
            unique_words.addAll(List.of(text2.split(" ")));

            Element pElement = document.select("p.wt-text-title-largest").first();
            String priceText = pElement.text();
            try{
                int price = Integer.parseInt(priceText.substring(7,priceText.indexOf(".")).replace(",","").trim());
                prices.add(price);
            }catch (Exception e){
                e.printStackTrace();
            }



            for (Element element : elements) {
                String linkText = element.text();
                String en = linkText.toLowerCase(new Locale("en")).trim();
                unique_tags.add(en);
                tags.add(en);
            }

            if(index < links.size() - 1){
                handleLink(links,index + 1);
            }else{
                System.out.println("\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int getCount(ArrayList<String> words, String givenWord){
        int count = 0;
        for(String word : words){
            if(word.equals(givenWord)) count++;
        }
        return count;
    }



}
