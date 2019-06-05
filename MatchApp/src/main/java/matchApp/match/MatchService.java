package matchApp.match;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import matchApp.user.User;
import matchApp.user.UserDto;
import matchApp.user.UserRepository;

@Service
public class MatchService {

	private final MatchRepository matchRepository;
	private final UserRepository userRepository;

	public MatchService(MatchRepository matchRepository, UserRepository userRepository) {

		this.matchRepository = matchRepository;
		this.userRepository = userRepository;

	}

	public void add(MatchDto matchDto, UserDto userDto) {

		List<Match> listOfMatchForUser = matchRepository.findAllMatchByUserIdAndDate(userDto.getId());

		if (listOfMatchForUser.size() < 3) {
			Match foundMatch = matchRepository.findMatchByName(matchDto.getName());
			if (Objects.isNull(foundMatch)) {
				matchDto.setCapitan(true);
				matchDto.setUserDto(userDto);
				matchDto.setHost(true);
				matchRepository.save(toDomain(matchDto));

			} else {
				JOptionPane.showMessageDialog(null, "This match name already exist");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Cannot add, you can join max 3 matches");
		}
	}

	private Match toDomain(MatchDto dto) {
		Match match = new Match();
		match.setId(dto.getId());
		match.setDateTime(dto.getDateTime());
		match.setName(dto.getName());
		User user = userRepository.findOne(dto.getUserDto().getId());
		match.setUser(user);
		match.setCapitan(dto.getCapitan());
		match.setDiscipline(dto.getDiscipline());
		match.setHost(dto.getHost());
		return match;
	}

	private MatchDto toDto(Match match) {
		MatchDto dto = new MatchDto();
		dto.setCapitan(match.getCapitan());
		dto.setDateTime(match.getDateTime());
		dto.setId(match.getId());
		dto.setName(match.getName());
		dto.setDiscipline(match.getDiscipline());
		dto.setUserDto(toDtoUser(match.getUser()));
		dto.setHost(match.getHost());
		return dto;
	}

	private UserDto toDtoUser(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setNickname(user.getNickname());
		dto.setPassword(user.getPassword());

		return dto;

	}

	private User toDomainUser(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setNickname(dto.getNickname());
		user.setPassword(dto.getPassword());

		return user;
	}

	public List<MatchDto> findMatch(String name, String discipline, String date) {
		List<Match> matches = new ArrayList<>();
		LocalDateTime formattedDate;

		if (date.equals("")) {
			if (name.equals("")) {
				matches = matchRepository.findAllByDisciplineAndCapitan(discipline, true);
			} else {
				matches = matchRepository.findAllByNameAndDisciplineAndCapitan(name, discipline, true);
			}
		} else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				formattedDate = LocalDateTime.parse(date, formatter);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Add correct date");
				return null;
			}

			if (name.equals("")) {
				matches = matchRepository.findAllByDisciplineAndDateTimeAndCapitan(discipline, formattedDate, true);
			} else {
				matches = matchRepository.findAllByNameAndDisciplineAndDateTimeAndCapitan(name, discipline,
						formattedDate, true);
			}

		}

		if (Objects.nonNull(matches)) {
			List<MatchDto> matchesDto = new ArrayList<>();
			for (Match m : matches) {

				matchesDto.add(toDto(m));
			}
			return matchesDto;
		}
		return null;
	}

	public MatchDto addDate(String date) {
		List<Match> matches = matchRepository.findAll();
		Match match = matches.get(matches.size() - 1);
		if (match.getDateTime() == null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
				match.setDateTime(formattedDate);
				matchRepository.save(match);
				return toDto(match);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Add correct date");
				return null;
			}
		} else {
			JOptionPane.showMessageDialog(null, "First fill the name of match and choose discipline ");
			return null;
		}

	}

