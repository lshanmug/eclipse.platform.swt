package org.eclipse.swt.widgets;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved
 */

import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.photon.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;

/**
 * Instances of this class represent a selectable user interface object
 * that issues notification when pressed and released. 
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>CHECK, CASCADE, PUSH, RADIO, SEPARATOR</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Arm, Help, Selection</dd>
 * </dl>
 * <p>
 * Note: Only one of the styles CHECK, CASCADE, PUSH, RADIO, and SEPARATOR 
 * may be specified.
 * </p><p>
 * IMPORTANT: This class is <em>not</em> intended to be subclassed.
 * </p>
 */
public class MenuItem extends Item {
	Menu parent, menu;
	int accelerator;
	boolean enabled = true;
	
/**
 * Constructs a new instance of this class given its parent
 * (which must be a <code>Menu</code>) and a style value
 * describing its behavior and appearance. The item is added
 * to the end of the items maintained by its parent.
 * <p>
 * The style value is either one of the style constants defined in
 * class <code>SWT</code> which is applicable to instances of this
 * class, or must be built by <em>bitwise OR</em>'ing together 
 * (that is, using the <code>int</code> "|" operator) two or more
 * of those <code>SWT</code> style constants. The class description
 * for all SWT widget classes should include a comment which
 * describes the style constants which are applicable to the class.
 * </p>
 *
 * @param parent a composite control which will be the parent of the new instance (cannot be null)
 * @param style the style of control to construct
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 *
 * @see SWT
 * @see Widget#checkSubclass
 * @see Widget#getStyle
 */
public MenuItem (Menu parent, int style) {
	this (parent, style, parent.getItemCount());
}

/**
 * Constructs a new instance of this class given its parent
 * (which must be a <code>Menu</code>), a style value
 * describing its behavior and appearance, and the index
 * at which to place it in the items maintained by its parent.
 * <p>
 * The style value is either one of the style constants defined in
 * class <code>SWT</code> which is applicable to instances of this
 * class, or must be built by <em>bitwise OR</em>'ing together 
 * (that is, using the <code>int</code> "|" operator) two or more
 * of those <code>SWT</code> style constants. The class description
 * for all SWT widget classes should include a comment which
 * describes the style constants which are applicable to the class.
 * </p>
 *
 * @param parent a composite control which will be the parent of the new instance (cannot be null)
 * @param style the style of control to construct
 * @param index the index to store the receiver in its parent
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the parent</li>
 *    <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed subclass</li>
 * </ul>
 *
 * @see SWT
 * @see Widget#checkSubclass
 * @see Widget#getStyle
 */
public MenuItem (Menu parent, int style, int index) {
	super (parent, checkStyle (style));
	this.parent = parent;
	createWidget (index);
}

/**
 * Adds the listener to the collection of listeners who will
 * be notified when the arm events are generated for the control, by sending
 * it one of the messages defined in the <code>ArmListener</code>
 * interface.
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see ArmListener
 * @see #removeArmListener
 */
public void addArmListener (ArmListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener (listener);
	addListener (SWT.Arm, typedListener);
}

/**
 * Adds the listener to the collection of listeners who will
 * be notified when the help events are generated for the control, by sending
 * it one of the messages defined in the <code>HelpListener</code>
 * interface.
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see HelpListener
 * @see #removeHelpListener
 */
public void addHelpListener (HelpListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener (listener);
	addListener (SWT.Help, typedListener);
}

/**
 * Adds the listener to the collection of listeners who will
 * be notified when the control is selected, by sending
 * it one of the messages defined in the <code>SelectionListener</code>
 * interface.
 * <p>
 * When <code>widgetSelected</code> is called, the stateMask field of the event object is valid.
 * <code>widgetDefaultSelected</code> is not called.
 * </p>
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see SelectionListener
 * @see #removeSelectionListener
 * @see SelectionEvent
 */
public void addSelectionListener (SelectionListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	TypedListener typedListener = new TypedListener(listener);
	addListener (SWT.Selection,typedListener);
	addListener (SWT.DefaultSelection,typedListener);
}

protected void checkSubclass () {
	if (!isValidSubclass ()) error (SWT.ERROR_INVALID_SUBCLASS);
}

static int checkStyle (int style) {
	return checkBits (style, SWT.PUSH, SWT.CHECK, SWT.RADIO, SWT.SEPARATOR, SWT.CASCADE, 0);
}

void createHandle (int index) {
	state |= HANDLE;
	int count = parent.getItemCount();
	if (!(0 <= index && index <= count)) error (SWT.ERROR_INVALID_RANGE);
	int parentHandle = parent.handle;
	if ((style & SWT.SEPARATOR) != 0) {
		handle = OS.PtCreateWidget (OS.PtSeparator (), parentHandle, 0, null);	
	} else if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
		int [] args = {
			OS.Pt_ARG_INDICATOR_TYPE, (style & SWT.CHECK) != 0 ? OS.Pt_N_OF_MANY : OS.Pt_ONE_OF_MANY, 0
		};
		handle = OS.PtCreateWidget (OS.PtToggleButton (), parentHandle, args.length / 3, args);	
	} else {
		handle = OS.PtCreateWidget (OS.PtMenuButton (), parentHandle, 0, null);
	}
	if (handle == 0) error (SWT.ERROR_NO_HANDLES);
	if (index != count) {
		int i = 0;
		int child = OS.PtWidgetChildBack (parentHandle);
		/*
		* Feature in Photon.  Menu bars have an extra widget which
		* is the parent of all menu items. PtValidParent() can not be
		* used, since it does not return that widget.
		*/
		if (child != 0  && (parent.style & SWT.BAR) != 0) child = OS.PtWidgetChildBack (child);
		while (i != index && child != 0) {
			child = OS.PtWidgetBrotherInFront (child);
			i++;
		}
		OS.PtWidgetInsert (topHandle (), child, 1);
	}
	if (OS.PtWidgetIsRealized (parentHandle)) {
		OS.PtRealizeWidget (topHandle ());
	}
}

