/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.tests.junit;

import junit.framework.*;
import junit.textui.*;

/**
 * Automated Test Suite for class org.eclipse.swt.accessibility.AccessibleTextListener
 *
 * @see org.eclipse.swt.accessibility.AccessibleTextListener
 */
public class Test_org_eclipse_swt_accessibility_AccessibleTextListener extends SwtTestCase {

public Test_org_eclipse_swt_accessibility_AccessibleTextListener(String name) {
	super(name);
}

public static void main(String[] args) {
	TestRunner.run(suite());
}

protected void setUp() {
}

protected void tearDown() {
}

public void test_getCaretOffsetLorg_eclipse_swt_accessibility_AccessibleTextEvent() {
	warnUnimpl("Test test_getCaretOffsetLorg_eclipse_swt_accessibility_AccessibleTextEvent not written");
}

public void test_getSelectionRangeLorg_eclipse_swt_accessibility_AccessibleTextEvent() {
	warnUnimpl("Test test_getSelectionRangeLorg_eclipse_swt_accessibility_AccessibleTextEvent not written");
}


public static Test suite() {
	TestSuite suite = new TestSuite();
	java.util.Vector methodNames = methodNames();
	java.util.Enumeration e = methodNames.elements();
	while (e.hasMoreElements()) {
		suite.addTest(new Test_org_eclipse_swt_accessibility_AccessibleTextListener((String)e.nextElement()));
	}
	return suite;
}

public static java.util.Vector methodNames() {
	java.util.Vector methodNames = new java.util.Vector();
	methodNames.addElement("test_getCaretOffsetLorg_eclipse_swt_accessibility_AccessibleTextEvent");
	methodNames.addElement("test_getSelectionRangeLorg_eclipse_swt_accessibility_AccessibleTextEvent");
	return methodNames;
}

protected void runTest() throws Throwable {
	if (getName().equals("test_getCaretOffsetLorg_eclipse_swt_accessibility_AccessibleTextEvent")) test_getCaretOffsetLorg_eclipse_swt_accessibility_AccessibleTextEvent();
	else if (getName().equals("test_getSelectionRangeLorg_eclipse_swt_accessibility_AccessibleTextEvent")) test_getSelectionRangeLorg_eclipse_swt_accessibility_AccessibleTextEvent();
}
}
