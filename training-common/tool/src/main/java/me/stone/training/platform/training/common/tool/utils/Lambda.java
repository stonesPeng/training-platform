/*
 *     Copyright (c) 2020.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: Lambda.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2020-12-12 15:00:20
 */

package me.stone.training.platform.training.common.tool.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Lambda 工具
 */
public interface Lambda {
    static <T> @NotNull T nullable(T nullable, T defaultVal) {
        return nullable == null ? defaultVal : nullable;
    }

    static long nullable(Long nullable, long defaultVal) {
        return nullable == null ? defaultVal : nullable;
    }

    static int nullable(Integer nullable, int defaultVal) {
        return nullable == null ? defaultVal : nullable;
    }
}
