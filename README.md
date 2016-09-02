# txbridgeInEAP7
setup txbridge under JBoss EAP7 for below scenario
found txbridge outbound did not work 

     servlet client - localhost:8180/user?action=add 
          |
--------------------------------------------------------------
    ServiceA1 JBoss EAP7
    Narayana JTA 
    Webservie - localhost:8280/user?action=add
--------------------------------------------------------------
          |
       txbridge
          |
--------------------------------------------------------------
    ServiceB1 JBoss EAP7
    Narayana JTA 
    Webservie - localhost:8380/user?action=add
--------------------------------------------------------------
          |
       txbridge 
          |
--------------------------------------------------------------
    ServiceC1 JBoss EAP7
    Narayana JTA 
--------------------------------------------------------------