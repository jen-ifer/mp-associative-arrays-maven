package edu.grinnell.csc207.util;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K and values of type V.
 * Associative Arrays store key/value pairs and permit you to look up values by key.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author Jenifer Silva
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V>[] pairs;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({"unchecked"})
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(), DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   *
   * @return a new copy of the array
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> clone = new AssociativeArray<K, V>();
    for (int i = 0; i < size; i++) {
      try {
        clone.set(this.pairs[i].key, this.pairs[i].val);
      } catch (NullKeyException e) {
      } //catch
    } //for
    return clone;
  } // clone()

  /**
   * Convert the array to a string.
   *
   * @return a string of the form "{Key0:Value0, Key1:Value1, ... KeyN:ValueN}"
   */
  public String toString() {
    String array2str = "{";

    for (int q = 0; q < this.size - 1; q++) {
      array2str += pairs[q].key.toString() + ":" + pairs[q].val.toString() + ", ";
    } //for
    array2str += pairs[this.size - 1].key + ":" + pairs[this.size - 1].val;
    array2str += "}";
    return array2str;
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to get(key) will return value.
   *
   * @param key The key whose value we are seeting.
   * @param value The value of that key.
   *
   * @throws NullKeyException If the client provides a null key.
   */
  public void set(K key, V value) throws NullKeyException {
    if (key == null) {
      throw new NullKeyException();
    } //if
    try {
      pairs[find(key)].val = value;
    } catch (KeyNotFoundException e) {
      if (pairs.length <= this.size) {
        this.expand();
      } //if
      this.pairs[this.size] = new KVPair<K, V>(key, value);
      this.size++;
    } //catch
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @param key The key we are looking for
   *
   * @throws KeyNotFoundException when the key is null or does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    if (key == null) {
      throw new KeyNotFoundException();
    } //if
    return pairs[find(key)].val;
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should return false for the null key.
   * @param key The key we are looking for
   */
  public boolean hasKey(K key) {
    if (key == null) {
      return false;
    } //if
    for (int b = 0; b < size; b++) {
      if (pairs[b].key.equals(key)) {
        return true;
      } //if
    } //for
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls to get(key) will throw an
   * exception. If the key does not appear in the associative array, does nothing.
   * @param key The key we are looking to remove
   */
  public void remove(K key) {
    boolean found = false;
    if (size == 0) {
      return;
    } //if
    for (int c = 0; c < size; c++) {
      if (pairs[c].key.equals(key)) {
        pairs[c] = null;
        found = true;
      } //if
    } //for
    if (found) {
      for (int a = 0; a < size - 1; a++) {
        if (pairs[a] == null) {
          pairs[a] = pairs[a + 1];
          pairs[a + 1] = null;
        } //if
      } //for
      this.size = this.size - 1;
    } //if
  } //remove(K)    

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key. If no such entry is found,
   * throws an exception.
   *
   * @param key The key of the entry.
   *
   * @throws KeyNotFoundException If the key does not appear in the associative array.
   */
  int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++) {
      if (key.equals(pairs[i].key)) {
        return i;
      } //if
    } //for
    throw new KeyNotFoundException();
  } // find(K)

} // class AssociativeArray
