package com.sproutsync.domain.meal;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meal")
class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_day_id", nullable = false)
    private MenuDay menuDay;

    @ManyToOne
    @JoinColumn(name = "meal_type_id", nullable = false)
    private MealType mealType;

    @Column(length = 500)
    private String description;
}
