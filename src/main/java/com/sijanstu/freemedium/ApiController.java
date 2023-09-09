package com.sijanstu.freemedium;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Connection;
import org.jsoup.parser.Parser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/search")
    public String search(@RequestParam(value = "query", defaultValue = "") String query) {
        Connection.Response response = null;
        String url = "";
        if (query.contains("medium")) {
            url = "https://medium-unlocker.onrender.com/unlock/?url=" + query;
        } else {
            System.out.println("https://becominghuman.ai/search?q=" + query);
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("User-Agent", "PostmanRuntime/7.26.8")
                .build();
        Response response1 = null;
        try {
            response1 = client.newCall(request).execute();
            String value = Parser.unescapeEntities(response1.body().string(), false);
            value = value.replaceAll("/@", "./search?query=https://medium.com/@");
            value = value.replaceAll("<script.*?</script>", "");
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