//tutaj podzial gospodarze i nie

	public List<MatchDto> findHostPlayers(String name) {
		List<Match> players = matchRepository.findAllMatchByName(name);
		if (Objects.nonNull(players)) {
			List<MatchDto> hostPlayersDto = new ArrayList<>();

			for (Match p : players) {
				if (p.getHost() == true) {
					hostPlayersDto.add(toDto(p));
				}

			}

			return hostPlayersDto;
		}

		return null;
	}

	public List<MatchDto> findGuestPlayers(String name) {
		List<Match> players = matchRepository.findAllMatchByName(name);
		if (Objects.nonNull(players)) {

			List<MatchDto> guestPlayersDto = new ArrayList<>();
			for (Match p : players) {
				if (p.getHost() == false) {
					guestPlayersDto.add(toDto(p));
				}
			}

			return guestPlayersDto;
		}

		return null;
	}

	public MatchDto joinMatch(String name, UserDto userDto, String role) {
		List<Match> foundMatches = matchRepository.findAllMatchByName(name);
		int temp = 0;
		int team = 0;

//sprawdzanie czy juz nie jest w druzynie
		for (Match m : foundMatches) {
			if (m.getUser().getNickname().equals(userDto.getNickname())) {
				temp++;
			}
		}
//sprawdzanie czy nie jest zapisany do 3
		List<Match> oneUserMatches = matchRepository.findAllMatchByUserIdAndDate(userDto.getId());
		if (oneUserMatches.size() >= 3) {
			temp++;
		}

		if (temp == 0) {

			if (role.equals("host")) {
				for (Match m : foundMatches) {
					if (m.getHost() == true) {
						team++;
					}
				}
			} else {
				for (Match m : foundMatches) {
					if (m.getHost() == false) {
						team++;
					}
				}
			}

			if (foundMatches.get(0).getDiscipline().equals("Football")) {
				if (team < 11) {
					return toDto(saveJoinedMatch(userDto, foundMatches.get(0), role));
				} else {
					JOptionPane.showMessageDialog(null, "This team is completed");
					return null;
				}
			} else if (foundMatches.get(0).getDiscipline().equals("Basketball")) {
				if (team < 5) {
					return toDto(saveJoinedMatch(userDto, foundMatches.get(0), role));
				} else {
					JOptionPane.showMessageDialog(null, "This team is completed");
					return null;
				}
			} else {
				if (team < 6) {
					return toDto(saveJoinedMatch(userDto, foundMatches.get(0), role));
				} else {
					JOptionPane.showMessageDialog(null, "This team is completed");
					return null;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Already joined this team");
			return null;
		}

	}

	// dolaczenie do meczu metoda wykorzystywana w metodzie joinMatch
	private Match saveJoinedMatch(UserDto userDto, Match foundMatches, String role) {
		Match match = new Match();
		match.setUser(toDomainUser(userDto));
		match.setCapitan(false);
		match.setDateTime(foundMatches.getDateTime());
		match.setDiscipline(foundMatches.getDiscipline());
		match.setName(foundMatches.getName());
		if (role.equals("host")) {
			match.setHost(true);
		} else {
			match.setHost(false);
		}
		Match saved = matchRepository.save(match);

		return saved;
	}

	public List<MatchDto> findFutureMatch(UserDto userDto) {
		List<Match> futureMatches = matchRepository.findAllMatchByUserIdAndDate(userDto.getId());
		if (Objects.nonNull(futureMatches)) {
			List<MatchDto> futureMatchesDto = new ArrayList<>();
			for (Match m : futureMatches) {
				futureMatchesDto.add(toDto(m));
			}
			return futureMatchesDto;
		}
		return null;
	}

	public List<MatchDto> findPastMatch(UserDto userDto) {
		List<Match> pastMatches = matchRepository.findAllPastMatch(userDto.getId());
		if (Objects.nonNull(pastMatches)) {
			List<MatchDto> pastMatchesDto = new ArrayList<>();
			for (Match m : pastMatches) {
				pastMatchesDto.add(toDto(m));
			}
			return pastMatchesDto;
		}
		return null;
	}

	public void remove(String matchName, Long userId) {
		Match match = matchRepository.findMatchByNameAndUserId(matchName, userId);
		if (Objects.nonNull(match)) {

			if (match.getDateTime().isBefore(LocalDateTime.now().plusDays(3))) {
				JOptionPane.showMessageDialog(null, "Cannot remove, date is too close");
			} else {
				matchRepository.delete(match);
				JOptionPane.showMessageDialog(null, "Removed");
			}
		} else {
			JOptionPane.showMessageDialog(null, "You did not join this team");
		}

	}

}
