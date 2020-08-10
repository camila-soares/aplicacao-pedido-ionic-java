package com.camilasoares.cursomc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CriarUmNovoUsuario {
	
	public static void main(String[] args) {
	
	 WebDriver driver = new ChromeDriver();
     
     driver.get("http://localhost:8100");
     
     WebElement botao = driver.findElement(By.cssSelector("button[name='cadastrese'"));
     botao.click();
     
     WebElement nome = driver.findElement(By.cssSelector("input[name='nomee'"));
     nome.click();
     nome.sendKeys("Camila");
    
     
	}
}
