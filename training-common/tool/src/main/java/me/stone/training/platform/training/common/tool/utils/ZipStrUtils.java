package me.stone.training.platform.training.common.tool.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;
import me.stone.training.platform.training.java.string.converter.ArraysStringConverter;
import org.jooq.lambda.Sneaky;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/8/3 11:04
 */
public interface ZipStrUtils {
    static byte[] to(Object o) {
        //  synchronized (internal.buffer) {
        Object instance;
        if (o.getClass().getCanonicalName().startsWith("com.medtreehealth.infra.rs.rpc.core")) {
            instance = o;
        } else {
            try {
                Field h = o.getClass().getSuperclass().getDeclaredField("h");
                h.setAccessible(true);
                instance = h.get(o);
            } catch (Exception ex) {
//                if (log.isDebugEnabled()) {
//                    log.warn("error to de proxy of {} ", o, ex);
//                }
                instance = o;
            }
        }

        final Object target = instance;
        final Schema<Object> schema = internal.schemaFrom.apply(target);
        if (schema == null) {
            throw new IllegalStateException("not found schema for type: " + o.getClass());
        }
        try {
            return ProtostuffIOUtil.toByteArray(target, schema, internal.buffer.get());
        } finally {
            internal.buffer.get().clear();
        }
        // }
    }

    public static void main(String[] args) {
        String str = Long.MAX_VALUE + "ee" + Short.MAX_VALUE + "ee" + Long.MAX_VALUE;
        final byte[] to = to(str);
        final String s = ArraysStringConverter.convertByteArrayToHexString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(str.length());
        System.out.println(str);
        System.out.println(s);
        System.out.println(s.length());
    }

    final class internal {
        static final DefaultIdStrategy STRATEGY = new DefaultIdStrategy(
            IdStrategy.DEFAULT_FLAGS
                | IdStrategy.ALLOW_NULL_ARRAY_ELEMENT
                | IdStrategy.MORPH_COLLECTION_INTERFACES
                | IdStrategy.MORPH_MAP_INTERFACES
                | IdStrategy.MORPH_NON_FINAL_POJOS
        );
        //        static final LinkedBuffer buffer = LinkedBuffer.allocate(512);
        static final ThreadLocal<LinkedBuffer> buffer = ThreadLocal.withInitial(() -> LinkedBuffer.allocate(512));//this may better
        static final Map<String, Schema<Object>> schemaPool = new ConcurrentHashMap<>();
        static final Function<String, Schema<Object>> schemaOf = Sneaky.function(internal::classFromString).andThen(internal::getSchema);
        static final Function<Object, Schema<Object>> schemaFrom = Sneaky.function(Object::getClass).andThen(internal::getSchema);
        static final Map<Class<?>, Class<?>> staticMapping = new ConcurrentHashMap<>();

        @SuppressWarnings("unchecked")
        static Schema<Object> getSchema(Class<?> clz) {
            Schema<Object> schema = schemaPool.get(clz.getCanonicalName());
            if (schema == null) {
                try {
                    schema = (Schema<Object>) RuntimeSchema.createFrom(clz, STRATEGY);
                } catch (Exception e) {
                    return null;
                }
                schemaPool.put(clz.getCanonicalName(), schema);
            }
            return schema;
        }

        static Schema<Object> getSchema(String clz) {
            Schema<Object> schema = schemaPool.get(clz);
            if (schema == null) {
                try {
                    final Class<?> aClass = classFromString(clz);
                    return getSchema(aClass);
                } catch (ClassNotFoundException e) {
                    return null;
                }
            }
            return schema;
        }

        static Class<?> classFromString(String name) throws ClassNotFoundException {
            //@formatter:off
            if (long.class.getCanonicalName().equals(name)) {
                return long.class;
            } else if (int.class.getCanonicalName().equals(name)) {
                return int.class;
            } else if (byte.class.getCanonicalName().equals(name)) {
                return byte.class;
            } else if (short.class.getCanonicalName().equals(name)) {
                return short.class;
            } else if (boolean.class.getCanonicalName().equals(name)) {
                return boolean.class;
            } else if (double.class.getCanonicalName().equals(name)) {
                return double.class;
            } else if (float.class.getCanonicalName().equals(name)) {
                return float.class;
            } else if (name.endsWith("[]")) {
                //array
                final String arrayName = "[L" + name.replace(Pattern.quote("[]"), ";");
                return Class.forName(arrayName);
            }
            return name.getClass().getClassLoader().loadClass(name);
            //@formatter:on
        }
    }
}
