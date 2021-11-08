/*
 *     Copyright (c) 2020.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: AnthropicIdGenerator.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2020-12-12 17:17:05
 */

package me.stone.training.platform.training.common.tool.utils;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 轻快的ID构建工具,不适合大量数据,不适合分布场景的唯一ID,人类可读<br>
 * 最大序号 21474 </br>
 * 格式[YYYY-MM-DD[8d]-SEQ[5d]-THREAD_ID[4-5d]]=8+5+4
 *
 * @author Zen.Liu
 * @apiNote
 * @since 2020-12-12
 */
public interface AnthropicIdGenerator extends Serializable {
    @NotNull String getName();

    /**
     * use to reset data after loaded
     */
    void validate();

    int peekSequence();

    int peekTs();


    long generate();

    short THREAD_ID = ThreadIdReader();

    @SneakyThrows
    static short ThreadIdReader() {
        return (short) (Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]) + 10000);
    }

    /**
     * a brand new Generator
     */
    static AnthropicIdGenerator getInstance(String name) {
        return new Impl(name);
    }

    /**
     * save to file
     */
    static void saveGenerator(AnthropicIdGenerator generator, Path path) {
        try (
            FileOutputStream fos = new FileOutputStream(path.toFile().getAbsolutePath());
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(generator);
        } catch (IOException ex) {
            throw (RuntimeException) (Throwable) ex;
        }
    }

    /**
     * load from file
     */
    static AnthropicIdGenerator loadGenerator(Path path) {
        try (
            FileInputStream fos = new FileInputStream(path.toFile().getAbsolutePath());
            ObjectInputStream oos = new ObjectInputStream(fos)
        ) {
            AnthropicIdGenerator generator = (AnthropicIdGenerator) oos.readObject();
            generator.validate();
            return generator;
        } catch (IOException | ClassNotFoundException ex) {
            throw (RuntimeException) ex;
        }
    }

    /**
     * JOS write to bytes
     */
    static byte[] serialize(AnthropicIdGenerator generator) {
        try (
            ByteArrayOutputStream fos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(generator);
            return fos.toByteArray();
        } catch (IOException ex) {
            throw (RuntimeException) (Throwable) ex;
        }
    }

    /**
     * load from bytes
     */
    static AnthropicIdGenerator deserialize(byte[] data) {
        try (
            ByteArrayInputStream fos = new ByteArrayInputStream(data);
            ObjectInputStream oos = new ObjectInputStream(fos)
        ) {
            AnthropicIdGenerator generator = (AnthropicIdGenerator) oos.readObject();
            generator.validate();
            return generator;
        } catch (IOException | ClassNotFoundException ex) {
            throw (RuntimeException) ex;
        }
    }

    short MAX_SEQ = 21474;

    @Slf4j(topic = "com.medtreehealth.infra.UniqueLongGenerator")
    final class Impl extends AtomicInteger implements AnthropicIdGenerator, Serializable {
        private static final long serialVersionUID = 3422091013995227196L;
        @Getter final String name;
        final AtomicInteger ts;
        final AtomicLong remain;
        private final AtomicInteger thread = new AtomicInteger(THREAD_ID);
        public static final DateFormat DEFAULT = new SimpleDateFormat("yyyyMMdd");

        public static int generateDate(int differ) {
            return Integer.parseInt(DEFAULT.format(
                Date.from(differ == 0 ? Instant.now()
                    : Instant.now().plus(differ, ChronoUnit.DAYS))
            ));
        }

        public static long PerfectlyHashThem(int a, int b) {
            return (long) Math.pow(10, Math.floor(Math.log10(b)) + 1) * a + b;
        }

        public static int PerfectlyHashThem(short a, short b) {
            return (int) Math.pow(10, Math.floor(Math.log10(b)) + 1) * a + b;
        }

        Impl(String name) {
            this.name = name;
            ts = new AtomicInteger(generateDate(0));
            remain = new AtomicLong(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli());
        }

        protected int getTs() {
            synchronized (ts) {
                if (remain.get() <= System.currentTimeMillis()) {
                    ts.set(generateDate(0));
                    remain.set(Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli());
                    set(-1);
                } else if (get() >= MAX_SEQ) {
                    log.error("current sequence is overflow (max 22767),will reset sequence to 0 and inc threadId to {}, on generator {}.", thread.incrementAndGet(), getName());
                    if (thread.get() > Short.MAX_VALUE) {
                        log.error("current thread is overflow will set threadId to 5, on generator {}.", getName());
                        thread.set(5);
                    }
                    set(-1);
                }
                return ts.get();
            }
        }

        @Override
        public int peekTs() {
            return ts.get();
        }

        @Override
        public void validate() {
            if (thread.get() != THREAD_ID) {
                thread.set(THREAD_ID);
                set(-1);
            }
        }

        @Override
        public int peekSequence() {
            return get();
        }

        /**
         * 1 digital almost 4bit(3.28..)
         * 8*4=32
         */
        @Override
        public long generate() {
            /*return ((long) getTs() << 32)
                | ((short) threadId << 16)
                | incrementAndGet();*/
            return PerfectlyHashThem(getTs(), PerfectlyHashThem((short) thread.get(),(short) ((short) incrementAndGet() + 10000)));
        }

    }
}
