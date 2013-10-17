<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="BlindTest sur la publicité">
	<s:layout-component name="include">
	
		<!--CSS file (default YUI Sam Skin) -->
<!--		<link rel="stylesheet" type="text/css" href="js/yui/fonts/fonts-min.css">-->
<!--		<link rel="stylesheet" type="text/css" href="js/yui/button/assets/skins/sam/button.css">-->
<!--		<link rel="stylesheet" type="text/css" href="js/yui/menu/assets/skins/sam/menu.css">-->
	 	
	 	<link type="text/css" rel="stylesheet" href="js/adnjoy/blindtest/assets/BlindTest.css">
 		<script type="text/javascript" src="js/extjs/pkgs/pkg-buttons.js"></script>
<!--		<script src="js/yui/yahoo-dom-event/yahoo-dom-event.js"></script>-->
<!--		<script src="js/yui/element/element-min.js"></script>-->
<!--		<script src="js/yui/button/button-min.js"></script>-->
		
		<script src='dwr/interface/userDwrManager.js'> </script>
		<script src='dwr/interface/brandDwrManager.js'> </script>
		<script src='dwr/interface/adDwrManager.js'> </script>
		<script src='dwr/interface/blindTestDwrManager.js'> </script>
		
		<title></title>
		
		<script src = "js/adnjoy/blindtest/ExtBlindTest.js" ></script>
		
	</s:layout-component>
  <s:layout-component name="body">
  
  <script language="JavaScript" type="text/javascript">
	var objectJs = null; 
	// -->
	function getMyApp(appName) {
	    if (navigator.appName.indexOf ("Microsoft") !=-1) {
	        return window[appName];
	    } else {
	        return document[appName];
	    }
	}

	function startGame(){
		objectJs._startGame();
	}

	function nextQuestion(){
		objectJs._drawNextQuestion();
	}
	
	</script>

<div id="game">

	<div style="text-align: center;">
		<span style="display: inline-block;">
			<div id="gameResume">
				<div>
					<span id="labelBT">En attente du blindTest !!!!</span><span id="nbPoint"></span>
				</div>
			</div>
		</span>
	</div>
		
	<div id="playGamePanel">
   		<div class="x-panel-header">BlindTest</div>
		<div class="x-panel-body"> 
			<div class="playerAd">
				<div id="playerDiv"></div>
				<div id="progressBarDiv"></div>
			</div>
			<div id="responses">
				<div class="explainBT">
				Trois logos vont apparaître, vous devez cliquer le plus rapidement possible sur celui qui correspond à la publicité.
				</div>
				<div class="explainBT">
				A chaque réponse est calculé un nombre de point.
				</div>
				<div class="explainBT">
				Attention : plus vous répondez vite, plus vous pouvez marquer de points, mais si vous vous trompez, plus le nombre de points enlevé est grand.
				</div>
				<div class="explainBT">
				A la fin du BlintTest, vous saurez si vous avez gagné.
				</div> 
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>
	
	<div style="display: none;">
		<s:form action="/Classment.htm" id="getClassement">
			<input type="hidden" name="jackpot" id="jackpot" value=""></input>
			<input type="submit"></input>
		</s:form>
	</div>
</div>
  </s:layout-component>
</s:layout-render>
