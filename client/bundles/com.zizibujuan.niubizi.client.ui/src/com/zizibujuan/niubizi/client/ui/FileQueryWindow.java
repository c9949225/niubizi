package com.zizibujuan.niubizi.client.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

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
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		shell.setLayout(gl);
		
		// 工具栏
		ToolBar tl = new ToolBar(shell, SWT.BAR);
		ToolItem tiAddCategory = new ToolItem(tl, SWT.NULL);
		tiAddCategory.setText("添加分类");
		ToolItem tiAddTag = new ToolItem(tl, SWT.NULL);
		tiAddTag.setText("添加标签");
		
		tiAddCategory.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				CategorySettingWindow categorySettingWindow = new CategorySettingWindow();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tiAddTag.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				TagsSettingWindow settingWindow = new TagsSettingWindow();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		files = fileService.get();
		
		for(FileInfo f : files){
			Composite row = new Composite(shell, SWT.BORDER);
			GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
			row.setLayoutData(gd);
			row.setLayout(new RowLayout(SWT.VERTICAL));
			
			Composite part1 = new Composite(row, SWT.NULL);
			
			RowLayout rl = new RowLayout();
			rl.spacing = 7;
			part1.setLayout(rl);
			
			Label lblFileIcon = new Label(part1, SWT.NULL);
			lblFileIcon.setImage(NBZUtils.getFileIcon(f.getFileType()));
			Label lblFileName = new Label(part1, SWT.WRAP);
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
				Label lblTag = new Label(part1, SWT.BORDER);
				lblTag.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
				lblTag.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
				lblTag.setText(t.getName());
			}
			
			Composite part2 = new Composite(row, SWT.NULL);
			part2.setLayout(new RowLayout());
			
			Label lblTime = new Label(part2, SWT.NULL);
			lblTime.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
			
			String strTime = DateFormatUtils.format(f.getCreateTime(), "yyyy-MM-dd HH:mm");
			lblTime.setText(strTime);
		}
		
		shell.open();
	}

}
