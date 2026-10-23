package com.household.app.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="items", indexes=@Index(name="idx_item_rfid", columnList="rfidUid", unique=true))
public class Item {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,length=60) private String category="Other";
 @Column(nullable=false,length=20) private String unit="g";
 @Column(nullable=false,precision=14,scale=3) private BigDecimal stock=BigDecimal.ZERO;
 @Column(nullable=false,precision=14,scale=3) private BigDecimal lowStockThreshold=BigDecimal.ZERO;
 @Column(precision=12,scale=2) private BigDecimal averageUnitPrice=BigDecimal.ZERO;
 @Column(unique=true,length=100) private String rfidUid;
 @Column(unique=true,length=100) private String barcode;
 private LocalDateTime createdAt=LocalDateTime.now();
 public Item() {}
 public Item(String name,String category,String unit,BigDecimal stock,BigDecimal lowStockThreshold,BigDecimal averageUnitPrice,String rfidUid,String barcode){this.name=name;this.category=category;this.unit=unit;this.stock=stock;this.lowStockThreshold=lowStockThreshold;this.averageUnitPrice=averageUnitPrice;this.rfidUid=rfidUid;this.barcode=barcode;}
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getCategory(){return category;} public void setCategory(String v){category=v;} public String getUnit(){return unit;} public void setUnit(String v){unit=v;} public BigDecimal getStock(){return stock;} public void setStock(BigDecimal v){stock=v;} public BigDecimal getLowStockThreshold(){return lowStockThreshold;} public void setLowStockThreshold(BigDecimal v){lowStockThreshold=v;} public BigDecimal getAverageUnitPrice(){return averageUnitPrice;} public void setAverageUnitPrice(BigDecimal v){averageUnitPrice=v;} public String getRfidUid(){return rfidUid;} public void setRfidUid(String v){rfidUid=v;} public String getBarcode(){return barcode;} public void setBarcode(String v){barcode=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}
