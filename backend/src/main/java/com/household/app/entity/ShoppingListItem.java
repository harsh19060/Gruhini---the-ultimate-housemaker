package com.household.app.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="shopping_list_items") public class ShoppingListItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne private Item item;
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,precision=14,scale=3) private BigDecimal quantity=BigDecimal.ONE;
 private String unit="item"; private boolean purchased=false; private LocalDateTime createdAt=LocalDateTime.now();
 public ShoppingListItem(){} public ShoppingListItem(Item item,String name,BigDecimal quantity,String unit){this.item=item;this.name=name;this.quantity=quantity;this.unit=unit;} public Long getId(){return id;} public Item getItem(){return item;} public String getName(){return name;} public BigDecimal getQuantity(){return quantity;} public String getUnit(){return unit;} public boolean isPurchased(){return purchased;} public void setPurchased(boolean v){purchased=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}
