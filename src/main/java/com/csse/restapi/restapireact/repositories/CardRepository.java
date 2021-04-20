package com.csse.restapi.restapireact.repositories;

import com.csse.restapi.restapireact.entities.Cards;
import com.csse.restapi.restapireact.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Cards, Long> {



}
