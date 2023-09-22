package com.clover.labs.cards;

public class CreditCardNumberValidator {

    // Return true if the card number is valid
    public static boolean check(long cardNumber) {
        return (getTotalNumberOfDigits(cardNumber) >= 13 && getTotalNumberOfDigits(cardNumber) <= 16) &&
                (isPrefixMatched(cardNumber, 3) || isPrefixMatched(cardNumber, 4) || isPrefixMatched(cardNumber, 5) || isPrefixMatched(cardNumber, 6)) &&
                ((sumEvenAndDouble(cardNumber) + sumOdd(cardNumber)) % 10 == 0);
    }

    // Return the number of digits in d
    public static int getTotalNumberOfDigits(long d) {
        return String.valueOf(d).length();
    }

    // Return true if the digit d is a prefix for cardNumber
    // 3 is American Express, 4 is Visa, 5 is Master Card, 6 is a Discover Card.
    public static boolean isPrefixMatched(long cardNumber, int prefix) {
        return getPrefix(cardNumber, getTotalNumberOfDigits(prefix)) == prefix;
    }

    // Return the first k number of digits from number.
    // If the number of digits in number is less than k, return number.
    public static long getPrefix(long cardNumber, int k) {
        if (getTotalNumberOfDigits(cardNumber) > k) {
            String num = cardNumber + "";
            return Long.parseLong(num.substring(0, k));
        }
        return cardNumber;
    }


    // Get the result from Step 2
    public static int sumEvenAndDouble(long cardNumber) {
        int sum = 0;
        String num = cardNumber + "";
        for (int i = getTotalNumberOfDigits(cardNumber) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
        return sum;
    }

    // Return this cnumber if it is a single digit, otherwise, return the sum of the two digits
    public static int getDigit(int cardNumber) {
        if (cardNumber < 9)
            return cardNumber;
        return cardNumber / 10 + cardNumber % 10;
    }

    // Return sum of odd-place digits in cnumber
    public static int sumOdd(long cardNumber) {
        int sum = 0;
        String num = cardNumber + "";
        for (int i = getTotalNumberOfDigits(cardNumber) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }




}
