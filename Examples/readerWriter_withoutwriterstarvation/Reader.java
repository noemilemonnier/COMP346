
import Database;

public class Reader extends Thread
{
  public Reader(int id, int steps, Database db){
    readerID = id;
    server = db;
    this.steps = steps;
  }

  public void run( ){
    int c;
    char item;

    System.out.println("Reader "+readerID+" start.");

    for ( int j = 0; j < steps; j++){
      c = server.startRead(readerID );
      
      //access the data from the database
      item = server.read( );
      System.out.println("Reader "+readerID+" reads "+item+" in step "+j);
      
      c = server.endRead(readerID );        
    }
    System.out.println("Reader "+readerID+" terminates.");
  }

private int steps;
private int readerID;
private Database server;

}
