//filename: Semaphore.java
/**************************************************************/
/* class Semaphore                                            */
/* The class defines and implements a semaphore.              */
/* The file is ported from the Dr. probst lab4.txt            */
/* Author: Jianxiong Dong                                     */
/* Date  : May 30, 2000                                       */
/**************************************************************/


public class Semaphore
{
  public Semaphore(){
    value = 0;

  }
  public Semaphore(int v){
    value = v;

  }
  public synchronized void P( ){
    while( value <= 0){
      try{
	wait( );
      }
      catch(InterruptedException e){ }
    }
    value--;

  }

  public synchronized void V( ){
    ++value;
    notify( );

  }
  private int value;
}
