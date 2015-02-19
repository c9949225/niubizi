package com.zizibujuan.niubizi.client.ui;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Shell;

public class NBZUtils {
	public static Image getImage(Device device, String imageName){
		ImageData imageData = new ImageData(NBZUtils.class.getResourceAsStream("/icons/" + imageName));
		return new Image(device, imageData);
	}
	
	public static Image getImage(Shell shell, String imageName){
		return getImage(shell.getDisplay(), imageName);
	}
}
