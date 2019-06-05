package matchApp.user;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	public UserDto save(UserDto dto) {
		User foundUser = userRepository.findUserByNickname(dto.getNickname());
		if (Objects.isNull(foundUser)) {
			User savedUser = userRepository.save(toDomain(dto));
			if (Objects.nonNull(savedUser)) {
				return toDto(savedUser);
			}
		}
		return null;
	}

	public UserDto logIn(UserDto userDto) {
		User foundedUser = userRepository.findUserByNickname(userDto.getNickname());
		if (Objects.nonNull(foundedUser)) {
			if (userDto.getPassword().equals(foundedUser.getPassword())) {
				return toDto(foundedUser);
			}
		}
		return null;
	}

	private User toDomain(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setNickname(dto.getNickname());
		user.setPassword(dto.getPassword());
		user.setPoints(dto.getPoints());
		return user;
	}

	private UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setNickname(user.getNickname());
		dto.setPassword(user.getPassword());
		dto.setPoints(user.getPoints());
		return dto;

	}

	public UserDto findOne(Long id) {
		User foundUser = userRepository.findOne(id);
		if (Objects.nonNull(foundUser)) {
			return toDto(foundUser);
		}
		return null;
	}

	public UserDto findByNickname(String name) {
		User user = userRepository.findUserByNickname(name);
		if (Objects.nonNull(user)) {
			return toDto(user);
		}
		return null;
	}

}
