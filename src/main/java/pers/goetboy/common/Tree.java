package pers.goetboy.common;

import lombok.Data;

import java.util.Collection;
import java.util.TreeSet;

/**
 * 对象树
 *
 * @author:goetb
 * @date 2019 /02 /18
 **/
@Data
public class Tree<T> {
    T entity;
    TreeSet<Tree<T>> children;

    public Tree() {
        this.children = new TreeSet<>();
    }

    public Tree(T t) {
        this.entity = t;
        this.children = new TreeSet<>();
    }

    /**
     * @param t    节点对象
     * @param list 子节点对象列表
     */
    public Tree(T t, Collection<? extends T> list) {
        this.entity = t;
        this.children = new TreeSet<>();
        list.forEach(child -> children.add(new Tree(child)));
    }

    public void addAll(Collection<? extends T> list) {
        list.forEach(t -> children.add(new Tree(t)));
    }

    public void remove(T t) {
        this.children.remove(t);
    }
}
