package edu.grinnell.csc207;

import static java.lang.reflect.Array.newInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.KeyNotFoundException;
import edu.grinnell.csc207.util.NullKeyException;

/**
 * Tests of the AssociativeArray class and related classes.
 *
 * @author CSC-207 2024Fa
 * @author Samuel A. Rebelsky
 */
public class TestAssociativeArrays {
  // +------------------------------+--------------------------------
  // | Tests by Rebelsky, Samuel A. |
  // +------------------------------+

  // Keep these at the top.

  /**
   * A non-test. Here mostly so that we don't get nasty messages about unused classes.
   */
  public void rebelskySamuelPlaceholder() {
    assertFalse(false, "false is false");
    assertNull(null, "null is null");
    BigInteger i = new BigInteger("123");
    assertEquals(i, i, "i think i is i");
  } // rebelskySamuelPlaceholder()

  /**
   * A test of cloning.
   * 
   * @throws NullKeyException
   */
  @Test
  public void rebelskySamuelTest01() throws NullKeyException {
    // Build an array
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("A", "Apple");
    try {
      assertEquals("Apple", arr.get("A"));
    } catch (Exception e) {
      fail("Original array does not contain expected value");
    }
    // Make a copy
    AssociativeArray<String, String> arr2 = arr.clone();
    // Make sure it contains the appropriate value
    try {
      assertEquals("Apple", arr2.get("A"));
    } catch (Exception e) {
      fail("Clone does not contain original value");
    } // try/catch
    // Change the original array
    arr.set("A", "aardvark");
    // Make sure we haven't changed the clone.
    try {
      assertEquals("Apple", arr2.get("A"));
    } catch (Exception e) {
      fail("Change to original changes clone.");
    }
    // Change the clone
    arr2.set("A", "Ant");
    // And look for values
    try {
      assertEquals("Ant", arr2.get("A"));
    } catch (Exception e) {
      fail("Cannot change clone");
    }
    try {
      assertEquals("aardvark", arr.get("A"));
    } catch (Exception e) {
      fail("Change to clone changes original");
    }
  } // rebelskySamuelTest01()

  /**
   * Can we successfully add a bunch of values? (Checks array expansion.)
   * 
   * @throws NullKeyException
   */
  @Test
  public void rebelskySamuelTest02() throws NullKeyException {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    // Add a bunch of values
    for (int i = 10; i < 50; i++) {
      arr.set(i, i * i);
    } // for
    try {
      for (int i = 49; i >= 10; i--) {
        assertEquals(i * i, arr.get(i));
      } // for
    } catch (Exception e) {
      fail("Exception in call to get");
    } // try/catch
  } // rebelskySamuelest02()

  /**
   * Do we get exceptions when grabbing a deleted value from the array?
   * 
   * @throws NullKeyException
   */
  @Test
  public void rebelskySamuelTest03() throws NullKeyException {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    // Add an element to the array
    arr.set("A", "Apple");
    // Make sure that it's there.
    try {
      assertEquals("Apple", arr.get("A"));
    } catch (KeyNotFoundException e) {
      fail("Could not set A to Apple");
    }
    // Remove it.
    arr.remove("A");
    // Make sure it's no longer there.
    try {
      // The following line should throw an exception
      arr.get("A");
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    }
  } // rebelskySamuelTest03

  /**
   * Does `toString` work correctly on multi-element arrays? Intended to demonstrate how complicated
   * writing such a test might be.
   *
   * @throws NullKeyException In unexpected situations.
   */
  @Test
  public void rebelskySamuelTest04() throws NullKeyException {
    AssociativeArray<String, Integer> si = new AssociativeArray<String, Integer>();
    si.set("A", 1);
    si.set("B", 2);
    si.set("C", 3);
    String result = si.toString();
    System.err.println(result);
    assertTrue(result.equals("{A:1, B:2, C:3}") || result.equals("{A:1, C:3, B:2}")
        || result.equals("{B:2, A:1, C:3}") || result.equals("{B:2, C:3, A:1}")
        || result.equals("{C:3, A:1, B:2}") || result.equals("{C:3, B:2, A:1}"));
  } // rebelskySamuelTest04()

  /**
   * Do we get exceptions when grabbing a value from the empty array?
   */
  @Test
  public void rebelskySamuelEdge01() {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    try {
      // The following line should throw an exception
      arr.get("A");
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    } // try/catch
  } // rebelskySamuelEdge01

