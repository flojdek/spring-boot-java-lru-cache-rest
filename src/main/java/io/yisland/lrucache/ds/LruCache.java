package io.yisland.lrucache.ds;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class LruCache<K, V> implements Cache<K, V> {

  // PUBLIC

  public LruCache() {
    head.next = tail;
    head.prev = null;
    tail.next = null;
    tail.prev = head;
  }

  public LruCache(int capacity) {
    this();
    if (capacity < 1)
      throw new IllegalArgumentException("capacity='" + capacity + "'");
    this.capacity = capacity;
  }

  @Override
  public void put(K k, V v) {
    Node newFrontNode = new Node(k, v);
    if (keyToNode.containsKey(k)) {
      delete(k, keyToNode.get(k).next);
    }
    addInFront(k, newFrontNode);
    if (keyToNode.size() > capacity) {
      delete(tail.prev.k, tail);
    }
  }

  @Override
  public Optional<V> get(K k) {
    return Optional.ofNullable(keyToNode.get(k)).map(x -> x.v);
  }

  @Override
  public String toString() {
    Node it = head;
    List<V> values = new ArrayList<>();
    while (it != null) {
      if (it.v != null) values.add(it.v);
      it = it.next;
    }
    return values.stream().map(Object::toString).collect(joining(","));
  }

  // PRIVATE

  private void addInFront(K k, Node newFrontNode) {
    keyToNode.put(k, newFrontNode);
    Node curFrontNode = head.next;
    newFrontNode.next = curFrontNode;
    head.next = newFrontNode;
    newFrontNode.prev = head;
    curFrontNode.prev = newFrontNode;
  }

  private void delete(K k, Node nextToTheOneAboutToBeDeleted) {
    Node delNode = keyToNode.get(k);
    delNode.prev.next = nextToTheOneAboutToBeDeleted;
    nextToTheOneAboutToBeDeleted.prev = delNode.prev;
    delNode.prev = null;
    delNode.next = null;
    keyToNode.remove(delNode.k);
  }

  @Data
  private class Node {
    private final K k;
    private final V v;
    private Node prev = null;
    private Node next = null;
  }

  private Node head = new Node(null, null);
  private Node tail = new Node(null, null);
  private HashMap<K, Node> keyToNode = new HashMap<>();

  private int capacity = 2;

}
