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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kulshres
 */
public class Counter
{
  /*  private int count;

    public void increment()
    {
      this.count++; // it's a trap!
      // a single "line" is not atomic
    }

    public int getValue()
    {
      return this.count;
    }*/
  private AtomicInteger count = new AtomicInteger();

  public void increment()
  {
    this.count.getAndIncrement(); // atomic operation
  }

  public int getValue()
  {
    return this.count.intValue();
  }
}
