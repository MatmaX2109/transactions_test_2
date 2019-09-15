package com.parent.persistence.constants;

/**
 * Tipuri de tranzactii posibile
 */
public enum TypeEnum {
    IBAN_TO_IBAN,
    IBAN_TO_WALLET,
    WALLET_TO_IBAN,
    WALLET_TO_WALLET;
}
