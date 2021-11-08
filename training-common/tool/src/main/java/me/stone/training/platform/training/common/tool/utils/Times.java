package me.stone.training.platform.training.common.tool.utils;

import lombok.*;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public interface Times {
    @ApiStatus.AvailableSince("2012-08-11")
    @ApiStatus.Experimental
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    final class IntDate {
        /**
         * zero_bytes [short year][4bit month] [5bit day of month]
         * [15][8+4+5] {32768 remains}
         */
        private static final int MASK_MONTH = ((1 << 4) - 1);
        private static final int MASK_DATE = ((1 << 5) - 1);
        public static int MIN_VALUE = 33;
        public static int MAX_VALUE = 5119903;//9999-12-31
        private final int value;


        public int getYear() {
            return value >> 9;
        }

        public int getMonth() {
            return (value >> 5) & MASK_MONTH;
        }

        public int getDate() {
            return value & MASK_DATE;
        }

        public Instant toInstant(@Nullable ZoneOffset offset) {
            //System.out.println(toString() + "T00:00:00" + (offset == null ? SYSTEM_OFFSET.get() : offset).toString());
            return OffsetDateTime.parse(toString() + "T00:00:00" + (offset == null ? SYSTEM_OFFSET.get() : offset).toString()).toInstant();
        }

        @Override
        public String toString() {
            return getYear() + "-" + (getMonth() < 10 ? "0" + getMonth() : getMonth()) + "-" + (getDate() < 10 ? "0" + getDate() : getDate());
        }

        public static IntDate from(Instant date, @Nullable ZoneOffset offset) {
            return new IntDate(intDateFrom(date, offset));
        }

        public static int intDateFrom(Instant date, @Nullable ZoneOffset offset) {
            val offsetDateTime = date.atZone(offset == null ? SYSTEM_OFFSET.get() : offset).truncatedTo(ChronoUnit.DAYS);
            val year = offsetDateTime.getYear();
            if (year > 9999) throw new IllegalArgumentException("only support year between 0 ~ 9999");
            val month = offsetDateTime.getMonthValue();
            val day = offsetDateTime.getDayOfMonth();
            return (year << 9) | (month << 5) | day;
        }

/*        public static void main(String[] args) {
            System.out.println(intDateFrom(Instant.parse("2020-12-31T08:00:00Z"), null));
            System.out.println(from(Instant.parse("2020-12-31T16:00:00Z"), null));
            System.out.println(from(Instant.parse("2020-12-31T16:00:00Z"), null).toInstant(null));
        }*/
    }

    /**
     * System default ZoneOffset (default is +8)
     */
    AtomicReference<ZoneOffset> SYSTEM_OFFSET = new AtomicReference<>(ZoneOffset.ofHours(8));
    /**
     * a number present of DATE as 'yyyyMMdd';
     */
    DateTimeFormatter NUMBER_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter NUMBER_DATE_FORMATTER_TWO_DIGIT_YEAR = DateTimeFormatter.ofPattern("yyMMdd");
    @SuppressWarnings("SpellCheckingInspection") DateTimeFormatter NUMBER_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        "yyyyMMddHHmmss"
    );
    @SuppressWarnings("SpellCheckingInspection") DateTimeFormatter NUMBER_DATE_TIME_FORMATTER_TWO_DIGIT_YEAR = DateTimeFormatter.ofPattern(
        "yyMMddHHmmss"
    );
    /**
     * common used date format as 'yyyy-MM-dd'
     */
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * common used date-time format as 'yyyy-MM-dd HH:mm:ss'
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * common used time format as 'HH:mm:ss'
     */
    DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    /**
     * common used zone date time format as 'yyyy-MM-dd HH:mm:ss O'
     */
    DateTimeFormatter ZONE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss O");
    /**
     * common used timestamp with zone FORMAT as 'yyyy-MM-dd HH:mm:ss.SSS O'
     */
    DateTimeFormatter ZONE_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS O");

    /**
     * convert current date to a int
     *
     * @param instant       the instant
     * @param atSystemZone  dose use {@link #SYSTEM_OFFSET} else use UTC
     * @param fourDigitYear dose output 4 digit Year
     */
    static int dateAsInt(Instant instant, boolean atSystemZone, boolean fourDigitYear) {
        if (atSystemZone) {
            return Integer.parseInt(instant.
                atOffset(SYSTEM_OFFSET.get())
                .format(fourDigitYear ? NUMBER_DATE_FORMATTER : NUMBER_DATE_FORMATTER_TWO_DIGIT_YEAR));
        } else {
            return Integer.parseInt(instant
                .atOffset(ZoneOffset.UTC)
                .format(fourDigitYear ? NUMBER_DATE_FORMATTER : NUMBER_DATE_FORMATTER_TWO_DIGIT_YEAR));
        }
    }

    static int dateAsInt(LocalDate date, boolean fourDigitYear) {
        return Integer.parseInt(date
            .format(fourDigitYear ? NUMBER_DATE_FORMATTER : NUMBER_DATE_FORMATTER_TWO_DIGIT_YEAR));
    }

    /**
     * use system offset and 4 digit year
     *
     * @see #dateAsInt(Instant)
     */
    static int dateAsInt(Instant instant) {
        return dateAsInt(instant, true, true);
    }

    static int dateAsInt(OffsetDateTime instant, boolean fourDigitYear) {
        return Integer.parseInt(instant
            .format(fourDigitYear ? NUMBER_DATE_FORMATTER : NUMBER_DATE_FORMATTER_TWO_DIGIT_YEAR));
    }

    static int dateAsInt(OffsetDateTime instant) {
        return dateAsInt(instant, true);
    }

    /**
     * convert a Date integer to date
     *
     * @param val the Date integer
     * @return null if invalid, else a  LocalDate
     */
    static @Nullable LocalDate intToDate(int val) {
        if (val <= 0) return null;
        val str = Integer.toString(val);
        if (str.length() != 6 && str.length() != 8) return null;
        return LocalDate.parse(str, str.length() == 6 ? NUMBER_DATE_FORMATTER_TWO_DIGIT_YEAR : NUMBER_DATE_FORMATTER);
    }


    /**
     * convert current time to a int
     *
     * @param instant       the instant
     * @param atSystemZone  dose use {@link #SYSTEM_OFFSET} else use UTC
     * @param fourDigitYear dose output 4 digit Year
     */
    static long dateTimeAsLong(Instant instant, boolean atSystemZone, boolean fourDigitYear) {
        if (atSystemZone) {
            return Long.parseLong(instant.
                atOffset(SYSTEM_OFFSET.get())
                .format(fourDigitYear ? NUMBER_DATE_TIME_FORMATTER : NUMBER_DATE_TIME_FORMATTER_TWO_DIGIT_YEAR));
        } else {
            return Long.parseLong(instant
                .atOffset(ZoneOffset.UTC)
                .format(fourDigitYear ? NUMBER_DATE_TIME_FORMATTER : NUMBER_DATE_TIME_FORMATTER_TWO_DIGIT_YEAR));
        }
    }

    /**
     * use system offset and 4 digit year
     *
     * @see #dateTimeAsLong(Instant)
     */
    static long dateTimeAsLong(Instant instant) {
        return dateTimeAsLong(instant, true, true);
    }

    static long dateTimeAsLong(OffsetDateTime instant, boolean fourDigitYear) {
        return Integer.parseInt(instant
            .format(fourDigitYear ? NUMBER_DATE_TIME_FORMATTER : NUMBER_DATE_TIME_FORMATTER_TWO_DIGIT_YEAR));
    }

    static long dateTimeAsLong(OffsetDateTime instant) {
        return dateTimeAsLong(instant, true);
    }

    static int daysBetween(Instant a, Instant b) {
        if (a.equals(b)) return 0;
        val p = (int) ChronoUnit.DAYS.between(
            a.atZone(SYSTEM_OFFSET.get()).toLocalDate(),
            b.atZone(SYSTEM_OFFSET.get()).toLocalDate());
        return Math.abs(p);
    }

    static int daysAfterNow(Instant a) {
        return (int) ChronoUnit.DAYS.between(
            Instant.now().atZone(SYSTEM_OFFSET.get()).toLocalDate(),
            a.atZone(SYSTEM_OFFSET.get()).toLocalDate()
        );
    }

    static int daysBeforeNow(Instant a) {
        return (int) ChronoUnit.DAYS.between(
            a.atZone(SYSTEM_OFFSET.get()).toLocalDate(),
            Instant.now().atZone(SYSTEM_OFFSET.get()).toLocalDate()
        );

    }

    static Instant plusPeriod(Instant a, Period period) {
        return a.plus(period.getDays(), ChronoUnit.DAYS);
    }

    static Instant plusDuration(Instant a, Duration period) {
        return a.plus(period.getSeconds(), ChronoUnit.SECONDS);
    }

    Supplier<Instant> now = Instant::now;

    static Instant now() {
        return now.get();
    }

    /**
     * @param offset optional
     * @return
     */
    static Tuple2<Instant, Instant> today(@Nullable ZoneOffset offset) {
        return dayOf(now(), offset);
    }

    /**
     * @param time   must
     * @param offset optional
     * @return (2020 - 03 - 01T00 : 00 + 08 : 00, 2020 - 03 - 01T23 : 59 : 59.999 + 08 : 00)
     */
    @ApiStatus.AvailableSince("2021-08-09")
    static Tuple2<Instant, Instant> dayOf(@NotNull Instant time, @Nullable ZoneOffset offset) {
        val z = offset == null ? SYSTEM_OFFSET.get() : offset;
        val todayBeginAt = time.atZone(z).truncatedTo(ChronoUnit.DAYS).toInstant();
        return Tuple.tuple(todayBeginAt, todayBeginAt.plus(1, ChronoUnit.DAYS)
            .minus(1, ChronoUnit.MILLIS));
    }

    /**
     * @param offset optional
     * @return (start instant of current month = > first mill seconds of first day of current month, end instant of current month = > last mill seconds of last day of current month)
     */
    @ApiStatus.AvailableSince("2021-08-09")
    static Tuple2<Instant, Instant> currentMonth(@Nullable ZoneOffset offset) {
        return monthOf(now(), offset);
    }

    /**
     * @param time   must
     * @param offset optional
     * @return (start instant of current month = > first mill seconds of first day of current month, end instant of current month = > last mill seconds of last day of current month)
     */
    @ApiStatus.AvailableSince("2021-08-09")
    static Tuple2<Instant, Instant> monthOf(@NotNull Instant time, @Nullable ZoneOffset offset) {
        val z = offset == null ? SYSTEM_OFFSET.get() : offset;
        var endOfMonth = time.atZone(z).truncatedTo(ChronoUnit.DAYS);
        val leapYear = endOfMonth.getChronology().isLeapYear(endOfMonth.getYear());
        endOfMonth = endOfMonth.plusDays(endOfMonth.getMonth().length(leapYear) - endOfMonth.getDayOfMonth());
        var startOfMonth = endOfMonth.minusDays(endOfMonth.getDayOfMonth() - 1);
        return Tuple.tuple(startOfMonth.toInstant(),
            endOfMonth.toInstant()
                .plus(23, ChronoUnit.HOURS)
                .plus(59, ChronoUnit.MINUTES)
                .plus(59, ChronoUnit.SECONDS)
                .plus(999, ChronoUnit.MILLIS));
    }

    /**
     * @param time   required
     * @param offset optional
     * @return (2024 - 01 - 01T00 : 00 + 08 : 00, 2024 - 12 - 31T23 : 59 : 59.999 + 08 : 00)
     */
    @ApiStatus.AvailableSince("2021-08-09")
    static Tuple2<Instant, Instant> yearOf(@NotNull Instant time, @Nullable ZoneOffset offset) {
        val z = offset == null ? SYSTEM_OFFSET.get() : offset;
        var year = time.atZone(z)
            .truncatedTo(ChronoUnit.DAYS)
            .withDayOfYear(1);
        return Tuple.tuple(year.toInstant(),
            year.plusYears(1).minusDays(1).toInstant()
                .plus(23, ChronoUnit.HOURS)
                .plus(59, ChronoUnit.MINUTES)
                .plus(59, ChronoUnit.SECONDS)
                .plus(999, ChronoUnit.MILLIS));
    }

    /**
     * @param offset optional
     * @return (2024 - 01 - 01T00 : 00 + 08 : 00, 2024 - 12 - 31T23 : 59 : 59.999 + 08 : 00)
     */
    @ApiStatus.AvailableSince("2021-08-10")
    static Tuple2<Instant, Instant> currentYear(@Nullable ZoneOffset offset) {
        return yearOf(now(), offset);
    }

    @ApiStatus.AvailableSince("2021-08-10")
    static Tuple2<Instant, Instant> currentWeek(@Nullable ZoneOffset offset) {
        return weekOf(now(), offset);
    }

    /**
     * NOTE: the start of week is Monday
     *
     * @param time   required
     * @param offset optional
     * @return (2021 - 08 - 09T00 : 00 + 08 : 00, 2021 - 08 - 15T23 : 59 : 59.999 + 08 : 00)
     */
    @ApiStatus.AvailableSince("2021-08-10")
    static Tuple2<Instant, Instant> weekOf(@NotNull Instant time, @Nullable ZoneOffset offset) {
        val z = offset == null ? SYSTEM_OFFSET.get() : offset;
        var today = time.atZone(z)
            .truncatedTo(ChronoUnit.DAYS);
        //1-7
        val dayOfWeek = today.getDayOfWeek().getValue();
        return Tuple.tuple(today.minusDays(dayOfWeek - 1).toInstant(),
            today.plusDays(7 - dayOfWeek).toInstant()
                .plus(23, ChronoUnit.HOURS)
                .plus(59, ChronoUnit.MINUTES)
                .plus(59, ChronoUnit.SECONDS)
                .plus(999, ChronoUnit.MILLIS));
    }

/*    public static void main(String[] args) {
        System.out.println(
            weekOf(Instant.parse("2021-08-10T00:00:00Z"), null)
                .map1(x -> x.atOffset(SYSTEM_OFFSET.get()))
                .map2(x -> x.atOffset(SYSTEM_OFFSET.get()))
        );
    }*/

}
