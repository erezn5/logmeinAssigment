Log Me In

Please follow below instructions in order to run the test:
1. Clone the project and use it in your local repository.
2. Please notice that env.properties file contains the configurations for parameters to run the test:   
 i. please notice that you can run the test both with a physical browser or headless , all you got to do is to change field name: 'selenium.headless' to true to run the test headless.(default value is headless).   
 ii. please notice that you can run it in several browsers (Chrome, IE, FireFox) all you got to do in order to change the browser is to update the following field: 'ui.browser.type'

3. Please navigate to path reference'\calculatorOperator\pom.xml' and run the following command to run it from your terminal/command line -> 
'mvn clean'
'mvn test'


Enjoy! 
