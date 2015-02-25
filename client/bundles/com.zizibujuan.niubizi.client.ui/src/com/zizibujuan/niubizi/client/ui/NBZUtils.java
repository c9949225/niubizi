package com.zizibujuan.niubizi.client.ui;

import java.io.File;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.service.prefs.Preferences;

public class NBZUtils {
	public static Image getImage(Device device, String imageName){
		ImageData imageData = new ImageData(NBZUtils.class.getResourceAsStream("/icons/" + imageName));
		return new Image(device, imageData);
	}
	
	public static Image getImage(Shell shell, String imageName){
		return getImage(shell.getDisplay(), imageName);
	}
	
	public static Image getFileIcon(String fileType){
		String iconName = "iconfont-file.png";
		if(fileType.equalsIgnoreCase("doc") || fileType.equalsIgnoreCase("docx")){
			iconName = "iconfont-word.png";
		}else if(fileType.equalsIgnoreCase("xls") || fileType.equalsIgnoreCase("xlsx")){
			iconName = "iconfont-fileexcel.png";
		}else if(fileType.equalsIgnoreCase("pdf")){
			iconName = "iconfont-filepdf.png";
		}else if(fileType.equalsIgnoreCase("ppt") || fileType.equalsIgnoreCase("pptx")){
			iconName = "iconfont-ppt.png";
		}else if(fileType.equalsIgnoreCase("txt")){
			iconName = "iconfont-txt.png";
		}
		
		ImageData imageData = new ImageData(NBZUtils.class.getResourceAsStream("/icons/" + iconName));
		return new Image(Display.getCurrent(), imageData);
	}
	
	public static File getManagedDir() {
		Preferences preferences = ConfigurationScope.INSTANCE.getNode("com.zizibujuan.niubizi.client.ui");
		String destDirString = preferences.get(NBZ.KEY_HOME, null);
		return new File(destDirString, NBZ.DIR_MANAGED);
	}
}
