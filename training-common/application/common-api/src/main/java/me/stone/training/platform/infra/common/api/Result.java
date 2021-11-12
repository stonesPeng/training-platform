package me.stone.training.platform.infra.common.api;

import lombok.SneakyThrows;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author honor_stone@163.com
 * @description 请求的结果对象
 * @date 2021/11/12 15:57
 */
public interface Result<T> {


    /**
     * 不确定是返回的正确的结果还是异常
     * @param data 结果
     * @param throwable 异常
     * @param <T> 类型
     * @return 结果
     */
    static <T> Result<T> both(T data, @Nullable Throwable throwable) {
        return throwable == null ? ok(data) : new ResultImpl<>(data, throwable);
    }


    /**
     *  Result.wrap(()->{
     *         return <result>;
     *     })
     * @param supplier  supplier
     * @param <T> 类型
     * @return 结果
     */
    static <T> Result<T> wrap(Supplier<T> supplier) {
        try {
            return ok(supplier.get());
        } catch (Throwable throwable) {
            return error(throwable);
        }
    }

    /**
     * 成功的结果
     * @param data 结果
     * @param <T>  类型
     * @return 结果
     */
    static <T> Result<T> ok(T data) {
        return new ResultImpl.SuccessResult<>(data);
    }

    /**
     * 异常的结果
     * @param throwable 异常
     * @param <T> 类型
     * @return 异常
     */
    static <T> Result<T> error(Throwable throwable) {
        return new ResultImpl.ErrorResult<>(throwable);
    }

    /**
     * 结果
     */
    @Nullable T  getResult();

    /**
     * 异常
     */
    @Nullable Throwable getError();

    /**
     *是否结果不为空
     */
    default boolean isPresent() {
        return getResult() != null;
    }

    /**
     * if success,do something
     * @param consumer consumer
     */
    default void ifSuccess(Consumer<@Nullable T> consumer) {
        if (isPresent()) {
            consumer.accept(getResult());
        }
    }

    /**
     * if error,do something
     * @param errorConsumer errorConsumer
     */
    default void ifError(Consumer<@NotNull Throwable> errorConsumer) {
        if (!isPresent()) {
            errorConsumer.accept(getError());
        }
    }

    /**
     * 转换为Optional 或者 抛出异常(如果有异常)
     */
    @SneakyThrows
    default Optional<T> getOrThrow(){
        if (getError() != null) {
            throw getError();
        }
        return Optional.ofNullable(getResult());
    }

    /**
     * 获取结果或者抛出异常(结果可能为Null)
     */
    @SneakyThrows
    default T getOrThrowError() {
        if (getError() != null) {
            throw getError();
        }
        return getResult();
    }

    /**
     * 映射结果
     */
    default <R> Result<R> map(Function<T, R> mapper) {
        Objects.requireNonNull(mapper);
        if (isPresent()) {
            return wrap(() -> mapper.apply(getResult()));
        } else {
            return error(getError());
        }
    }


    /**
     * 默认实现
     * @param <T>
     */
    @ToString
    final class  ResultImpl<T> implements Result<T>{
        private final T result;
        private final Throwable error;

        public ResultImpl(T result, Throwable error) {
            this.result = result;
            this.error = error;
        }

        @Override
        public @Nullable T getResult() {
            return result;
        }

        @Override
        public @Nullable Throwable getError() {
            return error;
        }

        /**
         * 异常的结果
         * @param <T> 类型
         */
        @ToString
        static  class ErrorResult<T> implements  Result<T>{
            final Throwable error;


            ErrorResult(Throwable error) {
                this.error = error;
            }


            @Override
            public @Nullable T getResult() {
                return null;
            }

            @Override
            public @Nullable Throwable getError() {
                return error;
            }

            @Override
            public  boolean isPresent(){
                return false;
            }

            @Override
            public void ifSuccess(Consumer<T> consumer) {
            }

            @Override
            @SneakyThrows
            public Optional<T> getOrThrow() {
                throw error;
            }

            @Override
            public void ifError(Consumer<Throwable> errorConsumer) {
                errorConsumer.accept(error);
            }

            @SneakyThrows
            @Override
            public T getOrThrowError() {
                throw error;
            }
        }

        /**
         * 成功的结果
         * @param <T> 类型
         */
        @ToString
        static class SuccessResult<T> implements Result<T> {
            final T result;

            SuccessResult(T result) {
                this.result = result;
            }

            @Override
            public T getResult() {
                return result;
            }

            @Override
            public Throwable getError() {
                return null;
            }

            @Override
            public boolean isPresent() {
                return true;
            }

            @Override
            public void ifSuccess(Consumer<T> consumer) {
                consumer.accept(result);
            }

            @Override
            public void ifError(Consumer<Throwable> errorConsumer) {

            }

            @Override
            public Optional<T> getOrThrow() {
                return Optional.of(result);
            }

            @Override
            public T getOrThrowError() {
                return result;
            }

        }
    }

}
