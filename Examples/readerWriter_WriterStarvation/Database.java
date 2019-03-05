
/*****************************************************/
/*  class Database                                   */
/* This class gives a solution to reader/writer      */
/* problem based on the policy that can not prevent  */
/* writer from starvation.                           */
/* The solution is implemented by java semaphore.    */
/* Author: Jianxiong Dong                            */
/* Date  : May 30, 2000                              */
/* This program is ported from Dr. probst lab6.txt   */
/*  (modula-3)                                       */
/*****************************************************/
    
import Semaphore;

public class Database {
  public Database( ){

    readerCount = 0;
    mutex = new Semaphore(1);
    db = new Semaphore(1);
  }

  //Readers and writers will call to nap
  public static void napping( ){

    int sleepTime = NAP_TIME;
    try{

      Thread.sleep(sleepTime*10);
    }
    catch(InterruptedException e){ }
  }

  public int startRead(int ReaderID ){
    mutex.P( );
    ++readerCount;//shared variable
    System.out.println("Reader "+ReaderID+" is reading"+" ReaderCount: "+readerCount);   
    
    //if I am the first reader, tell all others that the database is being
    // read

    if(readerCount == 1)
      db.P( );
    mutex.V( );
    
    return readerCount;
  }

  public int endRead(int ReaderID ){
    mutex.P( );
    --readerCount;

    System.out.println("Reader "+ReaderID+" end reading"+" ReaderCount: "+readerCount);
    //If I am the last reader, tell all others that the database is no longer
    //being read

    if(readerCount == 0)
      db.V( );
    
    mutex.V( );
    return readerCount;

  }

  public void startWrite( ){

    db.P( );
  }

  public void endWrite( ){

    db.V( );
  }

  public char read( ){
    return element;
  }

  public void write(char item){
    element = item;
  }

  private static final int NAP_TIME = 15;

  private char element = 'a';
  private int readerCount;
  private Semaphore mutex;//Lock variable 
  private Semaphore db;   //Semaphore that prevents reader and writer from ente                          //ring the database
}


