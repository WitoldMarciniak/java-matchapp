package matchApp.user;

import java.util.Objects;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "loggedUser" })
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "registerForm";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registerForm";
		} else {
			UserDto savedUser = userService.save(userDto);
			if (Objects.nonNull(savedUser)) {
				JOptionPane.showMessageDialog(null, "Registered");
				return "redirect:/user/logIn";
			} else {
				JOptionPane.showMessageDialog(null, "This nickname already exist");
				return "homePage";
			}
		}
	}

	@GetMapping("/logIn")
	public String logIn(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "logInForm";
	}

	@PostMapping("/logIn")
	public String logIn(@ModelAttribute("userDto") UserDto userDto, Model model) {
		UserDto foundUser = userService.logIn(userDto);
		if (Objects.nonNull(foundUser)) {
			model.addAttribute("loggedUser", foundUser);
			return "mainPage";
		} else {
			JOptionPane.showMessageDialog(null, "Wrong nickname or password");
			return "homePage";
		}
	}

	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Long id, Model model) {
		UserDto userDto = userService.findOne(id);
		if (Objects.nonNull(userDto)) {
			model.addAttribute("userDto", userDto);
			return "userDetails";
		} else {
			JOptionPane.showMessageDialog(null, "Cannot open");
			return "mainPage";
		}
	}

	@GetMapping("/find")
	public String findPlayer(Model model) {
		return "findUser";
	}

	@PostMapping("/find")
	public String find(@ModelAttribute("name") String name, Model model) {
		UserDto foundUser = userService.findByNickname(name);
		if (Objects.nonNull(foundUser)) {
			model.addAttribute("foundUser", foundUser);
			return "findUser";
		} else {
			JOptionPane.showMessageDialog(null, "Cannot open");
			return "mainPage";
		}
	}
}
