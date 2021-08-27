package me.stone.training.platform.training.pattern.singleton;

import java.io.*;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/27
 */

public class SerializableDestroySingleton implements Serializable {

    private static SerializableDestroySingleton INSTANCE = new SerializableDestroySingleton();

    private SerializableDestroySingleton() {
        if (INSTANCE != null) {
            throw new AssertionError();
        }
        INSTANCE = this;
    }

    public static SerializableDestroySingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        SerializableDestroySingleton instance1 = null;
        SerializableDestroySingleton instance2 = SerializableDestroySingleton.getInstance();
        try (FileOutputStream fos = new FileOutputStream("out.se");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             FileInputStream fis = new FileInputStream("out.se");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            oos.writeObject(instance2);
            oos.flush();
            instance1 = (SerializableDestroySingleton) ois.readObject();
            System.out.println(instance1);
            System.out.println(instance2);
            System.out.println(instance1 == instance2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
