package generics;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class Utility {

    public static String getFormatedDateTime() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return simpleDate.format(new Date());
    }

    public static boolean checkArrayListIsSorted(ArrayList allText,int startIndex) {
        ArrayList clone = new ArrayList(allText);
        Collections.sort(clone, String.CASE_INSENSITIVE_ORDER);
        for (int i = startIndex; i < clone.size(); i++) {
            System.out.println(allText.get(i) + " = " + clone.get(i));
        }
        System.out.println();
        return allText.equals(clone);
    }

    public static boolean checkArrayListHasDuplicate(ArrayList<String> allText) {
        HashSet<String> clone = new HashSet<String>();
        for (String s : allText) {
            if (clone.add(s)) {
                System.out.println(s + " not duplicate");
            } else {
                System.out.println(s + " duplicate");
                return true; // means it has duplicate
            }
        }
        return false;// means no duplicates
    }

    public static ArrayList<String> getAllTextFromListBox(WebElement listBox,int startIndex) {
        Select select = new Select(listBox);
        List<WebElement> allOptions = select.getOptions();
        ArrayList allText = new ArrayList();
        for (int i = startIndex; i < allOptions.size(); i++) {
            String text = allOptions.get(i).getText();
            allText.add(text);
        }
        return allText;
    }

    public static String getScreenShot(WebDriver driver, String imageFolderPath) {
        String imagePath = imageFolderPath + "/" + getFormatedDateTime() + ".png";
        TakesScreenshot page = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
        } catch (Exception e) {

        }
        return imagePath;
    }

    public static String getScreenShot(String imageFolderPath) {
        String imagePath = imageFolderPath + "/" + getFormatedDateTime() + ".png";

        try {
            Dimension desktopSize = Toolkit.getDefaultToolkit().getScreenSize();
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(desktopSize));
            ImageIO.write(image, "png", new File(imagePath));
        } catch (Exception e) {
        }
        return imagePath;
    }

    public static String getPropertyValue(String filePath, String key) {
        String value = "";
        Properties ppt = new Properties();
        try {
            ppt.load(new FileInputStream(filePath));
            value = ppt.getProperty(key);
        } catch (Exception e) {
        }
        return value;
    }

    public static int getExcelRowCount(String path, String sheet) {
        int r = 0;
        try {

            r = WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getLastRowNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static String getExcelCellValue(String path, String sheet, int r, int c) {
        String v = "";
        try {

            v = WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getRow(r).getCell(c).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public static void clickUsingJS(WebDriver driver,WebElement e){
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click()",e);
    }

    public static void enterValueUsingJS(WebDriver driver,WebElement e,String s){
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click()",e,"arguments[1]",s);
    }
}