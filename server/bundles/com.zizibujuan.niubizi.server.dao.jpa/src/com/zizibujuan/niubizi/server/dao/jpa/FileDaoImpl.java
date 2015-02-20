package com.zizibujuan.niubizi.server.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.zizibujuan.niubizi.server.dao.FileDao;
import com.zizibujuan.niubizi.server.model.EntityManagerFactoryService;
import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileOpenLog;
import com.zizibujuan.niubizi.server.model.FileTag;
import com.zizibujuan.niubizi.server.model.TagInfo;

public class FileDaoImpl implements FileDao{

	@Override
	public int add(FileInfo fileInfo) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(fileInfo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return fileInfo.getId();
	}

	@Override
	public void removeTag(FileTag fileTag) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		FileTag o = entityManager.createQuery("select t from FileTag t where t.fileId=:fileId and t.tagId=:tagId", FileTag.class)
			.setParameter("fileId", fileTag.getFileId())
			.setParameter("tagId", fileTag.getTagId()).getSingleResult();
		entityManager.remove(o);
		
		// 标签标注的文件数-1
		TagInfo t = entityManager.find(TagInfo.class, fileTag.getTagId());
		t.decreaseFileCount();
		entityManager.merge(t);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void addTag(FileTag fileTag) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(fileTag);
		
		// 标签标注的文件数+1
		TagInfo t = entityManager.find(TagInfo.class, fileTag.getTagId());
		t.increaseFileCount();
		entityManager.merge(t);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void update(FileInfo fileInfo) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(fileInfo);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public FileInfo findFileByName(String fileName) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		List<FileInfo> result = entityManager.createQuery("select t from FileInfo t where t.fileName=:fileName", FileInfo.class).setParameter("fileName", fileName).getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public List<FileInfo> get() {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		return entityManager.createQuery("select t from FileInfo t order by t.id desc", FileInfo.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TagInfo> getTags(int fileId) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		return entityManager
			.createNativeQuery("select b.DBID, b.TAG_NAME from NBZ_FILE_TAG a,NBZ_TAG b where a.TAG_ID = b.DBID and a.FILE_ID=?", TagInfo.class)
			.setParameter(1, fileId).getResultList();
		
	}

	@Override
	public void logOpenFile(FileOpenLog fileOpenLog) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(fileOpenLog);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
