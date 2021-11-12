package me.stone.training.platform.infra.common.api;

import lombok.ToString;
import lombok.With;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

/**
 * @author honor_stone@163.com
 * @description 分页容器, 支持: 1. 标准分页,2.流分页,3.非分页
 * @date 2021/11/12 15:51
 */
public interface Paged<T> {

    /**
     * 数据
     */
    Collection<T> getValue();


    /**
     * 页码
     * 流分页 无页码
     * 非分页 无页码
     */
    default Optional<Integer> getPage(){return Optional.empty();}


    /**
     * 页大小
     * 非分页 无页大小
     */
    default Optional<Integer> getPageSize(){return Optional.empty();}


    /**
     * 总页数
     * 流分页 无总页数
     * 非分页 无总页数
     */
    default Optional<Integer> getTotalPages(){return Optional.empty();}


    /**
     * 总数
     * 流分页 无总数
     * 非分页 无总数
     */
    default Optional<Long> getTotalItems(){return Optional.empty();}

    /**
     * 是否无穷分页
     */
    default boolean isFluent(){return false;}

    /**
     * 是否分页
     */
    default boolean isPaged(){return false;}

    /**
     * 映射当前页数据
     *
     * @param mapper 映射方法
     * @param <R>    输出类型
     */
    default <R> Paged<R> map(Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (T t : getValue()) {
            R r = mapper.apply(t);
            result.add(r);
        }
        if (isFluent()) {
            return new PagedImpl.FluentPagedImpl<>(
                result,
                getPage().orElse(-1),
                getPageSize().orElse(-1)
            );
        } else if (!isPaged()) {
            return new PagedImpl.NoPagedImpl<>(
                result
            );
        } else {
            return new PagedImpl<>(result,
                getPage().orElse(null),
                getPageSize().orElse(null),
                getTotalPages().orElse(null),
                getTotalItems().orElse(null)
            );
        }
    }


    /**
     * 映射所有页数据
     *
     * @param mapper 映射方法
     * @param <R>    输出类型
     */
    default <R> Paged<R> mapAll(@NotNull Function<Collection<T>, List<R>> mapper) {
        final List<R> result = mapper.apply(getValue());
        if (isFluent()) {
            return new PagedImpl.FluentPagedImpl<>(
                result,
                getPage().orElse(-1),
                getPageSize().orElse(-1)
            );
        } else if (!isPaged()) {
            return new PagedImpl.NoPagedImpl<>(
                result
            );
        } else {
            return new PagedImpl<>(result,
                getPage().orElse(null),
                getPageSize().orElse(null),
                getTotalPages().orElse(null),
                getTotalItems().orElse(null)
            );
        }
    }


    /**
     * 复制为新页数据,保留当前分页大小,总页数和总数
     *
     * @param newData 新页数据
     * @param newPage 新页号(非分页和流分页无效)
     * @param <R>     输出类型
     */
    default <R> Paged<R> cloneAs(Collection<R> newData, int newPage) throws IllegalAccessException {
        throw new IllegalAccessException("not supported!");
    }


    /**
     * 自动选择分页
     *
     * @param data        数据
     * @param currentPage 当前页码
     * @param pageSize    分页大小
     * @param totalPage   总页数(可空)
     * @param totalItem   总数(可空,Null时创建流分页)
     * @param <T>         数据类型
     */
    static <T> Paged<T> from(Collection<T> data, int currentPage, int pageSize, Integer totalPage, Long totalItem) {
        if (data == null || data.isEmpty()) {
            if (totalItem == null) return new PagedImpl.EmptyPage<>(currentPage, pageSize, null, null);
            return new PagedImpl.EmptyPage<T>(currentPage, pageSize, totalItem, totalPage == null ? calculatePageCounts(totalItem, pageSize) : totalPage);
        }
        if (totalItem == null) {
            return new PagedImpl.FluentPagedImpl<>(data, currentPage, pageSize);
        }
        return new PagedImpl<>(data,
            currentPage,
            pageSize,
            totalPage == null ? calculatePageCounts(totalItem, pageSize) : totalPage,
            totalItem);
    }

