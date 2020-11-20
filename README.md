# proyectog4-rest-assured
This repository to support the online course. This framework supports web services and BDD.

José Hurtado<br />
* Twitter : [@jhumbertoh](https://twitter.com/jhumbertoh) <br />
* Linkedin: [@jhumbertoh](https://www.linkedin.com/in/jhumbertoh/)

License
=======
You can use this code for your own personal learning and development. Please do not use it to train others. 

And do not use it for commercial purposes.

## Running  all test
    mvn clean test

## Running Cucumber Test with a subset of scenarios tagged with "@rest"
    mvn clean test -Dcucumber.filter.tags="@rest"

## Running Cucumber Test with a subset of scenarios tagged with "@rest and @Working"
    mvn clean test -Dcucumber.filter.tags="@rest and @Working"

## Running Cucumber Test with a subset of scenarios tagged with "@rest or @Working"
    mvn clean test -Dcucumber.filter.tags="@rest or @Working"