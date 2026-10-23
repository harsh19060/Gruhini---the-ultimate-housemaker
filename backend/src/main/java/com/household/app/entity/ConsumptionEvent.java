package com.household.app.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="consumption_events", uniqueConstraints=@UniqueConstraint(name="uk_consumption_event_key",columnNames="eventKey"))
public class ConsumptionEvent {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) private Item item;
 @Column(nullable=false,precision=14,scale=3) private BigDecimal initialWeightGrams;
 @Column(nullable=false,precision=14,scale=3) private BigDecimal finalWeightGrams;
 @Column(nullable=false,precision=14,scale=3) private BigDecimal consumedGrams;
 @Column(nullable=false,unique=true,length=100) private String eventKey;
 private LocalDateTime createdAt=LocalDateTime.now();
 public ConsumptionEvent(){} public ConsumptionEvent(Item item,BigDecimal initialWeightGrams,BigDecimal finalWeightGrams,BigDecimal consumedGrams,String eventKey){this.item=item;this.initialWeightGrams=initialWeightGrams;this.finalWeightGrams=finalWeightGrams;this.consumedGrams=consumedGrams;this.eventKey=eventKey;}
 public Long getId(){return id;} public Item getItem(){return item;} public BigDecimal getInitialWeightGrams(){return initialWeightGrams;} public BigDecimal getFinalWeightGrams(){return finalWeightGrams;} public BigDecimal getConsumedGrams(){return consumedGrams;} public String getEventKey(){return eventKey;} public LocalDateTime getCreatedAt(){return createdAt;}
}
