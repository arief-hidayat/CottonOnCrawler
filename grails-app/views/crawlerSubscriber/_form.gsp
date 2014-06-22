<%@ page import="com.hida.crawler.cottonon.CrawlerSubscriber" %>



<div class="form-group ${hasErrors(bean: crawlerSubscriberInstance, field: 'userName', 'has-error')} ">
	<label for="userName">
		<g:message code="crawlerSubscriber.userName.label" default="User Name" />
		
	</label>
	<g:textField class="form-control" name="userName" value="${crawlerSubscriberInstance?.userName}" readonly="${show ?: false}"/>
</div>

<div class="form-group ${hasErrors(bean: crawlerSubscriberInstance, field: 'email', 'has-error')} required">
	<label for="email">
		<g:message code="crawlerSubscriber.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField class="form-control" name="email" value="${crawlerSubscriberInstance?.email}" readonly="${show ?: false}"/>
</div>

<div class="form-group ${hasErrors(bean: crawlerSubscriberInstance, field: 'crawlerPageName', 'has-error')} required">
	<label for="crawlerPageName">
		<g:message code="crawlerSubscriber.crawlerPageName.label" default="Crawler Page Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:select class="form-control" name="crawlerPageName" from="${crawlerSubscriberInstance.constraints.crawlerPageName.inList}" value="${crawlerSubscriberInstance?.crawlerPageName}" valueMessagePrefix="crawlerSubscriber.crawlerPageName" noSelection="['': '']" readonly="${show ?: false}"/>
</div>

<div class="form-group ${hasErrors(bean: crawlerSubscriberInstance, field: 'count', 'has-error')} required">
	<label for="count">
		<g:message code="crawlerSubscriber.count.label" default="Count" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" class="form-control" name="count" required="" value="${fieldValue(bean: crawlerSubscriberInstance, field: 'count')}" readonly="${show ?: false}"/>
</div>

