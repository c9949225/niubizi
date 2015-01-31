package com.zizibujuan.niubizi.client.ui;


import java.awt.SystemTray;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
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

	@PostContextCreate
	public void postContextCreate(final IEventBroker eventBroker, final IEclipseContext context){
		eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, new AppStartupCompleteEventHandler(eventBroker, context));
	}
	
	private class AppStartupCompleteEventHandler implements EventHandler{
		
		private IEventBroker eventBroker;
		private IEclipseContext context;
		
		public AppStartupCompleteEventHandler(IEventBroker eventBroker, IEclipseContext context){
			this.eventBroker = eventBroker;
			this.context = context;
		}

		@Override
		public void handleEvent(Event event) {
			eventBroker.unsubscribe(this);
			
			Shell shell = (Shell)context.get(IServiceConstants.ACTIVE_SHELL);
			System.out.println(shell);
			
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
		}
		
	}
}
