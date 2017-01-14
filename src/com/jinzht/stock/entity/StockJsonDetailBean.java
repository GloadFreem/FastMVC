package com.jinzht.stock.entity;

import java.util.List;

public class StockJsonDetailBean {
	
	
	  /**
     * baseinfo : {"code":"838006","name":"神州优车股份有限公司","industry":"互联网和相关服务","listingDate":"20160722","transferMode":"做市","legalRepresentative":"陆正耀","secretaries":"陈良芸","phone":"010-59729000","email":"liangyun.chen@ucarinc.com","englishName":"UCAR INC.","address":"北京市海淀区中关村南大街乙12号院1号楼2层北区C-064","totalStockEquity":"2269023798","postcode":"null","fax":"010-59729222","website":"www.ucarinc.com","shortname":"神州优车","broker":"中国国际金融股份有限公司","area":"北京市"}
     * stamp : 2016-10-19 11:20:07.227
     * finance : {"earningsPerShare":"-3.60","income":"2324002796.73","profit":"-2405432842.49","netProfit":"-2379214761.82","nonDistributeProfit":"-6145979123.75","totalAssets":"7427816585.25","totalLiability":"2605548643.84","netAssets":"null","netAssetsPerShare":"6.39","netAssetsYield":"-0.4190"}
     * topTenHolders : [{"num":"1","date":"2016-06-30","name":"陆正耀","last_quantity":"0","quantity":"90000000","changeQty":"90000000","ratio":"0.1190","limitedQuantity":"90000000","unlimitedQuantity":"0"},{"num":"2","date":"2016-06-30","name":"Star Vantage (China) Limited","last_quantity":"0","quantity":"66360000","changeQty":"66360000","ratio":"0.0877","limitedQuantity":"66360000","unlimitedQuantity":"0"},{"num":"3","date":"2016-06-30","name":"Golden Ares Limited","last_quantity":"0","quantity":"59280000","changeQty":"59280000","ratio":"0.0784","limitedQuantity":"59280000","unlimitedQuantity":"0"},{"num":"4","date":"2016-06-30","name":"China Auto Rental Limited","last_quantity":"0","quantity":"56100000","changeQty":"56100000","ratio":"0.0742","limitedQuantity":"56100000","unlimitedQuantity":"0"},{"num":"5","date":"2016-06-30","name":"Gingko Avenue Limited","last_quantity":"0","quantity":"52020000","changeQty":"52020000","ratio":"0.0688","limitedQuantity":"52020000","unlimitedQuantity":"0"},{"num":"6","date":"2016-06-30","name":"Yunfeng SZ Investment (HK) Limited","last_quantity":"0","quantity":"33597312","changeQty":"33597312","ratio":"0.0444","limitedQuantity":"0","unlimitedQuantity":"33597312"},{"num":"7","date":"2016-06-30","name":"宁波梅山保税港区云岭股权投资合伙企业（有限合伙）","last_quantity":"0","quantity":"33597312","changeQty":"33597312","ratio":"0.0444","limitedQuantity":"0","unlimitedQuantity":"33597312"},{"num":"8","date":"2016-06-30","name":"Silver Birch Limited","last_quantity":"0","quantity":"30000000","changeQty":"30000000","ratio":"0.0397","limitedQuantity":"30000000","unlimitedQuantity":"0"},{"num":"9","date":"2016-06-30","name":"Orchids Capital Limited","last_quantity":"0","quantity":"30000000","changeQty":"30000000","ratio":"0.0397","limitedQuantity":"30000000","unlimitedQuantity":"0"},{"num":"10","date":"2016-06-30","name":"福建平潭自贸区君同和投资合伙企业（有限合伙）","last_quantity":"0","quantity":"28679706","changeQty":"28679706","ratio":"0.0379","limitedQuantity":"28679706","unlimitedQuantity":"0"}]
     * executives : [{"name":"陆正耀","job":"董事长/总经理","gender":"男","age":"47","education":"硕士研究生","term":"2016年1月-2019年1月","salary":"是"},{"name":"黎辉","job":"副董事长","gender":"男","age":"48","education":"硕士研究生","term":"2016年1月-2019年1月","salary":"否"},{"name":"钱治亚","job":"董事/副总经理","gender":"女","age":"40","education":"硕士研究生","term":"2016年3月-2019年1月","salary":"是"},{"name":"李晓耕","job":"董事/副总经理","gender":"女","age":"41","education":"博士","term":"2016年1月-2019年1月","salary":"是"},{"name":"Weiss Fung Kuen Chan","job":"董事","gender":"女","age":"51","education":"本科","term":"2016年1月-2019年1月","salary":"否"},{"name":"张翠霞","job":"监事会主席","gender":"女","age":"63","education":"中专","term":"2016年1月-2019年1月","salary":"是"},{"name":"许开新","job":"监事","gender":"男","age":"43","education":"博士","term":"2016年1月-2019年1月","salary":"是"},{"name":"薛茜","job":"监事","gender":"女","age":"31","education":"硕士研究生","term":"2016年1月-2019年1月","salary":"是"},{"name":"王培强","job":"副总经理","gender":"男","age":"41","education":"硕士研究生","term":"2016年8月-2019年1月","salary":"是"},{"name":"陈良芸","job":"财务总监/董事会秘书","gender":"女","age":"37","education":"硕士研究生","term":"2016年1月-2019年1月","salary":"是"}]
     */

