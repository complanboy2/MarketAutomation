package com.googleplus;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.google.gson.Gson;

public class LoginUtils {

	private static final String GMAIL_LOGIN_URL = "https://accounts.google.com/signin/v2/identifier";
	public static final WebDriver login2Gmail(WebDriver driver, String pathOfJsonFileName) {
		Cookies[] data = readJson(pathOfJsonFileName);

		driver.navigate().to(GMAIL_LOGIN_URL);

		// Set the expire time of each cookie.
		Date expiryTime = new Date(System.currentTimeMillis() + 1000000000);
		for (Cookies cookie : data) {
			Cookie ck = new Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(),
					expiryTime, Boolean.parseBoolean(cookie.getSecure()),
					Boolean.parseBoolean(cookie.getHttpOnly()));
			driver.manage().addCookie(ck);
		}
		return driver;
	}

	private static final Cookies[] readJson(String jsonFileName) {
		String json = null;
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(jsonFileName));
			json = new String(encoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Gson().fromJson(json, Cookies[].class);
	}
}
