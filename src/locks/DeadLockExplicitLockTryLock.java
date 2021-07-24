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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kulshres
 */
public class DeadLockExplicitLockTryLock
{
  final static Lock lock1 = new ReentrantLock();

  final static Lock lock2 = new ReentrantLock();

  public static void main( String[] args )
  {

    Thread t1 = new Thread( new Runnable5() );

    t1.setName( "Thread A" );

    t1.start();

    Thread t2 = new Thread( new Runnable6() );

    t2.setName( "Thread B" );

    t2.start();

  }
}

class Runnable5 implements Runnable

{

  @Override
  public void run()
  {

    boolean done = false;

    while ( !done )
    {

      if ( DeadLockExplicitLockTryLock.lock1.tryLock() )
      {

        try
        {

          System.out.println( Thread.currentThread()
                                    .getName()

          + ": Got lockObject1. Trying for lockObject2" );

          try
          {

            Thread.sleep( 1000 );

          }
          catch ( InterruptedException e )
          {

            e.printStackTrace();

          }

          if ( DeadLockExplicitLockTryLock.lock2.tryLock() )
          {

            try
            {

              System.out.println( Thread.currentThread()
                                        .getName()

              + ": Got lockObject2." );

              done = true;

            }
            finally
            {

              DeadLockExplicitLockTryLock.lock2.unlock();

            }

          }

        }
        finally
        {

          DeadLockExplicitLockTryLock.lock1.unlock();

          try
          {

            Thread.sleep( 500 );

          }
          catch ( InterruptedException e )
          {

            e.printStackTrace();

          }

        }

      }

    }

  }

}

class Runnable6 implements Runnable

{

  @Override
  public void run()

  {

    boolean done = false;

    while ( !done )
    {

      if ( DeadLockExplicitLockTryLock.lock2.tryLock() )
      {

        try
        {

          System.out.println( Thread.currentThread()
                                    .getName()

          + ": Got lockObject1. Trying for lockObject2" );

          try
          {

            Thread.sleep( 1000 );

          }
          catch ( InterruptedException e )
          {

            e.printStackTrace();

          }

          if ( DeadLockExplicitLockTryLock.lock1.tryLock() )
          {

            try
            {

              System.out.println( Thread.currentThread()
                                        .getName()

              + ": Got lockObject2." );

              done = true;

            }
            finally
            {

              DeadLockExplicitLockTryLock.lock1.unlock();

            }

          }

        }
        finally
        {

          DeadLockExplicitLockTryLock.lock2.unlock();

          try
          {

            Thread.sleep( 750 );

          }
          catch ( InterruptedException e )
          {

            e.printStackTrace();

          }

        }

      }

    }

  }

}
