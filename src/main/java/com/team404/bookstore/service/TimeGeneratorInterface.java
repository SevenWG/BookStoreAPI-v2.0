package com.team404.bookstore.service;

import java.sql.Timestamp;

public interface TimeGeneratorInterface {
    String GetOrderGenerationTime();
    Timestamp GetTimestampValue();
}
