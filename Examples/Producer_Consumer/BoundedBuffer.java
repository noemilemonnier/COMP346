
//filename: 1.java (If you want to compile it, rename it to  BoundedBuffer.java
/**************************************************************/
/* class BoundedBuffer                                        */
/* The class implements producer/consumer problem using       */
/* java semaphore.                                            */
/* The file is ported from the Dr. probst lab4.txt            */
/* Author: Jianxiong Dong                                     */
/* Date  : May 30, 2000                                       */
/**************************************************************/


import Semaphore;

public class BoundedBuffer
{
  public BoundedBuffer(int buffer_size){
    //buffer is initially empty

    this.buffer_size = buffer_size;    
    buffer = new byte [buffer_size];
    count = 0;

    mutex = new Semaphore(1);
    empty = new Semaphore(buffer_size);
    full  = new Semaphore(0);

    rear = 0;
    front= 0;
    rcopy= 0;
    fcopy= 0;
  }
 
  public void deposit(byte item, int ProducerID, int step){

    empty.P( );
    mutex.P( );
    
    //add an item to the buffer
    ++count;
    buffer[rear] = item;
    rcopy = rear;
    rear = (rear+1)%buffer_size;
    
    if(count == buffer_size)
      System.out.println("Producer entered " + (char)item + " Buffer full");
    else
      System.out.println("Producer "+ProducerID+" deposits message  " + (char)item +"  as step " +step+" into slot " + rcopy);   
     
    
    mutex.V( );
    full.V( );
         
  }

  public byte remove(int ConsumerID, int step ){
    byte item;

    full.P();
    mutex.P( );
 
    //remove an item from the buffer
    --count;
    item = buffer[front];
    fcopy = front;
    front = (front+1)%buffer_size;

    if(count == 0)
      System.out.println("Consumer consumed " + (char)item + " buffer empty");
    else
      System.out.println("Consumer "+ConsumerID+" removes message " +(char)item+" as step "+step+" from slot " + fcopy);    
 
    mutex.V( );
    empty.V( );

    return item;  
  }

  private int buffer_size ;
  private byte buffer[ ]; //producer/consumer buffer


  private  int rear;
  private  int front;
  private  int count;

  private  int rcopy;
  private  int fcopy;

  //semaphore
  private Semaphore   mutex; //delay-queue lock
  private Semaphore   full; //producer delay queue
  private Semaphore   empty; //consumer delay queue
 
}
