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
public class ReflectDestroysSingleton {
    public static void main(String[] args) {
        //好事者干的好事
        final Class<UpgradeSingletonInLazy> clazz = UpgradeSingletonInLazy.class;
        //通过反射获取私有构造方法
        final Constructor<UpgradeSingletonInLazy> declaredConstructor;
        try {
            declaredConstructor = clazz.getDeclaredConstructor(null);
            //强制性访问
            declaredConstructor.setAccessible(true);
            //进行两次实例化
            final UpgradeSingletonInLazy instance1 = declaredConstructor.newInstance();
            final UpgradeSingletonInLazy instance2 = declaredConstructor.newInstance();
            System.out.println(instance1 == instance2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
