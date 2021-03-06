package net.evlikat.games.munchkin.utils;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * Either
 *
 * @author Roman Prokhorov
 * @version 1.0
 */
public abstract class Either<LEFT, RIGHT> {

    public static <LEFT, RIGHT> Either<LEFT, RIGHT> left(LEFT value) {
        return new Left<LEFT, RIGHT>(requireNonNull(value));
    }

    public static <LEFT, RIGHT> Either<LEFT, RIGHT> right(RIGHT value) {
        return new Right<LEFT, RIGHT>(requireNonNull(value));
    }

    // accessed from enclosed class
    Either() {
    }

    public abstract boolean isLeft();

    public abstract boolean isRight();

    public abstract LEFT left();

    public abstract RIGHT right();

    @Override
    public abstract boolean equals(Object object);

    public abstract <T> T fold(Function<LEFT, T> leftFold, Function<RIGHT, T> rightFold);

    /**
     * Returns a hash code for this instance.
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns a string representation for this instance. The form of this string representation is unspecified.
     */
    @Override
    public abstract String toString();

    private static class Left<LEFT, RIGHT> extends Either<LEFT, RIGHT> {
        final LEFT value;

        public Left(LEFT value) {
            this.value = value;
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public LEFT left() {
            return value;
        }

        @Override
        public RIGHT right() {
            throw new IllegalStateException("No right");
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Left<?, ?>) {
                final Left<?, ?> other = (Left<?, ?>) object;
                return value.equals(other.value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "Either.left(" + value + ")";
        }

        @Override
        public <T> T fold(Function<LEFT, T> leftFold, Function<RIGHT, T> rightFold) {
            return leftFold.apply(value);
        }
    }

    private static class Right<LEFT, RIGHT> extends Either<LEFT, RIGHT> {
        final RIGHT value;

        public Right(RIGHT value) {
            this.value = value;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public LEFT left() {
            throw new IllegalStateException("No left");
        }

        @Override
        public RIGHT right() {
            return value;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Right<?, ?>) {
                final Right<?, ?> other = (Right<?, ?>) object;
                return value.equals(other.value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "Either.right(" + value + ")";
        }

        @Override
        public <T> T fold(Function<LEFT, T> leftFold, Function<RIGHT, T> rightFold) {
            return rightFold.apply(value);
        }
    }
}
