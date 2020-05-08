package io.yisland.lrucache.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CacheMissException extends RuntimeException {
  public CacheMissException(String k) {
    super("cache-miss: key='" + k + "'");
  }
}
