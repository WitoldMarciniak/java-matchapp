package matchApp.score;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import matchApp.match.Match;

@Entity
public class Score {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer hostScore;
	private Integer guestScore;
	@OneToOne
	private Match match;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getHostScore() {
		return hostScore;
	}
	public void setHostScore(Integer hostScore) {
		this.hostScore = hostScore;
	}
	public Integer getGuestScore() {
		return guestScore;
	}
	public void setGuestScore(Integer guestScore) {
		this.guestScore = guestScore;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
}
