package com.zizibujuan.niubizi.client.ui;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.zizibujuan.niubizi.client.ui.events.ItemChangedListener;
import com.zizibujuan.niubizi.server.model.CategoryInfo;
import com.zizibujuan.niubizi.server.model.TagInfo;
import com.zizibujuan.niubizi.server.service.CategoryService;

public class CategorySettingWindow {

	private Shell shell;
	
	private Text txtCategoryName;
	
	private Composite cpTable;
	private Table tblCategories;
	
	private CategoryService categoryService = ServiceHolder.getDefault().getCategoryService();
	
	public CategorySettingWindow(){
		shell = new Shell();
		
		GridLayout glShell = new GridLayout();
		glShell.numColumns = 3;
		shell.setLayout(glShell);
		
		Label lblCategoryName = new Label(shell, SWT.NULL);
		lblCategoryName.setText("分类名称");
		txtCategoryName = new Text(shell, SWT.BORDER);
		GridData gdCategoryName = new GridData();
		gdCategoryName.widthHint = 200;
		txtCategoryName.setLayoutData(gdCategoryName);
		
		Button btnSave = new Button(shell, SWT.PUSH);
		btnSave.setImage(NBZUtils.getImage(shell, "iconfont-baocun16_16.png"));
		btnSave.setText("保存");
		btnSave.addSelectionListener(new SaveCategoryListener());
		
		cpTable = new Composite(shell, SWT.NONE);
		GridData gdCategoryContainer = new GridData(SWT.FILL, SWT.FILL, true, true);
		gdCategoryContainer.horizontalSpan = 3;
		cpTable.setLayoutData(gdCategoryContainer);
		
		cpTable.setLayout(new FillLayout());
		refreshCategoryList(cpTable);
		
		shell.open();
	}
	
	private void refreshCategoryList(Composite parent) {
		if(tblCategories != null){
			tblCategories.dispose();
		}
		
		tblCategories = new Table(parent, SWT.BORDER | SWT.V_SCROLL);
		tblCategories.setHeaderVisible(false);
		
		List<CategoryInfo> definedCategories = categoryService.get();
		if(definedCategories.isEmpty()){
			// TODO: 显示"未定义标签"
		}else{
			// 表头
			TableColumn tcName = new TableColumn(tblCategories, SWT.CENTER);
			tcName.setWidth(300);
			
			TableColumn tcDocCount = new TableColumn(tblCategories, SWT.CENTER);
			tcDocCount.setWidth(200);
			
			TableColumn tcDelete = new TableColumn(tblCategories, SWT.CENTER);
			tcDelete.setWidth(100);
			
			TableColumn tcEdit = new TableColumn(tblCategories, SWT.CENTER);
			tcEdit.setWidth(100);
			
			for(CategoryInfo categoryInfo : definedCategories){
				TableItem item = new TableItem(tblCategories, SWT.NULL);
				
				item.setText(0, categoryInfo.getName());
				item.setData(categoryInfo);
				
				// TODO: 标注的文件数加粗显示
				//item.setText(1, "已关联 " + categoryInfo.getFileCount() + " 个文件");
				
				// 删除按钮
				TableEditor teDelete = new TableEditor(tblCategories);
				
				Button btnDelete = new Button(tblCategories, SWT.PUSH);
				
				ImageData idDelete = new ImageData(getClass().getResourceAsStream("/icons/iconfont-shanchu.png"));
				Image imgDelete = new Image(shell.getDisplay(), idDelete);
				btnDelete.setImage(imgDelete);
				btnDelete.setText("删除");
				btnDelete.setData("categoryId", categoryInfo.getId());
				btnDelete.addSelectionListener(new DeleteTagListener());
				
				teDelete.grabHorizontal = true;
				teDelete.minimumHeight = btnDelete.getSize().y;
				teDelete.minimumWidth = btnDelete.getSize().x;
						
				teDelete.setEditor(btnDelete, item, 2);
				
				// 编辑按钮
				TableEditor teEdit = new TableEditor(tblCategories);
				Button btnEdit = new Button(tblCategories, SWT.PUSH);
				
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
	
	private final class SaveCategoryListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String strCategoryName = txtCategoryName.getText().trim();
			if(StringUtils.isEmpty(strCategoryName)){
				MessageBox messageBox = new MessageBox(shell);
				messageBox.setMessage("请输入分类名称");
				messageBox.open();
			}else{
				CategoryInfo existCategory = categoryService.findByName(strCategoryName);
				if(existCategory != null){
					MessageBox messageBox = new MessageBox(shell);
					messageBox.setMessage("'"+strCategoryName+"'分类已存在");
					messageBox.open();
					return;
				}
				
				CategoryInfo categoryInfo = new CategoryInfo();
				categoryInfo.setName(strCategoryName);
				categoryInfo.setCreateTime(new Date()); 
				categoryService.add(categoryInfo);
				if(categoryChangedListener != null){
					categoryChangedListener.itemChanged();
				}
				
				// TODO： 改成刷新标签列表
				refreshCategoryList(cpTable);
				cpTable.pack();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			
		}
	}
	
	private final class DeleteTagListener implements SelectionListener {
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button source = (Button)e.getSource();
			int id = (int) source.getData("categoryId");
			categoryService.remove(id);
			if(categoryChangedListener != null){
				categoryChangedListener.itemChanged();
			}
			refreshCategoryList(cpTable);
			cpTable.pack();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private ItemChangedListener categoryChangedListener;
	public void addCategoryChangedListener(ItemChangedListener changedListener) {
		this.categoryChangedListener = changedListener;
	}
}
