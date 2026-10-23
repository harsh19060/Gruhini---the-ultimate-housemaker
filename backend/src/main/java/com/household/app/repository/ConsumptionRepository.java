package com.household.app.repository;
import com.household.app.entity.ConsumptionEvent; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ConsumptionRepository extends JpaRepository<ConsumptionEvent,Long>{ boolean existsByEventKey(String key); List<ConsumptionEvent> findTop100ByOrderByCreatedAtDesc(); }
