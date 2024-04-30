package controller;

import java.io.IOException;

/**
 * A Controller.CorruptAppendable implementation that always throws IOExceptions to test
 * if IOExceptions are handled correctly.
 */
public class CorruptAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Controller.CorruptAppendable");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Controller.CorruptAppendable");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Controller.CorruptAppendable");
  }
}
