package ke.co.dynamodigital.commons.utils;

import lombok.experimental.UtilityClass;

/**
 * @author lawrence
 * created 17/01/2020 at 11:22
 **/
@UtilityClass
public class ComparableUtils {


    /**
     * Check if value equals ref
     * @param value value to check
     * @param ref reference value to compare against
     * @param <T> type
     * @return true if value equals ref, false otherwise
     */
    public <T extends Comparable<T>> boolean isEqualTo(T value, T ref) {
        return value.compareTo(ref) == 0;
    }

    /**
     * Check if value greater than ref
     * @param value value to check
     * @param ref reference value to compare against
     * @param <T> type
     * @return true if value greater than ref, false otherwise
     */
    public <T extends Comparable<T>> boolean isGreaterThan(T value, T ref) {
        return value.compareTo(ref) > 0;
    }

    /**
     * Check if value less than ref
     * @param value value to check
     * @param ref reference value to compare against
     * @param <T> type
     * @return true if value less than ref, false otherwise
     */
    public <T extends Comparable<T>> boolean isLessThan(T value, T ref) {
        return value.compareTo(ref) < 0;
    }

    /**
     * Check if value greater than start and value less than end, inclusive
     * @param value value to check
     * @param start lower limit
     * @param end higher limit
     * @param <T> type
     * @throws IllegalArgumentException when end less than start
     * @return true if value is in specified range, false otherwise
     */
    public <T extends Comparable<T>> boolean isBetween(T value, T start, T end) throws IllegalArgumentException{
        if(isLessThan(end,start)) throw new IllegalArgumentException("end must be greater than start");
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

}
