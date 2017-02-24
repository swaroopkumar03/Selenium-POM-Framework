package com.hrm.pages;

import com.hrm.base.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by graceraj on 21-02-2017.
 */
public class EmployeeInformationPage extends HomePage {

    public EmployeeInformationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "empsearch_job_title")
    private WebElement jobTitleListBox;

    @FindBy(id = "empsearch_termination")
    private WebElement includeListBox;

    public void testSearchButtonIsPresent() {
        clickPIM_Menu();
    }

    public void verifyJobTitleIsSorted() {
        log.info("Verify that the content of the jobTitle list box is sorted");
        verifyListBoxIsSorted(jobTitleListBox, 1);
        log.info("ListBox content is sorted");
    }

    public void testJobTitleHasNoDuplicate() {
        log.info("Verify that the content of the jobTitle list box has no duplicate");
        verifyListBoxHasNoDuplicate(jobTitleListBox, 1);
        log.info("Listbox has no duplicates");
    }

    public void verifyIncludeListboxIsSorted() {
        log.info("verify that content of Include ListBox is sorted");
        verifyListBoxIsSorted(includeListBox, 0);
        log.info("ListBox is sorted");
    }

    public void verifyIncludeListBoxHasNoDuplicate() {
        log.info("verify that content of the Include list box has no duplicate");
        verifyListBoxHasNoDuplicate(includeListBox, 0);
        log.info("ListBox has no duplicates");
    }
}