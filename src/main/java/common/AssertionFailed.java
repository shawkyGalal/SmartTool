/*********************************************************************
*
*      Copyright (C) 2001 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Library General Public
* License as published by the Free Software Foundation; either
* version 2 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Library General Public License for more details.
*
* You should have received a copy of the GNU Library General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

package common;

/**
 * An exception thrown when an assert (from the Assert class) fails
 */
public class AssertionFailed extends RuntimeException
{
  /**
   * Default constructor
   * Prints the stack trace
   */
  public AssertionFailed()
  {
    super();
    printStackTrace();
  }

  /**
   * Constructor with message
   * Prints the stack trace
   * 
   * @param s Message thrown with the assertion
   */
  public AssertionFailed(String s)
  {
    super(s);
  }
}
