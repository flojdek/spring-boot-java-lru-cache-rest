package io.yisland.lrucache.util;

import java.util.Optional;

public class Util {

  public static <T> boolean exists(Optional<T> o, T v) {
    return o.map(v::equals).orElse(false);
  }

}
