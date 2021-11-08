package me.stone.training.platform.training.common.tool.utils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Predicate;

//tool to process throwable
public interface Causes {
    Logger log = LoggerFactory.getLogger(Causes.class);
    AtomicReference<Predicate<Throwable>> PREDICATE = new AtomicReference<Predicate<Throwable>>(Causes::commonMatcher);

    static void appendAndPredicate(@NotNull Predicate<Throwable> andPredict) {
        PREDICATE.set(PREDICATE.get().and(andPredict));
    }

    static void appendRrPredicate(@NotNull Predicate<Throwable> orPredict) {
        PREDICATE.set(PREDICATE.get().or(orPredict));
    }

    static void prependAndPredicate(@NotNull Predicate<Throwable> andPredict) {
        PREDICATE.set(andPredict.and(PREDICATE.get()));
    }

    static void prependRrPredicate(@NotNull Predicate<Throwable> orPredict) {
        PREDICATE.set(orPredict.or(PREDICATE.get()));
    }

    static <T extends Throwable> T format(
        BiFunction<String, Throwable, T> ctorCause,
        String pattern, Object... args) {
        final FormattingTuple msg = MessageFormatter.arrayFormat(pattern, args);
        final Throwable cause = msg.getThrowable();
        return ctorCause.apply(msg.getMessage(), cause);
    }

    static <T extends Throwable> T firstNodeMatch(Throwable throwable) {
        return firstNodeMatch(throwable, PREDICATE.get());
    }

    static <T extends Throwable> T lastNodeMatch(Throwable throwable) {
        return lastNodeMatch(throwable, PREDICATE.get());
    }


    static <T extends Throwable> T firstNodeMatch(Throwable throwable, Predicate<Throwable> predicate) {
        final List<Throwable> list = getThrowableList(throwable);
        for (Throwable t : list) {
            if (predicate.test(t)) {
                //noinspection unchecked
                return (T) t;
            } else {
                log.debug("skip cause for not supported by matcher", t);
            }
        }
        return null;
    }

    static <T extends Throwable> T lastNodeMatch(Throwable throwable, Predicate<Throwable> predicate) {
        final List<Throwable> lst = getThrowableList(throwable);
        final int len = lst.size();
        for (int i = len - 1; i >= 0; i--) {
            final Throwable t = lst.get(i);
            if (predicate.test(t)) {
                //noinspection unchecked
                return (T) t;
            } else {
                log.debug("skip cause for not supported by matcher", t);
            }
        }
        return null;
    }

    static Predicate<Throwable> currentMatcher() {
        return PREDICATE.get();
    }

    static boolean commonMatcher(Throwable e) {
        return e.getClass().getCanonicalName().startsWith("com.medtreehealth")
            || (

            !(
                e instanceof UndeclaredThrowableException
                    || e instanceof ExecutionException
            )
        );

    }

    static Throwable getRootCause(final Throwable throwable) {
        final List<Throwable> list = getThrowableList(throwable);
        return list.size() < 2 ? null : list.get(list.size() - 1);
    }

    static List<Throwable> getThrowableList(Throwable throwable) {
        final List<Throwable> list = new ArrayList<Throwable>();
        while (throwable != null && !list.contains(throwable)) {
            list.add(throwable);
            throwable = throwable.getCause();
        }
        return list;
    }


    static Throwable lastNodeMatchNotNull(Throwable e) {
        final Throwable t = lastNodeMatch(e);
        return t == null ? e : t;
    }

    static Throwable firstNodeMatchNotNull(Throwable e) {
        final Throwable t = firstNodeMatch(e);
        return t == null ? e : t;
    }


}
