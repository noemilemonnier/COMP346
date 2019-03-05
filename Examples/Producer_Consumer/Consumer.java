//filename: Consumer.java
/**************************************************************/
/* class Consumer                                             */
/* The class defines and implements a consumer thread.        */
/* The file is ported from the Dr. probst lab4.txt            */
/* Author: Jianxiong Dong                                     */
/* Date  : May 30, 2000                                       */
/**************************************************************/


import BoundedBuffer;

public class Consumer extends Thread
{
  public Consumer(BoundedBuffer b, int steps, int id){
    buffer = b;
    this.steps = steps;
    threadID = id;

  }
  public void run( ){
    System.out.println("Consumer "+threadID+" starts.");
    for ( int j = 0; j < steps; j++)
    {
      byte m;
      m = buffer.remove(threadID, j);
      yield( );
    }
    
    System.out.println("Consumer "+threadID+" terminates.");
  }
  private BoundedBuffer buffer;
  private int steps;
  private int threadID;

}