  // ================================================================

  // +------------------------------+--------------------------------
  // | Tests by Alexander, Princess |
  // +------------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Bakrac, Mina |
  // +-----------------------+

  // +----------------------------+----------------------------------
  // | Tests by Bat-Erdene, Maral |
  // +----------------------------+

  // +---------------------+-----------------------------------------
  // | Tests by Bell, Jake |
  // +---------------------+
  /**
   * Do we get an element after cloning an array then removing that element?
   * 
   * @throws NullKeyException
   */
  @Test
  public void bellJakeTest01() throws NullKeyException {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("John", "Pork");
    AssociativeArray<String, String> cloneArr = arr.clone();
    cloneArr.remove("John");
    try {
      // The following line should throw an exception
      cloneArr.get("John");
      fail("Failed bellJakeTest01");
    } catch (KeyNotFoundException e) {
      // Do nothing
    } // try/catch
    try {
      arr.get("John");
    } catch (KeyNotFoundException e) {
      fail("Removal from clone removes from original, bellJakeTest01");
    } // try/catch
  } // bellJakeEdge01

  /**
   * Does the array handle multiple changes, then is able to remove?
   * 
   * @throws NullKeyExceptions
   */
  @Test
  public void bellJakeTest02() throws NullKeyException {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("Baby", "Keem");
    try {
      assertEquals("Keem", arr.get("Baby"));
    } catch (Exception e) {
      fail("Array does not contain expected value bellJakeTest02");
    }
    arr.set("Baby", "Lil");
    try {
      assertEquals("Lil", arr.get("Baby"));
    } catch (Exception e) {
      fail("Array does not contain expected value bellJakeTest02");
    }
    arr.set("Baby", "Shark");
    try {
      assertEquals("Shark", arr.get("Baby"));
    } catch (Exception e) {
      fail("Array does not contain expected value bellJakeTest02");
    } // try/catch
    arr.remove("Baby");

    try {
      // The following line should throw an exception
      arr.get("Baby");
      fail("Failed bellJakeTest02");
    } catch (KeyNotFoundException e) {
      // Do nothing
    } // try/catch
  } // bellJakeTest02

  /**
   * Does a cloned array expand if the original array is greater than 16.
   * 
   * @throws NullKeyException
   */
  @Test
  public void bellJakeEdge01() throws NullKeyException {
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    for (int i = 1; i < 20; i++) {
      arr.set("key" + i, "val" + i);
    } // for
    AssociativeArray<String, String> clonedArr = arr.clone();
    try {
      for (int i = 19; i >= 1; i--) {
        assertEquals("val" + i, clonedArr.get("key" + i));
      } // for
    } catch (Exception e) {
      fail("Exception in call to get bellJakeEdge01");
    } // try/catch
  } // bellJakeEdge01

  // +--------------------------+------------------------------------
  // | Tests by Blanchard, Lily |
  // +--------------------------+

  // +--------------------------------+------------------------------
  // | Tests by Bohrer-Purnell, Myles |
  // +--------------------------------+

  // +-------------------------------+-------------------------------
  // | Tests by Castleberry, Anthony |
  // +-------------------------------+

  // +------------------------+--------------------------------------
  // | Tests by Cyphers, Alex |
  // +------------------------+

  // +---------------------------+-----------------------------------
  // | Tests by Deschamps, Sarah |
  // +---------------------------+

  // +--------------------+------------------------------------------
  // | Tests by Do, Khanh |
  // +--------------------+

  // +----------------------+----------------------------------------
  // | Tests by Fargo, Drew |
  // +----------------------+

  // +--------------------------+------------------------------------
  // | Tests by Gijima, Garikai |
  // +--------------------------+
  /**
   * Can we successfully set the same key to multiple values and get the last one? 
   */
  @Test
  public void garikaiGijimaTest01() {
    AssociativeArray<Integer, Integer> arr = 
        new AssociativeArray<Integer, Integer>();

    // Add a bunch of values
    for (int i = 0; i < 150; i++) {
      try {
        arr.set(5, i * i);
      } catch (Exception e) {
        fail("Failed to set 5 to " + i*i);
      } // try/catch
    } // for

    try {
      assertEquals(149*149, arr.get(5));
    } catch (KeyNotFoundException e) {
      fail("Failed to set to 5");
    }
  } // garikaiGijimaTest01()

