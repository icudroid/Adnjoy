<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Vos gains">
	<s:layout-component name="include">
	
		<link rel="stylesheet" type="text/css" href="js/extjs/ux/ux-all.css" />
		
		<script type="text/javascript" src="js/extjs/pkgs/data-json.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-buttons.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-forms.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-grid-foundation.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-menu.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-toolbars.js"></script>

		<script type="text/javascript" src="js/extjs/ux/ProgressBarPager.js"></script>
		<script type="text/javascript" src="js/extjs/ux/PagingMemoryProxy.js"></script>
		
		
		<script src='dwr/interface/userDwrManager.js'> </script>
		<script src='dwr/interface/countryDwrManager.js'> </script>
		
		<script src="js/adnjoy/user/ExtWonObjects.js"></script>
	</s:layout-component>
  <s:layout-component name="body">
	<div id="gains"></div>
  </s:layout-component>
</s:layout-render>
