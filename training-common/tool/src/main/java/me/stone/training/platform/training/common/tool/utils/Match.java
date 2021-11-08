package me.stone.training.platform.training.common.tool.utils;

import org.jetbrains.annotations.NotNull;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * {@link Match} is something with pattern match style code helper.
 * <p>
 * <b>Note</b> here is no default type of {@link Match} exists. <br/>
 * {@link Match} have two evaluation type: <br/>
 * 1. eager : mean it's branch will been executed as it be defined. <br/>
 * if match with a {@link Supplier} target,the supplier will been executed at same time as well. <br/>
 * 2. lazy : mean it's branch will been executed when a {@link Supplier#get()} be called, if it match target is a {@link Supplier},
 * the supplier will be evaluation at begin of all branch evaluation.<br/>
 * {@link Match} have two result type: <br/>
 * 1. void {@see {@link Void}} <br/>
 * 2. value of specific type <br/>
 * As defines we got 4 type of matches, which they are: <br/>
 * 1. {@link EagerVoidMatch} extends {@link Runnable}: eager evaluation with none value return match.<br/>
 * 2. {@link LazyVoidMatch} extends {@link Runnable}: lazy evaluation with none value return match.<br/>
 * 3. {@link EagerValueMatch} extends {@link Supplier}: eager evaluation with  have value returned match.<br/>
 * 4. {@link LazyValueMatch} extends {@link Supplier}: lazy evaluation with have value returned match.<br/>
 * </p>
 *
 * @author zenliu
 * @version 1.0
 * @since 7/14/20 11:12 AM
 **/
public interface Match<T> {
    /**
     * create a none return value eager match case sugar class with value
     * default factory with {@link EagerVoidMatch}
     *
     * @param value match value
     * @param <T>   value type
     * @return {@link EagerVoidMatch}
     */
    static <T> EagerVoidMatch<T> on(T value) {
        return new EagerVoidMatch.EagerVoidMatchImpl<>(value);
    }

    /**
     * create a none return value eager match case sugar class with value
     * default factory with {@link EagerVoidMatch}
     *
     * @param supplier match value supplier
     * @param <T>      value type
     * @return {@link EagerVoidMatch}
     */
    static <T> EagerVoidMatch<T> on(Supplier<T> supplier) {
        return new EagerVoidMatch.EagerVoidMatchImpl<>(supplier);
    }


    /**
     * create a  return value lazy match case sugar class with value
     * default factory with {@link LazyValueMatch}
     *
     * @param value match value
     * @param <T>   match value type
     * @param <F>   result value type
     * @return {@link LazyValueMatch}
     */
    static <T, F> LazyValueMatch<T, F> of(T value) {
        return new LazyValueMatch.LazyValueMatchImpl<>(value);
    }

    /**
     * create a  return value lazy match case sugar class with value
     * default factory with {@link LazyValueMatch}
     *
     * @param supplier match value supplier
     * @param <T>      match value type
     * @param <F>      result value type
     * @return {@link LazyValueMatch}
     */
    static <T, F> LazyValueMatch<T, F> of(Supplier<T> supplier) {
        return new LazyValueMatch.LazyValueMatchImpl<>(supplier);
    }

    /**
     * create a none return value eager match case sugar class with value
     *
     * @param value match value
     * @param <T>   value type
     * @return {@link EagerVoidMatch}
     */
    static <T> EagerVoidMatch<T> eagerOn(T value) {
        return new EagerVoidMatch.EagerVoidMatchImpl<>(value);
    }

    /**
     * create a none return value eager match case sugar class with value
     *
     * @param supplier match value supplier
     * @param <T>      value type
     * @return {@link EagerVoidMatch}
     */
    static <T> EagerVoidMatch<T> eagerOn(Supplier<T> supplier) {
        return new EagerVoidMatch.EagerVoidMatchImpl<>(supplier);
    }

