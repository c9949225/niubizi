package com.zizibujuan.niubizi.client.ui;


import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

public class ReceiverPart {
	
	private FileImportWindow childWindow;

	@PostConstruct
	public void createControls(Composite parent){
		System.out.println("创建控件");
		System.out.println(parent);
		
		Display display = parent.getDisplay();
		// 为了让label垂直居中显示
		Composite labelCell = new Composite(parent, SWT.BORDER);
		GridData gridData = new GridData();
		gridData.heightHint = 50;
		gridData.widthHint = 50;
		labelCell.setLayoutData(gridData);
		labelCell.setLayout(new GridLayout());
		
		
		CLabel lblText = new CLabel(labelCell, SWT.CENTER);
		lblText.setText("拖放文件");
		lblText.setFont(new Font(display,"宋体",8,SWT.BOLD | SWT.CENTER));
		lblText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		lblText.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		
		Point tracePoint = new Point(-1, -1);
		
		Listener listener = new ShellMoveListener(lblText.getShell(), tracePoint);
		lblText.addListener(SWT.MouseMove, listener);
		lblText.addListener(SWT.MouseDown, listener);
		lblText.addListener(SWT.MouseUp, listener);
		
		//lblText.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
		
		lblText.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
			@Override
			public void mouseDown(MouseEvent e) {
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				new FileQueryWindow();
				
			}
		});
		

		DropTarget dropTarget = new DropTarget(parent, DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK);
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
                
                // 有文件拖拽进来时，弹出一个窗口
                // 单例
    			childWindow = new FileImportWindow();
                childWindow.show(fileList);
			}
		});
	}
}
