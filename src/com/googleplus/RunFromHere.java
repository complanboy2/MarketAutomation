package com.googleplus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunFromHere {

	static final String YOUR_URL_TO_MARKET = "https://chrome.google.com/webstore/detail/download-whatsapp-group-p/hienlbgdeobcceaffmjpfeeafiabgnkk ";

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		LoginUtils.login2Gmail(driver, "Cookies.data");

		// COMMA SEPERATED EXTENSION URL WHERE YOU LOOK FOR POTENTIAL CUSTOMERS
		String[] ExtensionUrl = {
				"https://chrome.google.com/webstore/detail/save-to-facebook/jmfikkaogpplgnfjmbjdpalkhclendgd/reviews",
				"https://chrome.google.com/webstore/detail/better-facebook-for-chrom/opnfbjkeinmnibcpmlpjacekjaldnjmj/reviews" };

		for (String url : ExtensionUrl) {
			GroupPostingUtils.post2WallAndGroup(url, driver, YOUR_URL_TO_MARKET);
			Thread.sleep(1000);
		}
	}
}
