
import Database;

public class Writer extends Thread
{
  public Writer(int id, int steps, Database db){
    writerID = id;
    this.steps = steps;
    server = db;
  }
  
  public void run( ){
    char item ='*';

 
    System.out.println("Writer "+writerID+" start.");

    for (int j = 0; j < steps; j++)
    {
      server.startWrite( );
      //Write data into the database
      System.out.println("Writer "+writerID+" starts writing");

      switch(j%3){
        case 0:
	  item = '?';
	  break;
        case 1:
	  item = '!';
	  break;
        case 2:
	  item = 'b';
	  break;
        default:
	  break;
      }
      server.write(item);
      System.out.println("Writer "+writerID+" writes "+item+" in step "+j);
           
      server.endWrite();

      System.out.println("Writer "+writerID+" ends writing");
   

    }
    System.out.println("Writer "+writerID+" terminates");

  }
private int steps;
private int writerID;
private Database server;
}
