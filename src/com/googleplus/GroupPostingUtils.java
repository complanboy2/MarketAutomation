package com.googleplus;

import static com.googleplus.StringUtils.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupPostingUtils {

	static void post2WallAndGroup(String url, WebDriver driver, String yourUrl) throws InterruptedException {
		driver.navigate().to(url);

		set2AllLangues(driver);

		String oldTab = driver.getWindowHandle();

		Map<String, String> userlinks = new HashMap<String, String>();
		do {
			Thread.sleep(300);
			try {
			List<WebElement> userLink = driver.findElements(By.xpath("//a[@class='comment-thread-displayname']"));
			Thread.sleep(500);
				for (WebElement webElement : userLink) {
					String href = webElement.getAttribute("href");

					if (isEmpty(href)) {
						continue;
					}

					if (userlinks.get(href) != null) {
						continue;
					}
					userlinks.put(href, href);
					System.out.println(href);

					try {
						webElement.click();
						Thread.sleep(100);

						ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
						newTab.remove(oldTab);

						// change focus to new tab
						String memberTab = newTab.get(0);
						driver.switchTo().window(memberTab);
						Thread.sleep(1000);

						try {
							WebElement follow = driver.findElement(By.xpath("//div[text()='Follow']"));
							if (follow != null && follow.isDisplayed()) {
								follow.click();
							}
						} catch (Exception e) {

						}

						Thread.sleep(1000);

						List<WebElement> joins = driver.findElements(By.xpath("//span[text()='Join']"));
						if (joins != null) {
							for (WebElement join : joins) {
								if (join != null && join.isDisplayed() && join.isEnabled()) {
									try {
										Thread.sleep(1000);
										join.click();

										List<WebElement> groups = driver.findElements(By.xpath("//div[@jsname='KPZkec']"));
										if (groups != null) {
											for (WebElement group : groups) {
												if (group != null && group.isDisplayed()) {
													try {
														Thread.sleep(5000);
														group.click();

														Thread.sleep(5000);

														WebElement groupPost = driver.findElement(By
																.xpath("//span[text()='What do you want to share?']"));
														if (groupPost != null && groupPost.isDisplayed()
																&& groupPost.isEnabled()) {
															try {
																groupPost.click();

																Thread.sleep(2000);
																WebElement overlayText = driver.findElement(
																		By.xpath("//textarea[@id='XPxXbf']"));
																if (overlayText != null && overlayText.isDisplayed()
																		&& overlayText.isEnabled()) {
																	overlayText.click();
																	overlayText.sendKeys(yourUrl);

																	Thread.sleep(3500);
																	WebElement postButton = driver.findElement(
																			By.xpath("//span[text()='Post']"));
																	if (postButton != null) {
																		postButton.click();

																		Thread.sleep(2000);

																		try {
																			postButton.click();
																		} catch (Exception e) {

																		}
																		List<WebElement> groupTopics = driver
																				.findElements(By.xpath(
																						"//div[@jsname='wCboGc']"));
																		if (groupTopics != null) {
																			if (groupTopics.size() >= 1
																					&& groupTopics.get(1) != null
																					&& groupTopics.get(1).isDisplayed()
																					&& groupTopics.get(1).isEnabled()) {
																				groupTopics.get(1).click();
																			} else if (groupTopics.get(0) != null
																					&& groupTopics.get(0).isDisplayed()
																					&& groupTopics.get(0).isEnabled()) {
																				groupTopics.get(0).click();
																			}
																		}
																	}
																}

															} catch (Exception e) {

															}
														}

														Thread.sleep(1000);

													} catch (Exception e) {

													}
													WallPostingUtils.postCommentsToWall(driver, 8, yourUrl);
													driver.navigate().back();
													Thread.sleep(2000);
												}
											}
										}

									} catch (Exception e) {

									}
									Thread.sleep(2000);
								}
							}
						}

						// WebElement addAComment =
						// driver.findElement(By.xpath("//div[text()='Add a
						// comment...']"));
						// if (addAComment != null && addAComment.isDisplayed())
						// {
						// addAComment.click();
						// }

						WallPostingUtils.postCommentsToWall(driver, 7, yourUrl);
						driver.close();

						Thread.sleep(1000);
						// change focus back to old tab
						driver.switchTo().window(oldTab);
					} catch (Exception e) {

					}
				}

				WebElement nextLink = driver.findElement(By.xpath("//a[@class='Aa dc-se']"));
				if (nextLink == null || !nextLink.isDisplayed() || !nextLink.isEnabled()) {
					break;
				} else {
					try {
						nextLink.click();
						Thread.sleep(1000);
					} catch (Exception e) {
						break;
					}
				}
			} catch (Exception e) {
			}
		} while (true);
	}

	/**
	 * Extend the usage to all languages. And ignore if there is an error.
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	private static void set2AllLangues(WebDriver driver) throws InterruptedException {
		Thread.sleep(1500);
		try {
			WebElement reviewsLanguages = driver.findElement(By.xpath("//span[@class='z-Ha-j']"));
			if (reviewsLanguages != null && reviewsLanguages.isDisplayed()) {
				reviewsLanguages.click();
			}
		} catch (Exception e) {

		}
		Thread.sleep(1000);
		WebElement languagesSpan = null;
		try {
			languagesSpan = driver.findElement(By.xpath("//div[text()='All languages']"));
			if (languagesSpan != null) {
				languagesSpan.click();
			} else {
				languagesSpan = driver.findElement(By.xpath("//span[text()='All languages']"));
				if (languagesSpan != null) {
					languagesSpan.click();
				}
			}
		} catch (Exception e) {
			try {
				if (languagesSpan != null) {
					languagesSpan = driver.findElement(By.xpath("//span[text()='All languages']"));
					if (languagesSpan != null) {
						languagesSpan.click();
					}
				}
			} catch (Exception e1) {
//				e1.printStackTrace();
			}
		}
	}
}
