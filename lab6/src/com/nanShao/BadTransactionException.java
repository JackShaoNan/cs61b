package com.nanShao;

public class BadTransactionException extends Exception {
    public int withdrawAmmount;

    public BadTransactionException(int badWithdrawAmmount){
        super("Invalid withdraw ammount : "+ badWithdrawAmmount);
        withdrawAmmount = badWithdrawAmmount;
    }
}
