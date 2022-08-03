package pl.lextraduct.www;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Solution {

    public static void main(String[] args) {

//        String url0 = "mysite.com/pictures/holidays.html";
//        String url1 = "www.codewars.com/users/GiacomoSorbi";
//        String url2 = "www.microsoft.com/docs/index.htm";
//        String url3 = "mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.htm";
//        String url4 = "www.very-long-site_name-to-make-a-silly-yet-meaningful-example.com/users/giacomo-sorbi-_it";
//        String url5 = "https://www.linkedin.com/in/giacomosorbi";
        String url6 = "www.lexraduct.pl/";
        String separator = " : ";

//        String[] arrOfUrls = {url0, url1, url2, url3, url4, url5, url6};
        String[] arrOfUrls = {url6};

        for (String s : arrOfUrls) {
            System.out.println(generate_bc(s, separator));
        }
    }

    public static String generate_bc(String url, String separator) {

        String home = "<a href=\"/\">HOME</a>";
        String[] urlSection = getUrlArray(url);
        String[] urlArray = getUrlArray(url);

        UnaryOperator<String> urlFormatter = text -> {

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

        if (isAnchorContained(urlSection) || isTheLastElement(urlSection, urlArray, urlArray.length)) {
            finalUrl = "<span class=\"active\">" + urlSection.toUpperCase().replaceAll("\\.(\\w*)|\\?(\\w*)|#(\\w*)" +
                            "|\\$(\\w*)|%(\\w*)|&(\\w*)|=(\\w*)", "").replaceAll("[-_]", " ") + "</span>";
//        } else if (urlSection.length() < 2) {
//          finalUrl =
        } else if (urlSection.length() > 30) {
            finalUrl = "<a href=\"/" + urlSection + "/\">" + formatLongUrl(urlSection).toUpperCase() + "</a>";
        } else {
            finalUrl = "<a href=\"/" + urlSection + "/\">" + formatMediumSizeUrl(urlSection).toUpperCase() + "</a>";
        }
//            finalUrl = "<a href=\"/" + urlSection + "/\">" + urlSection.toUpperCase() + "</a>";

        return finalUrl;
    }

    public static boolean isTheLastElement(String urlSection, String[] urlArray, int urlSectionLength) {
        return urlSection.equals(urlArray[urlSectionLength - 1]);
    }

    public static String[] getUrlArray(String url) {

        String[] interimUrl = url.replaceFirst("//", "").split("/");
        String[] interimUrlBis = new String[interimUrl.length - 1];

        if (interimUrl[interimUrl.length - 1].contains("index")) {
            System.arraycopy(interimUrl, 0, interimUrlBis, 0, interimUrl.length - 1);
            return interimUrlBis;
        }
        return interimUrl;
    }

    public static String formatLongUrl(String url) {

        String[] wordsToIgnore = {"\\bthe\\b", "\\bof\\b", "\\bin\\b", "\\bfrom\\b", "\\bby\\b", "\\bwith\\b",
                "\\band\\b", "\\bor\\b", "\\bfor\\b", "\\bto\\b", "\\bat\\b", "\\ba\\b"};

        for (int j = 0; j < wordsToIgnore.length; j++) {
            url = url.replaceAll(wordsToIgnore[j], "");
        }

        String[] interimUrl = url.split("[-_]");
        StringBuilder concatenatedUrl = new StringBuilder();


        for (int i = 0; i < interimUrl.length; i++) {
            try {
                concatenatedUrl.append(interimUrl[i].toUpperCase().charAt(0));
            } catch (StringIndexOutOfBoundsException e) {
                concatenatedUrl.append("");
            }
        }

        return concatenatedUrl.toString();
    }

    public static String formatMediumSizeUrl(String url) {
        return url.replaceAll("[-_ ]", " ");
    }

    public static boolean isAnchorContained(String urlSection) {
        String[] anchors = {".", "?", "#", "$", "%", "&", "="};

        for (String anchor : anchors) {
            if (urlSection.contains(anchor)) {
                return true;
            }
        }
        return false;
    }
}