/**
 * Return the widget accelerator.  An accelerator is the bit-wise
 * OR of zero or more modifier masks and a key. Examples:
 * <code>SWT.CONTROL | SWT.SHIFT | 'T', SWT.ALT | SWT.F2</code>.
 *
 * @return the accelerator
 *
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public int getAccelerator () {
	checkWidget();
	return accelerator;
}

public Display getDisplay () {
	Menu parent = this.parent;
	if (parent == null) error (SWT.ERROR_WIDGET_DISPOSED);
	return parent.getDisplay ();
}

/**
 * Returns <code>true</code> if the receiver is enabled, and
 * <code>false</code> otherwise. A disabled control is typically
 * not selectable from the user interface and draws with an
 * inactive or "grayed" look.
 *
 * @return the receiver's enabled state
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public boolean getEnabled () {
	checkWidget ();
	/*
	* Bug in Photon. The Pt_BLOCKED flag of a menu item is cleared
	* when its parent menu is realized. The fix is to remember
	* the menu item state and reset it when the menu item is
	* realized.
	*/
//	int topHandle = topHandle ();
//	return (OS.PtWidgetFlags (topHandle) & OS.Pt_BLOCKED) == 0;
	return enabled;
}

/**
 * Returns the receiver's cascade menu if it has one or null
 * if it does not. Only <code>CASCADE</code> menu items can have
 * a pull down menu. The sequence of key strokes, button presses 
 * and/or button releases that are used to request a pull down
 * menu is platform specific.
 *
 * @return the receiver's menu
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public Menu getMenu () {
	checkWidget();
	return menu;
}

String getNameText () {
	if ((style & SWT.SEPARATOR) != 0) return "|";
	return super.getNameText ();
}

/**
 * Returns the receiver's parent, which must be a <code>Menu</code>.
 *
 * @return the receiver's parent
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public Menu getParent () {
	checkWidget();
	return parent;
}

/**
 * Returns <code>true</code> if the receiver is selected,
 * and false otherwise.
 * <p>
 * When the receiver is of type <code>CHECK</code> or <code>RADIO</code>,
 * it is selected when it is checked.
 *
 * @return the selection state
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public boolean getSelection () {	
	checkWidget();
	if ((style & (SWT.CHECK | SWT.RADIO | SWT.TOGGLE)) == 0) return false;
	return (OS.PtWidgetFlags (handle) & OS.Pt_SET) != 0;
}

void hookEvents () {
	if ((style & SWT.SEPARATOR) != 0) return;
	int windowProc = getDisplay ().windowProc;
	if ((style & SWT.CASCADE) != 0) {
		OS.PtAddCallback (handle, OS.Pt_CB_ARM, windowProc, SWT.Arm);
	}
	OS.PtAddCallback (handle, OS.Pt_CB_ACTIVATE, windowProc, SWT.Selection);
	if ((parent.style & SWT.BAR) == 0) {
		OS.PtAddCallback (handle, OS.Pt_CB_REALIZED, windowProc, SWT.Show);
	}
}

/**
 * Returns <code>true</code> if the receiver is enabled, and
 * <code>false</code> otherwise. A disabled control is typically
 * not selectable from the user interface and draws with an
 * inactive or "grayed" look.
 *
 * @return the receiver's enabled state
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public boolean isEnabled () {
	return getEnabled () && parent.isEnabled ();
}

int processActivate (int info) {
	showMenu ();
	return OS.Pt_CONTINUE;
}

int processSelection (int info) {
	if ((style & SWT.CASCADE) != 0 && menu != null) {
		return OS.Pt_CONTINUE;
	}
	Event event = new Event ();
	if (info != 0) {
		PtCallbackInfo_t cbinfo = new PtCallbackInfo_t ();
		OS.memmove (cbinfo, info, PtCallbackInfo_t.sizeof);
		if (cbinfo.event != 0) {
			PhEvent_t ev = new PhEvent_t ();
			OS.memmove (ev, cbinfo.event, PhEvent_t.sizeof);
			int data = OS.PhGetData (cbinfo.event);
			if (data != 0) {
				switch (ev.type) {
					case OS.Ph_EV_KEY:
						PhKeyEvent_t ke = new PhKeyEvent_t ();
						OS.memmove (ke, data, PhKeyEvent_t.sizeof);
						setKeyState (event, ke);
						break;
					case OS.Ph_EV_BUT_PRESS:
					case OS.Ph_EV_BUT_RELEASE:
						PhPointerEvent_t pe = new PhPointerEvent_t ();
						OS.memmove (pe, data, PhPointerEvent_t.sizeof);
						setMouseState (ev.type, event, pe);
						break;
				}	
			}
		}
	}
	postEvent (SWT.Selection, event);
	return OS.Pt_CONTINUE;
}

int processShow (int info) {
	/*
	* Bug in Photon. The Pt_BLOCKED flag of a menu item is cleared
	* when its parent menu is realized. The fix is to remember
	* the menu item state and reset it when the menu item is
	* realized.
	*/
	int topHandle = topHandle ();
	int flags = enabled ? 0 : OS.Pt_BLOCKED | OS.Pt_GHOST;
	OS.PtSetResource (handle, OS.Pt_ARG_FLAGS, flags, OS.Pt_BLOCKED | OS.Pt_GHOST);
	return OS.Pt_CONTINUE;
}

