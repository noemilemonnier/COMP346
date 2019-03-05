

//filename: test.java 
/**************************************************************/
/* class test( main class)                                    */
/* The class creates four producer threads and four consumer  */
/* threads to compete for boundedbuffer.                      */
/* The file is ported from the Dr. probst lab4.txt            */
/* Author: Jianxiong Dong                                     */
/* Date  : May 30, 2000                                       */
/**************************************************************/



import BoundedBuffer;
import Producer;
import Consumer;

public class test{
  public static void main(String[] args){
    BoundedBuffer server = new BoundedBuffer(7);
    int numproc = 8; //number of threads
    int steps = 3;   //number of steps
    Producer producerThreads[ ] = new Producer[numproc];
    Consumer consumerThreads[ ] = new Consumer[numproc];
    int j;

    //now create the producer and consumer threads
    for (j = 0; j < numproc; j++)
    {
      System.out.println("Top thread creates proc " + j);
      if(j%2 == 0)
      {
	producerThreads[j] = new Producer(server, steps, j);
      }
      else
      {
	consumerThreads[j] = new Consumer(server, steps, j);
      }
    }

    for ( j = 0; j < numproc; j++)
    {
      if(j%2 == 0)
      {
	producerThreads[j].start( );
      }
      else
      {
	consumerThreads[j].start( );
      }
    }

  }
}
