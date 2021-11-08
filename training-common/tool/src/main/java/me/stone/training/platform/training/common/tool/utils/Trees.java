package me.stone.training.platform.training.common.tool.utils;

import lombok.val;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <a href='https://github.com/ZenLiuCN/java-units/blob/master/src/main/java/units/util/Trees.java'>see original</a>
 *
 * @author Zen.Liu
 * @apiNote
 * @since 2021-05-22
 */
public interface Trees {


    interface Builder<T> {
        boolean isBuilt();

        Builder<T> reset();

        Builder<T> build(BiFunction<List<T>, T, Integer> parentDecider);

        <R> Set<R> toTree(Function<T, R> ctor, BiConsumer<R, R> addChild);
    }

    static <T> Builder<T> builder(List<T> element) {
        return new TreeBuilder<>(element);
    }

    final class TreeBuilder<T> implements Builder<T> {
        static final class N {
            int p = -1;
            final int l;
            final Set<N> children = new HashSet<>();

            N(int l) {
                this.l = l;
            }

            N setP(int i) {
                p = i;
                return this;
            }
        }

        final List<T> elements;
        final List<N> nodes = new ArrayList<>();

        TreeBuilder(List<T> elements) {
            this.elements = elements;
        }

        @Override
        public TreeBuilder<T> reset() {
            nodes.clear();
            return this;
        }

        public boolean isBuilt() {
            return !nodes.isEmpty();
        }

        public synchronized TreeBuilder<T> build(BiFunction<List<T>, T, Integer> parentDecider) {
            val m = new ArrayList<N>();
            for (int i = 0; i < elements.size(); i++) {
                m.add(new N(i));
            }
            for (int i = 0; i < elements.size(); i++) {
                val r = parentDecider.apply(elements, elements.get(i));
                if (r == null || r < 0) continue;
                m.get(r).children.add(m.get(i).setP(r));
            }
            for (N n : m) {
                if (n.p == -1) {
                    nodes.add(n);
                }
            }
            return this;
        }

        public <R> Set<R> toTree(Function<T, R> ctor, BiConsumer<R, R> addChild) {
            if (nodes.isEmpty()) return Collections.emptySet();
            val roots = new HashSet<R>();
            val nQ = new ArrayDeque<N>();
            val rQ = new ArrayDeque<R>();
            for (N node : nodes) {
                val r = ctor.apply(elements.get(node.l));
                roots.add(r);
                nQ.add(node);
                rQ.add(r);
                while (!nQ.isEmpty()) {
                    val n = nQ.pop();
                    if (n.children.isEmpty()) {
                        rQ.pop();
                    } else {
                        val nr = rQ.pop();
                        for (N child : n.children) {
                            val cR = ctor.apply(elements.get(child.l));
                            addChild.accept(nr, cR);
                            nQ.push(child);
                            rQ.push(cR);
                        }
                    }
                }
            }
            return roots;
        }
    }
}

