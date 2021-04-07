import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuSelenium {
    @Test
    public void main() {
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
        wd.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[2]/div/button")).click(); //кнопка крестик
        Assert.assertTrue(wd.findElement(By.cssSelector("#email > h2")).isDisplayed()); //3.7
        String awaiting = wd.findElement(By.cssSelector("#container-body > div > div.inbox > div > span")).getAttribute("textContent");
        Assert.assertEquals("В ожидании новых писем...", awaiting);     //3.8
        wd.findElement(By.cssSelector("#compose")).click();     //3.9
        wd.findElement(By.cssSelector("#to")).sendKeys(email);
        wd.findElement(By.cssSelector("#subject")).sendKeys("Test");
        wd.findElement(By.cssSelector("#text")).sendKeys(secretAddress);    //3.10
        wd.findElement(By.cssSelector("#submit")).click();      //3.11
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#container-body > div > div.inbox > div.mail")));//3.12
        wd.findElement(By.cssSelector("#container-body > div > div.inbox > div.mail")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#info > div.row.row-info.no-gutters > div.col.d-flex.mb-10"))); //3.13
        String adrEmail = wd.findElement(By.cssSelector("#info > div.row.row-info.no-gutters > div.col.d-flex.mb-10")).getAttribute("textContent");
        Assert.assertEquals(email, adrEmail);
        String themeLetters = wd.findElement(By.cssSelector("#info > div.subject.mb-20")).getAttribute("textContent");
        Assert.assertEquals("Test", themeLetters);
        String actualSecretAddress = wd.findElement(By.cssSelector("#info > div.overflow-auto.mb-20")).getAttribute("textContent");
        Assert.assertEquals(secretAddress, actualSecretAddress);
        wd.findElement(By.cssSelector("#reply")).click(); //3.14
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#form > div.modal-header.shadow-sm > div.bar")));
        wd.findElement(By.cssSelector("#text")).sendKeys("Test2");
        wd.findElement(By.cssSelector("#submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#back > span:nth-child(2)"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#inbox-head > p"))); //3.15
        wd.findElement(By.cssSelector("#back > span:nth-child(2)")).click();
        wd.findElement(By.cssSelector("#container-body > div > div.inbox > div:nth-child(2) > div")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#container-body > div > div.inbox > div:nth-child(2) > div > div.from.col-9.col-md-4 > img"))); // 3.16
        wd.findElement(By.cssSelector("#container-body > div > div.inbox > div:nth-child(2) > div > div.subj.col-12.col-md-7.px-md-3")).click();
        String reTestLetter = wd.findElement(By.cssSelector("#info > div.overflow-auto.mb-20")).getAttribute("textContent");// 3.17
        Assert.assertEquals("Test2", reTestLetter);
        wd.findElement(By.cssSelector("#delete_mail > span:nth-child(2)")).click(); //3.18
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#modal-destroy-mail > div > div > div > h5")));
        wd.findElement(By.cssSelector("#confirm_mail")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#inbox-head > p"))); //3.19
        Assert.assertFalse(wd.getPageSource().contains("Re: Test"));
        wd.quit();
            }
}
