package bsf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BfsCrawler {

    private static int WAIT_MS = 500;
    private static int MAX_CRWAL = 20;
    private static String urlRegex = "https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static Pattern urlParsePattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws InterruptedException, IOException {
        BfsCrawler crawler = new BfsCrawler();
        crawler.crawl("https://cnn.com");
    }

    Integer crawled = 0;

    public void crawl(String startUrl) throws InterruptedException, IOException {
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new LinkedList<>();
        addLinksToQueue(Arrays.asList(startUrl), queue, visited);

        while (!queue.isEmpty()) {
            String nextUrl = queue.remove();
            String content = fetchContent(nextUrl);
            List<String> links = parseLinks(content);
            updateVisited(nextUrl, visited);
            addLinksToQueue(links, queue, visited);
            waitBeforeNextCrawl(WAIT_MS);
            incrementCrawl();
            if (crawlLimitReached(MAX_CRWAL)) {
                break;
            }

        }
        printRestofQueue(queue);
    }

    private void printRestofQueue(Queue<String> queue) {
        System.out.println("Remaining in queue");

        queue.forEach(System.out::println);
    }

    private void updateVisited(String nextUrl, Set<String> visited) {
        String domain = nextUrl.replaceAll("^(https?://)?(www\\.)?", "").replaceFirst("(\\/.+)+?", "")
                .replaceAll("(\\?.+)+?", "");
        visited.add(domain);
    }

    private boolean crawlLimitReached(int max) {
        return crawled == max;
    }

    private void incrementCrawl() {
        crawled += 1;
    }

    private void waitBeforeNextCrawl(int waitMilli) throws InterruptedException {
        Thread.sleep(waitMilli);
    }

    private void addLinksToQueue(List<String> links, Deque<String> queue, Set<String> visited) {
        links.forEach(link -> {
            String domain = link.replaceAll("^(https?://)?(www\\.)?", "").replaceFirst("(\\/.+)+?", "")
                    .replaceAll("(\\?.+)+?", "");
            if (!visited.contains(domain)) {
                queue.add(link);
                visited.add(domain);
            }

        });
    }

    private List<String> parseLinks(String content) {
        Matcher matcher = urlParsePattern.matcher(content);
        var result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    private String fetchContent(String nextUrl) {
        System.out.println("nextUrl = " + nextUrl);
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(nextUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);

            }
            in.close();
            return content.toString();

        } catch (IOException  e) {
            System.out.println("error for host = " + e);
            return "";
        }

    }
}
