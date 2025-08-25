package com.example.Pathway.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AIUtil {

    private static final String API_KEY = System.getenv("GEMINI_API_KEY");

    public static String generateContent(String prompt) throws Exception {

        //String prompt = "What career can i pursue in Comp Sc";
        String model = "gemini-2.0-flash";

        String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/";

        JSONObject textPart = new JSONObject();
        textPart.put("text", prompt);

        JSONObject parts = new JSONObject();
        parts.put("parts", new JSONArray().put(textPart));

        JSONObject contents = new JSONObject();
        contents.put("contents", new JSONArray().put(parts));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + model + ":generateContent" + "?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(contents.toString(), StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() == 200) {
            JSONObject responseJson = new JSONObject(httpResponse.body());
            JSONArray candidates = responseJson.getJSONArray("candidates");

            if (!candidates.isEmpty()) {
                JSONObject contentBody = candidates.getJSONObject(0).getJSONObject("content");
                JSONArray part = contentBody.getJSONArray("parts");

                if (!parts.isEmpty()) {
                    String text = part.getJSONObject(0).getString("text");
                    //System.out.println("Extracted Text: " + text);
                    return text;
                } else {
                    return "No text found in parts array.";
                }
            } else {
                return "No candidates found.";
            }
        } else {
            return "Error: " + httpResponse.statusCode() + " - " + httpResponse.body();
        }
    }

    public static List<String> extractCourseNames(String promptResponse) {
        List<String> courseList = new ArrayList<>();
        String[] lines = promptResponse.split("\n");

        for (String line : lines) {
            String cleaned = line.replaceAll("^\\d+\\.\\s*", "")
                    .toUpperCase();
            courseList.add(cleaned);
        }

        return courseList;
    }
}
