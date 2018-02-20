# Banana Best Bank App
Banana Best Bank application for secure transactions. - Java Assignment.

1. Easiest way to run the App:

    a) git clone https://github.com/DajMiNazwe/holiday.git
    
    b) run ./mvnw clean install inside project directory. (or on Windows: mvnw.cmd clean install)
    
    c) navigate to /target
    
    d) run java -jar banana-0.0.1-SNAPSHOT.jar
    
    e) now you can make requests, default example: localhost:8080/history/user/userId
    
2. Configuration:

    Inside resources/application.yml there are few simple configuration options that you can use:
    
    server.port, oneTimeTokenLength and oneTimeTokenValiditySeconds.
    
3. Additional information:
    
    a) Opening the Account.
    
    At first there is nothing inside our "database" and the only way to open new account is to deposit some value to
    Banana Best Bank. After it's done there will be an account opened for ID that you provided. 
    
    b) Generating One Time Token.
    
    To withdraw money from the Account you need to generate OTT - the way to do it is to call (post): /tokens/user/userId
    You will receive your token - but remember that you have limited time to use it!
        
    
    


