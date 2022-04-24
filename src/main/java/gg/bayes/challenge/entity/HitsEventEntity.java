package gg.bayes.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
@Data
@Table(name = "hit_event")
public class HitsEventEntity extends EventEntity {

	@Column(name = "receiver_hero")
	private String receiverHero;
	@Column(name = "damage_count")
	private int damageCount;
	@Column(name = "damage_by")
	private String damageBy;

}
