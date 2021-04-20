package com.csse.restapi.restapireact.services.impl;

import com.csse.restapi.restapireact.entities.CardTasks;
import com.csse.restapi.restapireact.entities.Cards;
import com.csse.restapi.restapireact.entities.Items;
import com.csse.restapi.restapireact.repositories.CardRepository;
import com.csse.restapi.restapireact.repositories.ItemRepository;
import com.csse.restapi.restapireact.repositories.TaskRepository;
import com.csse.restapi.restapireact.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Items addItem(Items item) {
        return itemRepository.save(item);
    }

    @Override
    public Items getItem(Long id) {
        Optional<Items> opt = itemRepository.findById(id);
        return opt.isPresent()?opt.get():null;
    }

    @Override
    public Items saveItem(Items item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Items item) {
        itemRepository.delete(item);
    }

    @Override
    public List<Cards> getAllCards() {
        return cardRepository.findAll ();
    }

    @Override
    public Cards addCard(Cards card) {
        return cardRepository.save (card);
    }

    @Override
    public Cards getCard(Long id) {
        Optional<Cards> opt = cardRepository.findById(id);
        return opt.isPresent()?opt.get():null;

    }

    @Override
    public Cards saveCard(Cards card) {
        return cardRepository.save (card);
    }

    @Override
    public void deleteCard(Cards card) {
        cardRepository.delete (card);
    }

    @Override
    public CardTasks addTask(CardTasks task) {
        return taskRepository.save (task);
    }

    @Override
    public List<CardTasks> getAllTasks() {
        return taskRepository.findAll ();
    }

    @Override
    public CardTasks getTask(Long id) {
        Optional<CardTasks> opt = taskRepository.findById(id);
        return opt.isPresent()?opt.get():null;
    }

    @Override
    public CardTasks saveTask(CardTasks task) {
        return taskRepository.save (task);
    }
}
