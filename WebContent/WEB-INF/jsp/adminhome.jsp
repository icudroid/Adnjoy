<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/adminLayout.jsp" title="Bienvenue sur l'administration de ADNJOY">
	
	<s:layout-component name="include">
		
		<script src='../js/adnjoy.js'> </script>	

		<script src='../js/adnjoy/home/AdminHome.js'> </script>
		
	</s:layout-component>
  <s:layout-component name="body">
  	Administration
  </s:layout-component>
</s:layout-render>