int processArm(int info) {
	postEvent (SWT.Arm);
	showMenu ();
	return OS.Pt_CONTINUE;
}

void releaseChild () {
	super.releaseChild ();
	if (menu != null) menu.dispose ();
	menu = null;
}

void releaseWidget () {
	if (menu != null && !menu.isDisposed ()) {
		menu.releaseWidget ();
		menu.releaseHandle ();
	}
	menu = null;
	super.releaseWidget ();
	if (accelerator != 0) removeAccelerator ();
	accelerator = 0;
	parent = null;
}

/**
 * Removes the listener from the collection of listeners who will
 * be notified when the arm events are generated for the control.
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see ArmListener
 * @see #addArmListener
 */
public void removeArmListener (ArmListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (eventTable == null) return;
	eventTable.unhook (SWT.Arm, listener);
}

/**
 * Removes the listener from the collection of listeners who will
 * be notified when the help events are generated for the control.
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see HelpListener
 * @see #addHelpListener
 */
public void removeHelpListener (HelpListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (eventTable == null) return;
	eventTable.unhook (SWT.Help, listener);
}

void removeAccelerator () {
	if (accelerator == 0) return;

	int keyMods = 0;
	if ((accelerator & SWT.ALT) != 0) keyMods |= OS.Pk_KM_Alt;
	if ((accelerator & SWT.SHIFT) != 0) keyMods |= OS.Pk_KM_Shift;
	if ((accelerator & SWT.CONTROL) != 0) keyMods |= OS.Pk_KM_Ctrl;
	int key = (accelerator & ~(SWT.ALT | SWT.SHIFT | SWT.CONTROL));
	Display display = getDisplay ();
	int keyCode = display.untranslateKey (key);
	if (keyCode != 0) key = keyCode;
	else key = Character.toLowerCase ((char)key);
	Shell shell = parent.getShell ();
	OS.PtRemoveHotkeyHandler(shell.shellHandle, key, keyMods, (short)0, handle, display.hotkeyProc);
}

