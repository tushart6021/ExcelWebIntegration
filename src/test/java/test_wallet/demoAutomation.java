package test_wallet;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;



public class demoAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver", "C:\\browser drivers\\chromedriver.exe");
//		WebDriverManager.chromedriver().setup();
//		WebDriverManager.firefoxdriver().setup();
//		WebDriverManager.edgedriver().setup();
//		WebDriverManager.operadriver().setup();
//		WebDriverManager.chromiumdriver().setup();
//		WebDriverManager.iedriver().setup();
		ChromeDriver driver = new ChromeDriver();
//		driver.get("https://cardanoscan.io/");
//		driver.manage().window().maximize();
//		
////////////////////////////////////////////////////////////		

		        try {
		            // Load Excel workbook
		            FileInputStream file = new FileInputStream("C:\\test\\testbook.xlsx");
		            Workbook workbook = WorkbookFactory.create(file);
		            Sheet sheet = workbook.getSheetAt(0); // Assuming your data is in the first sheet

		            // Loop through each row starting from row 5
		            for (int rowNum = 453; rowNum < 457; rowNum++) {
		            	System.out.println(rowNum);		                
		            	Row row = sheet.getRow(rowNum);
		                // Read data from Excel (assuming text is in column 8)
		                
		                if(row == null) {
		                	continue;
		                }
		                Cell inputCell = row.getCell(7);
		                String inputData = inputCell.getStringCellValue();
		                

		                // Example: Open the website
		                driver.get("https://cardanoscan.io/");

		                // Find input box and submit button by their IDs (replace with actual IDs)
//		                String inbox = ".text-sm:nth-child(14)";
		                WebElement inputBox = driver.findElement(By.cssSelector(".text-sm.h-full.w-full.px-2"));
		                WebElement submitButton = driver.findElement(By.cssSelector("button.px-6 p.text-sm"));

		                // Type data into the input box
		                inputBox.sendKeys(inputData);

		                // Click the submit button
		                submitButton.click();

		                // Wait for some time to let the page load (you might need to adjust this)
		                Thread.sleep(2000);

		                
		                //first go to utxo instead of summary
		                String cssSelector = ".tabLink:nth-child(2)";
		                WebElement secondElement = driver.findElement(By.id("utxo"));
		                // Now you can interact with the second element as needed
		                // For example, you can set its display block or none if its not clickable
		                String script = "document.getElementById('summary').style.display='none';";
		                ((JavascriptExecutor) driver).executeScript(script);
		                String script1 = "document.getElementById('utxo').style.display='block';";
		                ((JavascriptExecutor) driver).executeScript(script1);
		             // Assuming the response is in some element on the page, locate and extract it
		                
		                Thread.sleep(2000);
		             // Find the table element within the 'utxo' div
		                WebElement table = driver.findElement(By.cssSelector("#utxo table"));
		                // Find the first row in the table
		                WebElement firstRow = table.findElement(By.cssSelector("#utxo table tr"));
		                WebElement firstRow1 = table.findElement(By.cssSelector("#utxo table tr td span"));
		                
		                WebElement firstRow2 = driver.findElement(By.cssSelector("#utxo .rounded-lg .flex.flex-col.mt-10.w-full table tr td span"));
//		                WebElement firstRow2 = driver.findElement(By.xpath("//div[@id='utxo']//table[2]"));
		                
		                
		                
//		                // Find the span tag in the first row
		                String spanText = firstRow1.getText();
		                String spanText2 = firstRow2.getText();
		                System.out.println(spanText);
		                System.out.println(spanText2);

		                // Write the response to Excel (assuming the output goes in column 10, 11)
		                Cell outputCell2 = row.createCell(9);
		                outputCell2.setCellValue(spanText2);
		                Cell outputCell = row.createCell(10);
		                outputCell.setCellValue(spanText);

		                // Close the browser for each iteration
		                driver.navigate().to("about:blank"); // navigate to a blank page to clear the session
		                Thread.sleep(1000); // wait for a second
		            }

		            // Save changes to Excel
		            FileOutputStream outFile = new FileOutputStream("C:\\test\\testbook.xlsx");
		            workbook.write(outFile);
		            outFile.close();
		            workbook.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
//		            // Close the browser after all iterations are done
		            driver.quit();
		        }
//		    }
//		}

		
		
///////////////////////////////////////////		
		
		
//		driver.close();
		

	}

}