    /**
     * create a none return value lazy match case sugar class with value
     *
     * @param value match value
     * @param <T>   value type
     * @return {@link LazyVoidMatch}
     */
    static <T> LazyVoidMatch<T> lazyOn(T value) {
        return new LazyVoidMatch.LazyVoidMatchImpl<>(value);
    }

    /**
     * create a none return value lazy match case sugar class with value
     *
     * @param supplier match value supplier
     * @param <T>      value type
     * @return {@link LazyVoidMatch}
     */
    static <T> LazyVoidMatch<T> lazyOn(Supplier<T> supplier) {
        return new LazyVoidMatch.LazyVoidMatchImpl<>(supplier);
    }

    /**
     * create a  return value eager match case sugar class with value
     *
     * @param value match value
     * @param <T>   match value type
     * @param <F>   result value type
     * @return {@link EagerValueMatch}
     */
    static <T, F> EagerValueMatch<T, F> eagerOf(T value) {
        return new EagerValueMatch.EagerValueMatchImpl<>(value);
    }

    /**
     * create a  return value eager match case sugar class with value
     *
     * @param supplier match value supplier
     * @param <T>      match value type
     * @param <F>      result value type
     * @return {@link EagerValueMatch}
     */

    static <T, F> EagerValueMatch<T, F> eagerOf(Supplier<T> supplier) {
        return new EagerValueMatch.EagerValueMatchImpl<>(supplier);
    }

    /**
     * create a  return value lazy match case sugar class with value
     *
     * @param value match value
     * @param <T>   match value type
     * @param <F>   result value type
     * @return {@link LazyValueMatch}
     */
    static <T, F> LazyValueMatch<T, F> lazyOf(T value) {
        return new LazyValueMatch.LazyValueMatchImpl<>(value);
    }

    /**
     * create a  return value lazy match case sugar class with value
     *
     * @param supplier match value supplier
     * @param <T>      match value type
     * @param <F>      result value type
     * @return {@link LazyValueMatch}
     */
    static <T, F> LazyValueMatch<T, F> lazyOf(Supplier<T> supplier) {
        return new LazyValueMatch.LazyValueMatchImpl<>(supplier);
    }

    /**
     * LazyVoidCase will eval all branches only on call for {@link Runnable#run()}
     */
    interface LazyVoidMatch<T> extends Match<T>, Runnable {
        /**
         * type match conditional branch
         *
         * @param targetType target class
         * @param consumer   consumer accept targetType
         * @return this
         */
        <R> LazyVoidMatch<T> caseIs(final @NotNull Class<R> targetType, final @NotNull Consumer<R> consumer);

        /**
         * predicate conditional branch
         *
         * @param predicate target predicate
         * @param consumer  consumer accept T
         * @return this
         */
        LazyVoidMatch<T> caseMatch(final @NotNull Predicate<T> predicate, final @NotNull Consumer<T> consumer);

        /**
         * value equals conditional branch
         *
         * @param value    target value
         * @param consumer consumer  accept T
         * @return this
         */
        LazyVoidMatch<T> caseEqual(final @NotNull T value, final @NotNull Consumer<T> consumer);

        /**
         * converter conditional branch
         *
         * @param converter target converter
         * @param consumer  consumer accept R
         * @param <R>       convert result type
         * @return this
         */
        <R> LazyVoidMatch<T> caseOption(final @NotNull Function<T, Optional<R>> converter, final @NotNull Consumer<R> consumer);

        /**
         * null conditional branch
         *
         * @param consumer consumer
         * @return this
         */
        LazyVoidMatch<T> caseNull(final @NotNull Consumer<T> consumer);

        /**
         * final branch
         *
         * @param consumer supplier
         */
        Runnable caseElse(final @NotNull Consumer<T> consumer);

        final class LazyVoidMatchImpl<T> implements LazyVoidMatch<T> {
            private final List<Tuple2<Predicate<T>, Consumer<T>>> stack = new ArrayList<>();
            private final T value;
            private final Supplier<T> valueSupplier;

            LazyVoidMatchImpl(T value) {
                this.value = value;
                this.valueSupplier = null;
            }

