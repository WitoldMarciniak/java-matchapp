package matchApp.match;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	
	public List<Match> findAllByDisciplineAndDateTimeAndCapitan(String discipline, LocalDateTime date, Boolean capitan);
	public List<Match> findAllByDisciplineAndCapitan(String discipline, Boolean capitan);
	public List<Match> findAllByNameAndDisciplineAndCapitan(String name, String discipline, Boolean capitan);
	public List<Match> findAllByNameAndDisciplineAndDateTimeAndCapitan(String name, String discipline, LocalDateTime date, Boolean capitan);
	@Query(value = "SELECT*FROM Matches where user_id=?1 and dateTime>=now()", nativeQuery = true)
	public List<Match> findAllMatchByUserIdAndDate(Long id);
	@Query(value = "SELECT*FROM Matches where user_id=?1 and dateTime<now()", nativeQuery = true)
	public List<Match> findAllPastMatch(Long id);
	public Match findMatchByName(String name);
	public List<Match> findAllMatchByName(String name);
	public Match findMatchByNameAndUserId(String name, Long id);
}
