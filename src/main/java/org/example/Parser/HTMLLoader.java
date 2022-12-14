package org.example.Parser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HTMLLoader {
    String url;

    public HTMLLoader(ParserSettings settings){
        url= Link.BASE_URL;
    }

    public WebDriver GetSourceByPageId()throws IOException{
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get(url);
        return driver;
    }
}
