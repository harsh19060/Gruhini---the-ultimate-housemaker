package com.household.app.repository;
import com.household.app.entity.Item; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ItemRepository extends JpaRepository<Item,Long>{ Optional<Item> findByRfidUidIgnoreCase(String uid); Optional<Item> findByBarcode(String barcode); boolean existsByRfidUid(String uid); boolean existsByBarcode(String barcode); List<Item> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name,String category); }
