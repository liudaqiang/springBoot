package com.hangdaoju.model;

import java.util.Iterator;

import com.hangdaoju.service.FolderService;

public class FolderTree {
	private Folder currentFolder;// 当前文件夹
	private java.util.List<FolderTree> children = new java.util.ArrayList<>();// 子文件夹

	public FolderTree() {
		// TODO Auto-generated constructor stub
	}

	public FolderTree(Folder folder) {
		this.currentFolder = folder;
	}

	public FolderTree init(FolderService folderService) {
		java.util.List<Folder> childrenFolders = folderService.findByParent(currentFolder.getId());
		Iterator<Folder> iterator = childrenFolders.iterator();
		while (iterator.hasNext()) {
			this.children.add(new FolderTree(iterator.next()).init(folderService));
		}
		return this;
	}

	public Folder getCurrentFolder() {
		return currentFolder;
	}

	public void setCurrentFolder(Folder currentFolder) {
		this.currentFolder = currentFolder;
	}

	public java.util.List<FolderTree> getChildren() {
		return children;
	}

	public void setChildren(java.util.List<FolderTree> children) {
		this.children = children;
	}

}
