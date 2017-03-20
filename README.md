# Apache Twill
This repo contains some examples of using Apache Twill API to run YARN applications on top of HDFS. 

### What is Apache Twill !
Apache Twill is an abstraction over Apache Hadoop® YARN that reduces the complexity of developing distributed applications, allowing developers to focus instead on their application logic. Apache Twill allows you to use YARN’s distributed capabilities with a programming model that is similar to running threads.

### You can find the official documentation <a href="http://twill.apache.org/index.html" > here </a>

### Prerequisites:
1- Single Node or Cluster installation of Hadoop with YARN (Hadoop >= 2.2.0) set-up and running.   
2- Single Node or Cluster installation of ZooKeeper set-up and running

### To run the examples:
Inside the projects 
```
mvn clean install 
```
Then take the jar file produced from the operation to any node in the cluster running hadoop and run this command
```
java -cp <the jar file path>:'hadoop classpath' com.mycompany.helloworld_twill.App <zookeeper ip adress host:port>
```
