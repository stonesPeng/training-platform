package me.stone.training.platform.training.common.tool.utils;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 链式操作工具类
 *
 * @author Zen Liu
 * @version 1.0
 * @apiNote Unit
 * @since 2021-05-19 12:59:12 v1.0.0
 **/
public interface M<S extends M<S, T>, T> extends Supplier<T> {
    <R> M<?, R> with(R value);

    S self();

    default S apply(Consumer<T> act) {
        act.accept(get());
        return self();
    }

    default M<?, T> applyWhen(Predicate<T> condition, Function<T, T> act) {
        if (condition.test(get())) {
            return with(act.apply(get()));
        }
        return self();
    }

    default Optional<S> take(Predicate<T> act) {
        if (!act.test(get())) return Optional.empty();
        return Optional.of(self());
    }

    default Optional<S> takeNot(Predicate<T> act) {
        if (act.test(get())) return Optional.empty();
        return Optional.of(self());
    }

    default <R> M<?, R> let(Function<T, R> act) {
        return with(act.apply(get()));
    }

    default <R> M<?, R> let(Predicate<T> condition, Function<T, R> act, Function<T, R> elseAct) {
        return with(condition.test(get()) ? act.apply(get()) : elseAct.apply(get()));
    }


    default S loop(int n, Consumer<T> act) {
        for (int i = 0; i < n; i++) {
            act.accept(get());
        }
        return self();
    }

    default S loop(int n, int step, Consumer<T> act) {
        for (int i = 0; i < n; i += step) {
            act.accept(get());
        }
        return self();
    }

    default S when(Predicate<T> condition, Consumer<T> act) {
        while (condition.test(get())) {
            act.accept(get());
        }
        return self();
    }


    default void run(Consumer<T> act) {
        act.accept(get());
    }

    default <R> R exec(Function<T, R> act) {
        return act.apply(get());
    }

    interface Q<S extends Q<S, T>, T> extends M<S, Collection<T>> {

        <R> Q<?, R> withSeq(Collection<R> value);

        default S every(Consumer<T> act) {
            get().forEach(act);
            return self();
        }

        default <R> Q<?, R> map(Function<T, R> mapping) {
            List<R> list = new ArrayList<>();
            for (T t : get()) {
                R r = mapping.apply(t);
                list.add(r);
            }
            return withSeq(list);
        }

        default <R> Q<?, R> mapSet(Function<T, R> mapping) {
            Set<R> set = new HashSet<>();
            for (T t : get()) {
                R r = mapping.apply(t);
                set.add(r);
            }
            return withSeq(set);
        }
    }

    static <T> M<?, T> of(T src) {
        return new Monad<>(src);
    }

    static <R> Q<?, R> seq(Collection<R> src) {
        return new MonadSequence<>(src);
    }

    @SafeVarargs
    static <R> Q<?, R> seqOf(R... src) {
        return new MonadSequence<>(Arrays.asList(src));
    }

    @AllArgsConstructor(staticName = "of")
    class MonadSequence<T> implements Q<MonadSequence<T>, T> {
        protected final Collection<T> value;

        @Override
        public Collection<T> get() {
            return value;
        }


        @Override
        public <R> M<?, R> with(R value) {
            return new Monad<>(value);
        }

        @Override
        public MonadSequence<T> self() {
            return this;
        }


        @Override
        public <X> Q<?, X> withSeq(Collection<X> value) {
            return new MonadSequence<>(value);
        }
    }

    @AllArgsConstructor(staticName = "of")
    class Monad<T> implements M<Monad<T>, T> {
        protected final T value;

        @Override
        public T get() {
            return value;
        }


        @Override
        public <R> M<?, R> with(R value) {
            return new Monad<>(value);
        }

        @Override
        public Monad<T> self() {
            return this;
        }
    }
}
