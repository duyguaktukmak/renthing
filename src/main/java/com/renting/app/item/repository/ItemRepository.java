package com.renting.app.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.renting.app.common.ItemState;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{

	List<ItemEntity> findByState(ItemState state);
	List<ItemEntity> findItemByUser_Username(String username);
}