/**
 * Removes the listener from the collection of listeners who will
 * be notified when the control is selected.
 *
 * @param listener the listener which should be notified
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_NULL_ARGUMENT - if the listener is null</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 *
 * @see SelectionListener
 * @see #addSelectionListener
 */
public void removeSelectionListener (SelectionListener listener) {
	checkWidget();
	if (listener == null) error (SWT.ERROR_NULL_ARGUMENT);
	if (eventTable == null) return;
	eventTable.unhook (SWT.Selection, listener);
	eventTable.unhook (SWT.DefaultSelection,listener);	
}

/**
 * Sets the widget accelerator.  An accelerator is the bit-wise
 * OR of zero or more modifier masks and a key. Examples:
 * <code>SWT.CONTROL | SWT.SHIFT | 'T', SWT.ALT | SWT.F2</code>.
 *
 * @param accelerator an integer that is the bit-wise OR of masks and a key
 *
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setAccelerator (int accelerator) {
	checkWidget();
	removeAccelerator ();

	this.accelerator = accelerator;		
	if (accelerator == 0) return;

	int keyMods = 0;
	if ((accelerator & SWT.ALT) != 0) keyMods |= OS.Pk_KM_Alt;
	if ((accelerator & SWT.SHIFT) != 0) keyMods |= OS.Pk_KM_Shift;
	if ((accelerator & SWT.CONTROL) != 0) keyMods |= OS.Pk_KM_Ctrl;
	int key = (accelerator & ~(SWT.ALT | SWT.SHIFT | SWT.CONTROL));
	Display display = getDisplay ();
	int keyCode = display.untranslateKey (key);
	if (keyCode != 0) key = keyCode;
	else key = Character.toLowerCase ((char)key);
	Shell shell = parent.getShell ();
	OS.PtAddHotkeyHandler(shell.shellHandle, key, keyMods, (short)0, handle, display.hotkeyProc);
}

/**
 * Enables the receiver if the argument is <code>true</code>,
 * and disables it otherwise. A disabled control is typically
 * not selectable from the user interface and draws with an
 * inactive or "grayed" look.
 *
 * @param enabled the new enabled state
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setEnabled (boolean enabled) {
	checkWidget ();
	this.enabled = enabled;
	int topHandle = topHandle ();
	int flags = enabled ? 0 : OS.Pt_BLOCKED | OS.Pt_GHOST;
	OS.PtSetResource (topHandle, OS.Pt_ARG_FLAGS, flags, OS.Pt_BLOCKED | OS.Pt_GHOST);
}

public void setImage (Image image) {
	checkWidget();
	if ((style & SWT.SEPARATOR) != 0) return;
	if (this.image == image) return;
	super.setImage (image);
	if ((style & (SWT.CHECK | SWT.RADIO)) != 0) return;
	int imageHandle = 0;
	int type = OS.Pt_Z_STRING;
	if (image != null) {
		imageHandle = copyPhImage (image.handle);
		if (text.length () != 0) type = OS.Pt_TEXT_IMAGE;
		else type = OS.Pt_IMAGE;
	} else {
		/*
		* Bug in Photon.  Photon will segment fault, if Pt_ARG_LABEL_IMAGE
		* is set to NULL.  This means that after setting an image into a
		* PtMenuButton, it can never be removed.  The fix is to set it to
		* a small blank image.
		*/
		Display display = getDisplay ();
		imageHandle = copyPhImage (display.nullImage);
	}
	int [] args = {
		OS.Pt_ARG_LABEL_IMAGE, imageHandle, 0,
		OS.Pt_ARG_LABEL_TYPE, type, 0,
	};
	OS.PtSetResources (handle, args.length / 3, args);
	if (imageHandle != 0) OS.free (imageHandle);
	/*
	* Bug on Photon.  When a the text is set on a menu
	* item that is realized, the menu item does not resize
	* to show the new text.  The fix is to force the item
	* to recalculate the size.
	*/
	if (OS.PtWidgetIsRealized (handle)) OS.PtExtentWidgetFamily (parent.handle);
}

