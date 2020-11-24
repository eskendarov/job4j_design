package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

class Tree<E> implements SimpleTree<E> {

    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        return findBy(parent).isPresent()
                && findBy(child).isEmpty()
                && root.children.add(new Node<>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        final Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            final Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
