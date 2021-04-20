package com.csse.restapi.restapireact.services;

import com.csse.restapi.restapireact.entities.CardTasks;
import com.csse.restapi.restapireact.entities.Cards;
import com.csse.restapi.restapireact.entities.Items;

import java.util.List;

public interface ItemService {

    List<Items> getAllItems();
    Items addItem(Items item);
    Items getItem(Long id);
    Items saveItem(Items item);
    void deleteItem(Items item);


    List<Cards> getAllCards();
    Cards addCard(Cards card);
    Cards getCard(Long id);
    Cards saveCard(Cards card);
    void deleteCard(Cards card);


    CardTasks addTask(CardTasks task);


    List<CardTasks> getAllTasks();

    CardTasks getTask(Long id);
    CardTasks saveTask(CardTasks task);
}
