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

package locks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kulshres
 */
public class MaxValueCollection
{
  private List< Integer >        integers = new ArrayList<>();
  private ReentrantReadWriteLock rwl      = new ReentrantReadWriteLock();

  public void add( Integer i )
  {
    this.rwl.writeLock()
            .lock(); // one at a time
    try
    {
      this.integers.add( i );
    }
    finally
    {
      this.rwl.writeLock()
              .unlock();
    }
  }

  public int findMax()
  {
    this.rwl.readLock()
            .lock(); // many at once
    try
    {
      return Collections.max( this.integers );
    }
    finally
    {
      this.rwl.readLock()
              .unlock();
    }
  }

  public static void main( String[] args )
  {
    final MaxValueCollection obj = new MaxValueCollection();
    new Thread()
    {
      @Override
      public void run()
      {
        obj.integers.add( 34 );
        obj.integers.add( 47 );
        obj.integers.add( 21 );
      }
    }.start();
    new Thread()
    {
      @Override
      public void run()
      {
        System.out.println( obj.findMax() );

      }
    }.start();
  }
}
