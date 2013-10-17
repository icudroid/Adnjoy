<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="Bienvenue sur ADNJOY">
	
	<s:layout-component name="include">
	
		
		<link rel="stylesheet" type="text/css" href="js/yui/carousel/assets/skins/sam/carousel.css">
	 	
	 	<link type="text/css" rel="stylesheet" href="js/adnjoy/home/assets/Home.css">
		<link rel="stylesheet" type="text/css" href="js/adnjoy/home/assets/Winner.css">

		<script src="js/yui/animation/animation-min.js"></script>
		<script src="js/yui/carousel/carousel-min.js"></script>	
				
		<script type="text/javascript" src="js/adnjoy/widget/playerpanel/ExtPlayerPanel.js"></script>
		
		<script src='dwr/interface/adDwrManager.js'> </script>	
		<script src='dwr/interface/wonJackPotDwrManager.js'> </script>
		<script src='dwr/interface/chanelDwrManager.js'> </script>

		<script src='js/adnjoy/home/Home.js'> </script>
		
	</s:layout-component>
  <s:layout-component name="body">
  		  <script type="text/javascript">
			var HomeObj = null;
  		  
			  function getMyApp(appName) {
			      if (navigator.appName.indexOf ("Microsoft") !=-1) {
			          return window[appName];
			      } else {
			          return document[appName];
			      }
			  }
			  
			 	function swfReady(){
			 		HomeObj.play();
				}
			  
		  </script>
		  
  	<div style="text-align: center;width: 100%;padding: 15px;">
		<span style="display: inline-block;">
			<div id="JackPotChanelContainer"></div>
    		<div id="newAdsContainer"></div>
    		
    		<div id="LastTVWinnersResult" style="float: left;">
  				<div class="x-panel-header">Derniers gagnants des jeux en direct</div>
	  			<div class="x-panel-body">
	  		
	  			</div>
	  		</div>
	  		<div id="LastBTWinnersResult" style="float: right;">
  				<div class="x-panel-header">Derniers gagnants des blindtests</div>
	  			<div class="x-panel-body">
	  		
	  			</div>
	  		</div>
	  		
<!--    		<div id="lastWinnersContainer"></div>-->
	    </span>
	</div>
<!--	<div id="planelPlayer"></div> -->
  </s:layout-component>
</s:layout-render>
