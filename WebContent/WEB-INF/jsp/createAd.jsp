<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/adminLayout.jsp" title="Ajout d'une publicité">
	<s:layout-component name="include">
		<title></title>
		
		<script type="text/javascript" src="../js/extjs/pkgs/data-grouping.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/data-json.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/data-list-views.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/data-xml.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/direct.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-buttons.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-charts.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-forms.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-grid-editor.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-grid-foundation.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-grid-grouping.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-grid-property.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-history.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-menu.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-tabs.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-tips.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-toolbars.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/pkg-tree.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/resizable.js"></script>
		<script type="text/javascript" src="../js/extjs/pkgs/state.js"></script>
		
		<link rel="stylesheet" type="text/css" href="../js/extjs/ux/fileuploadfield/css/fileuploadfield.css"/>
		<script type="text/javascript" src="../js/extjs/ux/fileuploadfield/FileUploadField.js"></script>

		<script type="text/javascript" src="../js/adnjoy/widget/playerpanel/ExtPlayerPanel.js"></script>
				
		<script src='../js/adnjoy/ad/ExtCreate.js'> </script>


		<style type=text/css>
	        .upload-icon {
	            background: url('../js/extjs/shared/icons/fam/image_add.png') no-repeat 0 0 !important;
	        }
	        #fi-button-msg {
	            border: 2px solid #ccc;
	            padding: 5px 10px;
	            background: #eee;
	            margin: 5px;
	            float: left;
	        }
	    </style>
		
	</s:layout-component>
  <s:layout-component name="body">
  
  <script language="JavaScript" type="text/javascript">
		var AdCreateObj = null;
	  
		  function getMyApp(appName) {
		      if (navigator.appName.indexOf ("Microsoft") !=-1) {
		          return window[appName];
		      } else {
		          return document[appName];
		      }
		  }
		  
		 	function swfReady(){
		 		AdCreateObj.play();
			}
		  
	  </script>
  
  <div id="gridAd"></div>
  </s:layout-component>
</s:layout-render>
