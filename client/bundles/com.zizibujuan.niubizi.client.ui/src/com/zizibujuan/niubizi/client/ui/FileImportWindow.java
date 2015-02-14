package com.zizibujuan.niubizi.client.ui;


import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.TagInfo;
import com.zizibujuan.niubizi.server.service.FileService;
import com.zizibujuan.niubizi.server.service.TagService;

/**
 * 补充文件信息的窗口
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class FileImportWindow {
	
	private Shell window;
	
	// 文件完整路径
	private String filePath;
	
	private Label lblFileType;
	private Text txtFileName;
	
	
	public FileImportWindow(){
		window = new Shell(SWT.CLOSE);
		window.setSize(300, 600);
        // TODO: 计算相对位置
		int x = 150;
		int y = 100;
		window.setLocation(x, y);
	        
		createWindowContent();
	}
	
	/**
	 * 创建窗体内容
	 */
	private void createWindowContent(){
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		window.setLayout(layout);
		// 文档后缀名
		GridData gdFileType = new GridData(GridData.FILL_HORIZONTAL);
		lblFileType = new Label(window, SWT.NULL);
		lblFileType.setLayoutData(gdFileType);
		// 文档名称
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		txtFileName = new Text(window, SWT.BORDER);
		txtFileName.setLayoutData(gd);
		
		// 显示tag标签
		Composite container = new Composite(window, SWT.FILL);
		container.setLayout(new RowLayout(SWT.VERTICAL));
		refreshTagList(container);
		
		Composite tagAddContainer = new Composite(window, SWT.FILL);
		tagAddContainer.setLayout(new RowLayout(SWT.HORIZONTAL));
		Text txtTagName = new Text(tagAddContainer, SWT.BORDER);
		Button btnAddTag = new Button(tagAddContainer, SWT.PUSH);
		btnAddTag.setText("新增标签");
		btnAddTag.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String tagName = txtTagName.getText().trim();
				if(StringUtils.isEmpty(tagName)){
					// TODO： 提示必填
				}else{
					TagInfo tagInfo = new TagInfo();
					tagInfo.setName(tagName);
					tagInfo.setCreateTime(new Date()); // TODO:如何设置默认值
					TagService tagService = ServiceHolder.getDefault().getTagService();
					tagService.add(tagInfo);
					
					// TODO： 改成刷新标签列表
					refreshTagList(container);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void refreshTagList(Composite container) {
		Control[] children = container.getChildren();
		for(Control c : children){
			c.dispose();
		}
		
		java.util.List<TagInfo> tags = ServiceHolder.getDefault().getTagService().get();
		for(TagInfo tag : tags){
			Button btn1 = new Button(container, SWT.TOGGLE);
			btn1.setText(tag.getName());
			btn1.setData(tag);
		}
		container.pack();
	}
	
	public void show(String... filePathArray){
		
		if(!window.isVisible()){
			window.open();
		}
		// 第一版本，只支持一次托动一个文件。
		if(filePathArray.length == 0){
			return;
		}
		filePath = filePathArray[0];
		String fileType = FilenameUtils.getExtension(filePath);
		lblFileType.setText(fileType);
		String fileName = FilenameUtils.getBaseName(filePath);
		txtFileName.setText(fileName);
		txtFileName.setSelection(fileName.length(), fileName.length());
		
		// 存储文件路径，文件名，保存时间
		FileService fileService = ServiceHolder.getDefault().getFileService();
		FileInfo fileInfo = new FileInfo();
		fileInfo.setPathName(filePath);
		fileInfo.setCreateTime(new Date());
		fileService.add(fileInfo);
		
		
		
	}
}
