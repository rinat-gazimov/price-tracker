package ru.tracker.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private Date created;
    private Date changed;
    private int categoryId;
}
