package org.shared.library.impl;

import org.shared.library.SharedService;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedServiceImpl implements SharedService {
  final static AtomicInteger instanceCount = new AtomicInteger(0);

  public SharedServiceImpl() {
    instanceCount.incrementAndGet();
  }

  @Override
  public String getHello(final String context) {
    return String.format("Hello from %s! Instances: %d", context, instanceCount.get());
  }
}
