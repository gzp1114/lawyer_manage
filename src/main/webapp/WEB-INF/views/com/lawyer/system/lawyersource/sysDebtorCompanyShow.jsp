<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/default.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>债务信息管理</title>
<link rel="stylesheet"
	href="${ctx}/static/threadui/thirdtree/zTreeStyle/metro.css" />
<style type="text/css">
#sidebar {
	min-height: 200px;
}

.select_option {
	width: 185px;
	margin-top: -5px;
	z-index: 1;
	position: absolute;
}

.select_showbox {
	width: 180px;
	margin-top: 0px;
	height: 28px;
	line-height: 30px;
}

.select_box {
	margin-top: 0px;
}
</style>
</head>
<body>
	<script
		src="${ctx}/static/threadui/thirdtree/ztree/js/jquery.ztree.core.js"></script>
	<div class="cinema_tip">
		<div class="cinema_subject clearfix">
			<div class="cinema_title">
				<a href="#">案源中心</a> > <a id="userinfo_list_href"
					href="${ctx}/sysDebtorCompany/toSearch">债务信息管理</a> > <a
					href="javascript:void(0);" class="cinema_option">债务信息展示</a>
			</div>
			<a href="${ctx}/sysDebtorCompany/toSearch" class="movie_return">返回列表</a>
		</div>
	</div>
	<div class="cinema_list">
		<div class="listxiangqing_content">
			<div class="xiangqing_list1">
				<h1>${result.debtorCompany.name}</h1>
				<div class="list1 clearfix">
					<ul class="list1_left">
						<li>地址:<span>${result.debtorCompany.address}</span>
						</li>
					</ul>
				</div>
				<dl class="movie_fill">
					<dt class="movie_word clearfix movie_bottom"></dt>
					<dd class="tab">
						<div class="new1_2">
							<div class="title_top clearfix">
								<ul class="new_title fl" id="activity_content">
									<li class="title_current">
										<span> 基本信息 </span>
									</li>
									<li >
										<span>法律诉讼(${result.lawyerCount })</span>
									</li>

								</ul>
							</div>
							<div class="list_content ticket_list ">
								<div class="list1 clearfix">
									<ul class="list1_left">
										<li>注册号：<span>${result.debtorCompany.registerNumber}</span></li>
										<li>公司类型：<span>${result.debtorCompany.type}</span></li>
										<li>公司法人：<span>${result.debtorCompany.corporateName}</span></li>
										<li>成立日期：<span>${result.debtorCompany.establishDate}</span></li>
										<li>营业期限：<span>${result.debtorCompany.businessStartDate}至${result.debtorCompany.businessEndDate}</span></li>
										<li>地址：<span>${result.debtorCompany.address}</span></li>
									</ul>
									<ul class="list1_right">
										<li>组织结构代码：<span>${result.debtorCompany.organizationCode}</span></li>
										<li>经营状态：<span>${result.debtorCompany.operateStatus}</span></li>
										<li>法人身份证号：<span>${result.debtorCompany.corporateCardnum}</span></li>
										<li>注册资金：<span>${result.debtorCompany.registerCapital}</span></li>
										<li>登记机关：<span>${result.debtorCompany.registrationAuthority}</span></li>
									</ul>
								</div>
							</div>

							<div class="list_content hidden">
								<div class="new1_2">
									<div class="title_top clearfix">
										<ul class="new1_title fl" id="activity_content2">
											<li class="title_current"><span> 被执行人信息(${result.debtorCount}) </span></li>
											<li><span>法院公告(${result.announcementCount})</span></li>

										</ul>
									</div>
									<div class="list_content2 ticket_list">
										<div class="list1 clearfix">
										<c:forEach var="res" items="${result.debtorCompany.debtors}" varStatus="status">
										  <c:if test="${status.index%2 == '0'}">
										  	<ul class="list1_left">
												<li>执行法院：<span>${res.execCourtname }</span></li>
												<li>立案时间：<span>${res.caseCreatetime }</span></li>
												<li>    案号：<span>${res.casecode }</span></li>
												<li>执行标的：<span>${res.executeMoney }</span></li>
												<li>案件状态：<span>${res.caseState }</span></li>
											</ul>
										  </c:if>
										 
										  <c:if test="${status.index%2 != '0'}">
										  	<ul class="list1_right">
												<li>执行法院：<span>${res.execCourtname }</span></li>
												<li>立案时间：<span>${res.caseCreatetime }</span></li>
												<li>    案号：<span>${res.casecode }</span></li>
												<li>执行标的：<span>${res.executeMoney }</span></li>
												<li>案件状态：<span>${res.caseState }</span></li>
											</ul>
										  </c:if>
										</c:forEach>
										<ul class="list1_right">
											<li><a href="${ctx}/sysDebtor/toAdd/${result.debtorCompany.id}" >+添加新信息</a></li>
										</ul>
										</div>
									</div>
									<div class="list_content2 hidden">
										<div class="list1 clearfix">
											<c:forEach var="res" items="${result.debtorCompany.announcements}" varStatus="status">
											  <c:if test="${status.index%2 == '0'}">
											  	<ul class="list1_left">
													<li>公告法院：<span>${res.announcementCourt }</span></li>
													<li>公告日期：<span>${res.announcementDate }</span></li>
													<li>刊登版面：<span>${res.publishedPage }</span></li>
													<li>备注：<span>${res.note }</span></li>
													<li>详情链接地址：<a href="${res.url }" target="_Blank">${res.url }</a></li>
												</ul>
											  </c:if>
											 
											  <c:if test="${status.index%2 != '0'}">
											  	<ul class="list1_right">
													<li>公告法院：<span>${res.announcementCourt }</span></li>
													<li>公告日期：<span>${res.announcementDate }</span></li>
													<li>刊登版面：<span>${res.publishedPage }</span></li>
													<li>备注：<span>${res.note }</span></li>
													<li>详情链接地址：<a href="${res.url }" target="_Blank">${res.url }</a></li>
												</ul>
											  </c:if>
										</c:forEach>
										<ul class="list1_right">
											<li><a href="${ctx}/sysDebtor/toAdd/${result.debtorCompany.id}" >+添加新信息</a></li>
										</ul>
										</div>
									</div>
								</div>
							</div>

						</div>

					</dd>
				</dl>
			</div>
		</div>
	</div>
	</dd>
	</dl>
	</div>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>
