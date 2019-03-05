

/*****************************************************/
/*  class Database                                   */
/* This class gives a solution to reader/writer      */
/* problem based on the policy that writer starvation*/ 
/* is prevented                                      */
/* The solution is implemented by java semaphore.    */
/* Author: Jianxiong Dong                            */
/* Date  : May 30, 2000                              */                        
/*****************************************************/

import Semaphore;

public class Database {
  public Database( ){

    readCount = 0;
    writeCount = 0;

    mutex1 = new Semaphore(1);
    mutex2 = new Semaphore(1);
    
    readBlock = new Semaphore(1);
    writeBlock = new Semaphore(1);
    
    writePending = new Semaphore(1);
    
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
    
    writePending.P( );
    readBlock.P( );
    mutex1.P( );
    ++readCount;//shared variable
    System.out.println("Reader "+ReaderID+" is reading"+" ReadCount: "+readCount);   
    
    //if I am the first reader, tell all others that the database is being
    // read

    if(readCount == 1)
      writeBlock.P( );
    mutex1.V( );
    readBlock.V( );
    writePending.V( );
    
    return readCount;
  }

  public int endRead(int ReaderID ){
    mutex1.P( );
    --readCount;

    System.out.println("Reader "+ReaderID+" end reading"+" ReadCount: "+readCount);
    //If I am the last reader, tell all others that the database is no longer
    //being read

    if(readCount == 0)
      writeBlock.V( );
    
    mutex1.V( );
    return readCount;

  }

  public void startWrite( ){

    mutex2.P( );
    writeCount++;
    if(writeCount == 1)
      readBlock.P( );
    mutex2.V( );
    writeBlock.P( );

  }

  public void endWrite( ){

    writeBlock.V( );
    mutex2.P( );
    writeCount--;
    if(writeCount == 0)
      readBlock.V( );
    mutex2.V( );
  }

  public char read( ){
    return element;
  }

  public void write(char item){
    element = item;
  }

  private static final int NAP_TIME = 15;

  private char element = 'a';

  private int writeCount;
  private int readCount;

  private Semaphore mutex1;//Lock for readerCount
  private Semaphore mutex2;//Lock for writerCount

  private Semaphore readBlock;
  private Semaphore writeBlock;

  private Semaphore writePending;
}


