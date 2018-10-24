/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

package jxl.biff.drawing;

import common.Assert;
import common.Logger;

/** 
 * Class used to concatenate all the data for the various drawing objects
 * into one continuous stream
 */
public class DrawingData implements EscherStream
{
  /**
   * The logger
   */
  private static Logger logger = Logger.getLogger(DrawingData.class);

  /**
   * The drawing data
   */
  private byte[] drawingData;

  /**
   * The number of drawings
   */
  private int numDrawings;

  /**
   * Initialized flag
   */
  private boolean initialized;

  /**
   * The spgr container. The contains the SpContainer for each drawing
   */
  private EscherRecord[] spgrChildren;

  /**
   * Constructor
   */
  public DrawingData()
  {
    numDrawings = 0;
    drawingData = null;
    initialized = false;
  }

  /**
   * Initialization
   */
  private void initialize()
  {
    EscherRecordData er = new EscherRecordData(this,0);
    Assert.verify(er.isContainer());
    
    EscherContainer dgContainer  = new EscherContainer(er);
    EscherRecord[] children = dgContainer.getChildren();

    children = dgContainer.getChildren();
    Dg dg = (Dg) children[0];

    EscherContainer spgrContainer = null;

    for (int i = 0 ; i < children.length && spgrContainer == null; i++)
    {
      EscherRecord child = children[i];
      if (child.getType() == EscherRecordType.SPGR_CONTAINER)
      {
        spgrContainer = (EscherContainer) child;
      }
    }
    Assert.verify(spgrContainer != null);

    spgrChildren = spgrContainer.getChildren();
    initialized = true;
    //    for (int i = 0 ; i < spgrChildren.length ; i++)
    //    {
    //      logger.debug("spgrChildren  " + i + " type " + Integer.toString(spgrChildren[i].getType().getValue(), 16));
    //    }
  }

  /**
   * Adds the byte stream to the drawing data
   *
   * @param data
   */
  void addData(byte[] data)
  {
    addRawData(data);
    numDrawings++;
  }
  
  /**
   * Adds the data to the array without incrementing the drawing number.
   * This is used by comments, which for some bizarre and inexplicable
   * reason split out the data
   */
  void addRawData(byte[] data)
  {
    if (drawingData == null)
    {
      drawingData = data;
      return;
    }

    // Resize the array
    byte[] newArray = new byte[drawingData.length + data.length];
    System.arraycopy(drawingData, 0, newArray, 0, drawingData.length);
    System.arraycopy(data, 0, newArray, drawingData.length, data.length);
    drawingData = newArray;
    
    // Dirty up this object
    initialized = false;
  }

  /**
   * Accessor for the number of drawings
   *
   * @return the current count of drawings
   */
  final int getNumDrawings()
  {
    return numDrawings;
  }

  /**
   * Gets the sp container for the specified drawing number
   *
   * @param drawingNum the drawing number for which to return the spContainer
   * @return the spcontainer
   */
  EscherContainer getSpContainer(int drawingNum)
  {
    if (!initialized)
    {
      initialize();
    }
    
    EscherContainer spContainer = (EscherContainer) spgrChildren[drawingNum+1];

    return spContainer;
  }

  /**
   * Gets the data which was read in for the drawings
   *
   * @return the drawing data
   */
  public byte[] getData()
  {
    return drawingData;
  }
}
