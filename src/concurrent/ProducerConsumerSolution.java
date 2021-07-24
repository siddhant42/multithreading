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

package concurrent;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Siddhant Kumar
 */
@SuppressWarnings( "nls" )
public class ProducerConsumerSolution
{

  public static void main( String args[] )
  {
    Vector sharedQueue = new Vector();
    int size = 4;
    Thread prodThread = new Thread( new Producer( sharedQueue, size ), "Producer" );
    Thread consThread = new Thread( new Consumer( sharedQueue, size ), "Consumer" );
    prodThread.start();
    consThread.start();
  }
}

class Producer implements Runnable
{

  private final Vector sharedQueue;
  private final int    SIZE;

  public Producer( Vector sharedQueue, int size )
  {
    this.sharedQueue = sharedQueue;
    this.SIZE = size;
  }

  @Override
  public void run()
  {
    for ( int i = 0; i < 7; i++ )
    {

      try
      {
        produce( i );
      }
      catch ( InterruptedException ex )
      {
        Logger.getLogger( Producer.class.getName() )
              .log( Level.SEVERE, null, ex );
      }

    }
  }

  private void produce( int i ) throws InterruptedException
  {

    //wait if queue is full
    while ( this.sharedQueue.size() == this.SIZE )
    {
      synchronized ( this.sharedQueue )
      {
        System.out.println( "Queue is full " + Thread.currentThread()
                                                     .getName() + " is waiting , size: " + this.sharedQueue.size() );

        this.sharedQueue.wait();
      }
    }
    System.out.println( "Produced: " + i );
    //producing element and notify consumers
    synchronized ( this.sharedQueue )
    {
      this.sharedQueue.add( i );
      this.sharedQueue.notifyAll();
    }
  }
}

class Consumer implements Runnable
{

  private final Vector sharedQueue;
  private final int    SIZE;

  public Consumer( Vector sharedQueue, int size )
  {
    this.sharedQueue = sharedQueue;
    this.SIZE = size;
  }

  @Override
  public void run()
  {
    while ( true )
    {
      try
      {
        System.out.println( "Consumed: " + consume() );
        Thread.sleep( 50 );
      }
      catch ( InterruptedException ex )
      {
        Logger.getLogger( Consumer.class.getName() )
              .log( Level.SEVERE, null, ex );
      }

    }
  }

  private int consume() throws InterruptedException
  {
    //wait if queue is empty
    while ( this.sharedQueue.isEmpty() )
    {
      synchronized ( this.sharedQueue )
      {
        System.out.println( "Queue is empty " + Thread.currentThread()
                                                      .getName() + " is waiting , size: " + this.sharedQueue.size() );

        this.sharedQueue.wait();
      }
    }

    //Otherwise consume element and notify waiting producer
    synchronized ( this.sharedQueue )
    {
      this.sharedQueue.notifyAll();
      return ( Integer ) this.sharedQueue.remove( 0 );
    }
  }
}
