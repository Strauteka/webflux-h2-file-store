package edu.strauteka.example.coffee;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import static edu.strauteka.example.coffee.Coffee.COFFEE_TABLE;

@Data
@NoArgsConstructor
@Table(value = COFFEE_TABLE)
public class Coffee {
    public static final String COFFEE_TABLE = "COFFEE";
    public static final String COFFEE_TABLE_ID = "id";
    public static final String COFFEE_TABLE_COFFEE = "coffee";
    public static final String COFFEE_TABLE_PRICE = "price";

    @Id
    @Column(value = COFFEE_TABLE_ID)
    private Long id;
    @Column(value = COFFEE_TABLE_COFFEE)
    private String coffee;
    @Column(value = COFFEE_TABLE_PRICE)
    private Double price;
}
