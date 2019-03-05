import Database;
import Reader;
import Writer;

public class test{
  public static void main(String[] args){
    Database server = new Database();
    int steps = 3;//number of steps
    int j;

    Reader readerThreads[ ] = new Reader[20];
    Writer writerThread = new Writer(21, steps, server);
    
    //create twenty reader threads
    for ( j = 0; j < 20; j++)
      readerThreads[j] = new Reader(j, steps, server);


    //Start one writer thread
    writerThread.start( );

    //Start ten reader threads
    for( j = 0; j < 10; j++)
      readerThreads[j].start( );

    //Start one writer thread
    //  writerThread.start( );

    server.napping( );
    //Start subsequent ten reader threads

    for ( j = 10; j < 20; j++)
      readerThreads[j].start( );
  }
}


