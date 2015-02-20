package com.zizibujuan.niubizi.client.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileOpenLog;
import com.zizibujuan.niubizi.server.model.TagInfo;
import com.zizibujuan.niubizi.server.service.FileService;

public class FileQueryWindow {
	
	private Shell shell;
	
	private List<FileInfo> files;
	
	private FileService fileService = ServiceHolder.getDefault().getFileService();
	
	public FileQueryWindow(){
		shell = new Shell(SWT.CLOSE | SWT.ON_TOP);
		shell.setSize(600, 700);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		
		files = fileService.get();
		
		for(FileInfo f : files){
			Composite row = new Composite(shell, SWT.NULL);
			RowLayout rl = new RowLayout();
			rl.spacing = 7;
			row.setLayout(rl);
			
			Label lblFileIcon = new Label(row, SWT.NULL);
			lblFileIcon.setImage(NBZUtils.getFileIcon(f.getFileType()));
			Label lblFileName = new Label(row, SWT.WRAP);
			lblFileName.setText(f.getFileName());
			lblFileName.setToolTipText("双击打开文件");
			lblFileName.setCursor(new Cursor(shell.getDisplay(), SWT.CURSOR_HAND));
			lblFileName.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					File dir = NBZUtils.getManagedDir();
					try {
						Desktop.getDesktop().open(new File(dir, f.getFileName() + "." + f.getFileType()));
						// 记录下打开时间和次数
						FileOpenLog fileOpenLog = new FileOpenLog();
						fileOpenLog.setFileId(f.getId());
						fileOpenLog.setCreateTime(new Date());
						fileService.logOpenFile(fileOpenLog);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
			
			
			// 逐个查询并显示每个文件对应的标签
			List<TagInfo> tags = fileService.getTags(f.getId());
			System.out.println(tags.size());
			for(TagInfo t : tags){
				Label lblTag = new Label(row, SWT.BORDER);
				lblTag.setText(t.getName());
			}
		}
		
		

		
		shell.open();
	}

}
