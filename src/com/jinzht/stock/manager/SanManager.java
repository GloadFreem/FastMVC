package com.jinzht.stock.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jinzht.stock.entity.SanAsset;
import com.jinzht.stock.entity.SanAssetDAO;
import com.jinzht.stock.entity.SanCompany;
import com.jinzht.stock.entity.SanCompanyDAO;
import com.jinzht.stock.entity.SanIncome;
import com.jinzht.stock.entity.SanIncomeDAO;
import com.jinzht.stock.entity.SanLiabilities;
import com.jinzht.stock.entity.SanLiabilitiesDAO;
import com.jinzht.stock.entity.SanManagementer;
import com.jinzht.stock.entity.SanManagementerDAO;
import com.jinzht.stock.entity.SanProfit;
import com.jinzht.stock.entity.SanProfitDAO;
import com.jinzht.stock.entity.SanShareholder;
import com.jinzht.stock.entity.SanShareholderDAO;
import com.jinzht.stock.entity.StockJsonBean;
import com.jinzht.stock.entity.StockJsonBean.ContentBean;
import com.jinzht.stock.entity.StockJsonDetailBean;
import com.jinzht.stock.entity.StockJsonDetailBean.BaseinfoBean;
import com.jinzht.stock.entity.StockJsonDetailBean.ExecutivesBean;
import com.jinzht.stock.entity.StockJsonDetailBean.TopTenHoldersBean;
import com.jinzht.tools.Config;
import com.jinzht.tools.HttpUtil;

/**
 * 新三板
 * 
 * @author Administrator
 *
 */
public class SanManager {

	private SanCompanyDAO companyDAO;
	private SanAssetDAO assetDAO;
	private SanIncomeDAO incomeDAO;
	private SanLiabilitiesDAO liabilitiesDAO;
	private SanManagementerDAO managementerDAO;
	private SanProfitDAO profitDAO;
	private SanShareholderDAO shareholderDAO;

	private String URL_SAN_LIST = "http://www.neeq.com.cn/nqhqController/nqhq.do?callback=&type=G&xxfcbj=1&page=";
	
	private String URL_SAN_LIST_2 = "http://www.neeq.com.cn/nqhqController/nqhq.do?callback=&type=G&xxfcbj=0&page=";
	
	private String URL_SAN_DETAIL = "http://www.neeq.com.cn/nqhqController/detailCompany.do?callback=&zqdm=";

	private String URL_SAN_DESC = "http://www.ipo3.com/company-show/stock-%d.html";

	private String URL_SAN_FINANCE = "http://www.ipo3.com/company-show/stock-%d-tab-finance.html";

	protected static final String LAG = "SanManager";
	// 是否是最后一页
	private boolean isLastPage = false;
	// 第一页
	private int pageIndex = -1;
	private int pageIndex2 = -1;
	

	// isNew
	private boolean isNewPush = true;

	private int allsize = 0;

	private int newSize = 0;
	
	private int changeNum = 0;
	
	private SanCompany changeCompany ;

	/**
	 * 开启抓取方法 执行一次
	 */
	public void startPushService(boolean isNew) {
		isNewPush = isNew;
		
		int nowBaseNum = getNewJsonDataNum(0);
		//创新层；
		int nowNewNum = getNewJsonDataNum(1);	
		
		System.out.println(LAG + "----------------基础i："+nowBaseNum+"  创新:  "+nowNewNum);
		
		int  page = nowBaseNum/20+2;
		int page2 = nowNewNum/20+2;
		
		System.out.println(LAG + "----------------pagei："+page+"  page2:  "+page2);
		
		
		// 线程池
		ExecutorService threadPool = Executors
				.newFixedThreadPool(Config.MAX_THREAD_NUM_SAN);
		// 开启线程
			for (int i=0;i<page2;i++) {
				System.out.println(LAG + "----------------抓取i："+i);
				threadPool.execute(new Runnable() {
					public void run() {
						int page = getPageIndex();
						String url = URL_SAN_LIST + page;
						pushJsonFromHttp(url);
					}
				});
			}
			
			for (int j=0;j<page;j++) {
				System.out.println(LAG + "----------------抓取j："+j);
				threadPool.execute(new Runnable() {
					public void run() {
						int page = getPageIndex2();
						String url = URL_SAN_LIST_2 + page;
						pushJsonFromHttp(url);
					}
				});
		}
		// 任务执行完毕，关闭线程池
		threadPool.shutdown();
	}
	
