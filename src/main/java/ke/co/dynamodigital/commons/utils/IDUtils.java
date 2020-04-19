package ke.co.dynamodigital.commons.utils;

import org.hashids.Hashids;


/**
 * @author lawrence
 * created 10/29/19 at 1:41 PM
 * <p>
 * Generates unique alphanumeric userId from the given list of numeric ids
 * This is basically a conversion from base 10 to base 36 using a custom alphabet
 * In favor of consistency and human readability the alphabet chosen is A-Z and Numbers 0-9 (case insensitive)
 * Collisions are impossible since this is a one to one mapping
 * A random output for a sequential input is guaranteed by shuffling the provided alphabet using some hashing salt
 * The maximum limit on the domain of the function is imposed by the underlying Library which is 2^53 -1
 * A minimum length of the produced hash can be specified, this is achieved by padding the resulting hash
 * </p>
 **/
public class IDUtils {

    private static String PAWA_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Integer PAWA_IDS_MIN_LENGTH = 5;

    public static String generateUserId(Long primaryKey) {
        String salt = "dGhpc2lzbWFsaXBvaGFzaGlkc2FsdA==";
        return generateUniqueId(PAWA_ALPHABET, salt, PAWA_IDS_MIN_LENGTH, primaryKey);
    }

    public static String generateLoanId(Long primaryKey) {
        String salt = "dGhpc19pc19tYWxpcG9fY2lyY2xlX2lkc19zYWx0";
        return generateUniqueId(PAWA_ALPHABET, salt, PAWA_IDS_MIN_LENGTH, primaryKey);
    }

    public static String generateAccountId(Long primaryKey) {
        String salt = "aGFzaElkSGFzaDEyMzQ1NTY3OGxvbmdoYXNoMjkwOTA5MA==";
        return generateUniqueId(PAWA_ALPHABET, salt, PAWA_IDS_MIN_LENGTH, primaryKey);
    }

    private static String generateUniqueId(String alphabet, String salt, Integer minLength, Long primaryKey) {
        Hashids hashids = new Hashids(salt, minLength, alphabet);
        return hashids.encode(primaryKey);
    }
}
