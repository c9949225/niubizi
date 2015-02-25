package com.zizibujuan.niubizi.client.ui;

import java.io.File;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.Preferences;

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
		String homeDirString = preferences.get(NBZ.KEY_HOME, null);
		if(homeDirString == null){
			homeDirString = System.getProperty("user.home") 
					+ System.getProperty("file.separator") 
					+ NBZ.MANAGED_FOLDER;
			preferences.put(NBZ.KEY_HOME, homeDirString);
			preferences.flush();
		}
		
		// 判断文件夹是否已存在,若不存在则创建
		if(!new File(homeDirString).exists()){
			File homeDir = new File(homeDirString);
			homeDir.mkdirs();
			// 创建子文件夹
			new File(homeDir, NBZ.DIR_MANAGED).mkdir();
			new File(homeDir, NBZ.DIR_UNTRACKED).mkdir();
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
