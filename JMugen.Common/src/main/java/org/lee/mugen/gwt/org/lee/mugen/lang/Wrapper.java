package org.lee.mugen.lang;

/** GWT: simple holder without Java serialization. */
public class Wrapper<T> implements Wrap<T> {

    private Object spec;

    public Wrapper() {
    }

    public void setValue(T o) {
        spec = o;
    }

    public T getValue() {
        return (T) spec;
    }
}