/**
 * Sets the receiver's pull down menu to the argument.
 * Only <code>CASCADE</code> menu items can have a
 * pull down menu. The sequence of key strokes, button presses
 * and/or button releases that are used to request a pull down
 * menu is platform specific.
 *
 * @param menu the new pull down menu
 *
 * @exception IllegalArgumentException <ul>
 *    <li>ERROR_MENU_NOT_DROP_DOWN - if the menu is not a drop down menu</li>
 *    <li>ERROR_MENUITEM_NOT_CASCADE - if the menu item is not a <code>CASCADE</code></li>
 *    <li>ERROR_INVALID_ARGUMENT - if the menu has been disposed</li>
 *    <li>ERROR_INVALID_PARENT - if the menu is not in the same widget tree</li>
 * </ul>
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setMenu (Menu menu) {
	checkWidget();
	if ((style & SWT.CASCADE) == 0) {
		error (SWT.ERROR_MENUITEM_NOT_CASCADE);
	}
	if (menu != null) {
		if (menu.isDisposed()) error(SWT.ERROR_INVALID_ARGUMENT);
		if ((menu.style & SWT.DROP_DOWN) == 0) {
			error (SWT.ERROR_MENU_NOT_DROP_DOWN);
		}
		if (menu.parent != parent.parent) {
			error (SWT.ERROR_INVALID_PARENT);
		}
	}
	Menu oldMenu = this.menu;
	if (oldMenu == menu) return;
	this.menu = menu;
	if (oldMenu != null) {
		oldMenu.cascade = null;
		if ((parent.style & SWT.BAR) == 0) {
			OS.PtSetResource (handle, OS.Pt_ARG_BUTTON_TYPE, OS.Pt_MENU_TEXT, 0);
		}
	}
	if (menu != null) {
		menu.cascade = this;
		if ((parent.style & SWT.BAR) == 0) {
			OS.PtSetResource (handle, OS.Pt_ARG_BUTTON_TYPE, OS.Pt_MENU_RIGHT, 0);		
		}
	}
}

/**
 * Sets the selection state of the receiver.
 * <p>
 * When the receiver is of type <code>CHECK</code> or <code>RADIO</code>,
 * it is selected when it is checked.
 *
 * @param selected the new selection state
 *
 * @exception SWTException <ul>
 *    <li>ERROR_WIDGET_DISPOSED - if the receiver has been disposed</li>
 *    <li>ERROR_THREAD_INVALID_ACCESS - if not called from the thread that created the receiver</li>
 * </ul>
 */
public void setSelection (boolean selected) {
	checkWidget();
	if ((style & (SWT.CHECK | SWT.RADIO | SWT.TOGGLE)) == 0) return;
	OS.PtSetResource (handle, OS.Pt_ARG_FLAGS, selected ? OS.Pt_SET : 0, OS.Pt_SET);
}

