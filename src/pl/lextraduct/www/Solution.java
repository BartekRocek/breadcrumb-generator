package pl.lextraduct.www;

import java.util.function.Function;

public class Solution {

    public static void main(String[] args) {

        String url0 = "mysite.com/pictures/holidays.html";
        String url1 = "www.codewars.com/users/GiacomoSorbi";
        String url2 = "www.microsoft.com/docs/index.htm";
        String url3 = "mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.htm";
        String url4 = "www.very-long-site_name-to-make-a-silly-yet-meaningful-example.com/users/giacomo-sorbi";
        String separator = " : ";

        String[] arrOfUrls = {url0, url1, url2, url3, url4};

        for (String s : arrOfUrls) {
            System.out.println(generate_bc(s, separator));
        }
    }

    public static String generate_bc(String url, String separator) {

        String home = "<a href=\"/\">HOME</a>";
        String[] urlSection = getUrlArray(url);
        String[] urlArray = new String[urlSection.length];

        Function<String, String> urlFormatter = text -> {

            String pathSequence = "";

            for (int i = 1; i < urlSection.length; i++) {
                pathSequence = pathSequence.concat(separator + incorporateUrlSection(urlSection[i], urlArray));
            }
            return home + pathSequence;
        };
        return urlFormatter.apply(url);
    }

    public static String incorporateUrlSection(String urlSection, String[] urlArray) {

        String finalUrl;

        if (urlSection.contains(".") || isTheLastElement(urlSection, urlArray, urlArray.length)) {
            finalUrl = "<span class=\"active\">" + urlSection.replaceAll("\\.(\\w*)", "").toUpperCase() + "</span>";
        } else finalUrl = "<a href=\"/" + urlSection + "/\">" + urlSection.toUpperCase() + "</a>";

        return finalUrl;
    }

    public static boolean isTheLastElement(String urlSection, String[] urlArray, int urlSectionLength) {
        return urlSection.equals(urlArray[urlSectionLength - 1]);
    }

    public static String[] getUrlArray(String url) {

        String[] interimUrl = url.split("/");
        String[] interimUrlBis = new String[interimUrl.length - 1];

        for (int i = 1; i < interimUrl.length; i++) {
            if (interimUrl[i].length() > 30) {
                interimUrl[i] = formatLongUrl(interimUrl[i]);
            } else if (interimUrl[i].length() <= 30 && interimUrl[i].contains("-")) {
                interimUrl[i] = formatShortUrl(interimUrl[i]);
            }
        }

        if (interimUrl[interimUrl.length - 1].contains("index")) {
            System.arraycopy(interimUrl, 0, interimUrlBis, 0, interimUrl.length - 1);
            return interimUrlBis;
        }
        return interimUrl;
    }
    public static String formatLongUrl(String url) {

        String[] wordsToIgnore = {"\\bthe\\b","\\bof\\b","\\bin\\b","\\bfrom\\b","\\bby\\b","\\bwith\\b","\\band\\b",
                "\\bor\\b", "\\bfor\\b", "\\bto\\b", "\\bat\\b", "\\ba\\b"};

            for (int j = 0; j < wordsToIgnore.length; j++) {
                url = url.replaceAll(wordsToIgnore[j], "");
            }
        return url;
    }

    public static String formatShortUrl(String url) {
        return url.toUpperCase().replaceAll("-", " ");
    }
}