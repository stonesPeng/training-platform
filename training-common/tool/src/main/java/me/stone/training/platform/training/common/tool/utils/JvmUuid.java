/*
 *     Copyright (c) 2021.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: JvmUuid.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2021-01-17 00:54:36
 */

package me.stone.training.platform.training.common.tool.utils;

import lombok.SneakyThrows;
import org.jetbrains.annotations.ApiStatus;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @author Zen.Liu
 * @apiNote
 * @since 2021-01-17
 */
@ApiStatus.AvailableSince("1.1.2")
public interface JvmUuid {
    long PROCESS_ID = internal.ProcessIdReader();
    String macHash = internal.getRawOrMd5Mac();
    final class internal {
        @SneakyThrows
        static long ProcessIdReader() {
            return Long.parseLong(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        }

        @SneakyThrows
        static String MacReader() {
            final StringBuilder result = new StringBuilder();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface net : Collections.list(nets)) {
                if (net.getHardwareAddress() == null || net.isLoopback() || !net.isUp()) continue;
                if (result.length() > 0) result.append(";");
                result.append(hexdump(net.getHardwareAddress()));
            }
            return result.toString();
        }

        @SneakyThrows
        static String Md5Macs() {
            return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(MacReader().getBytes(StandardCharsets.UTF_8)));
        }

        static String getRawOrMd5Mac() {
            final String s = MacReader();
            return s.length() > 32 ? Md5Macs() : s;
        }
    }

    static String hexdump(byte[] array) {
        final StringBuilder result = new StringBuilder();
        for (byte b : array) {
            result.append(Integer.toHexString(b));
        }
        return result.toString();
    }

}
