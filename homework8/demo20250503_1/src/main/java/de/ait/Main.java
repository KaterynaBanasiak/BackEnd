package de.ait;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Main {
    public static void main(String[] args) throws IOException {
        String apiKey = "helloworld";
        String imageUrl = "https://cdn.javarush.com/images/article/431abcb1-71aa-4137-97bd-ad26d7aa0e00/800.jpeg";
        URL url = new URL("https://api.ocr.space/parse/image"); // <-- правильный endpoint
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "apikey=" + URLEncoder.encode(apiKey, "UTF-8") +
                "&url=" + URLEncoder.encode(imageUrl, "UTF-8") +
                "&language=rus";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(data.getBytes());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("Response:");
        System.out.println(response);
    }
}