  /**
   * Set multiple keys to the same value and return them 
   */
  @Test
  public void garikaiGijimaTest02() {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    // Add a bunch of values
    for (int i = 0; i < 150; i++) {
      try {
        arr.set(i, 5);
      } catch (Exception e) {
        fail("Failed to set " + i + " to 5");
      } // try/catch
    } // for
    for (int i = 0; i < 150; i++) {
      try {
        assertEquals(5, arr.get(i), "Element " + i + " is not 5");
      } catch (Exception e) {
        fail("get(" + i + ") failed");
      } // try catch
    } // for
  } // garikaiGijimaTest02()

  /**
   * Clone an array
   */
  @Test
  public void garikaiGijimaTest03() {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    // Add a bunch of values
    for (int i = 0; i < 150; i++) {
      try {
        arr.set(i, i*i);
      } catch (Exception e) {
        fail("Could not call arr.set(" + i + ", " + i*i + ")");
      } // try/catch
    } // for

    // make sure size is as intended
    assertEquals(150, arr.size());
    

    // make clone and check clone size
    AssociativeArray<Integer, Integer> arrClone = arr.clone();
    assertEquals(arr.size(), arrClone.size());
    
    // check all values to make sure they match
    for (int i = 149; i > -1; i--) {
      try {
        assertEquals(arr.get(i), arrClone.get(i), 
            "Comparing elements with key " + i);
       } catch (Exception e) {
         fail("Failed to get element " + i + " from one of the dicts.");
       } // try/catch
    } // for
  } // garikaiGijimaTest03()

  /**
   * Can we set a key to have a value of null and retrieve it? 
   */
  @Test
  public void garikaiGijimaEdge01() {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    try {
      arr.set(1, null);
    } catch (Exception e) {
      fail("Could not call arr.set(1, null)");
    } // try/catch
    try {
      assertEquals(null, arr.get(1), "Comparing element 1 to null");
    } catch (KeyNotFoundException e) { 
      fail("Failed to get the value for 1");
    } // try/catch
  } // garikaiGijimaEdge01()

  /**
   * Does an invalid get/set throw exceptions? 
   */
  @Test
  public void garikaiGijimaEdge02() {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    assertThrows(Exception.class,
        () -> arr.get(5),
        "Get from empty array");
    assertThrows(Exception.class,
        () -> arr.set(null, 6),
        "Null key");
  } // garikaiGijimaEdge02()

  // +-----------------------+---------------------------------------
  // | Tests by Goldman, Leo |
  // +-----------------------+
  
