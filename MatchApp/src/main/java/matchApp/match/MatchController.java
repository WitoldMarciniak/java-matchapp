package matchApp.match;

import java.util.List;
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
@SessionAttributes({ "loggedUser" })
@RequestMapping("/match")
public class MatchController {

	private final MatchService matchService;

	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("matchDto", new MatchDto());
		return "addMatchForm";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("matchDto") MatchDto matchDto, BindingResult bindingResult,
			@SessionAttribute("loggedUser") UserDto userDto) {
		if (bindingResult.hasErrors()) {
			return "addMatchForm";
		}
		matchService.add(matchDto, userDto);
		return "addMatchForm";

	}

	@PostMapping("setDate")
	public String addMatchDate(@ModelAttribute("dateTime") String date) {
		MatchDto saved = matchService.addDate(date);
		if (Objects.nonNull(saved)) {
			JOptionPane.showMessageDialog(null, "Added");
		}
		return "mainPage";

	}

	@GetMapping("/find")
	public String findMatch() {
		return "findMatchForm";
	}

	@PostMapping("/find")
	public String findMatch(Model model, @ModelAttribute("name") String name,
			@ModelAttribute("discipline") String discipline, @ModelAttribute("date") String date) {

		List<MatchDto> foundMatches = matchService.findMatch(name, discipline, date);
		if (Objects.isNull(foundMatches)) {
			JOptionPane.showMessageDialog(null, "No matches found");
		} else {
			model.addAttribute("matches", foundMatches);
		}
		return "findMatchForm";
	}

	@GetMapping("/details/{name}")
	public String matchDetails(@PathVariable("name") String name, Model model, @SessionAttribute("loggedUser") UserDto userDto) {
		List<MatchDto> hostPlayers = matchService.findHostPlayers(name);
		List<MatchDto> guestPlayers = matchService.findGuestPlayers(name);
		model.addAttribute("guestPlayers", guestPlayers);
		model.addAttribute("hostPlayers", hostPlayers);
		model.addAttribute("matchName", hostPlayers.get(0).getName());
		model.addAttribute("userId", userDto.getId());
		return "matchDetails";
	}

	@GetMapping("/join/{name}/{role}")
	public String joinMatch(@PathVariable("name") String name, @SessionAttribute("loggedUser") UserDto dto,
			@PathVariable("role") String role) {
		MatchDto matchDto = matchService.joinMatch(name, dto, role);
		if (Objects.nonNull(matchDto)) {
			JOptionPane.showMessageDialog(null, "Joined");
		}
		return "redirect:/match/details/" + name;
	}

	@GetMapping("/future")
	public String futureMatch(@SessionAttribute("loggedUser") UserDto userDto, Model model) {
		List<MatchDto> futureMatches = matchService.findFutureMatch(userDto);
		if(Objects.nonNull(futureMatches)) {
			model.addAttribute("matches", futureMatches);
			return "myMatches";
		}
		JOptionPane.showMessageDialog(null, "No matches found");
		return "mainPage";
	}
	
	@GetMapping("/past")
	public String pastMatch(@SessionAttribute("loggedUser") UserDto userDto, Model model) {
		List<MatchDto> futureMatches = matchService.findPastMatch(userDto);
		if(Objects.nonNull(futureMatches)) {
			model.addAttribute("matches", futureMatches);
			return "myMatches";
		}
		JOptionPane.showMessageDialog(null, "No matches found");
		return "mainPage";
	}
	
	@GetMapping("/remove/{name}/{id}")
	public String removeFromMatch(@PathVariable("name") String matchName, @PathVariable("id") Long userId) {
		matchService.remove(matchName,userId);
		return "mainPage";
	}
}
