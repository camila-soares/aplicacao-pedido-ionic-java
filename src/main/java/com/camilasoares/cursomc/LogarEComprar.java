package com.camilasoares.cursomc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogarEComprar {

	public static void main(String[] args) {
				
		 WebDriver driver = new ChromeDriver();
	     
	     driver.get("http://localhost:8100");
	     
	     
	     WebElement username = driver.findElement(By.cssSelector("input[name='email'"));
	     WebElement password = driver.findElement(By.cssSelector("input[name='senha'"));
	     WebElement botao = driver.findElement(By.cssSelector("button[name='login'"));
	     username.sendKeys("camilasoares1507@gmail.com");
	     	password.sendKeys("123");
	     	
	     	botao.click();
	        WebDriverWait wait = new WebDriverWait(driver, 10);
		     WebElement categorias = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='categoria'")));
	     	
	     	categorias.click();
	     	
	     	 WebDriverWait waits = new WebDriverWait(driver, 10);
		     WebElement produtos = waits.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='produto'")));
		     produtos.click();
		     
		     WebDriverWait waitss = new WebDriverWait(driver, 10);
		     WebElement carrinho = waitss.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='carrinho'")));
		     carrinho.click();
		     
		     
	     	
	        
	 
	 
	 
	       
	}

}
