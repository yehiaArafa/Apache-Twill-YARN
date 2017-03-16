
package com.mycompany.helloworld_twill;

import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.twill.api.AbstractTwillRunnable;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunnerService;
import org.apache.twill.api.logging.PrinterLogHandler;
import org.apache.twill.yarn.YarnTwillRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

 /*
 * @author ninja
 */


/*

#Resemblance between writing YARN application an writing standalone java apps(Twill)

YARN                    Standalone Java App
Application Client	java command that provides options and arguments to the application.
Application Master	main() method preparing threads for the application.
Container Application	Runnable implementation, where each runs in its own thread.

==================================================================================================

#To make your application run on a YARN cluster we need four steps:s

1) Write your application by extending from AbstractTwillRunnable and implementing the run() method.
2) Create and start a TwillRunnerService.
3) Submit your application through the TwillRunnerService.
4) Control your application using TwillController, for example, wait for completion.

===================================================================================================

#By default, Twill starts one container per TwillRunnable with 1 virtual core and 512MB of memory.
    
You could, however, customize it when starting your application through TwillRunner. 
  

*/


public class App {

    
    
//Enabling LOG  
private static final Logger LOG = LoggerFactory.getLogger(App.class);  

 
 public static void main(String []args){
 
  //zookeeper server and port
  String zkStr = args[0];
  //YARN configration
  YarnConfiguration yarnConf = new YarnConfiguration();
  
  //create and run TwillRunnerService which we will submit our applcation with after
  final TwillRunnerService twillRunner = new YarnTwillRunnerService(yarnConf, zkStr);
  twillRunner.start();
 
  //Any custom command messages can be sent to the application through the WeaveController, 
  //  which bounces through Zookeeper
  final TwillController controller=twillRunner.prepare(new HelloWorldRunnable()).addLogHandler(
                                   new PrinterLogHandler(new PrintWriter(System.out, true))).start();
  
 }
 
 
 
 /* A class extending AbstractTwillRunnable where your appliction code will 
    be inside the run() function */
 private static class HelloWorldRunnable extends AbstractTwillRunnable {
     
    /*when the run function is invoked it prints hello world in then log 
      of every node container */ 
    @Override
    public void run(){
      LOG.info("Hello World. My first distributed application.");
    }
  }
 
 
 
 
 
}
