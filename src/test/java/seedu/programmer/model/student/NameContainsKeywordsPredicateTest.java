//package seedu.programmer.model.student;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.programmer.testutil.studentBuilder;
//
//public class NameContainsKeywordsPredicateTest {
//
//    @Test
//    public void equals() {
//        List<String> firstPredicateKeywordList = Collections.singletonList("first");
//        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
//
//        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
//        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);
//
//        // same object -> returns true
//        assertTrue(firstPredicate.equals(firstPredicate));
//
//        // same values -> returns true
//        NameContainsKeywordsPredicate firstPredicateCopy =
//        new NameContainsKeywordsPredicate(firstPredicateKeywordList);
//        assertTrue(firstPredicate.equals(firstPredicateCopy));
//
//        // different types -> returns false
//        assertFalse(firstPredicate.equals(1));
//
//        // null -> returns false
//        assertFalse(firstPredicate.equals(null));
//
//        // different student -> returns false
//        assertFalse(firstPredicate.equals(secondPredicate));
//    }
//
//    @Test
//    public void test_nameContainsKeywords_returnsTrue() {
//        // One keyword
//        NameContainsKeywordsPredicate predicate = ne
//        w NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
//        assertTrue(predicate.test(new studentBuilder().withName("Alice Bob").build()));
//
//        // Multiple keywords
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
//        assertTrue(predicate.test(new studentBuilder().withName("Alice Bob").build()));
//
//        // Only one matching keyword
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
//        assertTrue(predicate.test(new studentBuilder().withName("Alice Carol").build()));
//
//        // Mixed-case keywords
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
//        assertTrue(predicate.test(new studentBuilder().withName("Alice Bob").build()));
//    }
//
//    @Test
//    public void test_nameDoesNotContainKeywords_returnsFalse() {
//        // Zero keywords
//        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
//        assertFalse(predicate.test(new studentBuilder().withName("Alice").build()));
//
//        // Non-matching keyword
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
//        assertFalse(predicate.test(new studentBuilder().withName("Alice Bob").build()));
//
//        // Keywords match phone, email and programmer, but does not match name
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("A0214212H", "B01", "D"));
//        assertFalse(predicate.test(new studentBuilder().withName("Alice").withStudentId("A0214212H")
//                .withClassId("B01").withGrade("D").build()));
//    }
//}