package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {

    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        final Optional<Node<E>> nodeParent = findBy(parent);
        return nodeParent.isPresent() && findBy(child).isEmpty()
                && nodeParent.get().children.add(new Node<>(child));
    }

    private Optional<Node<E>> search(Predicate<Node<E>> condition) {
        final Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            final Node<E> el = data.poll();
            if (condition.test(el)) {
                return Optional.of(el);
            }
            data.addAll(el.children);
        }
        return Optional.empty();
    }

    public boolean isBinary() {
        return search(el -> el.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return search(el -> el.value.equals(value));
    }
}