  /**
   * Check general functionality of size(), hasKey(), set(), get(), and if AA expands properly.
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void goldmanLeoTest1() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, Integer> arr = new AssociativeArray<String, Integer>();
    for (int i = 0; i < 17; i++) {
      String str = new String("" + i);
      arr.set(str, i);
      assertEquals(i + 1, arr.size()); // check if size method is accurate
      assertEquals(true, arr.hasKey(str)); // check if key exists
      assertEquals(i, arr.get(str)); // check if key contains correct value
    } // add 17 entries to arr

    for (int i = 0; i < 17; i++) {
      String str = new String("" + i);
      arr.remove(str);
      assertEquals(16 - i, arr.size()); // check if size method is accurate
      assertEquals(false, arr.hasKey(str)); // check if key does not exist
      try {
        arr.get(str); // check if getting the key fails
        fail("Did not throw an exception");
      } catch (Exception e) {
        // Do nothing
      }
    } // remove 17 entries from arr

  } // Test1

  /**
   * Check if clone() creates a functionally identical copy.
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void goldmanLeoTest2() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<Integer, Integer> arr = new AssociativeArray<Integer, Integer>();
    for (int i = 0; i < 17; i++) {
      arr.set(i, i);
    } // add 17 entries to arr
    AssociativeArray<Integer, Integer> newArr = arr.clone();
    assertEquals(arr.size(), newArr.size());
    for (int i = 0; i < 17; i++) {
      assertEquals(arr.get(i), newArr.get(i));
    }
  } // Test2

  // @Test
  // public void goldmanLeoEdge1() throws NullKeyException, KeyNotFoundException {
  //   AssociativeArray<Integer[], String> arr = new AssociativeArray<Integer[], String>();
  //   Integer[] intArray = new Integer[1];
  //   arr.set(intArray, null);
  //   arr.get(intArray);
    
  // }

  // +--------------------------+------------------------------------
  // | Tests by Gorrell, Nicole |
  // +--------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Houck, Paden |
  // +-----------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Jaljaa, Sara |
  // +-----------------------+

  // +------------------------+--------------------------------------
  // | Tests by Jarrar, Jafar |
  // +------------------------+

  // +-------------------------+-------------------------------------
  // | Tests by Johnston, Cade |
  // +-------------------------+

  // +---------------------+-----------------------------------------
  // | Tests by Karki, Sal |
  // +---------------------+

  // +------------------+--------------------------------------------
  // | Tests by Kim, SJ |
  // +------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Lin, Richard |
  // +-----------------------+

  // +----------------------+----------------------------------------
  // | Tests by Lopez, Luis |
  // +----------------------+

  // +----------------------+----------------------------------------
  // | Tests by Malik, Yash |
  // +----------------------+

  // +---------------------------+-----------------------------------
  // | Tests by Manza, Sebastian |
  // +---------------------------+

  // +--------------------------+------------------------------------
  // | RTests by Milenge, Moise |
  // +--------------------------+

  // +------------------------+--------------------------------------
  // | Tests by Moreno, Nicky |
  // +------------------------+

  /**
   * A simple test for setting and getting values.
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void morenoNickyTest01() throws KeyNotFoundException, NullKeyException {
    AssociativeArray<String, String> map = new AssociativeArray<String, String>();
    map.set("Hello", "World");
    assertEquals("World", map.get("Hello"), "We can get what we just set.");
  } // morenoNickyTest01()

  /**
   * We can update a value with a duplicate key.
   * @throws NullKeyException
   */
  @Test
  public void morenoNickyTest02() throws NullKeyException {
    AssociativeArray<String, String> map = new AssociativeArray<String, String>();
    map.set("key", "value1");
    map.set("key", "value2");
    try {
      assertEquals("value2", map.get("key"), "Value should be updated to 'value2'.");
    } catch (Exception e) {
      fail("Unexpected exception");
    } // try/catch
    assertEquals(1, map.size(), "Size should remain 1 after updating duplicate key.");
  } // morenoNickyTest02()

