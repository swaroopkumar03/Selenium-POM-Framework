package generics;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.hrm.base.AutomationConstants;

public class TestListener implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Utility.getScreenShot(AutomationConstants.SNAP_PATH);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
    }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

}
