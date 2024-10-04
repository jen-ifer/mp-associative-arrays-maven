package edu.grinnell.csc207;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.NullKeyException;
import edu.grinnell.csc207.util.KeyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * A place for you to put your own tests (beyond the shared repo).
 *
 * @author Jenifer Silva
 */
public class TestsFromStudent {
  /**
   * A simple test.
   */
  @Test
  public void silvaJeniferTest1() throws Exception {
    AssociativeArray<String, String> strArr = new AssociativeArray<String, String>();
    strArr.set("Hi", "Amigo");
    strArr.set("Hola", "Friend");
    assertEquals("Amigo", strArr.get("Hi"), "We can get what we just set");
    assertEquals("Friend", strArr.get("Hola"), "We can get what we set everytime");
  } // alwaysPass()

  /**
   * A simple test.
   */
  @Test
  public void silvaJeniferTest2() throws Exception {
    AssociativeArray<String, String> strShape = new AssociativeArray<String, String>();
    strShape.set("rectangle", "square");
    strShape.set("rectangle", "rhombus");
    assertEquals("rhombus", strShape.get("rectangle"), "We can overwrite previous keys");
  } // alwaysPass()

  /**
   * Edge test.
   */
  @Test
  public void silvaJeniferTestEdge() throws Exception {
    AssociativeArray<String, String> strNull = new AssociativeArray<String, String>();
    strNull.set(":)", "");
    assertEquals("", strNull.get(":)"), "We can set a key that is null");
  } // alwaysPass()
} // class TestsFromStudent


