package io.yisland.lrucache;

import io.yisland.lrucache.datastructure.Cache;
import io.yisland.lrucache.datastructure.LruCache;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LruCacheTest {

  @Test
  public void checkMinCapacity() {
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      Cache<Integer, Integer> cache = new LruCache<>(0);
    });
    assertEquals(e.getMessage(), "capacity='0'");
  }

  @Test
  public void capacityOne() {
    Cache<Integer, Integer> cache = new LruCache<>(1);

    assert(cache.get(1).equals(Optional.empty()));

    cache.put(1, 100);
    assert(cache.get(1).equals(Optional.of(100)));

    cache.put(2, 200);
    assert(cache.get(2).equals(Optional.of(200)));
    assert(cache.get(1).equals(Optional.empty()));

    cache.put(2, 300);
    assert(cache.get(2).equals(Optional.of(300)));
    assert(cache.get(1).equals(Optional.empty()));
  }

  @Test
  public void capacityMoreThanOne() {
    Cache<Integer, Integer> cache = new LruCache<>(2);

    cache.put(1, 100);
    cache.put(2, 200);
    assert(cache.get(1).equals(Optional.of(100)));
    assert(cache.get(2).equals(Optional.of(200)));

    cache.put(3, 300);
    assert(cache.get(3).equals(Optional.of(300)));
    assert(cache.get(2).equals(Optional.of(200)));
    assert(!cache.get(1).isPresent());
  }

  @Test void checkToString() {
    Cache<Integer, Integer> cache = new LruCache<>(2);

    cache.put(1, 100);
    cache.put(2, 200);
    cache.put(3, 300);

    assertEquals(cache.toString(), "LruCache.Node(k=3, v=300),LruCache.Node(k=2, v=200)");
  }

}
