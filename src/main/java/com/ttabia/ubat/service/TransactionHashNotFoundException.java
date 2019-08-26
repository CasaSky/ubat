package com.ttabia.ubat.service;

class TransactionHashNotFoundException extends Exception {

    public TransactionHashNotFoundException() {
        super("Transaction hash not found. Transaction still pending maybe...");
    }
}
