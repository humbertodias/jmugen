package org.lee.mugen.lang;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.lee.mugen.imageIO.PCXLoader;
import org.lee.mugen.imageIO.RawPCXImage;
import org.lee.mugen.object.RawImage;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;

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

    private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		
	}
    
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		
	}
}