package haojiwu.clockwiseexercise;

import com.google.common.collect.ImmutableSet;
import javafx.util.Pair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class ClockwiseexerciseApplication {
	static Pair<List<Set<Integer>>, Integer> bestMeetingsWithSize(List<Set<Integer>> meetings) {
		Collections.sort(meetings, new Comparator<Set<?>>() {
			@Override
			public int compare(Set<?> o1, Set<?> o2) {
				return Integer.valueOf(o2.size()).compareTo(o1.size());
			}
		});
		Pair<List<Set<Integer>>, Integer> best = tryToFindBestMeetingsWithSizeBySortedMeetings(meetings);
		int aveSize = meetings.stream().mapToInt(Set::size).sum()/meetings.size();
		for (Set<Integer> meeting: meetings) {
			if (meeting.size() > aveSize) {
				List<Set<Integer>> filteredMeetings = meetings.stream()
								.filter(m -> m != meeting)
								.collect(Collectors.toList());
				Pair<List<Set<Integer>>, Integer> maybeBetter = tryToFindBestMeetingsWithSizeBySortedMeetings(filteredMeetings);
				if (maybeBetter.getValue() > best.getValue()) {
					best = maybeBetter;
				}
			}
		}
		return best;

	}

	static Pair<List<Set<Integer>>, Integer> tryToFindBestMeetingsWithSizeBySortedMeetings(List<Set<Integer>> meetings) {
		List<Set<Integer>> bestMeetings = new ArrayList<>();
		Set<Integer> allAttendees = new HashSet<>();
		for (Set<Integer> meeting: meetings) {
			if (Collections.disjoint(meeting, allAttendees)) {
				bestMeetings.add(meeting);
				allAttendees.addAll(meeting);
			}
		}

		return new Pair(bestMeetings, allAttendees.size());
	}

	public static void main(String[] args) {
		SpringApplication.run(ClockwiseexerciseApplication.class, args);

		//[[0,1],[0],[1]]
		List<Set<Integer>> input1 = Arrays.asList(ImmutableSet.of(0, 1), ImmutableSet.of(0), ImmutableSet.of(1));
		printMeetings("input1", input1);
		printBestMeetings("output1", bestMeetingsWithSize(input1));

		//[[0,1,2],[2,3]]
		List<Set<Integer>> input2 = Arrays.asList(ImmutableSet.of(0, 1, 2), ImmutableSet.of(2, 3));
		printMeetings("input2", input2);
		printBestMeetings("output2", bestMeetingsWithSize(input2));

		//[[4,10],[3,4,12],[0,8,9,10,13],[1,5,7],[2,6],[9,4,10,11,12],[11,13]]
		List<Set<Integer>> input3 = Arrays.asList(ImmutableSet.of(4, 10), ImmutableSet.of(3,4,12),
						ImmutableSet.of(0,8,9,10,13), ImmutableSet.of(1,5,7),
						ImmutableSet.of(2,6), ImmutableSet.of(9,4,10,11,12), ImmutableSet.of(11,13));
		printMeetings("input3", input3);
		printBestMeetings("output3", bestMeetingsWithSize(input3));

		//[[6,16,17],[8,9],[1],[7,14,9],[10,5],[2,7],[0,6,7,9],[10,11,5,13,15,16,17],[7,9],[5,9],[2,12,5,6,14,7,15,9],[10,5,14],[1,4,8],[1,3,9],[5]]
		List<Set<Integer>> input4 = Arrays.asList(
						ImmutableSet.of(6,16,17), ImmutableSet.of(8,9), ImmutableSet.of(1),
						ImmutableSet.of(7,14,9), ImmutableSet.of(10,5), ImmutableSet.of(2,7), ImmutableSet.of(0,6,7,9),
						ImmutableSet.of(10,11,5,13,15,16,17), ImmutableSet.of(7,9), ImmutableSet.of(5,9),
						ImmutableSet.of(2,12,5,6,14,7,15,9),
						ImmutableSet.of(10,5,14), ImmutableSet.of(1,4,8), ImmutableSet.of(1,3,9), ImmutableSet.of(5)
		);
		printMeetings("input4", input4);
		printBestMeetings("output5", bestMeetingsWithSize(input4));

   //[[9,16,34],[10,13,18,20,23,28,30,31,32],[4,7,8,11,16,17,19,25,29,36,37],[0,2,23,28],[1,3,7,8,14,15,19,24,25,26,32],[12,28],[16,21,24,33,34],[5,6,10,15,16,17,21,22,24,27,33,34,35]]
		List<Set<Integer>> input5 = Arrays.asList(
						ImmutableSet.of(9,16,34), ImmutableSet.of(10,13,18,20,23,28,30,31,32), ImmutableSet.of(4,7,8,11,16,17,19,25,29,36,37),
						ImmutableSet.of(0,2,23,28), ImmutableSet.of(1,3,7,8,14,15,19,24,25,26,32), ImmutableSet.of(12,28), ImmutableSet.of(16,21,24,33,34),
						ImmutableSet.of(5,6,10,15,16,17,21,22,24,27,33,34,35)
		);
		printMeetings("input5", input5);
		printBestMeetings("output5", bestMeetingsWithSize(input5));

		List<Set<Integer>> input6 = Arrays.asList(
						ImmutableSet.of(55,2), ImmutableSet.of(34,66,60), ImmutableSet.of(15,9,12,82), ImmutableSet.of(39,51,81), ImmutableSet.of(65,69,70), ImmutableSet.of(67,47,58,10,62), ImmutableSet.of(30), ImmutableSet.of(36,7), ImmutableSet.of(0,16), ImmutableSet.of(75,2,20,43), ImmutableSet.of(37,38,44), ImmutableSet.of(34,56,46,79), ImmutableSet.of(26,11,72), ImmutableSet.of(67,47), ImmutableSet.of(16,50), ImmutableSet.of(72), ImmutableSet.of(12), ImmutableSet.of(16,63), ImmutableSet.of(60,18), ImmutableSet.of(64,16), ImmutableSet.of(16,63), ImmutableSet.of(32), ImmutableSet.of(34,16), ImmutableSet.of(16,8), ImmutableSet.of(16,8), ImmutableSet.of(2,3,4,49,52,54,55,75,14,76,22,41,43), ImmutableSet.of(57,30), ImmutableSet.of(64,80,23), ImmutableSet.of(64,69,30), ImmutableSet.of(2,49), ImmutableSet.of(48,16,53), ImmutableSet.of(16,63), ImmutableSet.of(5,16), ImmutableSet.of(69,70), ImmutableSet.of(34,65,69,38,6,30,17), ImmutableSet.of(25,77,59,71,78,19,21,40,12,24), ImmutableSet.of(34,16), ImmutableSet.of(55,66,5,60), ImmutableSet.of(27,14,5,46,76,49), ImmutableSet.of(33,28,69), ImmutableSet.of(25,77,59,71,78,19,40,21,12,24), ImmutableSet.of(5,29), ImmutableSet.of(32), ImmutableSet.of(28,16), ImmutableSet.of(2,14), ImmutableSet.of(37,69,6,30,17,31,73), ImmutableSet.of(32,1,42), ImmutableSet.of(64,26,60,11), ImmutableSet.of(16,13), ImmutableSet.of(16,8), ImmutableSet.of(34,16), ImmutableSet.of(16,44), ImmutableSet.of(9,83), ImmutableSet.of(45,81), ImmutableSet.of(46,76), ImmutableSet.of(46,8,39,51), ImmutableSet.of(8,54), ImmutableSet.of(16,50), ImmutableSet.of(74,81,53), ImmutableSet.of(5,16), ImmutableSet.of(61,23), ImmutableSet.of(65,68,77,81), ImmutableSet.of(74,35,37,6,17)
		);
		printMeetings("input6", input6);
		printBestMeetings("output6", bestMeetingsWithSize(input6));

		List<Set<Integer>> input7 = Arrays.asList(
						ImmutableSet.of(71,61,26), ImmutableSet.of(72,75,76), ImmutableSet.of(73,54,64,10,68), ImmutableSet.of(88,56,69), ImmutableSet.of(71,17,8), ImmutableSet.of(18), ImmutableSet.of(40,17,66,47,13), ImmutableSet.of(40,62,52,88), ImmutableSet.of(11,79,36,58,15), ImmutableSet.of(17,67), ImmutableSet.of(33), ImmutableSet.of(38), ImmutableSet.of(3,4,5,55,59,60,61,82,18,85,26,48,50), ImmutableSet.of(17,84), ImmutableSet.of(74,83,43), ImmutableSet.of(71,89,27), ImmutableSet.of(11,79,46,58), ImmutableSet.of(34,33), ImmutableSet.of(71,75,35), ImmutableSet.of(66,14), ImmutableSet.of(72,33), ImmutableSet.of(75,76), ImmutableSet.of(19,77,27), ImmutableSet.of(40,72,75,44,7,35,20), ImmutableSet.of(18,34), ImmutableSet.of(71,17,33,67), ImmutableSet.of(29,86,65,78,87,22,23,47,16,28), ImmutableSet.of(38,37,27), ImmutableSet.of(30,18,6,52,85,55), ImmutableSet.of(79,28,58), ImmutableSet.of(17,88,69), ImmutableSet.of(76,23), ImmutableSet.of(39,32,75), ImmutableSet.of(29,86,65,78,87,22,47,23,16,28), ImmutableSet.of(11,79,58), ImmutableSet.of(21,88,51), ImmutableSet.of(38), ImmutableSet.of(61,88), ImmutableSet.of(3,18), ImmutableSet.of(39,0,24,23), ImmutableSet.of(33,70), ImmutableSet.of(42,75,7,35,20,37,80), ImmutableSet.of(38,1,49), ImmutableSet.of(31,12,88), ImmutableSet.of(39,40,43), ImmutableSet.of(33,56), ImmutableSet.of(33,25), ImmutableSet.of(33), ImmutableSet.of(2,56), ImmutableSet.of(52,85), ImmutableSet.of(52,9,45,57), ImmutableSet.of(53,33), ImmutableSet.of(66,27), ImmutableSet.of(63,88), ImmutableSet.of(81,41,42,7,20)
		);
		printMeetings("input7", input7);
		printBestMeetings("output7", bestMeetingsWithSize(input7));
	}

	static void printMeetings(String name, List<Set<Integer>> meetings) {
		System.out.println(name + ": " + meetings.stream()
						.map(Set::toString)
						.collect(Collectors.joining(",")));
	}

	static void printBestMeetings(String name, Pair<List<Set<Integer>>, Integer> bestMeetingsWithSize) {
		printMeetings(name, bestMeetingsWithSize.getKey());
		System.out.println("attendees size: " + bestMeetingsWithSize.getValue());
	}

}
