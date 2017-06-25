package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.FileDao;
import com.hangdaoju.model.File;
import com.hangdaoju.service.FileService;
import com.hangdaoju.util.StringUtils;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	FileDao fileDao;

	@Override
	public String add(File file) {
		file.setId(null);
		return fileDao.save(file).getId();
	}

	@Override
	public void update(File file) {
		fileDao.updateById(file.getId(), file);
	}

	@Override
	public void delete(File file) {
		fileDao.delete(file);
	}

	@Override
	public void deleteById(String id) {
		File file = new File();
		file.setId(id);
		fileDao.delete(file);
	}

	@Override
	public List<File> findByCondition(File file) {
		return fileDao.findByCondition(file);
	}

	@Override
	public List<File> findByCondition(File file, String groupBy, String sort) {
		if (StringUtils.isAnyBlank(groupBy, sort)) {
			return fileDao.findByCondition(file);
		}
		Order order = null;
		switch (sort) {
		case "asc":
			order = new Order(Direction.ASC, groupBy);
			break;
		case "desc":
			order = new Order(Direction.DESC, groupBy);
			break;
		default:
			throw new RuntimeException("排序方式必须是asc(升序)或desc(降序).");
		}
		return fileDao.sortByCondition(file, new Sort(order));
	}

	@Override
	public File find(String id) {
		return fileDao.findById(id);
	}

	@Override
	public boolean isLogicFileExsits(File file) {
		java.lang.reflect.Field[] fields = file.getClass().getDeclaredFields();
		File condition = new File();
		for (java.lang.reflect.Field field : fields) {
			if (field.getName().matches("^((projectId)|(name)|(logo)|(folderId))$")) {
				field.setAccessible(true);
				try {
					field.set(condition, field.get(file));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				continue;
			}
			field.setAccessible(true);
			try {
				field.set(condition, null);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return !findByCondition(condition).isEmpty();
	}

}
