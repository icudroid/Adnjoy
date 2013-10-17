<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="BlindTest sur la publicité">
	<s:layout-component name="include">
	
<!--		<link rel="stylesheet" type="text/css" href="js/yui/fonts/fonts-min.css">-->
		<link rel="stylesheet" type="text/css" href="js/yui/button/assets/skins/sam/button.css">

		<link rel="stylesheet" type="text/css" href="js/extjs/ux/ux-all.css" />

		<link rel="stylesheet" type="text/css" href="js/adnjoy/tvjackpot/assets/Classement.css">
	 	<link type="text/css" rel="stylesheet" href="js/adnjoy/tvjackpot/assets/JackPotPanel.css">
	 	<link type="text/css" rel="stylesheet" href="js/adnjoy/tvjackpot/assets/TVJackPot.css">
 
		<script src="js/yui/yahoo-dom-event/yahoo-dom-event.js"></script>
		<script src="js/yui/element/element-min.js"></script>
		<script src="js/yui/button/button-min.js"></script>
	
		
		<script type="text/javascript" src="js/extjs/pkgs/data-json.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-buttons.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-forms.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-grid-foundation.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-menu.js"></script>
		<script type="text/javascript" src="js/extjs/pkgs/pkg-toolbars.js"></script>

		<script type="text/javascript" src="js/extjs/ux/ProgressBarPager.js"></script>
		<script type="text/javascript" src="js/extjs/ux/PagingMemoryProxy.js"></script>

		
		<script src='dwr/interface/userDwrManager.js'> </script>
		<script src='dwr/interface/brandDwrManager.js'> </script>
		<script src='dwr/interface/adDwrManager.js'> </script>
		<script src='dwr/interface/tvBlindTestDwrManager.js'> </script>
		
		<script src="js/adnjoy/tvjackpot/ExtJackPotPanel.js"></script>
		<script src="js/adnjoy/tvjackpot/ExtClassement.js"></script>
		
		<title></title>
		
		<script language="JavaScript" type="text/javascript">
			var idJackPot = ${actionBean.jackpot};
		</script>
	</s:layout-component>
	
  <s:layout-component name="body">
  
  	<div id="panelResult">
<!--  		<div class="x-panel-header">Résultat du BlindTest</div>-->
<!--  		<div class="x-panel-body">-->

	            	<div style="padding-top: 15px;text-align: center;"><h1>Votre classement : ${actionBean.yourClassement.classement} / ${actionBean.yourClassement.nbPlayer}</h1></div>
			
			<div style="padding-top: 15px;text-align: center;"><h1>Votre Score : ${actionBean.yourClassement.score}</h1></div>  			
			
			<c:set var="showJackpot" value="${actionBean.yourClassement.showJackPotChanel}" scope="page"/>
			    <c:choose>
		            <c:when test="${showJackpot == true}">
			            <div id="jackPotResult" style="padding-top: 15px;">
					  		<div class="x-panel-header">Remportez le JACKPOT</div>
					  		<div class="x-panel-body">
							  	<div style="text-align: center;width: 100%">
						            <span style="display: inline-block;" id="containerPlayJackPot">
										<div>
											Vous avez la possiblité de remporter le <b>Jackpot</b> d'une valeur de <h2>${actionBean.yourClassement.jackPotValue} ${actionBean.yourClassement.currency}.</h2>.
										</div>
										<div>
											Cliquer sur "Jouer" et répondez à la question qui vous sera posée. Vous aurez <h3 style="color: red;">30</h3> secondes pour y répondre.
										</div>
										<div style="text-align: center;padding-top: 15px;">
											<span style="display: inline-block;">
									 		  		<div id="playJackpot"></div>
									 		 </span>
									 	</div>
									</span>
								</div>
					  		</div>
	  					</div>
					</c:when>
				</c:choose>

		  	
  			<div id="lotPanel" style="padding-top: 15px;">
  				<div class="x-panel-header">Votre lot</div>
  				<div class="x-panel-body">
  						<c:set var="lot" value="${actionBean.yourClassement.lot}" scope="page"/>
				    <c:choose>
			            <c:when test="${lot != null}">
							<div class="lotWin">
								<div class="lotImg" style="" >
									<script type="text/javascript">
										var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url=${actionBean.yourClassement.lot.photoUrl}';
										document.write("<img alt='' src='"+host+"' class='Img'>");
									</script>
								</div>
								<div class="lotDesc">
									<div class="lotTitle">${actionBean.yourClassement.lot.name}&nbsp;d'une valeur de&nbsp;${actionBean.yourClassement.lot.value} &euro;</div>
									<div class="lotDescTitle">Description :</div>
									<div class="lotDescValue">${actionBean.yourClassement.lot.description}</div>
								</div>
								<div style="clear: both;"></div>
							</div>
			            </c:when>
			            <c:otherwise>
							<span><h1>Perdu !!</h1></span>
			            </c:otherwise>
			        </c:choose>
  				</div>
  			</div>
		  	<div id="panelClassement" style="padding-top: 15px;"></div>	
  		</div>
<!--	</div>-->
	
	<div id="panelQuestion"></div>
  </s:layout-component>
</s:layout-render>
