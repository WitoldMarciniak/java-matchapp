package matchApp.score;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import matchApp.user.UserDto;

@Controller
@RequestMapping("/score")
@SessionAttributes("loggedUser")
public class ScoreController {

	private final ScoreService scoreService;

	public ScoreController(ScoreService scoreService) {

		this.scoreService = scoreService;
	}

	@GetMapping("/addScore/{id}")
	public String addScore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("matchId", id);
		model.addAttribute("scoreDto", new ScoreDto());
		return "addScoreForm";

	}

	@PostMapping("/addScore/{id}")
	public String addScore(@Valid @ModelAttribute("scoreDto") ScoreDto scoreDto, BindingResult bindingResult,
			@PathVariable("id") Long id, @SessionAttribute("loggedUser") UserDto userDto) {
		if (bindingResult.hasErrors()) {
			return "addScoreForm";
		} else {
			ScoreDto saved = scoreService.add(scoreDto, id, userDto.getId());
			if (Objects.nonNull(saved)) {
				JOptionPane.showMessageDialog(null, "Added");
			}
			return "redirect:/match/find";
		}

	}

	@GetMapping("/seeScore/{id}")
	public String seeScore(@PathVariable("id") Long id, Model model) {
		ScoreDto scoreDto = scoreService.findScore(id);
		if (Objects.nonNull(scoreDto)) {
			model.addAttribute("score", scoreDto);
		}
		model.addAttribute("id", id);
		return "seeScore";

	}
}
