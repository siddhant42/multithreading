/**************************************************************************************************
 *  ____  _  _  ____  _____  ____  __  __    __   ____  ____  ___    __   
 * (_  _)( \( )( ___)(  _  )(  _ \(  \/  )  /__\ (_  _)(_  _)/ __)  /__\  
 *  _)(_  )  (  )__)  )(_)(  )   / )    (  /(__)\  )(   _)(_( (__  /(__)\ 
 * (____)(_)\_)(__)  (_____)(_)\_)(_/\/\_)(__)(__)(__) (____)\___)(__)(__) 
 * 
 * Informatica PIM
 *
 * copyright: Informatica Corp. (c) 2003-2013.  All rights reserved.
 * 
 *************************************************************************************************/

package atomic;

/**
 * @author kulshres
 */
public class IncrementerThread extends Thread
{
  private Counter counter;

  // all instances are passed the same counter
  public IncrementerThread( Counter counter )
  {
    this.counter = counter;
  }

  @Override
  public void run()
  {
    // "i" is local and thread-safe
    for ( int i = 0; i < 10000; i++ )
    {
      this.counter.increment();
    }
  }

  public static void main( String[] args ) throws InterruptedException
  {
    Counter counter = new Counter(); // the shared object
    IncrementerThread it1 = new IncrementerThread( counter );
    IncrementerThread it2 = new IncrementerThread( counter );
    it1.start(); // thread 1 increments the count by 10000
    it2.start(); // thread 2 increments the count by 10000
    it1.join(); // wait for thread 1 to finish
    it2.join(); // wait for thread 2 to finish
    System.out.println( counter.getValue() );
  }
}