            LazyVoidMatchImpl(Supplier<T> valueSupplier) {
                this.value = null;
                this.valueSupplier = valueSupplier;
            }

            /**
             * type match conditional branch
             *
             * @param targetType target class
             * @param consumer   consumer accept targetType
             * @return this
             */
            @SuppressWarnings("unchecked")
            @Override
            public <R> LazyVoidMatch<T> caseIs(@NotNull Class<R> targetType, @NotNull Consumer<R> consumer) {
                stack.add(Tuple.tuple(targetType::isInstance, v -> consumer.accept((R) v)));
                return this;
            }

            /**
             * predicate conditional branch
             *
             * @param predicate target predicate
             * @param consumer  consumer accept T
             * @return this
             */
            @Override
            public LazyVoidMatch<T> caseMatch(@NotNull Predicate<T> predicate, @NotNull Consumer<T> consumer) {
                stack.add(Tuple.tuple(predicate, consumer));
                return this;
            }

            /**
             * value equals conditional branch
             *
             * @param value    target value
             * @param consumer consumer  accept T
             * @return this
             */
            @Override
            public LazyVoidMatch<T> caseEqual(@NotNull T value, @NotNull Consumer<T> consumer) {
                stack.add(Tuple.tuple(value::equals, consumer));
                return this;
            }

            @Override
            public <R> LazyVoidMatch<T> caseOption(@NotNull Function<T, Optional<R>> converter, @NotNull Consumer<R> consumer) {
                stack.add(Tuple.tuple(c -> converter.apply(c).isPresent(), c -> converter.apply(c).ifPresent(consumer)));
                return this;
            }

            /**
             * null conditional branch
             *
             * @param consumer consumer
             * @return this
             */
            @Override
            public LazyVoidMatch<T> caseNull(@NotNull Consumer<T> consumer) {
                stack.add(Tuple.tuple(Objects::isNull, consumer));
                return this;
            }

            /**
             * final branch
             *
             * @param consumer supplier
             */
            @Override
            public Runnable caseElse(@NotNull Consumer<T> consumer) {
                stack.add(Tuple.tuple(v -> true, consumer));
                return this;
            }

            /**
             * must call {@link LazyVoidMatch#run()} to start eval.
             */
            @Override
            public void run() {
                final T value = this.valueSupplier != null ? valueSupplier.get() : this.value;
                for (Tuple2<Predicate<T>, Consumer<T>> pair : stack) {
                    if (pair.v1.test(value)) {
                        pair.v2.accept(value);
                    }
                }
            }
        }

    }

    /**
     * LazyValueCase will eval all branches only on call for {@link Supplier#get()}
     **/
    interface LazyValueMatch<T, F> extends Match<T>, Supplier<Optional<F>> {
        /**
         * type match conditional branch
         *
         * @param targetType target class
         * @param mapper     convert T to F
         * @return this
         */
        <R> LazyValueMatch<T, F> caseIs(final @NotNull Class<R> targetType, final @NotNull Function<R, F> mapper);

        /**
         * predicate conditional branch
         *
         * @param predicate target predicate
         * @param mapper    convert T to F
         * @return this
         */
        LazyValueMatch<T, F> caseMatch(final @NotNull Predicate<T> predicate, final @NotNull Function<T, F> mapper);

        /**
         * value equals conditional branch
         *
         * @param value  target value
         * @param mapper convert T to F
         * @return this
         */
        LazyValueMatch<T, F> caseEqual(final @NotNull T value, final @NotNull Function<T, F> mapper);

        /**
         * converter conditional branch
         *
         * @param converter target converter
         * @param mapper    mapper accept R->F
         * @param <R>       convert result type
         * @return this
         */
        <R> LazyValueMatch<T, F> caseOption(final @NotNull Function<T, Optional<R>> converter, final @NotNull Function<R, F> mapper);

        /**
         * null conditional branch
         *
         * @param supplier supplier
         * @return this
         */
        LazyValueMatch<T, F> caseNull(final @NotNull Supplier<F> supplier);

