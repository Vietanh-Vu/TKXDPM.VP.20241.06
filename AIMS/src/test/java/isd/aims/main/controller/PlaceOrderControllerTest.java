package isd.aims.main.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlaceOrderControllerTest {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest(name = "#{index} - Test phone number validation with {0}")
    @CsvSource({
            // Test valid phone numbers
            "'0987654321', true",           // Valid 10-digit phone number
            "'09876543210', false",         // Invalid: 11 digits (exceeds max length)
            "'098765432', false",           // Invalid: 9 digits (below min length)
            "'0a87654321', false",          // Invalid character (letter)
            "'1234567890', false",           // start with 0
            "'0.987.654.321', true",         // valid
            "'0/987/654/321', true",         // valid
            "'0-987-654-321', false",         // valid
            "'0-987.654321', false",         // ManySeparatorTypes
            "'0-987/654321', false",        // ManySeparatorTypes
            "'0.987/654321', false",          // ManySeparatorTypes
            "'0..987654321', false",            // InvalidSeparatorPosition
            "'0--987654321', false",             // InvalidSeparatorPosition
            "'0//987654321', false"             // InvalidSeparatorPosition
    })
    void validatePhoneNumber(String phoneNumber, boolean expected) {
        boolean actual = placeOrderController.validatePhoneNumber(phoneNumber);
        assertEquals(expected, actual, "Phone validation failed for: " + phoneNumber);
    }

    @ParameterizedTest(name = "#{index} - Test null phone validation")
    @NullSource
    void validateNullPhoneNumber(String phoneNumber) {
        boolean actual = placeOrderController.validatePhoneNumber(phoneNumber);
        assertFalse(actual, "Null phone number should be invalid");
    }

    @ParameterizedTest(name = "#{index} - Test address validation with {0}")
    @CsvSource({
            // TC006 - Valid address with max length (100 chars)
            "'Abc/0123deAbc/0123deAbc/0123deAbc/0123deAbc/0123deAbc/0123deAbc/0123deAbc/0123deAbc/0123deAbc/0123de', true",

            // TC007 - Valid address with 99 chars
            "'AbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbc', true",

            // TC008 - Invalid character
            "., false",

            // TC009 - Exceed max length (101 chars)
            "'AbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAbcAb', false",

    })
    void validateAddress(String address, boolean expected) {
        boolean actual = placeOrderController.validateAddress(address);
        assertEquals(expected, actual, "Address validation failed for: " + address);
    }

    @ParameterizedTest(name = "#{index} - Test null address validation")
    @NullSource
    void validateNullAddress(String address) {
        boolean actual = placeOrderController.validateAddress(address);
        assertFalse(actual, "Null address should be invalid");
    }

    @ParameterizedTest
    @CsvSource({
            // Test cases for valid names
            "abcabcabcabcabcabcabcabcabcabc, true",   // TC001 - valid 30 chars
            "abcabcabcabcabcabcabcabcabcab, true",    // TC003 - valid 29 chars

            // Test cases for invalid names
            "abcabcabcabcabcabcabcabcabcab., false",  // TC002 - invalid character
            "abcabcabcabcabcabcabcabcabcabcd, false", // TC004 - too long (31 chars)
    })
    void validateName(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        assertEquals(expected, isValid);
    }

    @ParameterizedTest(name = "#{index} - Test null address validation")
    @NullSource
    void validateNullName(String name) {
        boolean actual = placeOrderController.validateAddress(name);
        assertFalse(actual, "Null name should be invalid");
    }

    @ParameterizedTest(name = "#{index} - Test delivery info validation")
    @MethodSource("provideDeliveryInfoTestCases")
    void validateDeliveryInfo(String name, String phoneNumber, String address, boolean expected) throws Exception {
        HashMap<String, String> info = new HashMap<>();
        info.put("name", name);
        info.put("phone", phoneNumber);
        info.put("address", address);

        boolean actual = placeOrderController.validateDeliveryInfo(info);
        assertEquals(expected, actual, "Delivery info validation failed");
    }

    private static Stream<Arguments> provideDeliveryInfoTestCases() {
        return Stream.of(
                Arguments.of("ABCDEF", "0987654321", "Hanoi", true),
                Arguments.of("ABCDEF", "1987654321", "Da Nang", false),
                Arguments.of("", "0987654321", "Da Nang", false),
                Arguments.of("ABCDEF", "0987654321", ".", false)
        );
    }
}