package com.mozilla.fire.common;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by mozilla on 2017/12/12.
 */
public class Hessian4Serializable{

    public static byte[] encode(Object obj) {

        if(obj != null) {
            if (obj.getClass() == short.class || obj.getClass() == Short.class) {
                obj = new ShortRestore(Short.parseShort(String.valueOf(obj)));
            }

            if (obj.getClass() == byte.class || obj.getClass() == Byte.class) {
                obj = new ByteRestore(Byte.parseByte(String.valueOf(obj)));
            }

            if (obj.getClass() == char.class || obj.getClass() == Character.class) {
                obj = new CharRestore((char) obj);
            }

            if (obj.getClass() == float.class || obj.getClass() == Float.class) {
                obj = new FloatRestore(Float.parseFloat(String.valueOf(obj)));
            }
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    public static Object decode(byte[] bytes) {
        if (bytes == null)
            throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Object object = null;
        try {
            HessianInput hi = new HessianInput(is);
            object = hi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(object != null){
            if(object.getClass() == ShortRestore.class){
                return ((ShortRestore)object).getValue();
            }

            if(object.getClass() == ByteRestore.class){
                return ((ByteRestore)object).getValue();
            }

            if(object.getClass() == CharRestore.class){
                return ((CharRestore)object).getValue();
            }

            if(object.getClass() == FloatRestore.class){
                return ((FloatRestore)object).getValue();
            }
        }

        return object;
    }

    public static void main(String[] args) {
        byte[] array = encode(null);
        System.out.println(array);

        System.out.println(decode(array));
    }
}

class ShortRestore implements Serializable {
    private short value;
    public ShortRestore(short value){
        this.value = value;
    }
    public short getValue() {
        return value;
    }
}

class ByteRestore implements Serializable{
    private byte value;
    public ByteRestore(byte value){
        this.value = value;
    }
    public byte getValue() {
        return value;
    }
}

class CharRestore implements Serializable{
    private char value;
    public CharRestore(char value){
        this.value = value;
    }
    public char getValue() {
        return value;
    }
}

class FloatRestore implements Serializable{
    private float value;
    public FloatRestore(float value){
        this.value = value;
    }
    public float getValue() {
        return value;
    }
}
