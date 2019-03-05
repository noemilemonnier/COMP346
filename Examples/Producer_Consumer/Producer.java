
//filename: Producer.java
/**************************************************************/
/* class Consumer                                             */
/* The class defines and implements a producer thread.        */
/* The file is ported from the Dr. probst lab4.txt            */
/* Author: Jianxiong Dong                                     */
/* Date  : May 30, 2000                                       */
/**************************************************************/


import BoundedBuffer;

public class Producer extends Thread
{  

  private static byte free[ ] = new byte[26];
 
  static{
      byte c = 'a';
      for ( int i = 25; i >= 0; i--)
      {
	free[i] = c;
	c++;
      }
    }

  public Producer(BoundedBuffer b, int steps, int id){

    buffer = b;
    this.steps = steps;
    threadID = id;
  }
  
  private byte get_message( ){
    byte temp;
    temp = free[top];
    top--;
    return temp;
  }


  public void run( ){

    System.out.println("Producer "+threadID+" starts.");
    yield( );

    for ( int j = 0; j < steps; j++)
    {
      byte  m;

      m = get_message( );
      buffer.deposit(m, threadID, j);     
      yield( );      
    }
    System.out.println("Producer "+threadID+" terminates.");
   
  }


  private static int top = 25;
  private BoundedBuffer buffer;
  private int steps;
  private int threadID;

}
