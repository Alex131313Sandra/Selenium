import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuSelenium {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Users/azaza/144/chromedriver.exe");
        WebDriver wd = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wd.get("https://tempmail.plus/ru/");
        wd.findElement(By.id("pre_rand")).click(); //нажать на кнопку случайное имя
        wd.findElement(By.id("domain")).click(); //кликнуть на дропдаун
        wd.findElement(By.xpath("//*[@id=\"pre_form\"]/div/div[2]/div/button[6]")).click(); //выбрать rover.info
        String email = wd.findElement(By.id("pre_button")).getAttribute("value") + "@rover.info"; //запомнить почту
        wd.findElement(By.id("pre_settings")).click(); //нажать настройки
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/form/div[3]/div[1]/div[2]/label[1]"))).click();//выбрать 10min
        wd.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[3]/div[2]/input")).click(); //сохранить настройки
        wd.findElement(By.id("pre_settings")).click(); //нажать настройки
        String color = wd.findElement(By.cssSelector("#modal-settings > div > form > div.modal-body > div:nth-child(1) > div.btn-group.btn-group-toggle.d-flex > label:nth-child(3)")).getCssValue("color");
        Assert.assertEquals("rgba(0, 199, 133, 1)", color);
        String secretAddress = wd.findElement(By.cssSelector("#secret-address")).getAttribute("textContent");
        wd.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[2]/div/button");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#modal-settings > div > form > div.modal-header > div > button")));
        wd.findElement(By.cssSelector("#modal-settings > div > form > div.modal-header > div > button")).click();
        Assert.assertTrue(wd.findElement(By.cssSelector("#compose")).isDisplayed()); //3.7
        String awaiting = wd.findElement(By.cssSelector("#container-body > div > div.inbox > div > span")).getAttribute("textContent");
        Assert.assertEquals("В ожидании новых писем...", awaiting);     //3.8
        wd.findElement(By.cssSelector("#compose")).click();     //3.9
        wd.findElement(By.cssSelector("#to")).sendKeys(email);
        wd.findElement(By.cssSelector("#subject")).sendKeys("Test");
        wd.findElement(By.cssSelector("#text")).sendKeys(secretAddress);    //3.10
        wd.findElement(By.cssSelector("#submit")).click();      //3.11
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#container-body > div > div.inbox > div.mail > div")));
        wd.findElement(By.cssSelector("#container-body > div > div.inbox > div.mail > div")).click();   //3.12



    }
}
