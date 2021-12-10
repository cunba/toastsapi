package com.sanvalero.toastsapi.service;

import java.time.LocalDate;
import java.util.List;

import com.sanvalero.toastsapi.model.Menu;

public interface MenuService {
    List<Menu> findAllMenus();

    List<Menu> findByDate(LocalDate date);

    List<Menu> findByDateBetween(LocalDate minDate, LocalDate maxDate);

    List<Menu> findByPrice(float price);

    List<Menu> findByPriceBetween(float minPrice, float maxPrice);

    List<Menu> findByPunctuation(float punctuation);

    List<Menu> findByPunctuationBetween(float minPunctuation, float maxPunctuation);

    Menu findById(int id);

    Menu addMenu(Menu menu);

    Menu deleteMenu(Menu menu);

    Menu modifyMenu(Menu menu, int id);
}