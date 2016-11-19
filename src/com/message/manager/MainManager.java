package com.message.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.message.Enity.Original;
import com.message.Enity.OriginalDAO;
import com.message.Enity.OriginalDetailDAO;
import com.message.Enity.OriginalImgDAO;


public class MainManager {
	
	@Autowired
	private OriginalDAO origianlDao;
	@Autowired
	private OriginalDetailDAO originalDetailDao;
	private OriginalImgDAO originalImgDao;

	public OriginalDetailDAO getOriginalDetailDao() {
		return originalDetailDao;
	}

	public OriginalDAO getOrigianlDao() {
		return origianlDao;
	}

	public List obtainOriginal(int pageIndex,int pageSize) {
		return getOrigianlDao().findByPage(pageIndex, pageSize);
	}
	
	public List obtainAllOriginal() {
		return getOrigianlDao().findAll();
	}

	public Original obtainOriginalByInfoId(Integer id) {
		return getOrigianlDao().findById(id);
	}

	public List obtainSearchOriginal(int pageIndex, int pageSize, String keyWords) {
		return getOrigianlDao().searchByKeyWords(pageIndex, pageSize, keyWords);
	}

	public OriginalImgDAO getOriginalImgDao() {
		return originalImgDao;
	}
	@Autowired
	public void setOriginalImgDao(OriginalImgDAO originalImgDao) {
		this.originalImgDao = originalImgDao;
	}
	
	/**
	 * 鐑棬
	 * @return
	 */
	public List<Original> getHotList() {
		List<Original> originals = origianlDao.findHotOri();
		return originals;
	}
}
