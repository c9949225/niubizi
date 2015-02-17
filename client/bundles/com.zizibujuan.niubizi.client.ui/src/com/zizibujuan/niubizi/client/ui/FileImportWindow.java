package com.zizibujuan.niubizi.client.ui;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.Preferences;

import com.zizibujuan.niubizi.client.ui.events.TagChangedListener;
import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileTag;
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
	
	private final class ToggleTagListener implements MouseListener {
		@Override
		public void mouseUp(MouseEvent e) {
			System.out.println(e);
			Label lbl = (Label)e.getSource();
			Composite c = lbl.getParent();
			Boolean selected = false;
			Object o = c.getData("selected");
			if(o != null){
				selected = Boolean.valueOf(o.toString());
			}

			Control[] children = c.getChildren();
			Label lblDuihao = (Label) children[0];
			Label lblRemove = (Label)children[2];
			
			FileTag ft = new FileTag();
			ft.setFileId(fileId);
			TagInfo tag = (TagInfo) c.getData("tagInfo");
			ft.setTagId(tag.getId());
			
			if(selected){
				lblDuihao.setImage(null);
				lblRemove.setImage(null);
				fileService.removeTag(ft);
			}else{
				ImageData imgDuihaoData = new ImageData(getClass().getResourceAsStream("/icons/iconfont-duihao.png"));
				lblDuihao.setImage(new Image(window.getDisplay(), imgDuihaoData));
				
				ImageData imgRemoveData = new ImageData(getClass().getResourceAsStream("/icons/iconfont-shanchu.png"));
				lblRemove.setImage(new Image(window.getDisplay(), imgRemoveData));
				
				fileService.addTag(ft);
			}
			c.setData("selected", !selected);
			
		}

		@Override
		public void mouseDown(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	private Shell window;
	
	// 文件完整路径
	private String filePath;
	private int fileId;
	
	private Label lblFileType;
	private Text txtFileName;
	
	private Composite tagContainer;
	
	private FileService fileService = ServiceHolder.getDefault().getFileService();
	private TagService tagService = ServiceHolder.getDefault().getTagService();
	
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
		RowLayout rwTagContainer = new RowLayout(SWT.VERTICAL);
		tagContainer.setLayout(rwTagContainer);
		
		refreshTagList(tagContainer);
	}

	private void refreshTagList(Composite container) {
		Control[] children = container.getChildren();
		for(Control c : children){
			c.dispose();
		}
		
		java.util.List<TagInfo> tags = tagService.get();
		for(TagInfo tag : tags){
			Composite cpTagContainer = new Composite(container, SWT.BORDER);
			RowData rd = new RowData(200, 20);
			cpTagContainer.setLayoutData(rd);
			cpTagContainer.setData("tagInfo", tag);
			cpTagContainer.setLayout(new RowLayout());
			
			Label lblSelected = new Label(cpTagContainer, SWT.NULL);
			RowData rdSelected = new RowData(16, 16);
			lblSelected.setLayoutData(rdSelected);
			lblSelected.setCursor(new Cursor(window.getDisplay(), SWT.CURSOR_HAND));
			
			Label lblTagName = new Label(cpTagContainer, SWT.NULL);
			lblTagName.setText(tag.getName());
			RowData rdTagName = new RowData(150, 16);
			lblTagName.setLayoutData(rdTagName);
			lblTagName.setCursor(new Cursor(window.getDisplay(), SWT.CURSOR_HAND));
			
			Label lblDelete = new Label(cpTagContainer, SWT.NULL);
			lblDelete.setSize(16, 16);
			RowData rdDelete = new RowData(16, 16);
			lblDelete.setLayoutData(rdDelete);
			lblDelete.setCursor(new Cursor(window.getDisplay(), SWT.CURSOR_HAND));
			
			lblSelected.addMouseListener(new ToggleTagListener());
			lblTagName.addMouseListener(new ToggleTagListener());
			lblDelete.addMouseListener(new ToggleTagListener());
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
		
		// 怎么判断文件已存在？先通过文件路径判断
		// 如果文件已存在，则加载出已存在文件的信息
		// 如果文件没有存在，则新增文件
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFilePath(filePath);
		fileInfo.setFileName(fileName);
		fileInfo.setCreateTime(new Date());
		
		// TODO:解析文件名是否符合规范
		
		// 复制文件
		Preferences preferences = ConfigurationScope.INSTANCE.getNode("com.zizibujuan.niubizi.client.ui");
		String destDirString = preferences.get("niubizi.home.dir", null);
		
		// 先判断同名的文件是否已存在，如果存在则提示用户修改文件名，不能重名。
		
		try {
			File destFile = new File(new File(destDirString, NBZ.DIR_MANAGED), new File(filePath).getName());
			if(destFile.exists()){
				System.out.println("同名文件已存在");
				String uuid = UUID.randomUUID().toString();
				File unTrackedDir = new File(destDirString, NBZ.DIR_UNTRACKED);
				FileUtils.copyFile(new File(filePath), new File(unTrackedDir, uuid));
				
				fileInfo.setUntrackFileName(uuid);
				fileInfo.setFileManageStatus(NBZ.FILE_UNTRACKED);
			}else{
				// 将文件移到受管的文件夹下
				File managedDir = new File(destDirString, NBZ.DIR_MANAGED);
				FileUtils.copyFileToDirectory(new File(filePath), managedDir);
				fileInfo.setFileManageStatus(NBZ.FILE_MANAGED);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileId = fileService.add(fileInfo);
	}
}
