package matchApp.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDto {
	
	private Long id;
	@Size(min=5)
	@NotNull
	private String nickname;
	@NotNull
	private String password;
	private Integer points;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	




}