        /**
         * final branch
         *
         * @param supplier supplier
         * @return supplier of F
         */
        Supplier<Optional<F>> caseElse(final @NotNull Supplier<F> supplier);

        final class LazyValueMatchImpl<T, F> implements LazyValueMatch<T, F> {
            private final List<Tuple2<Predicate<T>, Function<T, F>>> stack = new ArrayList<>();
            private final T value;
            final Supplier<T> valueSupplier;

            LazyValueMatchImpl(Supplier<T> valueSupplier) {
                this.valueSupplier = valueSupplier;
                this.value = null;
            }

            LazyValueMatchImpl(T value) {
                this.value = value;
                this.valueSupplier = null;
            }

            /**
             * type match conditional branch
             *
             * @param targetType target class
             * @param mapper     convert T to F
             * @return this
             */
            @SuppressWarnings("unchecked")
            @Override
            public <R> LazyValueMatch<T, F> caseIs(@NotNull Class<R> targetType, @NotNull Function<R, F> mapper) {
                stack.add(Tuple.tuple(targetType::isInstance, v -> mapper.apply((R) v)));
                return this;
            }

            /**
             * predicate conditional branch
             *
             * @param predicate target predicate
             * @param mapper    convert T to F
             * @return this
             */
            @Override
            public LazyValueMatch<T, F> caseMatch(@NotNull Predicate<T> predicate, @NotNull Function<T, F> mapper) {
                stack.add(Tuple.tuple(predicate, mapper));
                return this;
            }

            /**
             * value equals conditional branch
             *
             * @param value  target value
             * @param mapper convert T to F
             * @return this
             */
            @Override
            public LazyValueMatch<T, F> caseEqual(@NotNull T value, @NotNull Function<T, F> mapper) {
                stack.add(Tuple.tuple(value::equals, mapper));
                return this;
            }

            @Override
            public <R> LazyValueMatch<T, F> caseOption(@NotNull Function<T, Optional<R>> converter, @NotNull Function<R, F> mapper) {
                stack.add(Tuple.tuple(c -> converter.apply(c).isPresent(), converter.andThen(Optional::get).andThen(mapper)));
                return this;
            }

            /**
             * null conditional branch
             *
             * @param supplier supplier
             * @return this
             */
            @Override
            public LazyValueMatch<T, F> caseNull(@NotNull Supplier<F> supplier) {
                stack.add(Tuple.tuple(Objects::isNull, v -> supplier.get()));
                return this;
            }

            /**
             * final branch
             *
             * @param supplier supplier
             * @return supplier of F
             */
            @Override
            public Supplier<Optional<F>> caseElse(@NotNull Supplier<F> supplier) {
                stack.add(Tuple.tuple(v -> true, v -> supplier.get()));
                return this;
            }

            /**
             * Gets a result.
             *
             * @return a result
             */
            @Override
            public Optional<F> get() {
                final T value = this.valueSupplier != null ? valueSupplier.get() : this.value;
                for (Tuple2<Predicate<T>, Function<T, F>> pair : stack) {
                    if (pair.v1.test(value)) {
                        return Optional.ofNullable(pair.v2.apply(value));
                    }
                }
                return Optional.empty();
            }
        }

    }

    /**
     * EagerVoidCase will eval branch on it is added.
     * call of {@link Runnable#run()} is optional.
     *
     * @author zenliu
     * @version 1.0
     * @since 7/16/20 5:24 PM
     **/
    interface EagerVoidMatch<T> extends Match<T>, Runnable {
        /**
         * type match conditional branch
         *
         * @param targetType target class
         * @param consumer   consumer accept targetType
         * @return this
         */
        <R> EagerVoidMatch<T> caseIs(final @NotNull Class<R> targetType, final @NotNull Consumer<R> consumer);

        /**
         * predicate conditional branch
         *
         * @param predicate target predicate
         * @param consumer  consumer accept T
         * @return this
         */
        EagerVoidMatch<T> caseMatch(final @NotNull Predicate<T> predicate, final @NotNull Consumer<T> consumer);

