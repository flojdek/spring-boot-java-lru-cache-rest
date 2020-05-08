package io.yisland.lrucache.service;

import io.yisland.lrucache.datastructure.Cache;
import io.yisland.lrucache.datastructure.LruCache;
import io.yisland.lrucache.exception.CacheMissException;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  // PUBLIC

  public void put(String k, String v) {
    cache.put(k, v);
  }

  public String get(String k) {
    return cache.get(k).orElseThrow(() -> new CacheMissException(k));
  }

  public String cacheToString() {
    return cache.toString();
  }

  // PRIVATE

  private Cache<String, String> cache = new LruCache<>(3);

}
