package gg.bayes.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
@Data
@Table(name = "kill_event")
public class KillEventEntity extends EventEntity {

	@Column(name = "receiver_hero")
	private String receiverHero;

}
