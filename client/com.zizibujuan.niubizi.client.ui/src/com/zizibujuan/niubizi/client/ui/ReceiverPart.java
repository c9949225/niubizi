package com.zizibujuan.niubizi.client.ui;


import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class ReceiverPart {

	@PostConstruct
	public void createControls(Composite parent){
		System.out.println("创建控件");
		System.out.println(parent);
		
		Text text = new Text(parent,SWT.FILL);
		Display display = parent.getDisplay();
		text.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
		DropTarget dropTarget = new DropTarget(text, DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK);
		dropTarget.setTransfer(new Transfer[]{FileTransfer.getInstance()});
		dropTarget.addDropListener(new DropTargetAdapter(){
			@Override
			public void drop(DropTargetEvent event) {
				String fileList[] = null;
                FileTransfer ft = FileTransfer.getInstance();
                if (ft.isSupportedType(event.currentDataType)) {
                    fileList = (String[]) event.data;
                }
                System.out.println(Arrays.toString(fileList));
			}
		});
	}
}
