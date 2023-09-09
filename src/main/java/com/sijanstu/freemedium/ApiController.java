package com.sijanstu.freemedium;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/search")
    public String search(@RequestParam(value = "query", defaultValue = "") String query) {
        String url;
        try {
            if (query.contains("medium")) {
                url = "https://medium-unlocker.onrender.com/unlock?url=" + query;
            } else {
                System.out.println("https://becominghuman.ai/" + query);
                url = "https://becominghuman.ai/" + query;
            }
            Connection.Response response1;
            response1 = Jsoup.connect(url).followRedirects(true).execute();
            String value = Parser.unescapeEntities(response1.body(), false);
            String value1 = value.replace("/unlock/?url=", "/api/search?query=https://medium.com");
            return value1.replaceAll("<script.*?</script>", "");
        } catch (Exception e) {
            return "SORRY! Something went wrong.";
        }
    }

}
