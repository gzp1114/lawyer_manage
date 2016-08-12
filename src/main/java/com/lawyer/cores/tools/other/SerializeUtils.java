package com.lawyer.cores.tools.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeUtils {
	/**
     * 序列化
     * 
     * @param object
     * @return
     */
    public static String serialize(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            String str = new String(bytes);
            return str;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static byte[] serializeByte(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * 反序列化
     * 
     * @param bytes
     * @return
     */
    public static Object deserialize(String bytes)
    {
        ByteArrayInputStream bais = null;
        try
        {
            // 反序列化
            bais = new ByteArrayInputStream(bytes.getBytes());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object deserialize(byte[] bytes)
    {
        ByteArrayInputStream bais = null;
        try
        {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
