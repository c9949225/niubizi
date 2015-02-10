package com.zizibujuan.niubizi.client.ui;


import org.apache.commons.io.FilenameUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zizibujuan.niubizi.client.ui.model.Tag;
import com.zizibujuan.niubizi.client.ui.service.TagService;
import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.service.FileService;

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
		window = new Shell(SWT.NULL);
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
		
		Composite container = new Composite(window, SWT.FILL);
		container.setLayout(new RowLayout(SWT.VERTICAL));
		java.util.List<Tag> tags = new TagService().get();
		for(Tag tag : tags){
			Button btn1 = new Button(container, SWT.TOGGLE);
			btn1.setText(tag.getName());
			btn1.setData(tag);
		}
		
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
		
		// 存储文件路径，文件名，保存时间
		FileService fileService = ServiceHolder.getDefault().getFileService();
		FileInfo fileInfo = new FileInfo();
		fileInfo.setPathName(filePath);
		fileService.add(fileInfo);
	}
}