	/**
	 * 更新所有数据
	 */
	public void refreshDataService() {
		//获取当前数据库数据
		//基础层；
		int  oldBaseNum = this.companyDAO.getCountByType("0");
		//创新层
		int oldNewNum = this.companyDAO.getCountByType("1");		
		
		
		System.out.println(LAG + "----------------基础i："+oldBaseNum+"  创新:  "+oldNewNum);
		//获取股转网站最新数据
		//基础层；
		int nowBaseNum = getNewJsonDataNum(0);
		//创新层；
		int nowNewNum = getNewJsonDataNum(1);			
		
		System.out.println(LAG + "----------------基础i："+nowBaseNum+"  创新:  "+nowNewNum);
		//比对数据
		ExecutorService threadPool;
		threadPool = Executors
				.newFixedThreadPool(Config.MAX_THREAD_NUM_SAN);
		if(nowBaseNum>oldBaseNum){
			int maxPage = (nowBaseNum-oldBaseNum)%20+2;
			pageIndex2 = -1;
			isNewPush = false;
			for (int i=0;i<2;i++) {
				System.out.println(LAG + "----------------抓取i："+pageIndex2);
				// if(pageIndex<499){
				// System.out.println(LAG + "----------------pageIndex："
				// + pageIndex);
				threadPool.execute(new Runnable() {
					public void run() {
						int page2 = getPageIndex2();
						String url = URL_SAN_LIST_2 + page2;
						pushJsonFromHttp(url);
					}
				});
			}
		}
//		if(nowNewNum>oldNewNum){
//			int maxPage2 = (nowNewNum-nowNewNum)%20+2;
//			pageIndex = -1;
//			isNewPush = false;
//			for (int i=0;i<maxPage2;i++) {
//				System.out.println(LAG + "----------------抓取i："+pageIndex);
//				// if(pageIndex<499){
//				// System.out.println(LAG + "----------------pageIndex："
//				// + pageIndex);
//				threadPool.execute(new Runnable() {
//					public void run() {
//						int page = getPageIndex();
//						String url = URL_SAN_LIST + page;
//						pushJsonFromHttp(url);
//					}
//				});
//			}
//		}
		
		threadPool.shutdown();
		
		//执行操作；
	

	}
	
	
	private int getNewJsonDataNum(int i) {
		String url = "";
		if(i==1){
			url = URL_SAN_LIST +0;
		}else{
			url = URL_SAN_LIST_2+0;
		}
		int num = -1;
		String jsonStr = HttpUtil.getHtmlString(url);
		StockJsonBean stockJsonBean = new StockJsonBean();

		if (jsonStr != null && jsonStr.length() > 0) {
			jsonStr = jsonStr.substring(2, jsonStr.length() - 2);
			try {
				Gson gson = new Gson();
				java.lang.reflect.Type type = new TypeToken<StockJsonBean>() {
				}.getType();
				stockJsonBean = gson.fromJson(jsonStr, type);
				num = stockJsonBean.getTotalElements();
			}catch(Exception e){
				
			}
		}
		
		return num;
	}

	/**
	 * 更新run方法
	 * @param list
	 */
	public synchronized void refreshData(List<SanCompany> list){
		System.out.println(LAG + "---11");
		SanCompany changeCompany =  list.get(changeNum);
		System.out.println(LAG + "----------------code：" + changeCompany.getCCode());
		changeCompany = getCompanyDetail(changeCompany);
		companyDAO.attachDirty(changeCompany);
		if(changeNum<list.size()){
			changeNum++;
		}
	}

