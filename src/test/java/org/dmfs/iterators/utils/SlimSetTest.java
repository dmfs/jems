/*
 * Copyright 2017 dmfs GmbH
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.iterators.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


/*
 * Taken from https://raw.githubusercontent.com/AndroidSDKSources/android-sdk-sources-for-api-level-23/d32e2eafffc20c5e9f6e3f31794d17e25484c30f/org/apache/harmony/tests/java/util/HashSetTest.java
 * 
 * Changes:
 * 
 * * SlimSet accepts negative sizes and falls back to the default minimum size
 * * Serialization is not tested (yet)
 * * suppress all the warning about raw types and unchecked types casts
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SlimSetTest extends junit.framework.TestCase
{

    SlimSet hs;

    Object[] objArray;


    /**
     * java.util.FastSet#FastSet()
     */
    public void test_Constructor()
    {
        // Test for method java.util.FastSet()
        SlimSet hs2 = new SlimSet();
        assertEquals("Created incorrect FastSet", 0, hs2.size());
    }


    /**
     * java.util.FastSet#FastSet(int)
     */
    public void test_ConstructorI()
    {
        // Test for method java.util.FastSet(int)
        SlimSet hs2 = new SlimSet(5);
        assertEquals("Created incorrect FastSet", 0, hs2.size());
    }


    /**
     * java.util.FastSet#FastSet(int, float)
     */
    public void test_ConstructorIF()
    {
        // Test for method java.util.FastSet(int, float)
        SlimSet hs2 = new SlimSet(5, (float) 0.5);
        assertEquals("Created incorrect FastSet", 0, hs2.size());
        try
        {
            new SlimSet(0, 0);
        }
        catch (IllegalArgumentException e)
        {
            return;
        }
        fail("Failed to throw IllegalArgumentException for initial load factor <= 0");
    }


    /**
     * java.util.FastSet#FastSet(java.util.Collection)
     */
    public void test_ConstructorLjava_util_Collection()
    {
        // Test for method java.util.FastSet(java.util.Collection)
        SlimSet hs2 = new SlimSet(Arrays.asList(objArray));
        for (int counter = 0; counter < objArray.length; counter++)
        {
            assertTrue("FastSet does not contain correct elements", hs.contains(objArray[counter]));
        }
        assertTrue("FastSet created from collection incorrect size", hs2.size() == objArray.length);

        try
        {
            new SlimSet((Collection) null);
            fail("NullPointerException expected");
        }
        catch (NullPointerException e)
        {
            // expected
        }
    }


    /**
     * java.util.FastSet#add(java.lang.Object)
     */
    public void test_addLjava_lang_Object()
    {
        // Test for method boolean java.util.FastSet.add(java.lang.Object)
        int size = hs.size();
        hs.add(new Integer(8));
        assertTrue("Added element already contained by set", hs.size() == size);
        hs.add(new Integer(-9));
        assertTrue("Failed to increment set size after add", hs.size() == size + 1);
        assertTrue("Failed to add element to set", hs.contains(new Integer(-9)));
    }


    /**
     * java.util.FastSet#clear()
     */
    public void test_clear()
    {
        // Test for method void java.util.FastSet.clear()
        Set orgSet = (Set) hs.clone();
        hs.clear();
        Iterator i = orgSet.iterator();
        assertEquals("Returned non-zero size after clear", 0, hs.size());
        while (i.hasNext())
        {
            assertTrue("Failed to clear set", !hs.contains(i.next()));
        }
    }


    /**
     * java.util.FastSet#clone()
     */
    public void test_clone()
    {
        // Test for method java.lang.Object java.util.FastSet.clone()
        SlimSet hs2 = (SlimSet) hs.clone();
        assertTrue("clone returned an equivalent FastSet", hs != hs2);
        assertTrue("clone did not return an equal FastSet", hs.equals(hs2));
    }


    /**
     * java.util.FastSet#contains(java.lang.Object)
     */
    public void test_containsLjava_lang_Object()
    {
        // Test for method boolean java.util.FastSet.contains(java.lang.Object)
        assertTrue("Returned false for valid object", hs.contains(objArray[90]));
        assertTrue("Returned true for invalid Object", !hs.contains(new Object()));

        SlimSet s = new SlimSet();
        s.add(null);
        assertTrue("Cannot handle null", s.contains(null));
    }


    /**
     * java.util.FastSet#isEmpty()
     */
    public void test_isEmpty()
    {
        // Test for method boolean java.util.FastSet.isEmpty()
        assertTrue("Empty set returned false", new SlimSet().isEmpty());
        assertTrue("Non-empty set returned true", !hs.isEmpty());
    }


    /**
     * java.util.FastSet#iterator()
     */
    public void test_iterator()
    {
        // Test for method java.util.Iterator java.util.FastSet.iterator()
        Iterator i = hs.iterator();
        int x = 0;
        while (i.hasNext())
        {
            assertTrue("Failed to iterate over all elements", hs.contains(i.next()));
            ++x;
        }
        assertEquals("Returned iteration of incorrect size", hs.size(), x);

        SlimSet s = new SlimSet();
        s.add(null);
        assertNull("Cannot handle null", s.iterator().next());
    }


    /**
     * java.util.FastSet#remove(java.lang.Object)
     */
    public void test_removeLjava_lang_Object()
    {
        // Test for method boolean java.util.FastSet.remove(java.lang.Object)
        int size = hs.size();
        hs.remove(new Integer(98));
        assertTrue("Failed to remove element", !hs.contains(new Integer(98)));
        assertTrue("Failed to decrement set size", hs.size() == size - 1);

        SlimSet s = new SlimSet();
        s.add(null);
        assertTrue("Cannot handle null", s.remove(null));
        assertFalse(hs.remove(new Integer(-98)));
    }


    /**
     * java.util.FastSet#size()
     */
    public void test_size()
    {
        // Test for method int java.util.FastSet.size()
        assertTrue("Returned incorrect size", hs.size() == (objArray.length + 1));
        hs.clear();
        assertEquals("Cleared set returned non-zero size", 0, hs.size());
    }

    // /**
    // * java.util.FastSet#SerializationTest
    // */
    // public void test_Serialization() throws Exception{
    // FastSet<String> hs = new FastSet<String>();
    // hs.add("hello");
    // hs.add("world");
    // SerializationTest.verifySelf(hs, comparator);
    // SerializationTest.verifyGolden(this, hs, comparator);
    // }


    /**
     * Sets up the fixture, for example, open a network connection. This method is called before a test is executed.
     */
    protected void setUp()
    {
        objArray = new Object[1000];
        for (int i = 0; i < objArray.length; i++)
        {
            objArray[i] = new Integer(i);
        }

        hs = new SlimSet();
        for (int i = 0; i < objArray.length; i++)
        {
            hs.add(objArray[i]);
        }

        hs.add(null);
    }


    /**
     * Tears down the fixture, for example, close a network connection. This method is called after a test is executed.
     */
    protected void tearDown()
    {
        hs = null;
        objArray = null;
    }

    // private static final SerializationTest.SerializableAssert comparator = new
    // SerializationTest.SerializableAssert() {
    // public void assertDeserialized(Serializable initial, Serializable deserialized) {
    // FastSet<String> initialHs = (FastSet<String>) initial;
    // FastSet<String> deseriaHs = (FastSet<String>) deserialized;
    // assertEquals("should be equal", initialHs.size(), deseriaHs.size());
    // assertEquals("should be equal", initialHs, deseriaHs);
    // }
    //
    // };
}
