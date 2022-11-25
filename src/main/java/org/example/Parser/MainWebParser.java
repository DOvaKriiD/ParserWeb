package org.example.Parser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainWebParser implements Parser<ArrayList<String>> {

    @Override
    public ArrayList<String> Parse(WebDriver driver) {

        ArrayList<String> list = new ArrayList<>();

        List<WebElement> reviewers = driver.findElements(By.cssSelector("div[class='find-list-box']"));
        int len = reviewers.size();
        for (int i = 0; i < len; i++) {
            reviewers = driver.findElements(By.cssSelector("div[class='find-list-box']"));
            WebElement reviewer = reviewers.get(i);
            WebElement button = reviewer.findElement(By.cssSelector("a[class='ss']"));
            button.click();
            StringBuilder Review = new StringBuilder();
            WebElement header = driver.findElement(By.cssSelector("header[class='main-head']"));
            WebElement h1 = header.findElement(By.cssSelector("h1[class='ib']"));
            Review.append(h1.getText());
            Review.append("\n");
            //перчисление всех вещей
            WebElement all = driver.findElement(By.cssSelector("div[class='reviewers-block']"));
            List<WebElement> one = all.findElements(By.cssSelector("div[class='reviewers-box']"));
            if (one.size() == 0) Review.append("-");
            else
                for (WebElement webElement : one) {

                    int point = 5;
                    //Все отзывы на вещь
                    Review.append(webElement.findElement(By.cssSelector("span[itemprop='author']")).getText());
                    Review.append(" score: ");
                    WebElement head = webElement.findElement(By.cssSelector("header[class='head']"));
                    List<WebElement> li = head.findElements(By.cssSelector("li"));
                    for (WebElement element : li) {
                        WebElement span = element.findElement(By.cssSelector("span"));
                        if (span.getAttribute("class").equals("star e")) point--;
                    }
                    Review.append(point);

                    Review.append("\n");

                    WebElement tbody = webElement.findElement(By.cssSelector("tbody"));
                    List<WebElement> tr = tbody.findElements(By.cssSelector("tr"));
                    for (int j = 0; j < tr.size(); j++) {
                        if (j == 0) {
                            Review.append("+ : ");
                            Review.append(webElement.findElement(By.cssSelector("td[itemprop='pro']")).getText());
                            Review.append("\n");
                        } else if (j == 1) {
                            Review.append("- : ");
                            Review.append(webElement.findElement(By.cssSelector("td[itemprop='contra']")).getText());
                            Review.append("\n");
                        } else if (j == 2) {
                            Review.append("review: ");
                            Review.append(webElement.findElement(By.cssSelector("td[itemprop='reviewBody']")).getText());
                            Review.append("\n");
                        }
                    }
                    Review.append("\n");
                }
            Review.append("\n");


            //System.out.println(Review.toString());
            //вариант сохранения

            // выгружаем
            list.add(Review.toString());
            driver.navigate().back();
        }


        return list;
    }
}
