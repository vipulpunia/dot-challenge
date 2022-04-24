package gg.bayes.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
@Data
@Table(name = "cast_event")
public class CastEventEntity extends EventEntity {

	@Column(name = "spell_name")
	private String spellName;
	@Column(name = "receiver_hero")
	private String receiverHero;

}