  /**
   * Checks behavior when setting a null value for an existing key.
   * @throws NullKeyException
   */
  @Test
  public void morenoNickyEdge01() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, Integer> map = new AssociativeArray<String, Integer>();
    map.set("one", 1);
    map.set("one", null);  // Updating existing key with null value
    assertNull(map.get("one"), " Value for key 'one' should be null after update.");
    assertTrue(map.hasKey("one"), " The key 'one' should still exist after setting null value.");
  } // morenoNickyEdge01()

  // +-----------------------------+---------------------------------
  // | Tests by Muligande, Sheilla |
  // +-----------------------------+

  // +---------------------------+-----------------------------------
  // | Tests by Nardone, Natalie |
  // +---------------------------+

  // +---------------------+-----------------------------------------
  // | Tests by Nunes, Leo |
  // +---------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Paiva, Mitch |
  // +-----------------------+

  // +------------------------+--------------------------------------
  // | Tests by Pollock, Alex |
  // +------------------------+

  /**
   * Can we add an element, check that it's there, remove it and then check for it again.
   * 
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void pollockAlexTest1() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<Integer, String> testArray = new AssociativeArray<Integer, String>();
    testArray.set(1, "Hello");
    assertEquals(true, testArray.hasKey(1), "Does the key '1' exist?");
    assertEquals("Hello", testArray.get(1), "What is at the key '1'?");
    testArray.remove(1);
    assertEquals(false, testArray.hasKey(1), "The key should no longer exist.");
    try {
      testArray.get(1);
      System.err.println("This key shouldn't exist.");
    } catch (KeyNotFoundException e) {
      // This is supposed to happen
    } // try/catch
  } // pollockAlexTest1()

  /**
   * Try removing non-existant elements. Then see if changing keys works as well as size. Then try
   * removing a valid element.
   * 
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void pollockAlexTest2() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, String> testArray2 = new AssociativeArray<String, String>();
    testArray2.remove("Hello");
    assertEquals(0, testArray2.size(), "There should be nothing in the array.");
    testArray2.set("Hello", "World");
    testArray2.set("Hello", "Samuel");
    assertEquals("Samuel", testArray2.get("Hello"), "Test to see if 'set' can change the value of pairs.");
    assertEquals(1, testArray2.size());
    testArray2.remove("Hello");
    assertEquals(0, testArray2.size());
    try {
      testArray2.get("Hello");
      System.err.println("Error: Value should not exist");
    } catch (KeyNotFoundException e) {
      // Supposed to happen
    } // try/catch
  } // pollockAlexTest2()

  /**
   * Check if you can properly remove the only value in a list. Certain implementations will fail.
   * 
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void pollockAlexEdge1() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, String> edgeArray1 = new AssociativeArray<String, String>();
    edgeArray1.set("Edge", "Cases");
    edgeArray1.remove("Edge");
    try {
      edgeArray1.get("Edge");
      System.err.println("Error: Failed pollockAlexEdge1");
    } catch (KeyNotFoundException e) {
      // Supposed to happen
    } // try/catch
  } // pollockAlexEdge1()

  // +----------------------------+----------------------------------
  // | Tests by Rajbhandari, Slok |
  // +----------------------------+

  // +---------------------------+-----------------------------------
  // | Tests by Rodriguez, Maria |
  // +---------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Ryan, Alyssa |
  // +-----------------------+

  // +--------------------------+------------------------------------
  // | Tests by Sackmann, Grant |
  // +--------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Schmidt, Sam |
  // +-----------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Sheeley, Ben |
  // +-----------------------+

  // +-------------------------+-------------------------------------
  // | Tests by Silva, Jenifer |
  // +-------------------------+

  // +------------------------+--------------------------------------
  // | Tests by Stroud, David |
  // +------------------------+

  /**
   * This test checks whether the .remove method works as expected and does not cause issues with the internal state of the object.
   *
   * @throws NullKeyException This exception is thrown if any of the keys used are null. This should not happen.
   * @throws KeyNotFoundException This exception is thrown if one of the keys used is missing. This indicates a failing test.
   */
  @Test
  public void stroudDavidTestRemove() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<String, String> assocArr = new AssociativeArray<>();

    assocArr.set("hello", "world");
    assocArr.set("good", "morning");
    assocArr.set("amazing", "afternoon");
    assocArr.set("frabjous", "day");

    assocArr.remove("amazing");
    assertEquals("day", assocArr.get("frabjous"), "Key 'frabjous' should not be affected by removal of 'amazing'");
    assertEquals("morning", assocArr.get("good"), "Key 'good' should not be affected by removal of 'amazing'");

    assocArr.set("good", "evening");
    assertEquals("evening", assocArr.get("good"), "Setting 'good' should work after removal of 'amazing'");

    assocArr.set("frabjous", "the jabberwocky");
    assertEquals("the jabberwocky", assocArr.get("frabjous"), "Setting 'frabjous' should work after removal of 'amazing'");

    assocArr.remove("good");
    assertFalse(assocArr.hasKey("good"), "Removing 'good' should continue to work after removing 'amazing' and setting 'good'");
  } // stroudDavidTestRemove()

  /**
   * This test checks whether toString displays both keys and values.
   *
   * Sam notes that in order to pass this test, you may need to fetch 
   * from upstream to get an updated version of KVPair.
   *
   * @throws NullKeyException This exception is thrown if any of the keys used are null. This should not happen.
   */
  @Test
  public void stroudDavidTestToString() throws NullKeyException {
    AssociativeArray<String, String> assocArr = new AssociativeArray<>();

    String[] literals = new String[] {"frabjous1337-2448", "the-jAbBeRwoCKy!!!_3559-4660", "The-Key HAS SPACES 4nd NumB3rS", "key(value, \"result\")", "/VALUE/=null"};
    assocArr.set(literals[0], literals[1]);
    assocArr.set(literals[2], literals[3]);
    assocArr.set("I will never", "see the light of day.");
    assocArr.set(literals[4], null);

    assocArr.remove("I will never");

    String stringified = assocArr.toString();
    for (String literal : literals) {
      assertTrue(stringified.contains(literal), "String '" + literal + "' should be contained in .toString() result");
    } // for
  } // stroudDavidTestToString()

  /**
   * This test checks whether the associative array is expandable.
   * @throws NullKeyException This exception is thrown if any of the keys used are null. This should not happen.
   * @throws KeyNotFoundException This exception is thrown if any of the keys used do not exist. This indicates a failing test.
   */
  @Test
  public void stroudDavidTestExpandability() throws NullKeyException, KeyNotFoundException {
    AssociativeArray<Integer, Integer> assocArr = new AssociativeArray<>();

    for (int i = 0; i < 1337; i++) {
      assocArr.set(Integer.valueOf(i), Integer.valueOf(i + 1));
    } // for

    for (int j = 0; j < 1337; j++) {
      assertTrue(assocArr.hasKey(Integer.valueOf(j)), "Key '" + j + "' should exist in the associative array");
      assertEquals(j + 1, assocArr.get(Integer.valueOf(j)), "Key '" + j + "' should contain value '" + (j + 1) + "'");
    } // for
  } // stroudDavidTestExpandability()

  // +----------------------+----------------------------------------
  // | Tests by Tang, Kevin |
  // +----------------------+
  
  /**
   * Check general functionality of set() and hasKey().
   * @throws NullKeyException
   */
  @Test
  public void kevinTangTest1() throws NullKeyException {
    AssociativeArray<String, Integer> testArray = new AssociativeArray<String, Integer>();
    testArray.set("mango", 1);
    assertEquals(true, testArray.hasKey("mango"), "setted key should be found");
    assertEquals(false, testArray.hasKey("Mango"), "edge case of key");
    testArray.remove("mango");
    assertFalse(testArray.hasKey("Mango"), "removed key couldn't be found");
  } //kevinTangTest1

  /**
   * Check general functionality of set() and hasKey().
   * @throws NullKeyException
   */
  @Test
  public void kevinTangTest2() throws NullKeyException {
    AssociativeArray<Character, Integer> testArray = new AssociativeArray<Character, Integer>();
    testArray.set('a', 65);
    assertTrue(testArray.hasKey('a'), "setted key should be found");
    assertFalse(testArray.hasKey('A'), "edge case of key");
    testArray.remove('a');
    assertFalse(testArray.hasKey('a'), "removed key couldn't be found");
  } //kevinTangTest2

  /**
   * Check general functionality of repeated set() and hasKey().
   * @throws NullKeyException
   */
  @Test
  public void kevinTangEdge() throws NullKeyException {
    AssociativeArray<Boolean, Integer> testArray = new AssociativeArray<Boolean, Integer>();
    testArray.set(true, 1);
    testArray.set(true, 1);
    assertTrue(testArray.hasKey(true), "setted key should be found");
    assertFalse(testArray.hasKey(false), "edge case of key");
    testArray.remove(true);
    assertFalse(testArray.hasKey(true), "removed key couldn't be found");
  } //kevinTangEdge

  // +------------------------+--------------------------------------
  // | Tests by Tang, Tiffany |
  // +------------------------+

  // +----------------------+----------------------------------------
  // | Tests by Trimble, AJ |
  // +----------------------+

  // +-------------------------+-------------------------------------
  // | Tests by Tsymbal, Koast |
  // +-------------------------+

  // +------------------------+--------------------------------------
  // | Tests by Vadillo, Jana |
  // +------------------------+

  // +-----------------------+---------------------------------------
  // | Tests by Yan, Tiffany |
  // +-----------------------+
   /**
   * A test for the remove method. We will do this by first add a key-value pair, then remove it.
   * After that we will check if we could still get the removed key-value pair.
   * 
   * @throws NullKeyException
   */
  @Test
  public void TiffanyYan01() throws NullKeyException {
    AssociativeArray<Integer, String> testarr = new AssociativeArray<Integer, String>();
    testarr.set(1, "Tiffany");
    // Make sure that the value has been set.
    try {
      assertEquals("Tiffany", testarr.get(1));
    } catch (KeyNotFoundException e) {
      fail("Could not set 1 to Tiffany");
    } // try/catch
    // Test for remove, where we would expect the following line to throw an expection
    testarr.remove(1);
    try {
      testarr.get(1);
      fail("Did not throw an exception");
    } catch (KeyNotFoundException e) {
      // Do nothing
    } // try/catch
  } // TiffanyYan01()

  /**
   * A test for the clone method. We will do this by first add a key-value pair to an initial
   * associate array. we will then clone the initial associate array. After that we will check if we
   * could find the key-value pair at the cloned array.
   * 
   * @throws NullKeyException
   */
  @Test
  public void TiffanyYan02() throws NullKeyException {
    AssociativeArray<String, Double> testarr = new AssociativeArray<String, Double>();
    testarr.set("Pi", 3.1415926535);

    // Make sure that the value has been set.
    try {
      assertEquals(3.1415926535, testarr.get("Pi"));
    } catch (KeyNotFoundException e) {
      fail("Could not set Pi to 3.1415926535");
    } // try/catch
    AssociativeArray<String, Double> clonearr = testarr.clone();
    // Make sure that the cloned array has been set and have the same pair as the original
    try {
      assertEquals(3.1415926535, clonearr.get("Pi"));
    } catch (KeyNotFoundException e) {
      fail("Could not clone testarr to clonearr");
    } // try/catch
  } // TiffanyYan02()

  /**
   * A test expand method. We will check if the associate array will expand, given the number of key
   * value pair is greater than 16
   * 
   * @throws NullKeyException
   */
  @Test
  public void TiffanyYanEdge01() throws NullKeyException {
    AssociativeArray<Double, Double> testarr = new AssociativeArray<Double, Double>();
    for (int i = 0; i < 18; i++) {
      testarr.set(1.23 + i, 1.23 + i);
    } // for

    try {
      for (int i = 17; i >= 1; i--) {
        assertEquals(1.23 + i, testarr.get(1.23 + i));
      } // for
    } catch (Exception e) {
      fail("Expansion of the array failed");
    } // try/catch
  } // TiffanyYanEdge01()

  // +------------------------+--------------------------------------
  // | Tests by Yusuf, Bonsen |
  // +------------------------+

  // +------------------------+--------------------------------------
  // | Tests by Zhu, Harrison |
  // +------------------------+

  /**
   * Tests adding and deleting. Tests if toString handles null values.
   */
  @Test
  public void zhuHarrisonTestAddingDeleting() throws Exception{ 
    AssociativeArray<String, String> arr = new AssociativeArray<String, String>();
    arr.set("a", "apple");
    arr.set("b", null);
    try {
      arr.toString();
    } catch (Exception e) {
      fail("To string should not throw exception");
    } // try/catch toString

    arr.remove("b");
    try { 
      // remove non-existed keys
      arr.remove("b");
    } catch (Exception e) { 
      fail("remove should not throw exception");
    } // try/catch remove

    assertEquals(1, arr.size());

    arr.set("b", "HIII");
    assertEquals(2, arr.size());
    assertEquals("HIII", arr.get("b"));

  }

  /**
   * Test to see if associative arrays are correctly cloned
   * @throws Exception
   */
  @Test 
  public void zhuHarrisonTestClone() throws Exception { 
    AssociativeArray<Integer, Boolean> arr = new AssociativeArray<Integer, Boolean>();
    for(int i = 0; i < 128; ++i) { 
      arr.set(Integer.valueOf(i), false);
    } // fill values
    assertEquals(128, arr.size());
    AssociativeArray<Integer, Boolean> arr1 = arr.clone();
    arr.set(1, true);
    assertEquals(128, arr1.size());
    //test cloning
    assertNotEquals(arr.get(1), arr1.get(1));
    arr.remove(1);
    assertNotEquals(arr.size(), arr1.size());
  }

  /**
   * Test to see if exceptions are thrown correctly. 
   * @throws Exception
   */
  @Test
  public void zhuHarrisonTestExceptions() throws Exception { 
    AssociativeArray<Integer, Boolean> arr = new AssociativeArray<Integer, Boolean>();
    for(int i = 0; i < 128; ++i) { 
      arr.set(Integer.valueOf(i), false);
    } // add some values

    try {
      arr.get(129);
      fail("should throw KeyNotFoundException");
    } catch (KeyNotFoundException e) {

    } // try/catch

    try {
      arr.set(null, false);
      fail("should throw NullKeyException");
    } catch (NullKeyException e) {

    } // try/catch
  } // znuHarrisonTestExceptions()

} // class TestAssociativeArrays
