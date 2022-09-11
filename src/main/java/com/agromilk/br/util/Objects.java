package com.agromilk.br.util;

public final class Objects {
    private Objects() {
        throw new AssertionError("No java.util.Objects instances for you!");
    }
    public static boolean isNull(Object obj) {
        return obj == null;
        }

/**
 * Returns {@code true} if the provided reference is non-{@code null}
 * otherwise returns {@code false}.
 *
 * @apiNote This method exists to be used as a
 * {@link java.util.function.Predicate}, {@code filter(Objects::nonNull)}
 *
 * @param obj a reference to be checked against {@code null}
 * @return {@code true} if the provided reference is non-{@code null}
 * otherwise {@code false}
 *
 * @see java.util.function.Predicate
 * @since 1.8
 */
public static boolean nonNull(Object obj) {
        return obj != null;
        }

    public static <T> int hashCode(T value) {
        return 0;
    }

    public static <T> T requireNonNull(T value) {
        return value;
    }

    public static <T> boolean equals(T value, Object value1) {
        return false;
    }

/**
 * Returns the first argument if it is non-{@code null} and
 * otherwise returns the non-{@code null} second argument.
 *
 * @param obj an object
 * @param defaultObj a non-{@code null} object to return if the first argument
 *                   is {@code null}
 * @param <T> the type of the reference
 * @return the first argument if it is non-{@code null} and
 *        otherwise the second argument if it is non-{@code null}
 * @throws NullPointerException if both {@code obj} is null and
 *        {@code defaultObj} is {@code null}
 * @since 9
 */

    /**
     * If a value is  not present, returns {@code true}, otherwise
     * {@code false}.
     *
     * @return  {@code true} if a value is not present, otherwise {@code false}
     * @since   11
     */

}