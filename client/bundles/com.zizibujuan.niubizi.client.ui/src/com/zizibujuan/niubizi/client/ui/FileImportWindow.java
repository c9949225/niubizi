package com.zizibujuan.niubizi.client.ui;


import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zizibujuan.niubizi.client.ui.events.TagChangedListener;
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
	
	private Composite tagContainer;
	
	
	public FileImportWindow(){
		window = new Shell(SWT.CLOSE | SWT.ON_TOP);
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
		
		// 操作图标， 设置标签
		Button btnTags = new Button(window, SWT.PUSH);
		ImageData imgTagsData = new ImageData(getClass().getResourceAsStream("/icons/iconfont-tags2.png"));
		Image imgTags = new Image(window.getDisplay(), imgTagsData);
		btnTags.setImage(imgTags);
		btnTags.setText("设置标签");
		btnTags.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				TagsSettingWindow settingWindow = new TagsSettingWindow();
				// 同步标签
				settingWindow.addTagChangedListener(new TagChangedListener() {
					@Override
					public void tagChanged() {
						if(tagContainer != null){
							refreshTagList(tagContainer);
						}
					}
				});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		// 文档后缀名
		GridData gdFileType = new GridData(GridData.FILL_HORIZONTAL);
		lblFileType = new Label(window, SWT.NULL);
		lblFileType.setLayoutData(gdFileType);
		// 文档名称
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		txtFileName = new Text(window, SWT.BORDER);
		txtFileName.setLayoutData(gd);
		
		// 显示tag标签
		tagContainer = new Composite(window, SWT.FILL);
		tagContainer.setLayout(new RowLayout(SWT.VERTICAL));
		refreshTagList(tagContainer);		
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
		//String fileType = FilenameUtils.getExtension(filePath);
		lblFileType.setText(FilenameUtils.getName(filePath));
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
