package ru.vtb.Last.entity;

import javax.persistence.*;

@Entity
@Table(name = "premieres")
public class PremiereEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "premieres_id_seq", sequenceName = "premieres_id_seq")
    private Long id;

    @Column(name = "name", length = 64)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "agecategory")
    private Integer ageCategory;
    @Column(name = "numberofseats")
    private Integer numberOfSeats;


    public PremiereEntity() {
    }


    public PremiereEntity(String name, String description, Integer ageCategory, Integer numberOfSeats) {
        this.name = name;
        this.description = description;
        this.ageCategory = ageCategory;
        this.numberOfSeats = numberOfSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(Integer ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Премьера{" +
                "Наименование='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", Возростная категория=" + ageCategory +
                ", Количестиво доступных мест=" + numberOfSeats +
                '}';
    }
}