    /**
     * 计算总页数
     *
     * @param totalItem 总数
     * @param pageSize  分页大小
     * @return 总页数
     */
    static int calculatePageCounts(long totalItem, int pageSize) {
        long total = totalItem / pageSize;
        if (total * pageSize < totalItem) {
            return (int) (total + 1);
        }
        return (int) total;
    }

    static <T> Paged<T> empty(int page, int pageSize) {
        return from(Collections.emptyList(), page, pageSize, 0, 0L);
    }


    static <T> Paged<T> from(Collection<T> data) {
        return new PagedImpl.NoPagedImpl<>(data);
    }

    @ToString
    final  class PagedImpl<T> implements Paged<T>{

        final Collection<T> value;

        final Integer pageSize;

        @With final Integer page;

        final Integer totalPage;

        final Long totalItems;

        public PagedImpl(Collection<T> value,
                         Integer pageSize,
                         Integer page,
                         Integer totalPage,
                         Long totalItems){

            this.value = value == null? Collections.EMPTY_LIST :value;
            this.pageSize = pageSize;
            this.page = page;
            this.totalPage = totalPage;
            this.totalItems = totalItems;
        }

        @Override
        public Collection<T> getValue() {
            return value;
        }

        @Override
        public Optional<Integer> getPage() {
            return Optional.of(page);
        }

        @Override
        public Optional<Integer> getPageSize() {
            return Optional.of(pageSize);
        }

        @Override
        public Optional<Integer> getTotalPages() {
            return Optional.of(totalPage);
        }

        @Override
        public Optional<Long> getTotalItems() {
            return Optional.of(totalItems);
        }

        @Override
        public boolean isFluent() {
            return page !=null && pageSize != null && totalItems == null;
        }

        @Override
        public boolean isPaged() {
            return page != null;
        }


        static final class EmptyFluentPage<T> implements Paged<T>{
            @Override
            public Collection<T> getValue() {
                return Collections.emptyList();
            }

            @Override
            public boolean isFluent() {
                return true;
            }
        }

        static final class EmptyPage<T> implements Paged<T> {
            final int page;
            final int pageSize;
            final Long totalItems;
            final Integer totalPages;

            EmptyPage(int page, int pageSize, Long totalItems, Integer totalPages) {
                this.page = page;
                this.pageSize = pageSize;
                this.totalItems = totalItems;
                this.totalPages = totalPages;
            }

            @Override
            public Optional<Integer> getPage() {
                return Optional.of(page);
            }

            @Override
            public Optional<Integer> getPageSize() {
                return Optional.of(pageSize);
            }

            @Override
            public Optional<Integer> getTotalPages() {
                return Optional.of(totalPages);
            }

            @Override
            public Optional<Long> getTotalItems() {
                return Optional.of(totalItems);
            }

            @Override
            public Collection<T> getValue() {
                return Collections.emptyList();
            }

            @Override
            public boolean isPaged() {
                return totalItems != null;
            }

            @Override
            public boolean isFluent() {
                return !isPaged();
            }
        }


        static final class NoPagedImpl<T> implements Paged<T> {
            final Collection<T> value;

            NoPagedImpl(Collection<T> value) {
                this.value = value == null ? Collections.emptyList() : value;
            }

            @Override
            public Collection<T> getValue() {
                return value;
            }


            @Override
            public <R> Paged<R> cloneAs(Collection<R> newData, int newPage) throws IllegalAccessException {
                return new NoPagedImpl<>(newData);
            }
        }

        static final class FluentPagedImpl<T> implements Paged<T> {
            final Collection<T> value;
            @With final int page;
            final int pageSize;

            FluentPagedImpl(Collection<T> value, int page, int pageSize) {
                this.value = value == null ? Collections.emptyList() : value;
                this.page = page;
                this.pageSize = pageSize;
            }

            @Override
            public Collection<T> getValue() {
                return value;
            }

            @Override
            public boolean isPaged() {
                return true;
            }

            @Override
            public boolean isFluent() {
                return false;
            }

            @Override
            public Optional<Integer> getPage() {
                return Optional.of(page);
            }

            @Override
            public Optional<Integer> getPageSize() {
                return Optional.of(pageSize);
            }

            @Override
            public <R> Paged<R> cloneAs(Collection<R> newData, int newPage) throws IllegalAccessException {
                return new FluentPagedImpl<>(newData, newPage, pageSize);
            }
        }
    }


}
