<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Modification de votre compte">
	<s:layout-component name="include">

		<script type="text/javascript" src="js/extjs/pkgs/data-json.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/data-list-views.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/data-xml.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/direct.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-buttons.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-forms.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-tips.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-toolbars.js"></script>
		
		<script src='dwr/interface/userDwrManager.js'> </script>
		<script src='dwr/interface/countryDwrManager.js'> </script>
		
		<script src="js/adnjoy/user/ExtModify.js"></script>
	</s:layout-component>
  <s:layout-component name="body">
		<div id="inscritDiv"></div>
  </s:layout-component>
</s:layout-render>
