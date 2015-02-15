package com.zizibujuan.niubizi.client.ui;

import java.io.File;

import org.osgi.service.prefs.Preferences;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.prefs.PreferencesService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		// TODO:插入系统默认的标签
		
		Preferences preferences = ConfigurationScope.INSTANCE.getNode("com.zizibujuan.niubizi.client.ui");
		String homeDir = preferences.get("niubizi.home.dir", null);
		if(homeDir == null){
			homeDir = System.getProperty("user.home") + System.getProperty("file.separator") + ".niubizi";
			preferences.put("niubizi.home.dir", homeDir);
			preferences.flush();
		}
		
		// 判断文件夹是否已存在,若不存在则创建
		if(!new File(homeDir).exists()){
			new File(homeDir).mkdirs();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
