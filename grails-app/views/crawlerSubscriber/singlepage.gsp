<%@ page import="com.hida.crawler.cottonon.CrawlerSubscriber" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="imms"/>
    <g:set var="entityName" value="${message(code: 'crawlerSubscriber.label', default: 'CrawlerSubscriber')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<g:render template="partialSinglePage"/>
<script type="text/javascript" charset="utf-8">
    App.url = "${request.contextPath}";
    jQuery(document).ready(function() {
        new App.view.TableFormSinglePage({ key : "CrawlerSubscriber"});
    } );
</script>
</body>
</html>
