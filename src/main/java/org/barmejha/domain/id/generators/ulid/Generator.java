package org.barmejha.domain.id.generators.ulid;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;

public class Generator {
  private Generator() {}
  // New alphabet with 26 characters
  private static final char[] ENCODING_CHARS = "0123456789ACEFGHJMPRSTVXYZ".toCharArray();
  private static final SecureRandom RANDOM = new SecureRandom();
  // For full 128 bits: 48 bits timestamp + 80 bits randomness
  // We need 11 digits for the timestamp (since 26^10 < 2^48 ≤ 26^11)
  // and 18 digits for the randomness (since 26^17 < 2^80 ≤ 26^18)
  // Total length = 11 + 18 = 29 characters.
  private static final int ULID_LENGTH = 29;
  private static final int TIMESTAMP_LENGTH = 11; // positions 0 to 10
  private static final int RANDOM_LENGTH = 18;    // positions 11 to 28

  public static String generateID() {
    // Step 1: Get the current timestamp (48 bits)
    long timestamp = Instant.now().toEpochMilli();

    // Step 2: Create a char array to hold the ULID
    char[] ulid = new char[ULID_LENGTH];

    // Step 3: Encode the timestamp into the first 11 characters (base-26)
    encodeTimestamp(timestamp, ulid);

    // Step 4: Generate 10 random bytes (80 bits)
    byte[] randomBytes = new byte[10];
    RANDOM.nextBytes(randomBytes);

    // Step 5: Encode the random bytes into the last 18 characters (base-26)
    encodeRandom(randomBytes, ulid);

    // Step 6: Convert the char array to a String and return
    return new String(ulid);
  }

  // Encodes the 48-bit timestamp into the first 11 characters using base-26
  private static void encodeTimestamp(long timestamp, char[] ulid) {
    // Fill from rightmost (least-significant) to left.
    for (int i = TIMESTAMP_LENGTH - 1; i >= 0; i--) {
      int remainder = (int) (timestamp % 26);
      ulid[i] = ENCODING_CHARS[remainder];
      timestamp /= 26;
    }
  }

  // Encodes the 80-bit random value into the last 18 characters using base-26
  private static void encodeRandom(byte[] randomBytes, char[] ulid) {
    // Use BigInteger to represent the 80-bit random value.
    BigInteger randomValue = new BigInteger(1, randomBytes); // positive value

    // Fill positions ULID_LENGTH-1 down to TIMESTAMP_LENGTH.
    for (int i = ULID_LENGTH - 1; i >= TIMESTAMP_LENGTH; i--) {
      BigInteger[] divRem = randomValue.divideAndRemainder(BigInteger.valueOf(26));
      ulid[i] = ENCODING_CHARS[divRem[1].intValue()];
      randomValue = divRem[0];
    }
  }
}
