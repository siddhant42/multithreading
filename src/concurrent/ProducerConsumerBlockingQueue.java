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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @author Siddhant Kumar
 */
@SuppressWarnings( "nls" )
public class ProducerConsumerBlockingQueue
{
  public static void main( String args[] )
  {

    //Creating shared object  
    BlockingQueue< Integer > sharedQueue = new LinkedTransferQueue< Integer >();

    Producer1 producer = new Producer1( sharedQueue );
    Consumer1 consumer = new Consumer1( sharedQueue );

    Thread producerThread = new Thread( producer, "ProducerThread" );
    Thread consumerThread = new Thread( consumer, "ConsumerThread" );
    producerThread.start();
    consumerThread.start();

  }
}

/**
 * Producer Class.
 */
class Producer1 implements Runnable
{

  private final BlockingQueue< Integer > sharedQueue;

  public Producer1( BlockingQueue< Integer > sharedQueue )
  {
    this.sharedQueue = sharedQueue;
  }

  @Override
  public void run()
  {
    try
    {
      Thread.sleep( 100 );
    }
    catch ( InterruptedException e )
    {
    }
    for ( int i = 1; i <= 10; i++ )
    {
      try
      {

        System.out.println( "Produced : " + i );
        //put/produce into sharedQueue.
        this.sharedQueue.put( i );
      }
      catch ( InterruptedException ex )
      {

      }
    }
  }
}

/**
 * Consumer Class.
 */
class Consumer1 implements Runnable
{

  private BlockingQueue< Integer > sharedQueue;

  public Consumer1( BlockingQueue< Integer > sharedQueue )
  {
    this.sharedQueue = sharedQueue;
  }

  @Override
  public void run()
  {
    while ( true )
    {
      try
      {
        //take/consume from sharedQueue.
        System.out.println( "CONSUMED : " + this.sharedQueue.take() );
      }
      catch ( InterruptedException ex )
      {

      }
    }
  }

}