        /**
         * converter conditional branch
         *
         * @param converter target converter
         * @param consumer  consumer accept R
         * @param <R>       convert result type
         * @return this
         */
        <R> EagerVoidMatch<T> caseOption(final @NotNull Function<T, Optional<R>> converter, final @NotNull Consumer<R> consumer);

        /**
         * value equals conditional branch
         *
         * @param value    target value
         * @param consumer consumer  accept T
         * @return this
         */
        EagerVoidMatch<T> caseEqual(final @NotNull T value, final @NotNull Consumer<T> consumer);

        /**
         * null conditional branch
         *
         * @param consumer consumer
         * @return this
         */
        EagerVoidMatch<T> caseNull(final @NotNull Consumer<T> consumer);

        void caseElse(@NotNull Consumer<T> consumer);

        final class EagerVoidMatchImpl<T> implements EagerVoidMatch<T> {
            private boolean consumed = false;
            private final T value;

            EagerVoidMatchImpl(Supplier<T> value) {
                this.value = value.get();
            }

            EagerVoidMatchImpl(T value) {
                this.value = value;
            }


            /**
             * type match conditional branch
             *
             * @param targetType target class
             * @param consumer   consumer accept targetType
             * @return this
             */
            @SuppressWarnings("unchecked")
            @Override
            public <R> EagerVoidMatch<T> caseIs(@NotNull Class<R> targetType, @NotNull Consumer<R> consumer) {
                if (!consumed && targetType.isInstance(value)) {
                    consumer.accept((R) value);
                    consumed = true;
                }
                return this;
            }

            /**
             * predicate conditional branch
             *
             * @param predicate target predicate
             * @param consumer  consumer accept T
             * @return this
             */
            @Override
            public EagerVoidMatch<T> caseMatch(@NotNull Predicate<T> predicate, @NotNull Consumer<T> consumer) {
                if (!consumed && predicate.test(value)) {
                    consumer.accept(value);
                    consumed = true;
                }
                return this;
            }

            @Override
            public <R> EagerVoidMatch<T> caseOption(@NotNull Function<T, Optional<R>> converter, @NotNull Consumer<R> consumer) {
                if (!consumed) {
                    final Optional<R> status = converter.apply(value);
                    if (status.isPresent()) {
                        consumer.accept(status.get());
                        consumed = true;
                    }
                }
                return this;
            }

            /**
             * value equals conditional branch
             *
             * @param value    target value
             * @param consumer consumer  accept T
             * @return this
             */
            @Override
            public EagerVoidMatch<T> caseEqual(@NotNull T value, @NotNull Consumer<T> consumer) {
                if (!consumed && this.value.equals(value)) {
                    consumer.accept(value);
                    consumed = true;
                }
                return this;
            }

            /**
             * null conditional branch
             *
             * @param consumer consumer
             * @return this
             */
            @Override
            public EagerVoidMatch<T> caseNull(@NotNull Consumer<T> consumer) {
                if (!consumed) {
                    consumer.accept(value);
                    consumed = true;
                }
                return this;
            }

            /**
             * final branch
             *
             * @param consumer supplier
             */
            @Override
            public void caseElse(@NotNull Consumer<T> consumer) {
                if (!consumed) consumer.accept(value);
            }

            /**
             * eval by call run is not need with {@link EagerVoidMatch}
             *
             * @see Thread#run()
             */
            @Override
            public void run() {

            }
        }
    }

    /**
     * EagerValueCase will eval branch on it is added.
     * call of {@link Supplier#get()} will got the final value.
     **/

    interface EagerValueMatch<T, F> extends Match<T>, Supplier<Optional<F>> {
        /**
         * type match conditional branch
         *
         * @param targetType target class
         * @param mapper     convert T to F
         * @return this
         */
        <R> EagerValueMatch<T, F> caseIs(final @NotNull Class<R> targetType, final @NotNull Function<R, F> mapper);

