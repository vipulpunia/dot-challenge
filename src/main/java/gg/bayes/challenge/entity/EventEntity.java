package gg.bayes.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "event")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "event_time")
	private Long eventTime;

	@Column(name = "match_id")
	private Long matchId;

	@Column(name = "action_hero")
	private String eventActionHero;

}
