package matchApp.score;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import matchApp.match.Match;
import matchApp.match.MatchRepository;
import matchApp.user.User;
import matchApp.user.UserRepository;

@Service
public class ScoreService {

	private final MatchRepository matchRepository;
	private final UserRepository userRepository;
	private final ScoreRepository scoreRepository;

	public ScoreService(MatchRepository matchRepository, UserRepository userRepository,
			ScoreRepository scoreRepository) {
		this.matchRepository = matchRepository;
		this.userRepository = userRepository;
		this.scoreRepository = scoreRepository;
	}

	public ScoreDto add(ScoreDto scoreDto, Long id, Long userId) {
		Match match = matchRepository.findOne(id);
		Score foundScore = scoreRepository.findScoreByMatchId(id);
		if(Objects.isNull(foundScore)) {
			if (match.getUser().getId() == userId) {
				Score score = new Score();
				score.setGuestScore(scoreDto.getGuestScore());
				score.setHostScore(scoreDto.getHostScore());
				score.setMatch(match);
				Score savedScore = scoreRepository.save(score);
				countUserPoints(savedScore);
				return toDto(savedScore);
			} else {
				JOptionPane.showMessageDialog(null, "You are not a capitan");
				return null;
			}
		}else {
			JOptionPane.showMessageDialog(null, "Score already added");
			return null;
		}
		

	}

	private void countUserPoints(Score score) {
		List<Match> matches = matchRepository.findAllMatchByName(score.getMatch().getName());
		for (Match m : matches) {
			User user = m.getUser();
			Integer startPoints;
			if (user.getPoints() == null) {

				startPoints = 0;
				user.setPoints(startPoints);
			} else {
				startPoints = user.getPoints();
			}

			if (m.getHost() == true) {
				if (score.getHostScore() > score.getGuestScore()) {
					Integer points = startPoints + 3;
					user.setPoints(points);

				} else if (score.getHostScore() < score.getGuestScore()) {
					Integer points = startPoints - 1;
					user.setPoints(points);

				}
			} else {
				if (score.getHostScore() < score.getGuestScore()) {
					Integer points = startPoints + 3;
					user.setPoints(points);

				} else if (score.getHostScore() > score.getGuestScore()) {
					Integer points = startPoints - 1;
					user.setPoints(points);

				}
			}
			userRepository.save(user);

		}
	}

	private ScoreDto toDto(Score score) {
		ScoreDto dto = new ScoreDto();
		dto.setId(score.getId());
		dto.setGuestScore(score.getGuestScore());
		dto.setHostScore(score.getHostScore());
		return dto;
	}

	public ScoreDto findScore(Long id) {
		Score score = scoreRepository.findOne(id);
		if (Objects.nonNull(score)) {
			return toDto(score);
		}
		return null;
	}

}
