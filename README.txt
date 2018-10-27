instructions:
1. first clone the project to your computer using git clone
2. open cmd in the project folder
3. write sbt run and then press enter
4. in the browser play will run on http://localhost:9000
5. for event types count go to http://localhost:9000/eventsCount
6. for word count go to http://localhost:9000/wordsCount


3 things I would improve in my submission:
1. I would use ElasticSearch and store the logs in it because it would be much more easy to analyze logs using ElasticSearch 
2. in order to scale out and fulfil decoupling in the best way i would use data source such kafka which implements producer and consumer
and then i would split the between the serivce that listen to the exe output and writes to kafka and the service that reads from kafka and give statistics.
3. I would change the mutable map i used to immutable map in order to make my code more functional

