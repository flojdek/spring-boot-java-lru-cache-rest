package io.yisland.lrucache.ds;

import java.util.Optional;

public interface Cache<K, V> {

  public void put(K k, V v);

  public Optional<V> get(K k);

}
