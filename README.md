# Quiz-Service
Microservice to CRUD some Quiz, it's Questions and it's possible (Options) answer(s).

At /Quiz-Service/Actions/ Maven build and a Docker image are executed for every push to master.

Inside the quiz-curl-tests.zip there are .curl files to check the json format complies as specified.

After running 'mvn clean install' on the ./quiz-reactor/pom.xml To launch the microservice, run 'java -jar quiz-web-rs-0.0.1-SNAPSHOT.war' in the path './quiz-reactor/quiz-web-rs/target/' it will be deployed at http://localhost:8080/game (/game/option -- /game/question -- /game/quiz)
