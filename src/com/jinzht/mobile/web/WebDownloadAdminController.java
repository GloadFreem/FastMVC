package com.jinzht.mobile.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinzht.tools.ExcelUtil;
import com.jinzht.tools.Project;
import com.jinzht.web.entity.BusinessInvitationCode;
import com.jinzht.web.manager.CourseManager;

@Controller
public class WebDownloadAdminController {
	@Autowired
	private CourseManager curseManager; 
	
	
	@RequestMapping(value = "newSystem/downloadInviteCode")
	public String downloadInviteCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fileName = "excel文件";
		// 填充projects数据
		List<BusinessInvitationCode> codes = createInviteCodeData();
		List<Map<String, Object>> list = createExcelInviteCodeRecord(codes);
		String columnNames[] = { "ID", "所属课程", "邀请码", "是否已过期"};// 列名
		String keys[] = { "cid", "bname", "ccode", "cvalid"};// map中的key
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
	
	private List<BusinessInvitationCode> createInviteCodeData() {
		// TODO Auto-generated method stub
		// 自己实现
		List list  = this.curseManager.getBusinessInvitationCodeDao().findAll();
		
		return list;
	}
	
	private List<Map<String, Object>> createExcelInviteCodeRecord(List<BusinessInvitationCode> codes) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		BusinessInvitationCode project = null;
		for (int j = 0; j < codes.size(); j++) {
			project = codes.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("cid", project.getCid());
			mapValue.put("bname", project.getBusinessSchool().getBname());
			mapValue.put("ccode", project.getCcode());
			
			String valid = "已失效";
			if(project.getCvalid()!=null)
			{
				if(project.getCvalid().equals("1"))
				{
					valid= "有效";
				}
				
			}
			mapValue.put("cvalid", valid);
			
			listmap.add(mapValue);
		}
		return listmap;
	}
	@RequestMapping(value = "newSystem/downloadproject")
	public String download(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fileName = "excel文件";
		// 填充projects数据
		List<Project> projects = createData();
		List<Map<String, Object>> list = createExcelRecord(projects);
		String columnNames[] = { "ID", "项目名", "销售人", "负责人", "所用技术", "备注" };// 列名
		String keys[] = { "id", "name", "saler", "principal", "technology",
				"remarks" };// map中的key
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	private List<Project> createData() {
		// TODO Auto-generated method stub
		// 自己实现
		Project pro = new Project();
		pro.setName("科技");
		pro.setRemarks("ss");
		pro.setTechnology("高科技");

		List list = new ArrayList();

		list.add(pro);
		return list;
	}

	private List<Map<String, Object>> createExcelRecord(List<Project> projects) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		Project project = null;
		for (int j = 0; j < projects.size(); j++) {
			project = projects.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("id", project.getId());
			mapValue.put("name", project.getName());
			mapValue.put("technology", project.getTechnology());
			mapValue.put("remarks", project.getRemarks());
			listmap.add(mapValue);
		}
		return listmap;
	}
}
