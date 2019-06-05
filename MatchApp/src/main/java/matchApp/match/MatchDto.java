package matchApp.match;

import java.time.LocalDateTime;
import matchApp.user.UserDto;

public class MatchDto {
	
	private Long id;
	private String name;
	private LocalDateTime dateTime;
	private Boolean capitan;
	private UserDto userDto;
	private String discipline;
	private Boolean host;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getCapitan() {
		return capitan;
	}
	public void setCapitan(Boolean capitan) {
		this.capitan = capitan;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Boolean getHost() {
		return host;
	}
	public void setHost(Boolean host) {
		this.host = host;
	}

}
