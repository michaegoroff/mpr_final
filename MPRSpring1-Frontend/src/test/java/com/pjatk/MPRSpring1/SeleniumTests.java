package com.pjatk.MPRSpring1;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    private MyRestService service = new MyRestService();
    WebDriver chromeDriver = new ChromeDriver();
    @Test
    public void testIfRecordsAreAddedCorrectly(){

        chromeDriver.get("http://localhost:8081/index");
        service.addCat(new Cat("Dodatek",33,"Red"));
        chromeDriver.get("http://localhost:8081/index");
        List<WebElement> elements = chromeDriver.findElements(By.tagName("tr"));
        assertEquals(elements.size(),3);
        assertEquals(chromeDriver.getTitle(),"Cats");
    }

    @Test
    public void testIfRecordsAreUpdatedCorrectly(){
        chromeDriver.get("http://localhost:8081/index");
        service.updateCat((long)2,new Cat("Updatek",2,"White"));
        chromeDriver.get("http://localhost:8081/index");
        List<WebElement> elements = chromeDriver.findElements(By.id("name"));
        assertEquals(elements.size(),2);
        assertEquals(elements.get(0).getText(),"Mietek");
        assertEquals(elements.get(1).getText(),"Updatek");
    }

    @Test
    public void testIfRecordsAreDeletedCorrectly(){
        chromeDriver.get("http://localhost:8081/index");
        service.deleteCat((long)2);
        chromeDriver.get("http://localhost:8081/index");
        List<WebElement> elements = chromeDriver.findElements(By.id("name"));
        assertEquals(elements.size(),1);
        assertEquals(elements.get(0).getText(),"Mietek");
    }
}
