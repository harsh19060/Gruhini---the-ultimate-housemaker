package com.household.app.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="expenses") public class Expense {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String description;
 @Column(nullable=false,length=60) private String category="Other";
 @Column(nullable=false,precision=12,scale=2) private BigDecimal amount;
 private LocalDateTime spentAt=LocalDateTime.now();
 public Expense(){} public Expense(String d,String c,BigDecimal a){description=d;category=c;amount=a;} public Long getId(){return id;} public String getDescription(){return description;} public String getCategory(){return category;} public BigDecimal getAmount(){return amount;} public LocalDateTime getSpentAt(){return spentAt;}
}
