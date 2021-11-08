/*
 *     Copyright (c) 2021.  MedtreeHealth All Rights Reserved.
 *     @Project: application-common
 *     @Module: common-api
 *     @File: Case.java
 *     @Author:  zen.liu@medtreehealth.com
 *     @LastModified:  2021-01-06 00:47:01
 */

package me.stone.training.platform.training.common.tool.utils;

import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Zen.Liu
 * @apiNote
 * @since 2021-01-05
 */
@ApiStatus.Experimental
public interface Case<T> {
    public static void main(String[] args) {
        final Execution<Object> execution = Case.execute()
            .equal(1, x -> System.out.print("v"))
            .is(Integer.class, System.out::println)
            .doElse(System.out::println);
        long st = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            execution.exec(true);
        }
        System.out.println(" none ops: " + ((System.nanoTime() - st) / (1000.0)) + " ns");
        final Evaluation<Integer, Long> eval = Case.evaluate(Integer.class, long.class)
            .equal(1, x -> x + 1L)
            .is(Integer.class, x -> x * 2L)
            .doElse(() -> 1L);
        st = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            eval.eval(i % 2 == 0 ? 2 : 1);
        }
        System.out.println(eval.eval(100000).getOrThrowError());
        System.out.println("eval ops: " + ((System.nanoTime() - st) / (1000.0)) + " ns");
    }

    static <T> None<T> execute(Class<T> type) {
        return new None.NoneImpl<>(null);
    }

    static <T> None<T> execute() {
        return new None.NoneImpl<>(null);
    }

    static <T, R> Any<T, R> evaluate(Class<T> target, Class<R> result) {
        return new Any.AnyImpl<>(null);
    }

    static <T, R> Any<T, R> evaluate() {
        return new Any.AnyImpl<>(null);
    }

    @FunctionalInterface
    interface Execution<T> {
        Maybe<Void> exec(T value);
    }

    @FunctionalInterface
    interface Evaluation<T, R> {
        Maybe<R> eval(T value);
    }

    interface Maybe<T> {
        Optional<T> get();

        Optional<T> getOrThrow();

        T getOrThrowError();

        Throwable getError();

        static <T> Maybe<T> error(Throwable e) {
            return new MaybeError<T>(e);
        }

        static <T> Maybe<T> ok(T e) {
            return new MaybeResult<T>(e);
        }

        static <T> Maybe<T> wrap(Supplier<T> supplier) {
            try {
                return ok(supplier.get());
            } catch (Exception e) {
                return error(e);
            }
        }

        static Maybe<Void> wrap(Runnable supplier) {
            try {
                supplier.run();
                return MaybeNone.INSTANCE;
            } catch (Exception e) {
                return error(e);
            }
        }

        final class MaybeError<T> implements Maybe<T> {
            final Throwable error;

            MaybeError(Throwable error) {
                this.error = error;
            }

            @Override
            public Optional<T> get() {
                return Optional.empty();
            }

            @Override
            public Optional<T> getOrThrow() {
                throw (RuntimeException) error;
            }

            @Override
            public T getOrThrowError() {
                throw (RuntimeException) error;
            }

            @Override
            public Throwable getError() {
                return error;
            }
        }

        final class MaybeResult<T> implements Maybe<T> {
            final T value;

            MaybeResult(T value) {
                this.value = value;
            }

            @Override
            public Optional<T> get() {
                return Optional.ofNullable(value);
            }

            @Override
            public Optional<T> getOrThrow() {
                return Optional.ofNullable(value);
            }

            @Override
            public T getOrThrowError() {
                return value;
            }

            @Override
            public Throwable getError() {
                return null;
            }
        }

        final class MaybeNone implements Maybe<Void> {
            static final MaybeNone INSTANCE = new MaybeNone();

            @Override
            public Optional<Void> get() {
                return Optional.empty();
            }

            @Override
            public Optional<Void> getOrThrow() {
                return Optional.empty();
            }

            @Override
            public Void getOrThrowError() {
                return null;
            }

            @Override
            public Throwable getError() {
                return null;
            }
        }
    }

    interface None<T> extends Case<T> {
        <R> None<T> is(Class<R> klass, Consumer<R> act);

        None<T> is(Predicate<T> predicate, Consumer<T> act);

        <R> None<T> is(Function<T, Optional<R>> predicate, Consumer<R> act);

        None<T> equal(T value, Consumer<T> act);

        None<T> isNull(Runnable act);

        Execution<T> doElse(Consumer<T> act);

        final class NoneImpl<T> implements None<T> {
            final Predicate<T> chain;

            NoneImpl(Predicate<T> chain) {
                this.chain = chain;
            }

            @Override
            public <R> None<T> is(Class<R> klass, Consumer<R> act) {
                return chain == null ?
                    new NoneImpl<T>(
                        x -> {
                            if (x.getClass().isAssignableFrom(klass)) {
                                act.accept((R) x);
                                return true;
                            } else return false;
                        }) :
                    new NoneImpl<T>(
                        chain.or(x -> {
                            if (x.getClass().isAssignableFrom(klass)) {
                                act.accept((R) x);
                                return true;
                            } else return false;
                        }));
            }

            @Override
            public None<T> is(Predicate<T> predicate, Consumer<T> act) {
                return chain == null ?
                    new NoneImpl<T>(
                        x -> {
                            if (predicate.test(x)) {
                                act.accept(x);
                                return true;
                            } else return false;
                        }) :
                    new NoneImpl<T>(chain.or(x -> {
                        if (predicate.test(x)) {
                            act.accept(x);
                            return true;
                        } else return false;
                    }));
            }

            @Override
            public <R> None<T> is(Function<T, Optional<R>> predicate, Consumer<R> act) {
                return chain == null ?
                    new NoneImpl<T>(
                        x -> {
                            final Optional<R> r = predicate.apply(x);
                            if (r.isPresent()) {
                                act.accept(r.get());
                                return true;
                            } else return false;
                        }) :
                    new NoneImpl<T>(chain.or(x -> {
                        final Optional<R> r = predicate.apply(x);
                        if (r.isPresent()) {
                            act.accept(r.get());
                            return true;
                        } else return false;
                    }));
            }

            @Override
            public None<T> equal(T value, Consumer<T> act) {
                return chain == null ?
                    new NoneImpl<T>(
                        x -> {
                            if (value.equals(x)) {
                                act.accept(x);
                                return true;
                            } else return false;
                        }) :
                    new NoneImpl<T>(chain.or(x -> {
                        if (value.equals(x)) {
                            act.accept(x);
                            return true;
                        } else return false;
                    }));
            }

            @Override
            public None<T> isNull(Runnable act) {
                return chain == null ?
                    new NoneImpl<T>(
                        x -> {
                            if (x == null) {
                                act.run();
                                return true;
                            } else return false;
                        }) :
                    new NoneImpl<T>(chain.or(x -> {
                        if (x == null) {
                            act.run();
                            return true;
                        } else return false;
                    }));
            }

            @Override
            public Execution<T> doElse(Consumer<T> act) {
                return chain == null ?
                    x -> Maybe.wrap(() -> act.accept(x))
                    : x -> Maybe.wrap(() -> {
                    chain.or(t -> {
                        act.accept(t);
                        return true;
                    }).test(x);
                });
            }
        }
    }

    interface Any<T, R> extends Case<T> {
        <F> Any<T, R> is(Class<F> klass, Function<F, R> act);

        Any<T, R> is(Predicate<T> predicate, Function<T, R> act);

        <F> Any<T, R> is(Function<T, Optional<F>> predicate, Function<F, R> act);

        Any<T, R> equal(T value, Function<T, R> act);

        Any<T, R> isNull(Supplier<R> act);

        Evaluation<T, R> doElse(Supplier<R> act);

        final class AnyImpl<T, R> implements Any<T, R> {
            final Function<T, Optional<R>> chain;

            AnyImpl(Function<T, Optional<R>> chain) {
                this.chain = chain;
            }

            @Override
            public <F> Any<T, R> is(Class<F> klass, Function<F, R> act) {
                return chain == null ?
                    new AnyImpl<>(x -> x.getClass().isAssignableFrom(klass) ? Optional.of(act.apply((F) x)) : Optional.empty()) :
                    new AnyImpl<>(
                        x -> {
                            final Optional<R> last = chain.apply(x);
                            if (last.isPresent()) return last;
                            return x.getClass().isAssignableFrom(klass) ? Optional.of(act.apply((F) x)) : Optional.empty();
                        }
                    );
            }

            @Override
            public Any<T, R> is(Predicate<T> predicate, Function<T, R> act) {
                return chain == null ?
                    new AnyImpl<>(x -> predicate.test(x) ? Optional.of(act.apply(x)) : Optional.empty()) :
                    new AnyImpl<>(
                        x -> {
                            final Optional<R> last = chain.apply(x);
                            if (last.isPresent()) return last;
                            return predicate.test(x) ? Optional.of(act.apply(x)) : Optional.empty();
                        }
                    );
            }

            @Override
            public <F> Any<T, R> is(Function<T, Optional<F>> predicate, Function<F, R> act) {
                return chain == null ?
                    new AnyImpl<>(x -> predicate.apply(x).map(act)) :
                    new AnyImpl<>(
                        x -> {
                            final Optional<R> last = chain.apply(x);
                            if (last.isPresent()) return last;
                            return predicate.apply(x).map(act);
                        }
                    );
            }

            @Override
            public Any<T, R> equal(T value, Function<T, R> act) {
                return chain == null ?
                    new AnyImpl<>(x -> value.equals(x) ? Optional.of(act.apply(x)) : Optional.empty()) :
                    new AnyImpl<>(
                        x -> {
                            final Optional<R> last = chain.apply(x);
                            if (last.isPresent()) return last;
                            return value.equals(x) ? Optional.of(act.apply(x)) : Optional.empty();
                        }
                    );
            }

            @Override
            public Any<T, R> isNull(Supplier<R> act) {
                return chain == null ?
                    new AnyImpl<>(x -> null == x ? Optional.of(act.get()) : Optional.empty()) :
                    new AnyImpl<>(
                        x -> {
                            final Optional<R> last = chain.apply(x);
                            if (last.isPresent()) return last;
                            return null == x ? Optional.of(act.get()) : Optional.empty();
                        }
                    );
            }

            @Override
            public Evaluation<T, R> doElse(Supplier<R> act) {
                return chain == null ? x -> Maybe.wrap(act) : x -> Maybe.wrap(() -> chain.apply(x).orElseGet(act));
            }
        }
    }


}
