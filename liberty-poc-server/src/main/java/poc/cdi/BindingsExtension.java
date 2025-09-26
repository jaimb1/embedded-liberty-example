package poc.cdi;

import org.shared.library.SharedService;
import org.shared.library.impl.SharedServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * This would obviously need to be a much richer interface. This is just to illustrate the mechanism.
 */
public final class BindingsExtension {
  private static final Map<Class<?>, Object> instanceMap;

  static {
    instanceMap = new HashMap<>();
    instanceMap.put(SharedService.class, new SharedServiceImpl());
  }

  private BindingsExtension() {
  }

  public static <T> T getInstance(final Class<T> clazz) {
    return (T) instanceMap.get(clazz);
  }
}
