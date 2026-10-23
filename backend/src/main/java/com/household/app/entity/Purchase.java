package com.household.app.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="purchases") public class Purchase {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Item item;
 @Column(nullable=false,precision=14,scale=3) private BigDecimal quantity;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal totalPrice;
 private String storeName; private LocalDateTime purchasedAt=LocalDateTime.now();
 public Purchase(){} public Purchase(Item i,BigDecimal q,BigDecimal p,String store){item=i;quantity=q;totalPrice=p;storeName=store;}
 public Long getId(){return id;} public Item getItem(){return item;} public BigDecimal getQuantity(){return quantity;} public BigDecimal getTotalPrice(){return totalPrice;} public String getStoreName(){return storeName;} public LocalDateTime getPurchasedAt(){return purchasedAt;}
}
