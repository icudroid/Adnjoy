<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>

<s:layout-render name="/WEB-INF/jsp/layout.jsp" title="BlindTest sur la publicité en Direct">
	<s:layout-component name="include">
	
		<!--CSS file (default YUI Sam Skin) -->
	 	
	 	<link type="text/css" rel="stylesheet" href="js/adnjoy/tvjackpot/assets/ChanelChooser.css">
	 	<link type="text/css" rel="stylesheet" href="js/adnjoy/tvjackpot/assets/TVJackPot.css">
 
		<script type="text/javascript" src="js/extjs/pkgs/pkg-buttons.js"></script>
				
		<script src = "js/adnjoy/tvjackpot/ChanelChooser.js" ></script>
		
		<script src='dwr/interface/chanelDwrManager.js'> </script>
		<script src='dwr/interface/tvBlindTestDwrManager.js'> </script>
		
		<script src='js/adnjoy/tvjackpot/TVJackPot.js'> </script>
		
	</s:layout-component>
  <s:layout-component name="body">

	<div style="text-align: center;">
		<span style="display: inline-block;">
		  	<div id="chanelChooser"></div>
			<div id="game">
				<div id="gameResume" style="padding: 15px;">
					<div>
						<h1><span id='labelPoint'>En attente du jeu !!!!</span><span id="nbPoint"></span></h1>
					</div>
				</div>
				
					<div id="progressBarDiv"></div>
				
				    <div id="playGamePanel">
				      	<div class="x-panel-header">BlindTest en direct</div>
				  		<div class="x-panel-body"> 
				  			<div style="text-align: center;">
								<span style="display: inline-block;">
									<div id="responsesBrand">
										<div class="explainBT">
											Lors de l'espace publicitaire, trois logos de marque différentes vous seront proposés. 
										</div>
										<div class="explainBT">
											A vous de sélectionner le logo correspondant à la <b>publicité</b>.
										</div>
										<div class="explainBT">
											A la fin de l'espace publicitaire les meilleurs joueurs remporteront des cadeaux.
										</div>
										<div class="explainBT">
											Plus vous avez d'adversaires, plus beaux seront les <b>cadeaux</b>.
<!--											Plus les joueurs seront nombreux, plus les cadeaux seront importants.-->
										</div>
										<div class="explainBT">
											Le meilleur pourra remporter le JACKPOT de la chaîne en répondant correctement à une question.
										</div>
										<div class="explainBT">
											<b>Bonne chance</b>
										</div>
									</div>
								</span>
							</div>
						</div>
					</div>
			
				<div style="display: none;">
					<s:form action="/ClassmentJP.htm" id="getClassement">
						<input type="hidden" name="jackpot" id="jackpot" value=""></input>
						<input type="submit"></input>
					</s:form>
				</div>
			</div>
		
		</span>
	</div>
  </s:layout-component>
</s:layout-render>
