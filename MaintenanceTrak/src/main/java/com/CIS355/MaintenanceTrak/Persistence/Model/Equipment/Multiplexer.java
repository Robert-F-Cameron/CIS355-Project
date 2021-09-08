package com.CIS355.MaintenanceTrak.Persistence.Model.Equipment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Multiplexer")
public class Multiplexer extends Equipment{
    @Column(name="type")
    private String type;

    @Column(name="numCards")
    private Integer numCards;

    @OneToMany
    @JoinColumn(name="cardId")
    private List<Card> cards;

    public Multiplexer(String type, Integer numCards) {
        this.type = type;
        this.numCards = numCards;
    }

    public Multiplexer(String equipmentId, String name, String equipmentLocation, Boolean deployed, String type, Integer numCards) {
        super(equipmentId, name, equipmentLocation, deployed);
        this.type = type;
        this.numCards = numCards;
    }

    public Multiplexer(String type, Integer numCards, List<Card> cards) {
        this.type = type;
        this.numCards = numCards;
        this.cards = cards;
    }

    public Multiplexer(String equipmentId, String name, String equipmentLocation, Boolean deployed, String type, Integer numCards, List<Card> cards) {
        super(equipmentId, name, equipmentLocation, deployed);
        this.type = type;
        this.numCards = numCards;
        this.cards = cards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumCards() {
        return numCards;
    }

    public void setNumCards(Integer numCards) {
        this.numCards = numCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
