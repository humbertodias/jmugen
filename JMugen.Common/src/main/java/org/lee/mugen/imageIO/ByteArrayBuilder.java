package org.lee.mugen.imageIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A utility set which will build byte arrays from specified data types.
 *
 * @author Kevin Glass
 */
public class ByteArrayBuilder {

    /**
     * A static utility to completly read an input stream and 
     * produce a byte array of the read data
     */
    public static byte[] fromStream(InputStream in) throws IOException {
        ByteArrayOutputStream store = new ByteArrayOutputStream();
        
        InputStreamReader reader = new InputStreamReader(in);
        byte[] bData = new byte[10000];
        int read = 0;
        
        while (reader.ready()) {
            read = in.read(bData);
            
            if (read > 0) {
                store.write(bData,0,read);
            } else {
                break;
            }
        }
       
        return store.toByteArray();
    }

}
