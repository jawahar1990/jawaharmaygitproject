package table;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.lang3.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Webtable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.get("https://www.icc-cricket.com/world-test-championship/standings");

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(15)).ignoring(Exception.class);
		WebElement until = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.tagName("table"));
			}
		});
		
		WebElement Table = driver.findElement(By.tagName("table"));
		WebElement Head = Table.findElement(By.tagName("thead"));
		WebElement Rows = Head.findElement(By.tagName("tr"));
		List<WebElement> elements = Rows.findElements(By.tagName("th"));
		for (int i = 0; i < elements.size(); i++) {
			WebElement element = elements.get(i);
			System.out.print(element.getText()+ " ");
		}
		
		List<WebElement> rows = Table.findElements(By.tagName("tr"));
		for (int i = 1; i < rows.size(); i++) {
			WebElement row = rows.get(i);
			System.out.print("");
			List<WebElement> datas = row.findElements(By.tagName("td"));
			for (int j = 0; j < datas.size(); j++) {
				WebElement data = datas.get(j);
				System.out.print(" ");
				System.out.print(data.getText()+" ");
			}
			System.out.println(" ");
		}
		driver.close();
	}

}
