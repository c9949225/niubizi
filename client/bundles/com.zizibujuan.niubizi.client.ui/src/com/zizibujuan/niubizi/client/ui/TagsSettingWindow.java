package com.zizibujuan.niubizi.client.ui;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.zizibujuan.niubizi.client.ui.events.TagChangedListener;
import com.zizibujuan.niubizi.server.model.TagInfo;
import com.zizibujuan.niubizi.server.service.TagService;

/**
 * 标签设置窗口
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class TagsSettingWindow {

	private Shell shell;
	private Text txtTag;
	
	private Composite cpTable;
	private Table tblTags;
	
	private TagService tagService = ServiceHolder.getDefault().getTagService();
	
	public TagsSettingWindow(){
		shell = new Shell(SWT.CLOSE | SWT.ON_TOP | SWT.RESIZE);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		shell.setText("管理标签");
		
		// 输入区域
		Composite cpForm = new Composite(shell, SWT.NULL);
		GridLayout glForm = new GridLayout();
		glForm.numColumns = 3;
		cpForm.setLayout(glForm);
		
		// 标签文本
		Label lblTag = new Label(cpForm, SWT.NULL);
		lblTag.setText("标签名");
		// 标签名称输入框
		txtTag = new Text(cpForm, SWT.BORDER);
		txtTag.setSize(300, 50);
		
		// TODO:标签颜色框
		
		// 保存标签按钮
		Button btnOK = new Button(cpForm, SWT.PUSH);
		btnOK.setText("保存");
		btnOK.setBackground(new Color(shell.getDisplay(), 96,176,68));
		btnOK.addSelectionListener(new AddTagListener());
		
		// 标签列表区域
		cpTable = new Composite(shell, SWT.NONE);
		cpTable.setLayout(new FillLayout());
		refreshTagList(cpTable);
		
		shell.pack();
		shell.open();
	}

	private void refreshTagList(Composite parent) {
		if(tblTags != null){
			tblTags.dispose();
		}
		
		tblTags = new Table(parent, SWT.BORDER | SWT.V_SCROLL);
		
		tblTags.setHeaderVisible(false);
		
		List<TagInfo> definedTags = tagService.get();
		if(definedTags.isEmpty()){
			// TODO: 显示未定义标签
		}else{
			// 表头
			TableColumn tcName = new TableColumn(tblTags, SWT.CENTER);
			tcName.setWidth(300);
			
			TableColumn tcDocCount = new TableColumn(tblTags, SWT.CENTER);
			tcDocCount.setWidth(200);
			
			TableColumn tcDelete = new TableColumn(tblTags, SWT.CENTER);
			tcDelete.setWidth(100);
			
			TableColumn tcEdit = new TableColumn(tblTags, SWT.CENTER);
			tcEdit.setWidth(100);
			
			for(TagInfo tagInfo : definedTags){
				TableItem item = new TableItem(tblTags, SWT.NULL);
				
				item.setText(0, tagInfo.getName());
				item.setData(tagInfo);
				
				// TODO: 标注的文件数加粗显示
				item.setText(1, "已标注 " + tagInfo.getFileCount() + " 个文件");
				
				// 删除按钮
				TableEditor teDelete = new TableEditor(tblTags);
				
				Button btnDelete = new Button(tblTags, SWT.PUSH);
				
				ImageData idDelete = new ImageData(getClass().getResourceAsStream("/icons/iconfont-shanchu.png"));
				Image imgDelete = new Image(shell.getDisplay(), idDelete);
				btnDelete.setImage(imgDelete);
				btnDelete.setText("删除");
				btnDelete.setData("tagId", tagInfo.getId());
				btnDelete.addSelectionListener(new DeleteTagListener());
				
				teDelete.grabHorizontal = true;
				teDelete.minimumHeight = btnDelete.getSize().y;
				teDelete.minimumWidth = btnDelete.getSize().x;
						
				teDelete.setEditor(btnDelete, item, 2);
				
				// 编辑按钮
				TableEditor teEdit = new TableEditor(tblTags);
				Button btnEdit = new Button(tblTags, SWT.PUSH);
				
				ImageData idEdit = new ImageData(getClass().getResourceAsStream("/icons/iconfont-bianji.png"));
				Image imgEdit = new Image(shell.getDisplay(), idEdit);
				btnEdit.setImage(imgEdit);
				btnEdit.setText("编辑");
				
				teEdit.grabHorizontal = true;
				teEdit.minimumHeight = btnEdit.getSize().y;
				teEdit.minimumWidth = btnEdit.getSize().x;
						
				teEdit.setEditor(btnEdit, item, 3);
			}
		}
	}
	
	private final class AddTagListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String tagName = txtTag.getText().trim();
			if(StringUtils.isEmpty(tagName)){
				MessageBox messageBox = new MessageBox(shell);
				messageBox.setMessage("请输入标签名称");
				messageBox.open();
			}else{
				TagInfo existTag = tagService.findByName(tagName);
				if(existTag != null){
					MessageBox messageBox = new MessageBox(shell);
					messageBox.setMessage("'"+tagName+"'标签已存在");
					messageBox.open();
					return;
				}
				
				TagInfo tagInfo = new TagInfo();
				tagInfo.setName(tagName);
				tagInfo.setCreateTime(new Date()); // TODO:如何设置默认值
				tagService.add(tagInfo);
				if(tagChangedListener != null){
					tagChangedListener.tagChanged();
				}
				
				// TODO： 改成刷新标签列表
				refreshTagList(cpTable);
				cpTable.pack();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	private final class DeleteTagListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button source = (Button)e.getSource();
			int id = (int) source.getData("tagId");
			tagService.remove(id);
			if(tagChangedListener != null){
				tagChangedListener.tagChanged();
			}
			refreshTagList(cpTable);
			cpTable.pack();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	private TagChangedListener tagChangedListener;
	public void addTagChangedListener(TagChangedListener changedListener) {
		this.tagChangedListener = changedListener;
	}
}
