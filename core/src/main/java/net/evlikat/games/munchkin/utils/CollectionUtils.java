package net.evlikat.games.munchkin.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * CollectionUtils
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class CollectionUtils {

    public static <T, E extends Comparable<E>> List<T> allMaxOf(Stream<? extends T> stream, Function<T, E> toComparableFunction) {
        return stream.reduce(new ArrayList<>(),
            toList(toComparableFunction),
            mergeLists(toComparableFunction));
    }

    public static <T, E extends Comparable<E>> BiFunction<List<T>, T, List<T>> toList(Function<T, E> toComparableFunction) {
        return (l, e) -> {
            if (l.isEmpty()) {
                l.add(e);
            } else {
                E bonusMax = toComparableFunction.apply(l.get(0));
                E bonusCurrent = toComparableFunction.apply(e);
                switch (bonusMax.compareTo(bonusCurrent)) {
                    case -1:
                        l.clear();
                        l.add(e);
                        break;
                    case 0:
                        l.add(e);
                        break;
                }
            }
            return l;
        };
    }

    public static <T, E extends Comparable<E>> BinaryOperator<List<T>> mergeLists(Function<T, E> toComparableFunction) {
        return (l1, l2) -> {
            if (!l1.isEmpty() && l2.isEmpty()) {
                return l1;
            }
            if (l1.isEmpty() && !l2.isEmpty()) {
                return l2;
            }
            if (l1.isEmpty()) {
                return l1;
            }
            E v1 = toComparableFunction.apply(l1.get(0));
            E v2 = toComparableFunction.apply(l2.get(0));
            switch (v1.compareTo(v2)) {
                case -1:
                    return l2;
                case 0:
                    l1.addAll(l2);
                    return l1;
                case 1:
                default:
                    return l1;
            }
        };
    }

}
