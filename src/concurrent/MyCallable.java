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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Siddhant Kumar
 */
@SuppressWarnings( "nls" )
public class MyCallable implements Callable
{

  @Override
  public Object call() throws Exception
  {
    // Obtain a random number from 1 to 10
//    int count = ThreadLocalRandom.current()
//                                 .nextInt( 1, 11 );
    for ( int i = 1; i <= 10; i++ )
    {
      System.out.println( "Running..." + i );
    }
    return 10;

  }

  public static void main( String[] args )
  {
    Callable< Integer > c = new MyCallable();
    ThreadPoolExecutor tpe = ( ThreadPoolExecutor ) Executors.newFixedThreadPool( 15 );
    tpe.setCorePoolSize( 20 );
    tpe.setMaximumPoolSize( 20 );
    Future< Integer > f = tpe.submit( c ); // finishes in the future
    try
    {
      Integer v = f.get(); // blocks until done
      System.out.println( "Ran:" + v );
    }
    catch ( InterruptedException | ExecutionException iex )
    {
      System.out.println( "Failed" );
    }
  }

}
