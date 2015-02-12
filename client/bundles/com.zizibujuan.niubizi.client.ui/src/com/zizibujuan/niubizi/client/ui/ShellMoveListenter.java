package com.zizibujuan.niubizi.client.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ShellMoveListenter implements Listener {
	private Shell shell;
	private Point tracePoint;

	public ShellMoveListenter(Shell shell, Point tracePoint) {
		this.shell = shell;
		this.tracePoint = tracePoint;
	}

	@Override
	public void handleEvent(Event event) {
		switch (event.type) {
		case SWT.MouseDown:
			tracePoint.x = event.x;
			tracePoint.y = event.y;
			System.out.println(tracePoint);
			break;
		case SWT.MouseMove:
			if (tracePoint.x == -1) {
				break;
			}
			Point point = shell.toDisplay(event.x, event.y);
			shell.setLocation(point.x - tracePoint.x, point.y - tracePoint.y);
			System.out.println(tracePoint);
			break;
		case SWT.MouseUp:
			tracePoint.x = -1;
			tracePoint.y = -1;
			System.out.println(tracePoint);
			break;

		default:
			break;

		}

	}
}