public void setText (String string) {
	checkWidget();
	if (string == null) error (SWT.ERROR_NULL_ARGUMENT);
	super.setText (string);
	char [] text = new char [string.length ()];
	string.getChars (0, text.length, text, 0);
	boolean accel = false;
	int i=0, j=0;
	char mnemonic=0;
	while (i < text.length) {
		if (text [i] == '\t') {accel = true; break;};
		if ((text [j++] = text [i++]) == Mnemonic) {
			if (i == text.length) {continue;}
			if (text [i] == Mnemonic) {i++; continue;}
			if (mnemonic == 0) mnemonic = text [i];
			j--;
		}
	}
	int keyMods = 0; 
	byte [] buffer2 = new byte [1];
	if (accel && ++i < text.length) {
		int start = i;
//		while (i < text.length) {
//			if (text [i] == '+') {
//				String str = new String (text, start, i - start);
//				if (str.equals ("Ctrl")) keyMods |= OS.Pk_KM_Ctrl;
//				if (str.equals ("Shift")) keyMods |= OS.Pk_KM_Shift;
//				if (str.equals ("Alt")) keyMods |= OS.Pk_KM_Alt;
//				start = i + 1;
//			}
//			i++;
//		}
		if (start < text.length) {
			char [] accelText = new char [text.length - start];
			System.arraycopy (text, start, accelText, 0, accelText.length);
			buffer2 = Converter.wcsToMbcs (null, accelText, true);
		}
	}
	while (j < text.length) text [j++] = 0;
	byte [] buffer1 = Converter.wcsToMbcs (null, text, true);
	int ptr1 = OS.malloc (buffer1.length);
	OS.memmove (ptr1, buffer1, buffer1.length);
	int ptr2 = OS.malloc (buffer2.length);
	OS.memmove (ptr2, buffer2, buffer2.length);
	int ptr3 = 0;
	if (mnemonic != 0) {
		byte [] buffer3 = Converter.wcsToMbcs (null, new char []{mnemonic}, true);
		ptr3 = OS.malloc (buffer3.length);
		OS.memmove (ptr3, buffer3, buffer3.length);
	}
	if ((parent.style & SWT.BAR) != 0) {
		replaceMnemonic (mnemonic, false, true);
	}
	int type = OS.Pt_Z_STRING;
	if ((style & SWT.PUSH) != 0) {
		if (image != null)  type = OS.Pt_TEXT_IMAGE;
	}
	int [] args = {
		OS.Pt_ARG_TEXT_STRING, ptr1, 0,
		OS.Pt_ARG_ACCEL_TEXT, ptr2, 0,
		OS.Pt_ARG_MODIFIER_KEYS, keyMods, keyMods,
		OS.Pt_ARG_ACCEL_KEY, ptr3, 0,
		OS.Pt_ARG_LABEL_TYPE, type, 0,
	};
	OS.PtSetResources (handle, args.length / 3, args);
	OS.free (ptr1);
	OS.free (ptr2);
	OS.free (ptr3);
	/*
	* Bug on Photon.  When a the text is set on a menu
	* item that is realized, the menu item does not resize
	* to show the new text.  The fix is to force the item
	* to recalculate the size.
	*/
	if (OS.PtWidgetIsRealized (handle)) OS.PtExtentWidgetFamily (parent.handle);
}

void showMenu() {
	if (menu == null)  return;
	int menuHandle = menu.handle;
	if (!OS.PtWidgetIsRealized (menuHandle)) {
		if ((parent.style & SWT.BAR) == 0) {
			OS.PtSetResource (menuHandle, OS.Pt_ARG_MENU_FLAGS, OS.Pt_MENU_CHILD, OS.Pt_MENU_CHILD);
		}
		OS.PtReParentWidget (menuHandle, handle);
		
		/*
		* Bug in Photon. PtPositionMenu does not position the menu
		* properly when the menu is a direct child a menu bar item.
		* The fix is to position the menu ourselfs.
		*/
		if ((parent.style & SWT.BAR) != 0) {
			PhPoint_t pt = new PhPoint_t ();
			short [] x = new short [1], y = new short [1];
			OS.PtGetAbsPosition (handle, x, y);
			pt.x = x [0];
			pt.y = y [0];
			int [] args = {OS.Pt_ARG_HEIGHT, 0, 0};
			OS.PtGetResources (handle, args.length / 3, args);
			pt.y += args [1];
			int ptr = OS.malloc (PhPoint_t.sizeof);
			OS.memmove (ptr, pt, PhPoint_t.sizeof);
			OS.PtSetResource (menuHandle, OS.Pt_ARG_POS, ptr, 0);
			OS.free (ptr);
		} else {
			OS.PtPositionMenu (menuHandle, null);
		}
		
		menu.sendEvent (SWT.Show);
		OS.PtRealizeWidget (menuHandle);
	}
}

}
