package matchApp.score;

import org.springframework.format.annotation.NumberFormat;

import matchApp.match.MatchDto;

public class ScoreDto {
	
	private Long id;
	@NumberFormat
	private Integer hostScore;
	@NumberFormat
	private Integer guestScore;
	
	private MatchDto matchDto;
	
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
	public MatchDto getMatchDto() {
		return matchDto;
	}
	public void setMatchDto(MatchDto matchDto) {
		this.matchDto = matchDto;
	}
}
