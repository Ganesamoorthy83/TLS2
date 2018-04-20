package com.mfic.core.action;

import junit.framework.TestCase;

import com.mfic.data.LoanProduct;

public class ApprovalActionTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.mfic.dao.ApprovalAction#changeStatusToLoanApproved()}.
	 * This Test case tests whether the method ChangeStatusToLoanApproved successfully changes the status to loan approved & returns "success"
	 */
	public final void testChangeStatusToLoanApproved() {
		System.out.println("Testing Change Status To Loan Approved function");
		ApprovalAction approval = new ApprovalAction();
		String result = approval.changeStatusToLoanApproved();
		
        String expresult = "success";

    assertEquals(result,expresult);
	}

	/**
	 * Test method for {@link com.mfic.dao.ApprovalAction#isMandatoryAttrbCompleted(long,LoanProduct)}.
	 * This Test case tests whether the method isMandatoryAttrbCompleted successfully validates that the Mandatory Attributes are completed
	 */
	public final void testIsMandatoryAttrbCompleted() {
		System.out.println("Testing Is Mandatory Attribute Completed function");
		ApprovalAction approval = new ApprovalAction();
		long loanid=111;
		LoanProduct loanProduct = new LoanProduct();
		Boolean result = approval.isMandatoryAttrbCompleted(loanid,loanProduct);
		
        Boolean expresult = true;

    assertEquals(result,expresult);
	}

}
