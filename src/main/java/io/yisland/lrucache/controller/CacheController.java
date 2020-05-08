package io.yisland.lrucache.controller;

import io.yisland.lrucache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

  // PUBLIC

  @PutMapping("/cache/keys/{k}/values/{v}")
  public ResponseEntity<?> get(@PathVariable String k, @PathVariable String v) {
    cacheService.put(k, v);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/cache/keys/{k}")
  public ResponseEntity<?> get(@PathVariable String k) {
    return ResponseEntity.ok(cacheService.get(k));
  }

  @GetMapping("/cache")
  public ResponseEntity<?> get() {
    return ResponseEntity.ok(cacheService.cacheToString());
  }

  // PRIVATE

  @Autowired
  private CacheService cacheService;

}
