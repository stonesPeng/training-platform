package me.stone.training.platform.training.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/26
 */
public enum SingletonInEnum {

    INSTANCE;

    private Object data;

    public static SingletonInEnum getInstance() {
        return INSTANCE;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        final Class<SingletonInEnum> singletonInEnumClass = SingletonInEnum.class;
        final Constructor<SingletonInEnum> declaredConstructor = singletonInEnumClass.getDeclaredConstructor();
        declaredConstructor.newInstance();

//        SingletonInEnum instance1;
//        SingletonInEnum instance2 = SingletonInEnum.getInstance();
//        instance2.setData(new Object());
//        try(
//             FileOutputStream fos = new FileOutputStream("out.se");
//             ObjectOutputStream oos = new ObjectOutputStream(fos);
//             FileInputStream fis = new FileInputStream("out.se");
//             ObjectInputStream ois = new ObjectInputStream(fis);
//            ){
//            oos.writeObject(instance2);
//            oos.flush();
//            instance1 = (SingletonInEnum)ois.readObject();
//            System.out.println(instance1.getData());
//            System.out.println(instance2.getData());
//            System.out.println(instance1.getData() == instance2.getData());
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

}
