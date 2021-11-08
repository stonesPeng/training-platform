/*
 *     Copyright (c) 2021.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: DelegateFactory.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2021-01-10 21:26:31
 */

package me.stone.training.platform.training.common.tool.utils;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代理生成接口容器,用于内部调用快速实现接口DTO
 *
 * @author Zen Liu
 * @version 1.1.1
 * @apiNote DelegateFactory
 * @since 2021-01-05 16:48:35
 * @deprecated will build a new one next version
 **/
@Deprecated
public interface DelegateFactory<I> {
    /**
     * 按字段->值创建代理对象: 注意字段首字母大写!
     *
     * @param values 要赋予的字段和值
     * @return 代理对象
     */
    I build(Map<String, Object> values);

    /**
     * 按值创建代理对象,必须是所有的字段,按定义顺序
     *
     * @param values 值
     * @return
     */
    I build(Object... values);

    static <T> DelegateFactory<T> delegate(Class<T> type) {
        return new DelegateFactoryImpl<>(type);
    }

    final class DelegateFactoryImpl<E> implements DelegateFactory<E> {
        private Constructor<MethodHandles.Lookup> constructor;
        private final Class<E> type;
        private final List<String> fields;

        DelegateFactoryImpl(Class<E> type) {
            this.type = type;
            this.fields = Seq.of(type.getMethods())
                .filter(x ->
                    Modifier.isPublic(x.getModifiers())
                        && !Modifier.isStatic(x.getModifiers())
                        && (
                        x.getName().startsWith("get") || x.getName().startsWith("is")
                    )
                ).map(x -> {
                    final String name = x.getName();
                    if (name.startsWith("is")) return name.substring(2);
                    else return name.substring(3);
                }).toList();
        }

        @Override
        @SuppressWarnings("unchecked")
        public E build(Map<String, Object> values) {
            final Object[] result = new Object[1];
            final Map<String, Object> map = new HashMap<>(values == null ? Collections.emptyMap() : values);
            final InvocationHandler handler = new InvocationHandler() {
                @SuppressWarnings("null")
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) {
                    String name = method.getName();
                    int length = (args == null ? 0 : args.length);
                    if (length == 0 && name.startsWith("get"))
                        return map.get(name.substring(3));
                    else if (length == 0 && name.startsWith("is"))
                        return map.get(name.substring(2));
                    else if (length == 1 && name.startsWith("set"))
                        map.put(name.substring(3), args[0]);
                        // [#5442] Default methods should be invoked to run client implementation
                    else if (method.isDefault())
                        try {
                            if (constructor == null)
                                constructor = accessible(MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class));
                            Class<?> declaringClass = method.getDeclaringClass();
                            return constructor
                                .newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
                                .unreflectSpecial(method, declaringClass)
                                .bindTo(result[0])
                                .invokeWithArguments(args);
                        } catch (Throwable e) {
                            throw new IllegalStateException("Cannot invoke default method", e);
                        }
                    else if (length == 0 && name.equals("toString")) return type.getName() + "{" + map.toString() + "}";
                    return null;
                }
            };
            result[0] = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, handler);
            return (E) result[0];
        }

        @Override
        public E build(Object... values) {
            if (fields.size() != values.length) throw new IllegalArgumentException("value not match getter size!");
            final Map<String, Object> val = Seq.seq(fields).zipWithIndex()
                .map(x -> x.map2(i -> values[Math.toIntExact(i)]))
                .toMap(Tuple2::v1, Tuple2::v2);
            return build(val);
        }

        static <T extends AccessibleObject> T accessible(T accessible) {
            if (accessible == null) {
                return null;
            }

            if (accessible instanceof Member) {
                Member member = (Member) accessible;

                if (Modifier.isPublic(member.getModifiers()) &&
                    Modifier.isPublic(member.getDeclaringClass().getModifiers())) {
                    return accessible;
                }
            }

            // [jOOQ #3392] The accessible flag is set to false by default, also for public members.
            if (!accessible.isAccessible())
                accessible.setAccessible(true);
            return accessible;
        }
    }


}
