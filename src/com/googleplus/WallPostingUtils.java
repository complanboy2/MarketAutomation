package com.googleplus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WallPostingUtils {

	public static final void postCommentsToWall(final WebDriver driver, final int noOfPosts, final String yourUrl)
			throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> divComment = driver.findElements(By.xpath("//div[@class='QBU0S PnFuuf']"));
		if (divComment != null && divComment.size() > 0) {

			try {
				divComment.get(0).click();
			} catch (Exception e) {
				divComment = driver.findElements(By.xpath("//div[@jsname='uxkNvd']//div[@class='QBU0S PnFuuf']"));
				try {
					divComment.get(0).click();
				} catch (Exception e1) {
					// Thread.sleep(2000);
					divComment = driver.findElements(By.xpath("//div[text()='Add a comment...']"));
				}
			}
			try {
				int i = 1;
				for (WebElement e1 : divComment) {
					if (i++ >= noOfPosts) {
						break;
					}
					Thread.sleep(1000);
					WebElement plusOne = driver.findElement(By.xpath("//div[@jsname='p6QS']"));
					if (plusOne != null) {
						try {
							if (!plusOne.getAttribute("class").contains("y7OZL")) {
								plusOne.click();
							}
						} catch (Exception e) {

						}
					}

					Thread.sleep(2000);
					try {
						if (e1 != null) {
							e1.click();
						}
					} catch (Exception e) {
						try {
							driver.findElements(By.xpath("//div[text()='Add a comment...']")).get(i).click();
						} catch (Exception ex) {

						}
					}
					Thread.sleep(3000);

					try {
						WebElement commentTextarea = driver.findElement(By.xpath("//textarea[@id='XPxXbf']"));
						if (commentTextarea != null) {
							commentTextarea.sendKeys(yourUrl);
						}
					} catch (Exception e) {

					}

					Thread.sleep(3000);
					int nl = 0;
					do {
						if (nl++ > 10000) {
							break;
						}
						driver.findElement(By.xpath("//div[@jsname='M2UYVd']")).click();
					} while (driver.findElement(By.xpath("//div[@jsname='M2UYVd']")) == null);
				}
				Thread.sleep(2000);
			} catch (Exception e) {

			}
			// Thread.sleep(2000);
		}
		Thread.sleep(2000);
	}
}
