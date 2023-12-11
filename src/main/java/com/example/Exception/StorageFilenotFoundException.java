package com.example.Exception;

public class StorageFilenotFoundException extends StorageException{

    public StorageFilenotFoundException(String message) {
        super(message);
    }

    public StorageFilenotFoundException(String message, Exception e) {
        super(message, e);
    }
}
