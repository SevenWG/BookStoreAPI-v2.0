package com.team404.bookstore.service;

import java.util.List;
/*
 * Implementation of Facade Pattern
 * */
public interface AmountCalculatorInterface<T> {
    int CalculateAmount(List<T> list);
}
