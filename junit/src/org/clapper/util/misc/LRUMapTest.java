/*---------------------------------------------------------------------------*\
  $Id$
  ---------------------------------------------------------------------------
  This software is released under a BSD-style license:

  Copyright (c) 2004-2006 Brian M. Clapper. All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

  1.  Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.

  2.  The end-user documentation included with the redistribution, if any,
      must include the following acknowlegement:

        "This product includes software developed by Brian M. Clapper
        (bmc@clapper.org, http://www.clapper.org/bmc/). That software is
        copyright (c) 2004-2006 Brian M. Clapper."

      Alternately, this acknowlegement may appear in the software itself,
      if wherever such third-party acknowlegements normally appear.

  3.  Neither the names "clapper.org", "clapper.org Java Utility Library",
      nor any of the names of the project contributors may be used to
      endorse or promote products derived from this software without prior
      written permission. For written permission, please contact
      bmc@clapper.org.

  4.  Products derived from this software may not be called "clapper.org
      Java Utility Library", nor may "clapper.org" appear in their names
      without prior written permission of Brian M.a Clapper.

  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
  NO EVENT SHALL BRIAN M. CLAPPER BE LIABLE FOR ANY DIRECT, INDIRECT,
  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
\*---------------------------------------------------------------------------*/

package org.clapper.util.misc;

import java.util.Map;

/**
 *
 */
public class LRUMapTest extends MapTestBase
{
    class TestListener implements ObjectRemovalListener
    {
        private String lookForKey;
        private String lookForValue;
        private boolean called;

        TestListener(String lookForKey, String lookForValue)
        {
            this.lookForKey = lookForKey;
            this.lookForValue = lookForValue;
        }

        public void objectRemoved(ObjectRemovalEvent event)
        {
            Map.Entry<String,String> removed =
                (Map.Entry<String,String>) event.getSource();
            System.out.println("ObjectRemovalListener called for " +
                               removed.getKey() + "=" + removed.getValue());
            assertEquals("Removed item has wrong key",
                         lookForKey, removed.getKey());
            assertEquals("Removed item has wrong value",
                         lookForValue, removed.getValue());
            called = true;
        }

        boolean wasCalled()
        {
            return called;
        }
    }

    public LRUMapTest(String testName)
    {
        super(testName);
    }

    protected void setUp() throws Exception
    {
    }

    protected void tearDown() throws Exception
    {
    }

    /**
     * Test of addRemovalListener method, of class org.clapper.util.misc.LRUMap.
     */
    public void testAddRemovalListener()
    {
        LRUMap<String,String> map = new LRUMap<String,String>(1);
        map.put("a", "a value");

        TestListener listener = new TestListener("a", "a value");
        map.addRemovalListener(listener, true);
        map.put("b", "b value");
        assertEquals("Map size should be 1", 1, map.size());
        assertTrue("Listener not invoked as expected", listener.wasCalled());
    }

    /**
     * Test of removeRemovalListener method, of class org.clapper.util.misc.LRUMap.
     */
    public void testRemoveRemovalListener()
    {
        LRUMap<String,String> map = new LRUMap<String,String>(1);
        map.put("a", "a value");

        TestListener listener = new TestListener("a", "a value");
        map.addRemovalListener(listener, false);
        map.removeRemovalListener(listener);
        map.remove("a");
        assertEquals("Map size should be 0", 0, map.size());
        assertFalse("Listener unexpectedly invoked", listener.wasCalled());
    }

    /**
     * Test of getInitialCapacity method, of class org.clapper.util.misc.LRUMap.
     */
    public void testGetInitialCapacity()
    {
    }

    /**
     * Test of getLoadFactor method, of class org.clapper.util.misc.LRUMap.
     */
    public void testGetLoadFactor()
    {
    }

    /**
     * Test of getMaximumCapacity method, of class org.clapper.util.misc.LRUMap.
     */
    public void testGetMaximumCapacity()
    {
    }

    /**
     * Test of isEmpty method, of class org.clapper.util.misc.LRUMap.
     */
    public void testIsEmpty()
    {
    }

    /**
     * Test of keySet method, of class org.clapper.util.misc.LRUMap.
     */
    public void testKeySet()
    {
    }

    /**
     * Test of put method, of class org.clapper.util.misc.LRUMap.
     */
    public void testPut()
    {
    }

    /**
     * Test of putAll method, of class org.clapper.util.misc.LRUMap.
     */
    public void testPutAll()
    {
    }

    /**
     * Test of remove method, of class org.clapper.util.misc.LRUMap.
     */
    public void testRemove()
    {
    }

    /**
     * Test of setMaximumCapacity method, of class org.clapper.util.misc.LRUMap.
     */
    public void testSetMaximumCapacity()
    {
    }

    /**
     * Test of size method, of class org.clapper.util.misc.LRUMap.
     */
    public void testSize()
    {
    }

    /**
     * Test of clone method, of class org.clapper.util.misc.LRUMap.
     */
    public void testClone() throws Exception
    {
    }

    /*----------------------------------------------------------------------*\
                             Protected Methods
    \*----------------------------------------------------------------------*/

    protected Map<String,String> newMap()
    {
        return new LRUMap<String,String>(100);
    }
}
