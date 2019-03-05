

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
