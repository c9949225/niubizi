package com.zizibujuan.niubizi.client.ui;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

/**
 * 应用程序级别的生命周期管理器
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class LifeCycleManager {

	private Region region;
	@PostContextCreate
	public void postContextCreate(final IEventBroker eventBroker, final IEclipseContext context){
		eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, new AppStartupCompleteEventHandler(eventBroker, context));
		eventBroker.subscribe(UIEvents.UILifeCycle.APP_SHUTDOWN_STARTED, new AppShutdownStartedEventHandler(eventBroker));
	}
	
	private class AppShutdownStartedEventHandler implements EventHandler{
		private IEventBroker eventBroker;
		
		public AppShutdownStartedEventHandler(IEventBroker eventBroker){
			this.eventBroker = eventBroker;
		}
		@Override
		public void handleEvent(Event event) {
			eventBroker.unsubscribe(this);
			
			if(region != null){
				region.dispose();
			}
		}
		
	}
	
	private class AppStartupCompleteEventHandler implements EventHandler{
		
		private IEventBroker eventBroker;
		private IEclipseContext context;
		
		public AppStartupCompleteEventHandler(IEventBroker eventBroker, IEclipseContext context){
			this.eventBroker = eventBroker;
			this.context = context;
		}

		int[] circle(int r, int offsetX, int offsetY) {
			int[] polygon = new int[8 * r + 4];
			// x^2 + y^2 = r^2
			for (int i = 0; i < 2 * r + 1; i++) {
				int x = i - r;
				int y = (int) Math.sqrt(r * r - x * x);
				polygon[2 * i] = offsetX + x;
				polygon[2 * i + 1] = offsetY + y;
				polygon[8 * r - 2 * i - 2] = offsetX + x;
				polygon[8 * r - 2 * i - 1] = offsetY - y;
			}
			return polygon;
		}
				
		@Override
		public void handleEvent(Event event) {
			eventBroker.unsubscribe(this);
			Shell shell = (Shell)context.get(IServiceConstants.ACTIVE_SHELL);
			System.out.println(shell);
			
			// 窗口始终在最前面，即一直对用户可见
			final long hWnd = shell.handle;
			OS.SetWindowPos(hWnd, OS.HWND_TOPMOST, 0, 100, 80, 80, SWT.NULL);
			//OS.SetWindowLong(hWnd, OS.GWL_EXSTYLE, OS.WS_EX_CAPTIONOKBTN);
			//OS.SetWindowLong(hWnd,OS.GWL_EXSTYLE,OS.WS_EX_TOOLWINDOW);
			
			Display display = shell.getDisplay();
			
			ImageData imgBackData = new ImageData(getClass().getResourceAsStream("/icons/iconfont-tongzhi.png"));
			Image imgBack = new Image(display, imgBackData);
			shell.setBackgroundImage(imgBack);
			
			region = new Region();
			region.add(circle(35, 40, 40));
			
			shell.setRegion(region);
			shell.setCursor(new Cursor(display, SWT.CURSOR_HAND));
			
			//shell.setBackground(new Color(shell.getDisplay(), new RGB(0,187,156)));
			
			Tray tray = display.getSystemTray();
			if(tray == null){
				System.out.println("当前操作系统不支持系统托盘");
			}else{
				TrayItem trayItem = new TrayItem(tray, SWT.NONE);
				ImageData imgTrayData = new ImageData(getClass().getResourceAsStream("/icons/iconfont-folder.png"));
				Image imgTray = new Image(display, imgTrayData);
				trayItem.setImage(imgTray);
				trayItem.setToolTipText("牛鼻子 · 协助管理日常工作");
			}
			
			
			
			//ReceiverPart w = new ReceiverPart();
			
			
//			// 窗口始终在最前面，即一直对用户可见
//			final long hWnd = shell.handle;
//			OS.SetWindowPos(hWnd, OS.HWND_TOPMOST, 0, 200, 100, 100, SWT.NULL);
//			// 在任务栏不显示
//			//OS.SetWindowLong(hWnd, OS.GWL_EXSTYLE, OS.WS_EX_CAPTIONOKBTN);
//			Display display = shell.getDisplay();
//			Tray tray = display.getSystemTray();
//			if(tray == null){
//				System.out.println("当前操作系统不支持系统托盘");
//			}else{
//				TrayItem trayItem = new TrayItem(tray, SWT.NONE);
//				trayItem.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
//				trayItem.setToolTipText("牛鼻子 · 协助管理日常工作");
//			}
			
			
		}
		
	}
}

/*
 * 
			// 窗口始终在最前面，即一直对用户可见
			final long hWnd = shell.handle;
			OS.SetWindowPos(hWnd, OS.HWND_TOPMOST, 0, 200, 100, 100, SWT.NULL);
			// 在任务栏不显示
			//OS.SetWindowLong(hWnd, OS.GWL_EXSTYLE, OS.WS_EX_CAPTIONOKBTN);
			Display display = shell.getDisplay();
			Tray tray = display.getSystemTray();
			if(tray == null){
				System.out.println("当前操作系统不支持系统托盘");
			}else{
				TrayItem trayItem = new TrayItem(tray, SWT.NONE);
				trayItem.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
				trayItem.setToolTipText("牛鼻子 · 协助管理日常工作");
			}
 */
