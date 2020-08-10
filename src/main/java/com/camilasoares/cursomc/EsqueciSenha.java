package com.camilasoares.cursomc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EsqueciSenha {

	public static void main(String[] args) {
		
		 WebDriver driver = new ChromeDriver();
	     
	     driver.get("http://localhost:8100");
	     
	     WebElement botao = driver.findElement(By.cssSelector("button[name='esqueciSenha'"));
	     botao.click();
	    
	     WebElement email = driver.findElement(By.cssSelector("input[name='emaill'"));
	     email.sendKeys("camilasoares1507@gmail.com");
	     
	     WebDriverWait wait = new WebDriverWait(driver, 20);
	     WebElement enviar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='enviar'")));
     	
	     
	     enviar.click();
	     
		    

	}

}
