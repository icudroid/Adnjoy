var menuData = [
        { text: "Accueil",url: "Home.htm"},
        { text: "Jeu en direct",url: "TVJackPot.htm"},   
        { text: "BlindTest",url: "BlindTest.htm"},
        { text: "Mon compte", submenu:{ id: "accountId", itemdata:[
                                                     				{ text:"Modifier mon compte",url:"ModAccount.htm"},
                                                     				{ text:"Mes gains",url:"WonObjects.htm"},
                                                     				{ text:"Mes amis",url:"FriendsGroup.htm"}
                                                             	]}
                                                             }
      ];
