import spock.lang.Specification

/**
 * Created by dylangrald on 6/27/15.
 */
class BowlingScorerSpec extends Specification {

    def "all gutter balls should yield a score of 0"() {
        when:
        def bowlingScorer = new BowlingScorer()
        (1..20).each {bowlingScorer.roll(0)}
        def score = bowlingScorer.totalScore()

        then:
        score == 0
    }

    def "all fours should yield a score of 80"() {
        when:
        def bowlingScorer = new BowlingScorer()
        (1..20).each {bowlingScorer.roll(4)}
        def score = bowlingScorer.totalScore()

        then:
        score == 80
    }

    def "three strikes in a row followed by all gutter balls should yield a score of 60"() {
        when:
        def bowlingScorer = new BowlingScorer()
        (1..3).each {bowlingScorer.roll(10)}
        (1..14).each {bowlingScorer.roll(0)}
        def score = bowlingScorer.totalScore()

        then:
        score == 60
    }

    def "all strikes scores a 300"() {
        when:
        def bowlingScorer = new BowlingScorer()
        (1..12).each {bowlingScorer.roll(10)}
        def score = bowlingScorer.totalScore()

        then:
        score == 300
    }

    def "rolling all fives scores a 150"() {
        when:
        def bowlingScorer = new BowlingScorer()
        (1..21).each {bowlingScorer.roll(5)}
        def score = bowlingScorer.totalScore()

        then:
        score == 150
    }
}