	public void deleteService() {
		// TODO Auto-generated method stub

		ExecutorService threadPool = Executors
				.newFixedThreadPool(2);
		int coutNum1 = getCompanyDAO().getCountByType(0);
		int coutNum2 = getCompanyDAO().getCountByType(1);
		final int page = (coutNum1+coutNum2)/10 +1;
		System.out.println("allNum:"+(coutNum1+coutNum2)+";page:"+page);
		threadPool.execute(new Runnable() {
			public void run() {
				List<SanCompany> compaynlist;
				for(int i=0;i<page;i++){
					compaynlist = getCompanyDAO().findListByPage(0);
					for(SanCompany s: compaynlist){
						getCompanyDAO().delete(s);
					}
				}
				
			}
		});
		
		// 任务执行完毕，关闭线程池
		threadPool.shutdown();
		
		//
	}
	
	public synchronized int  getPageIndex2() {
		pageIndex2++;
		return pageIndex2;
	}

	public void setPageIndex2(int pageIndex2) {
		this.pageIndex2 = pageIndex2;
	}

	public synchronized int getPageIndex() {
		pageIndex++;
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 抓取任务 
	 * 线程 不同步
	 */
	private  void pushJsonFromHttp(String pushurl) {
      String url = pushurl;
		String jsonStr = HttpUtil.getHtmlString(url);
		StockJsonBean stockJsonBeans = new StockJsonBean();
		List<SanCompany> companieList = new ArrayList<SanCompany>();

		if (jsonStr != null && jsonStr.length() > 0) {
			jsonStr = jsonStr.substring(2, jsonStr.length() - 2);
			try {
				Gson gson = new Gson();
				java.lang.reflect.Type type = new TypeToken<StockJsonBean>() {
				}.getType();
				stockJsonBeans = gson.fromJson(jsonStr, type);
				List<ContentBean> contentlist = stockJsonBeans.getContent();
				isLastPage = stockJsonBeans.isFirstPage();
//				 System.out.println(LAG + "--------contentlist：" +
//				 contentlist.size());
				if (contentlist != null && contentlist.size() > 0) {
					for (ContentBean s : contentlist) {
						SanCompany company = new SanCompany();
						// 公司名称
						company.setCName(removeFourChar(s.getHqzqjc()));
						// 股票代码
						company.setCCode(s.getHqzqdm());
						// 板块类型；
						company.setSharesType(s.getXxfcbj());
						// 获取其它数据
						company = getCompanyDetail(company);

						companieList.add(company);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(LAG + "--------error：");
			}

			// System.out.println(LAG + "--------companieList：" +
			// companieList.size());
			// 存储 数据
			if (companieList.size() > 0) {
				if (companieList.size() != 20) {
					System.out.println(LAG + "-----!!!!!!!!!!!!!!未抓取成功地址地址："
							+ url);
				} else {
					System.out.println(LAG + "----------------成功地址地址：" + url);
				}
				saveOrUpdate(companieList);
			}
		}

		// isLastPage = true;

	}

	/**
	 * 获取公司详情
	 * 
	 * @param hqzqdm
	 * @return
	 */
	private SanCompany getCompanyDetail(SanCompany company) {
		// TODO Auto-generated method stub
		String code = company.getCCode();
		String url = URL_SAN_DETAIL + code;
		// json对象
		StockJsonDetailBean detailBean = new StockJsonDetailBean();

		// company.setSanAssets(null);
		// company.setSanIncomes(null);
		// company.setSanIncomes(null);
		// company.setSanShareholders(null);
		// company.setSanProfits(null);
		// company.setSanManagementers(null);
		// company.setSanLiabilitieses(null);

		if (!code.isEmpty()) {
			String detailStr = HttpUtil.getHtmlString(url);

			if (detailStr.length() > 0) {
				try {
					detailStr = detailStr.substring(1, detailStr.length() - 1);
//					 System.out.println(LAG + "--------detailStr：" +
//					 detailStr);
					Gson gson = new Gson();
					java.lang.reflect.Type type = new TypeToken<StockJsonDetailBean>() {
					}.getType();
					detailBean = gson.fromJson(detailStr, type);
					if (detailBean != null) {
						BaseinfoBean baseinfo = detailBean.getBaseinfo();
						// 全名
						company.setCFullname(baseinfo.getName());
						// 注册地址： 杭州市滨江区秋溢路500号1幢4楼405—407室
						company.setCAddress(baseinfo.getAddress());
						// 法人代表： 彭笑刚
						company.setCCorporate(removeFourChar(baseinfo.getLegalRepresentative()));
						// 公司董秘： 张玉仿
						company.setCSecretary(baseinfo.getSecretaries());
						// 行业分类： 化学原料和化学制品制造业
						company.setCType(baseinfo.getIndustry());
						// 挂牌日期： 20140805
						company.setCCreateDate(baseinfo.getListingDate());
						// 总股本： 75000000
						company.setSharesNum(baseinfo.getTotalStockEquity());
						// 邮政编码：
						// 公司电话： 010-82491169
						company.setCTel(baseinfo.getPhone());
						//省份
						company.setSanCompanycol(baseinfo.getArea());
						// 公司传真： 010-82491279
						// 公司网址： http://www.najingtech.com
						// 主办券商： 中泰证券股份有限公司
						company.setSecuritiesTrader(baseinfo.getBroker());
						// 转让方式： 做市
						company.setAssignment(baseinfo.getTransferMode());

						// 高管人员
						List<ExecutivesBean> eBeans = detailBean
								.getExecutives();
						if (eBeans != null) {
							Set<SanManagementer> mList = new HashSet<SanManagementer>();
							for (ExecutivesBean e : eBeans) {
								SanManagementer managementer = new SanManagementer();
								managementer.setName(removeFourChar(e.getName()));
								managementer.setPosition(e.getJob());
								managementer.setSanCompany(company);
								mList.add(managementer);
							}
							company.setSanManagementers(mList);
						}

						// 10大股东
						List<TopTenHoldersBean> tHoldersBeans = detailBean
								.getTopTenHolders();
						Set<SanShareholder> hShareholders = new HashSet<SanShareholder>();
						if (tHoldersBeans != null) {
							for (TopTenHoldersBean holder : tHoldersBeans) {
								SanShareholder hShareholder = new SanShareholder();
								hShareholder.setName(removeFourChar(holder.getName()));
								hShareholder.setPercent(holder.getQuantity());
								hShareholder.setSanCompany(company);
								hShareholder.setStock(holder.getRatio());
								hShareholders.add(hShareholder);
							}
							company.setSanShareholders(hShareholders);
						}

						// desc
						String desc = getCompanyDesc(company.getCCode());
						company.setCDesc(desc);

						// 财务报表
						company = getFinanceDetail(company);

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		}
		return company;
	}

	/**
	 * 财务报表
	 * 
	 * @param cCode
	 * @return
	 */
	private SanCompany getFinanceDetail(SanCompany canCompany) {
		// 资产表
		Set<SanAsset> sanAssets = new HashSet<SanAsset>();
		// 负债表
		Set<SanLiabilities> sanLiabilities = new HashSet<SanLiabilities>();
		// 营业收入
		Set<SanIncome> sanIncomeme = new HashSet<SanIncome>();
		// 利润表
		Set<SanProfit> sanProfits = new HashSet<SanProfit>();
		// 解析html
		String url = URL_SAN_FINANCE.replace("%d", canCompany.getCCode());

		try {
			// div .edit-intro-inner
			String htmlStr = HttpUtil.getHtmlString(url);
			Document doc = Jsoup.parseBodyFragment(htmlStr);
			// System.out.println(LAG + "--------doc："+ doc.toString());
			// edit-panel
			Elements elements = doc.select("div.edit-panel");
			Elements titlerowlist = doc.select("dt.table-head");
			// System.out.println(LAG + "--------titlerowlist："+
			// titlerowlist.toString());
			if (titlerowlist != null && titlerowlist.size() > 0) {
				Element titlerow = titlerowlist.get(0);
				// System.out.println(LAG + "--------titlerow："+
				// titlerow.toString());
				Elements titleTextlist = titlerow.select(".table-col");

				// if(titlelist.size()>1){
				Elements rows = doc.select("div.table-row");
				// System.out.println(LAG + "--------rows："+ rows.toString());
				// for(int i=0;i<titlelist.size()-1;i++){
				for (int j = 0; j < rows.size(); j++) {
					Element tr = rows.get(j);

					// System.out.println(LAG + "--------tr："+ tr.toString());

					// for(int k = 0; k < td.size(); k++){
					// }

					Elements td = tr.select(".table-col");

					if (titleTextlist.size() > 1) {
						for (int t = 1; t < titleTextlist.size(); t++) {
							// System.out.println(LAG + "------头："
							// + td.get(0).text().toString());
							// System.out.println(LAG + "------year："
							// + titleTextlist.get(t).toString());
							// System.out.println(LAG + "------数值："
							// + td.get(t).text().toString());
							if (td.get(0).text().equals("营业收入")) {
								SanIncome income = new SanIncome();
								income.setSanCompany(canCompany);
								income.setIncome(td.get(t).text());
								income.setYear(titleTextlist.get(t).text());
								sanIncomeme.add(income);
							} else if (td.get(0).text().equals("资产总计")) {
								SanAsset asset = new SanAsset();
								asset.setSanCompany(canCompany);
								asset.setAllAsset(td.get(t).text());
								asset.setYear(titleTextlist.get(t).text());
								sanAssets.add(asset);
							} else if (td.get(0).text().equals("负债合计")) {
								SanLiabilities liabilities = new SanLiabilities();
								liabilities.setSanCompany(canCompany);
								liabilities.setLiabilities(td.get(t).text());
								liabilities.setYear(titleTextlist.get(t).text());
								sanLiabilities.add(liabilities);
							} else if (td.get(0).text().equals("净利润")) {
								SanProfit profit = new SanProfit();
								profit.setSanCompany(canCompany);
								profit.setProfit(td.get(t).text());
								profit.setYear(titleTextlist.get(t).text());
								sanProfits.add(profit);
							}
						}
					}
				}
				// System.out.println(LAG + "----："+ sanIncomeme.size());
				// System.out.println(LAG + "----："+ sanAssets.size());
				// System.out.println(LAG + "----："+ sanLiabilities.size());
				// System.out.println(LAG + "----："+ sanProfits.size());
				canCompany.setSanAssets(sanAssets);
				canCompany.setSanLiabilitieses(sanLiabilities);
				canCompany.setSanProfits(sanProfits);
				canCompany.setSanIncomes(sanIncomeme);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(LAG + "-------失败地址：" + url);
		}
		return canCompany;
	}

	/**
	 * 公司简介
	 * @param cCode
	 * @return
	 */
	private String getCompanyDesc(String cCode) {
		// TODO Auto-generated method stub
		String desc = "";
		String url = URL_SAN_DESC.replace("%d", cCode);

		// div .edit-intro-inner
		String htmlStr = HttpUtil.getHtmlString(url);
		Document doc = Jsoup.parseBodyFragment(htmlStr);
		Elements elements = doc.select("div.edit-intro-inner");

		if (elements.size() > 0) {
			desc = elements.get(1).text();
		} else {
			System.out.println(LAG + "-------无详情-descUrl：" + url);
		}

		// System.out.println(LAG + "--------desc：" + desc);
		return desc;
	}

	/**
	 * 存储数据
	 * 
	 * @param companieList
	 */
	private void saveOrUpdate(List<SanCompany> companieList) {
		// TODO Auto-generated method stub
		// System.out.println(LAG + "--------companieList2：" +
		// companieList.size());
		int successNum = 0;

		for (SanCompany sanCompany : companieList) {

			// }else{
			if (isNewPush) {
				this.companyDAO.save(sanCompany);
			} else {
				List<SanCompany> beanlist = companyDAO.findByCCode(sanCompany
						.getCCode());
				if (beanlist != null & beanlist.size() > 0) {
					// System.out.println(LAG + "-------重复code：" +
					// beanlist.get(0).getCid());
					// sanCompany.setCid(beanlist.get(0).getCid());
					// this.companyDAO.attachDirty(sanCompany);
				} else {
					this.companyDAO.save(sanCompany);
					System.out.println(LAG + "新增数据：" + sanCompany.getCCode());
					newSize++;
				}
			}
			// }
		}
		int num = companieList.size() + allsize;
		allsize = num;
		if (!isNewPush) {
			System.out.println(LAG + "新增数据：" + newSize);
		} else {
			System.out.println(LAG + "数据库添加数据：" + num);
		}
	}
	
	/**
	 * 三板列表
	 * @param page
	 * @return
	 */
	public List findListByPage(Integer page) {
		// TODO Auto-generated method stub
		List<SanCompany> list = companyDAO.findListByPage(page);
		List<SanCompany> newlist = new ArrayList<SanCompany>();
		for(SanCompany sCompany:list){
			sCompany.setCAddress(null);
			sCompany.setCCreateDate(null);
			sCompany.setAssignment(null);
			sCompany.setCCorporate(null);
			sCompany.setCFullname(null);
			sCompany.setCTest(null);
			sCompany.setSanShareholders(null);
			sCompany.setSanManagementers(null);
			sCompany.setSecuritiesTrader(null);
			sCompany.setSanAssets(null);
			sCompany.setSanLiabilitieses(null);
			sCompany.setCDesc(null);
			sCompany.setCTel(null);
			sCompany.setSharesNum(null);
			sCompany.setCSecretary(null);
			newlist.add(sCompany);
		}
		return newlist;
	}
	
	
	public List findListBySearchPage(String seach, String type,
			Integer page) {
		// TODO Auto-generated method stub
				List<SanCompany> list = companyDAO.findListBySearchPage(seach,type,page);
				List<SanCompany> newlist = new ArrayList<SanCompany>();
				for(SanCompany sCompany:list){
					sCompany.setCAddress(null);
					sCompany.setCCreateDate(null);
					sCompany.setAssignment(null);
					sCompany.setCCorporate(null);
					sCompany.setCFullname(null);
					sCompany.setCTest(null);
					sCompany.setSanShareholders(null);
					sCompany.setSanManagementers(null);
					sCompany.setSecuritiesTrader(null);
					sCompany.setSanAssets(null);
					sCompany.setSanLiabilitieses(null);
					sCompany.setCDesc(null);
					sCompany.setCTel(null);
					sCompany.setSharesNum(null);
					sCompany.setCSecretary(null);
					newlist.add(sCompany);
				}
				return newlist;
	}
	
	
	
	
	

	public SanCompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	@Autowired
	public void setCompanyDAO(SanCompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public SanAssetDAO getAssetDAO() {
		return assetDAO;
	}

	@Autowired
	public void setAssetDAO(SanAssetDAO assetDAO) {
		this.assetDAO = assetDAO;
	}

	public SanIncomeDAO getIncomeDAO() {
		return incomeDAO;
	}

	@Autowired
	public void setIncomeDAO(SanIncomeDAO incomeDAO) {
		this.incomeDAO = incomeDAO;
	}

	public SanLiabilitiesDAO getLiabilitiesDAO() {
		return liabilitiesDAO;
	}

	@Autowired
	public void setLiabilitiesDAO(SanLiabilitiesDAO liabilitiesDAO) {
		this.liabilitiesDAO = liabilitiesDAO;
	}

	public SanManagementerDAO getManagementerDAO() {
		return managementerDAO;
	}

	@Autowired
	public void setManagementerDAO(SanManagementerDAO managementerDAO) {
		this.managementerDAO = managementerDAO;
	}

	public SanProfitDAO getProfitDAO() {
		return profitDAO;
	}

	@Autowired
	public void setProfitDAO(SanProfitDAO profitDAO) {
		this.profitDAO = profitDAO;
	}

	public SanShareholderDAO getShareholderDAO() {
		return shareholderDAO;
	}

	@Autowired
	public void setShareholderDAO(SanShareholderDAO shareholderDAO) {
		this.shareholderDAO = shareholderDAO;
	}

	

	 public static String removeFourChar(String content) {
		 try {
			 if(content!=null){
				  byte[] conbyte = content.getBytes();
				  
			        for (int i = 0; i < conbyte.length; i++) {
			            if ((conbyte[i] & 0xF8) == 0xF0) {
			                for (int j = 0; j < 4; j++) {                          
			                    conbyte[i+j]=0x30;                     
			                }  
			                i += 3;
			            }
			        }
			        content = new String(conbyte);
			        return content.replaceAll("0000", "");
			 }else{
				 return "";
			 }
		} catch (Exception e) {
			// TODO: handle exception、
			e.printStackTrace();
				System.out.print("字符转换错误：content:"+content);
			return "";
		}
		
	      
	    }
	 
	 
	 

	

}
