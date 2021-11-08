package me.stone.training.platform.training.common.tool.utils;

import at.favre.lib.bytes.Bytes;
import org.jetbrains.annotations.Nullable;
import org.jooq.lambda.Seq;
import org.slf4j.helpers.MessageFormatter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public interface Formatter {
    static String format(String pattern, Object... args) {
        return MessageFormatter.arrayFormat(pattern, args).getMessage();
    }

    static String text(String template, Object... args) {
        return MessageFormatter.arrayFormat(template, args).getMessage();
    }

    DateTimeFormatter TIMESTAMP_FORMATTER_CHS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    DateTimeFormatter TIMESTAMP_ZONED_FORMATTER_CHS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS X");
    DateTimeFormatter DATE_FORMATTER_CHS = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter TIME_FORMATTER_CHS = DateTimeFormatter.ofPattern("HH:mm:ss");

    static String chsDate(Instant time, @Nullable DateTimeFormatter formatter) {
        return time.atOffset(ZoneOffset.ofHours(8)).format(formatter == null ? TIMESTAMP_FORMATTER_CHS : formatter);
    }

    static String utc(Instant time, @Nullable DateTimeFormatter formatter) {
        return (formatter == null ? TIMESTAMP_FORMATTER_CHS : formatter).format(time);
    }

    static String bytes(byte[] data) {
        return Bytes.from(data).encodeHex(true);
    }

    static String bytesPretty(byte[] data, int colByteSize, int rowCols, boolean axis) {
        return charsPrettyTable(Bytes.from(data).encodeHex(true), colByteSize * 2, rowCols, axis);
    }

    static Character intToCharacter(int i) {
        return (Character) (char) i;
    }

    static String charsPrettyTable(String chars, int colSize, int rowCols, boolean axis) {
        final String hex = Seq.seq(chars.chars())
            .map(Formatter::intToCharacter)
            .zipWithIndex()
            //chunk by column size
            .grouped(t -> (Integer) (int) (t.v2 / colSize))
            .map(t -> t.v2.map(x -> intToCharacter(x.v1)).toString(""))
            .zipWithIndex()
            //chunk as row
            .grouped(t -> t.v2 / rowCols)
            .map(t -> t.v2.map(x -> x.v1).toString(" "))
            //join as table
            .toString("\n");
        if (axis) {
            StringBuilder sp = new StringBuilder("----");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < (colSize / 2 * rowCols); i++) {
                sp.append("--");
                if (i > 0 && i % (colSize / 2) == 0) {
                    sb.append(" ");
                }
                sb.append(i < 10 ? "0" + i : i);
            }
            sb.append("\n");
            String spt = sp.append("\n").toString();
            return sp.append(sb).append(spt).append(hex).toString();
        }
        return hex;
    }
}