    private BaseinfoBean baseinfo;
    private String stamp;
    private FinanceBean finance;
    private List<TopTenHoldersBean> topTenHolders;
    private List<ExecutivesBean> executives;

    public BaseinfoBean getBaseinfo() {
        return baseinfo;
    }

    public void setBaseinfo(BaseinfoBean baseinfo) {
        this.baseinfo = baseinfo;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public FinanceBean getFinance() {
        return finance;
    }

    public void setFinance(FinanceBean finance) {
        this.finance = finance;
    }

    public List<TopTenHoldersBean> getTopTenHolders() {
        return topTenHolders;
    }

    public void setTopTenHolders(List<TopTenHoldersBean> topTenHolders) {
        this.topTenHolders = topTenHolders;
    }

    public List<ExecutivesBean> getExecutives() {
        return executives;
    }

    public void setExecutives(List<ExecutivesBean> executives) {
        this.executives = executives;
    }

    public static class BaseinfoBean {
        /**
         * code : 838006
         * name : 神州优车股份有限公司
         * industry : 互联网和相关服务
         * listingDate : 20160722
         * transferMode : 做市
         * legalRepresentative : 陆正耀
         * secretaries : 陈良芸
         * phone : 010-59729000
         * email : liangyun.chen@ucarinc.com
         * englishName : UCAR INC.
         * address : 北京市海淀区中关村南大街乙12号院1号楼2层北区C-064
         * totalStockEquity : 2269023798
         * postcode : null
         * fax : 010-59729222
         * website : www.ucarinc.com
         * shortname : 神州优车
         * broker : 中国国际金融股份有限公司
         * area : 北京市
         */

        private String code;
        private String name;
        private String industry;
        private String listingDate;
        private String transferMode;
        private String legalRepresentative;
        private String secretaries;
        private String phone;
        private String email;
        private String englishName;
        private String address;
        private String totalStockEquity;
        private String postcode;
        private String fax;
        private String website;
        private String shortname;
        private String broker;
        private String area;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getListingDate() {
            return listingDate;
        }

        public void setListingDate(String listingDate) {
            this.listingDate = listingDate;
        }

        public String getTransferMode() {
            return transferMode;
        }

        public void setTransferMode(String transferMode) {
            this.transferMode = transferMode;
        }

        public String getLegalRepresentative() {
            return legalRepresentative;
        }

        public void setLegalRepresentative(String legalRepresentative) {
            this.legalRepresentative = legalRepresentative;
        }

        public String getSecretaries() {
            return secretaries;
        }

        public void setSecretaries(String secretaries) {
            this.secretaries = secretaries;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTotalStockEquity() {
            return totalStockEquity;
        }

        public void setTotalStockEquity(String totalStockEquity) {
            this.totalStockEquity = totalStockEquity;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public String getBroker() {
            return broker;
        }

        public void setBroker(String broker) {
            this.broker = broker;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }

    public static class FinanceBean {
        /**
         * earningsPerShare : -3.60
         * income : 2324002796.73
         * profit : -2405432842.49
         * netProfit : -2379214761.82
         * nonDistributeProfit : -6145979123.75
         * totalAssets : 7427816585.25
         * totalLiability : 2605548643.84
         * netAssets : null
         * netAssetsPerShare : 6.39
         * netAssetsYield : -0.4190
         */

        private String earningsPerShare;
        private String income;
        private String profit;
        private String netProfit;
        private String nonDistributeProfit;
        private String totalAssets;
        private String totalLiability;
        private String netAssets;
        private String netAssetsPerShare;
        private String netAssetsYield;

        public String getEarningsPerShare() {
            return earningsPerShare;
        }

        public void setEarningsPerShare(String earningsPerShare) {
            this.earningsPerShare = earningsPerShare;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

        public String getNetProfit() {
            return netProfit;
        }

        public void setNetProfit(String netProfit) {
            this.netProfit = netProfit;
        }

        public String getNonDistributeProfit() {
            return nonDistributeProfit;
        }

        public void setNonDistributeProfit(String nonDistributeProfit) {
            this.nonDistributeProfit = nonDistributeProfit;
        }

        public String getTotalAssets() {
            return totalAssets;
        }

        public void setTotalAssets(String totalAssets) {
            this.totalAssets = totalAssets;
        }

        public String getTotalLiability() {
            return totalLiability;
        }

        public void setTotalLiability(String totalLiability) {
            this.totalLiability = totalLiability;
        }

        public String getNetAssets() {
            return netAssets;
        }

        public void setNetAssets(String netAssets) {
            this.netAssets = netAssets;
        }

        public String getNetAssetsPerShare() {
            return netAssetsPerShare;
        }

        public void setNetAssetsPerShare(String netAssetsPerShare) {
            this.netAssetsPerShare = netAssetsPerShare;
        }

        public String getNetAssetsYield() {
            return netAssetsYield;
        }

        public void setNetAssetsYield(String netAssetsYield) {
            this.netAssetsYield = netAssetsYield;
        }
    }

    public static class TopTenHoldersBean {
        /**
         * num : 1
         * date : 2016-06-30
         * name : 陆正耀
         * last_quantity : 0
         * quantity : 90000000
         * changeQty : 90000000
         * ratio : 0.1190
         * limitedQuantity : 90000000
         * unlimitedQuantity : 0
         */

        private String num;
        private String date;
        private String name;
        private String last_quantity;
        private String quantity;
        private String changeQty;
        private String ratio;
        private String limitedQuantity;
        private String unlimitedQuantity;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLast_quantity() {
            return last_quantity;
        }

        public void setLast_quantity(String last_quantity) {
            this.last_quantity = last_quantity;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getChangeQty() {
            return changeQty;
        }

        public void setChangeQty(String changeQty) {
            this.changeQty = changeQty;
        }

        public String getRatio() {
            return ratio;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }

        public String getLimitedQuantity() {
            return limitedQuantity;
        }

        public void setLimitedQuantity(String limitedQuantity) {
            this.limitedQuantity = limitedQuantity;
        }

        public String getUnlimitedQuantity() {
            return unlimitedQuantity;
        }

        public void setUnlimitedQuantity(String unlimitedQuantity) {
            this.unlimitedQuantity = unlimitedQuantity;
        }
    }

    public static class ExecutivesBean {
        /**
         * name : 陆正耀
         * job : 董事长/总经理
         * gender : 男
         * age : 47
         * education : 硕士研究生
         * term : 2016年1月-2019年1月
         * salary : 是
         */

        private String name;
        private String job;
        private String gender;
        private String age;
        private String education;
        private String term;
        private String salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }
    }
//


}
