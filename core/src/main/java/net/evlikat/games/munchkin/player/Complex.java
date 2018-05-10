package net.evlikat.games.munchkin.player;

import net.evlikat.games.munchkin.utils.Either;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Complex
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public class Complex<T> {

    private Either<T, Pair<T, T>> value;

    public Complex(T value) {
        this.value = Either.left(value);
    }

    public List<T> all() {
        return value.fold(Collections::singletonList, p -> Arrays.asList(p.getLeft(), p.getRight()));
    }

    public Either<T, Pair<T, T>> getValue() {
        return value;
    }

    public void setValue(Either<T, Pair<T, T>> value) {
        this.value = value;
    }

    public boolean has(Class<? extends T> valueCls) {
        return value.fold(
            r -> r.getClass() == valueCls,
            r -> r.getLeft().getClass() == valueCls || r.getRight().getClass() == valueCls);
    }
}
