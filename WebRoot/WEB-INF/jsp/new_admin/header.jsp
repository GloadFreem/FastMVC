﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="vbox">
	<header class="bg-dark dk header navbar navbar-fixed-top-xs">
		<div class="navbar-header aside-md">
			<a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen"
				data-target="#nav"> <i class="fa fa-bars"></i>
			</a> <a href="#" class="navbar-brand" data-toggle="fullscreen"><img
				src="images/logo.png" class="m-r-sm">金指投</a> <a
				class="btn btn-link visible-xs" data-toggle="dropdown"
				data-target=".nav-user"> <i class="fa fa-cog"></i>
			</a>
		</div>
		<!-- <ul class="nav navbar-nav hidden-xs">
			<li class="dropdown"><a href="index.action"
				class="dropdown-toggle dker" data-toggle="dropdown"> <i
					class="fa fa-building-o"></i> <span class="font-bold">Banner</span>
			</a>
				<section
					class="dropdown-menu aside-xl on animated fadeInLeft no-borders lt">
					<div class="wrapper lter m-t-n-xs">
						<a href="news.action" class="thumb pull-left m-r"> <img
							src="images/avatar.jpg" class="img-circle">
						</a>
						<div class="clear">
							<a href="index.action"><span class="text-white font-bold">@陈生珠</a></span>
							<small class="block">系统管理员</small> <a href="#"
								class="btn btn-xs btn-success m-t-xs">Upgrade</a>
						</div>
					</div>
					<div class="row m-l-none m-r-none m-b-n-xs text-center">
						<div class="col-xs-4">
							<div class="padder-v">
								<span class="m-b-xs h4 block text-white">245</span> <small
									class="text-muted">Followers</small>
							</div>
						</div>
						<div class="col-xs-4 dk">
							<div class="padder-v">
								<span class="m-b-xs h4 block text-white">55</span> <small
									class="text-muted">Likes</small>
							</div>
						</div>
						<div class="col-xs-4">
							<div class="padder-v">
								<span class="m-b-xs h4 block text-white">2,035</span> <small
									class="text-muted">Photos</small>
							</div>
						</div>
					</div>
				</section></li>
			<li>
				<div class="m-t m-l">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">认证审核</span>
					</a>
				</div>
			</li>
			<li>
				<div class="m-t m-l">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">认证审核</span>
					</a>
				</div>
			</li>
			<li>
				<div class="m-t m-l">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">圈子</span>
					</a>
				</div>
			</li>
			<li>
				<div class="m-t m-l">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">金日投条</span>
					</a>
				</div>
			</li>
			<li>
				<div class="m-t m-l">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">活动</span>
					</a>
				</div>
			</li>
			<li>
				<div class="m-t m-l">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown"> <i
						class="fa fa-building-o"></i> <span class="font-bold">消息推送</span>
					</a>
				</div>
			</li>
		</ul> -->
		<ul class="nav navbar-nav navbar-right hidden-xs nav-user">
			<li class="hidden-xs"><a href="#" class="dropdown-toggle dk"
				data-toggle="dropdown"> <i class="fa fa-bell"></i> <span
					class="badge badge-sm up bg-danger m-l-n-sm count">2</span>
			</a>
				<section class="dropdown-menu aside-xl">
					<section class="panel bg-white">
						<header class="panel-heading b-light bg-light">
							<strong>您有 <span class="count">2</span> 条通知
							</strong>
						</header>
						<div class="list-group list-group-alt animated fadeInRight">
							<a href="#" class="media list-group-item"> <span
								class="pull-left thumb-sm"> <img src="images/avatar.jpg"
									alt="John said" class="img-circle">
							</span> <span class="media-body block m-b-none"> 上报事件<br> <small
									class="text-muted">10 分钟前</small>
							</span>
							</a> <a href="#" class="media list-group-item"> <span
								class="media-body block m-b-none"> 新增2条订单<br> <small
									class="text-muted">1 小时前</small>
							</span>
							</a>
						</div>
						<footer class="panel-footer text-sm">
							<a href="#" class="pull-right"><i class="fa fa-cog"></i></a> <a
								href="#notes" data-toggle="class:show animated fadeInRight">查看全部消息</a>
						</footer>
					</section>
				</section></li>
			<li class="dropdown hidden-xs"><a href="#"
				class="dropdown-toggle dker" data-toggle="dropdown"><i
					class="fa fa-fw fa-search"></i></a>
				<section class="dropdown-menu aside-xl animated fadeInUp">
					<section class="panel bg-white">
						<form role="search">
							<div class="form-group wrapper m-b-none">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="输入 关键字 进行搜索"> <span
										class="input-group-btn">
										<button type="submit" class="btn btn-info btn-icon">
											<i class="fa fa-search"></i>
										</button>
									</span>
								</div>
							</div>
						</form>
					</section>
				</section></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <span class="thumb-sm avatar pull-left">
						<img src="images/avatar.jpg">
				</span> 金指投科技 <b class="caret"></b>
			</a>
				<ul class="dropdown-menu animated fadeInRight">
					<span class="arrow top"></span>
					<li><a href="#">设置</a></li>
					<li><a href="profile.html">简介</a></li>
					<li><a href="#"> <span class="badge bg-danger pull-right">3</span>
							消息
					</a></li>
					<li><a href="docs.html">帮助</a></li>
					<li class="divider"></li>
					<li><a href="modal.lockme.html" data-toggle="ajaxModal">注销</a>
					</li>
				</ul></li>
		</ul>
	</header>
	<section>
		<section class="hbox stretch">
			<!-- .aside -->
			<aside class="bg-dark lter aside-md hidden-print" id="nav">
				<section class="vbox">
					<section class="w-f scrollable">
						<div class="slim-scroll" data-height="auto"
							data-disable-fade-out="true" data-distance="0" data-size="5px"
							data-color="#333333">
							<!-- nav -->
							<nav class="nav-primary hidden-xs">
								<ul class="nav">
									<c:choose>
										<c:when test="${menu==3}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>用户管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>用户管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="userList.action?menu=3&sortmenu=1&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>用户列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="userDetail.action?menu=3&sortmenu=1&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加用户</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>认证管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="authenticList.action?menu=3&sortmenu=2&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>认证审核</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="authenticDetail.action?menu=3&sortmenu=2&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加认证身份</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>金条管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="rewardList.action?menu=3&sortmenu=3&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>金条列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="rewardTradeList.action?menu=3&sortmenu=3&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>金条交易列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==3}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="rewardDetail.action?menu=3&sortmenu=3&submenu=3&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加金条</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==4}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>黑名单管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="userList.action?menu=3&sortmenu=4&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>黑名单列表</span>
											</a>
											</li>
											<li><a
												href="userList.action?menu=3&sortmenu=4&submenu=2&requestType=webRequest">
													<i class="fa fa-angle-right"></i> <span>加入黑名单</span>
											</a></li>
										</ul>
										</li>
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==4}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>项目管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>项目管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="projectList.action?menu=4&sortmenu=1&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>项目列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="projectDetail.action?menu=4&sortmenu=1&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加项目</span>
											</a>
											</li>
										</ul>
										</li>

										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>路演管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="roadshowList.action?menu=4&sortmenu=2&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>路演列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="roadShowDetail.action?menu=4&sortmenu=2&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加路演</span>
											</a>
											</li>
										</ul>
										</li>

										<c:choose>
											<c:when test="${sortmenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>现场管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="sceneList.action?menu=4&sortmenu=3&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>现场列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="sceneDetail.action?menu=4&sortmenu=3&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加现场</span>
											</a>
											</li>
										</ul>
										</li>


										<c:choose>
											<c:when test="${sortmenu==4}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>财务管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="financeStandingList.action?menu=4&sortmenu=4&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>财务状况</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="financeStandingDetail.action?menu=4&sortmenu=4&submenu=2&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>添加财务状况</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==5}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>商业计划管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="businessPlanList.action?menu=4&sortmenu=5&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>商业计划</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="businessPlanDetail.action?menu=4&sortmenu=5&submenu=2&requestType=webRequest"> <i
													class="fa fa-angle-right"></i> <span>添加商业计划</span>
											</a></li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==6}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>融资方案管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="financeCaseList.action?menu=4&sortmenu=6&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>融资方案</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加融资状况</span>
											</a></li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==7}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>退出渠道管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="financeExitList.action?menu=4&sortmenu=7&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>退出渠道</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加退出渠道</span>
											</a></li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==8}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>成员管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="memberList.action?menu=4&sortmenu=8&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>成员列表</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加成员</span>
											</a></li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==9}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>核心团队管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="teamList.action?menu=4&sortmenu=9&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>成员列表</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加核心成员</span>
											</a></li>
										</ul>
										</li>

									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==5}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>活动管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>活动管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="actionList.action?menu=5&sortmenu=1&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>活动列表</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加活动</span>
											</a></li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>活动内容管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="actionContentList.action?menu=5&sortmenu=2&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>内容列表</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加活动内容</span>
											</a></li>
										</ul>
										</li>
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==6}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>圈子管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${submenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a
											href="feelingList.action?menu=6&sortmenu=1&submenu=1&requestType=webRequest">
											<i class="fa fa-angle-right"></i> <span>圈子列表</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="AttendList.action"> <i class="fa fa-angle-right"></i>
											<span>添加圈子</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a
											href="feelingList.action?menu=6&sortmenu=1&submenu=3&requestType=webRequest">
											<i class="fa fa-angle-right"></i> <span>黑名单</span>
										</a>
										</li>

									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==7}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>资讯管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>资讯管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="consultList.action?menu=7&sortmenu=1&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>资讯列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>

											<a href="AttendList.action"> <i class="fa fa-angle-right"></i>
												<span>添加资讯</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>原创管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="originalList.action?menu=7&sortmenu=2&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>原创列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="AttendList.action"> <i class="fa fa-angle-right"></i>
												<span>添加原创内容</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>金日投条管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="kingConsultList.action?menu=7&sortmenu=3&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>投条列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="AttendList.action"> <i class="fa fa-angle-right"></i>
												<span>添加金日投条 </span>
											</a>
											</li>
										</ul>
										</li>
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==8}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>IM管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>聊天室管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="chatRoomsList.action?menu=8&sortmenu=1&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>聊天室列表</span>
											</a>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>添加聊天室</span>
											</a></li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>IM用户管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											</li>
											<li><a href="AttendList.action"> <i
													class="fa fa-angle-right"></i> <span>注册用户到IM</span>
											</a></li>
										</ul>
										</li>
									</ul>
									</li>

									<c:choose>
										<c:when test="${menu==1}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-file-text icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>商学管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>课程管理</span>
										</a>
										<ul class="nav bg">

											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="CourseList.action?menu=1&sortmenu=1&submenu=1">
												<i class="fa fa-angle-right"></i> <span>课程列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="courseDetail.action?menu=1&sortmenu=1&submenu=2">
												<i class="fa fa-angle-right"></i> <span>添加课程</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==3}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="sourceList.action?menu=1&sortmenu=1&submenu=3">
												<i class="fa fa-angle-right"></i> <span>资源列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==4}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="sourceDetail.action?menu=1&sortmenu=1&submenu=4">
												<i class="fa fa-angle-right"></i> <span>添加资源</span>
											</a>
											</li>
										</ul>
										</li>

										<c:choose>
											<c:when test="${sortmenu==2 && menu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>邀请码管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="InviteCodeList.action?menu=1&sortmenu=2&submenu=1">
												<i class="fa fa-angle-right"></i> <span>邀请码列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="codeDetail.action?menu=1&sortmenu=2&submenu=2">
												<i class="fa fa-angle-right"></i> <span>添加邀请码</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==3}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="generateCode.action?menu=1&sortmenu=2&submenu=3">
												<i class="fa fa-angle-right"></i> <span>一键生成邀请码</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>参课管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="AttendList.action?menu=1&sortmenu=3&submenu=1">
												<i class="fa fa-angle-right"></i> <span>参课列表</span>
											</a>
											</li>
											<!-- 		<li><a href="AttendList.action"> <i
															class="fa fa-angle-right"></i> <span>添加报名</span>
													</a></li> -->
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==4}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>讲师管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="speecherList.action?menu=1&sortmenu=4&submenu=1">
												<i class="fa fa-angle-right"></i> <span>讲师列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="speecherDetail.action?menu=1&sortmenu=4&submenu=2">
												<i class="fa fa-angle-right"></i> <span>添加讲师</span>
											</a>
											</li>
										</ul>
										</li>

										<c:choose>
											<c:when test="${sortmenu==5}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>运营管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="WorkerList.action?menu=1&sortmenu=5&submenu=1">
												<i class="fa fa-angle-right"></i> <span>运营人员列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="workerDetail.action?menu=1&sortmenu=5&submenu=2">
												<i class="fa fa-angle-right"></i> <span>添加运营人员</span>
											</a>
											</li>
										</ul>
										</li>
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==9}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-file-text icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>新三板管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>企业库管理</span>
										</a>
										<ul class="nav bg">

											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="newOTCList.action?menu=9&sortmenu=1&submenu=1&requestType=webRequest">
												<i class="fa fa-angle-right"></i> <span>企业列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="courseDetail.action?menu=1&sortmenu=1&submenu=2">
												<i class="fa fa-angle-right"></i> <span>添加企业</span>
											</a>
											</li>
										</ul>
										</li>
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==2}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>广告管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${submenu==1&&menu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="newsBanner.action?menu=2&sortmenu=1&submenu=1"> <i
											class="fa fa-angle-right"></i> <span>App Banner列表</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="newsBanner.action?menu=2&sortmenu=1&submenu=2"> <i
											class="fa fa-angle-right"></i> <span>资讯Banner </span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="originalBanner.action?menu=2&sortmenu=1&submenu=3">
											<i class="fa fa-angle-right"></i> <span>原创Banner</span>
										</a>
										</li>
									</ul>
									</li>
									<li><a href="notebook.action"> <i
											class="fa fa-pencil icon"> <b class="bg-info"></b>
										</i> <span>信息反馈</span>
									</a></li>
								</ul>
							</nav>
							<!-- / nav -->
						</div>
					</section>
				</section>
			</aside>
			<!-- /.aside -->
			<jsp:include page="${content}.jsp"></jsp:include>
		</section>
	</section>
</section>
