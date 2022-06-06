package myTest.myExtends;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface Extends extends Grand, List , Set {

    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(Object o);

    @Override
    Iterator iterator();



    @Override
    Object[] toArray();


    @Override
    boolean add(Object o);

    @Override
    boolean remove(Object o);

    @Override
    boolean addAll(Collection c);


    @Override
    boolean addAll(int index, Collection c);


    @Override
    void clear();

    @Override
    Object get(int index);

    @Override
    Object set(int index, Object element);

    @Override
    void add(int index, Object element);

    @Override
    Object remove(int index);

    @Override
    int indexOf(Object o);

    @Override
    int lastIndexOf(Object o);

    @Override
    ListIterator listIterator();

    @Override
    ListIterator listIterator(int index);

    @Override
    List subList(int fromIndex, int toIndex);

    @Override
    default Spliterator spliterator() {
        return List.super.spliterator();
    }


    @Override
    boolean retainAll(Collection c);

    @Override
    boolean removeAll(Collection c);

    @Override
    boolean containsAll(Collection c);

    @Override
    Object[] toArray(Object[] a);

    @Override
    void kk();

    @Override
    default void ll() {
        Grand.super.ll();
    }
}
