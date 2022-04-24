package gg.bayes.challenge.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
@Data
@Table(name = "buy_event")
public class BuyEventEntity extends EventEntity {

	private String item;

}
