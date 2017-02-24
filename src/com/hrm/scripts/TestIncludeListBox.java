package com.hrm.scripts;

import com.hrm.base.BaseTest;
import com.hrm.pages.DashboardPage;
import com.hrm.pages.EmployeeInformationPage;
import org.testng.annotations.Test;

/**
 * Created by graceraj on 22-02-2017.
 */
public class TestIncludeListBox extends BaseTest{

    @Test
    public void testIncludeListBox(){
        DashboardPage dbPage = new DashboardPage(driver);
        dbPage.clickPIM_Menu();

        EmployeeInformationPage eiPage = new EmployeeInformationPage(driver);
        eiPage.verifyIncludeListboxIsSorted();
        eiPage.verifyIncludeListBoxHasNoDuplicate();
    }
}