        /**
         * predicate conditional branch
         *
         * @param predicate target predicate
         * @param mapper    convert T to F
         * @return this
         */
        EagerValueMatch<T, F> caseMatch(final @NotNull Predicate<T> predicate, final @NotNull Function<T, F> mapper);

        /**
         * converter conditional branch
         *
         * @param converter target converter
         * @param mapper    mapper accept R->F
         * @param <R>       convert result type
         * @return this
         */
        <R> EagerValueMatch<T, F> caseOption(final @NotNull Function<T, Optional<R>> converter, final @NotNull Function<R, F> mapper);

        /**
         * value equals conditional branch
         *
         * @param value  target value
         * @param mapper convert T to F
         * @return this
         */
        EagerValueMatch<T, F> caseEqual(final @NotNull T value, final @NotNull Function<T, F> mapper);

        /**
         * null conditional branch
         *
         * @param supplier supplier
         * @return this
         */
        EagerValueMatch<T, F> caseNull(final @NotNull Supplier<F> supplier);

        /**
         * final branch
         *
         * @param supplier supplier
         * @return supplier of F
         */
        Supplier<Optional<F>> caseElse(final @NotNull Supplier<F> supplier);

        final class EagerValueMatchImpl<T, F> implements EagerValueMatch<T, F> {
            private Supplier<F> finalSupplier = null;
            private final T value;

            EagerValueMatchImpl(Supplier<T> value) {
                this.value = value.get();
            }

            EagerValueMatchImpl(T value) {
                this.value = value;
            }

            /**
             * type match conditional branch
             *
             * @param targetType target class
             * @param mapper     convert T to F
             * @return this
             */
            @SuppressWarnings("unchecked")
            @Override
            public <R> EagerValueMatch<T, F> caseIs(@NotNull Class<R> targetType, @NotNull Function<R, F> mapper) {
                if (finalSupplier == null && targetType.isInstance(value))
                    finalSupplier = () -> mapper.apply((R) value);
                return this;
            }

            /**
             * predicate conditional branch
             *
             * @param predicate target predicate
             * @param mapper    convert T to F
             * @return this
             */
            @Override
            public EagerValueMatch<T, F> caseMatch(@NotNull Predicate<T> predicate, @NotNull Function<T, F> mapper) {
                if (finalSupplier == null && predicate.test(value))
                    finalSupplier = () -> mapper.apply(value);
                return this;
            }

            @Override
            public <R> EagerValueMatch<T, F> caseOption(@NotNull Function<T, Optional<R>> converter, @NotNull Function<R, F> mapper) {
                if (finalSupplier == null) {
                    final Optional<R> status = converter.apply(value);
                    status.ifPresent(r -> finalSupplier = () -> mapper.apply(r));
                }
                return this;
            }

            /**
             * value equals conditional branch
             *
             * @param value  target value
             * @param mapper convert T to F
             * @return this
             */
            @Override
            public EagerValueMatch<T, F> caseEqual(@NotNull T value, @NotNull Function<T, F> mapper) {
                if (finalSupplier == null && this.value.equals(value))
                    finalSupplier = () -> mapper.apply(value);
                return this;
            }

            /**
             * null conditional branch
             *
             * @param supplier supplier
             * @return this
             */
            @Override
            public EagerValueMatch<T, F> caseNull(@NotNull Supplier<F> supplier) {
                if (finalSupplier == null && this.value == null)
                    finalSupplier = supplier;
                return this;
            }

            /**
             * final branch
             *
             * @param supplier supplier
             * @return supplier of F
             */
            @Override
            public Supplier<Optional<F>> caseElse(@NotNull Supplier<F> supplier) {
                if (finalSupplier == null)
                    finalSupplier = supplier;
                return this;
            }

            /**
             * Gets a result.
             *
             * @return a result
             */
            @Override
            public Optional<F> get() {
                return Optional.ofNullable(finalSupplier.get());
            }
        }

    }

}
