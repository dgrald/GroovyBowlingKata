import spock.lang.Specification

/**
 * Created by dylangrald on 6/27/15.
 */
class BowlingFrameSpec extends Specification {

    def "should determine whether it's a spare or not"() {
        when:
        def spare = new BowlingFrame(5,5)

        then:
        spare.isSpare()
        !spare.isStrike()
    }

    def "should determine whether it's a strike or not"() {
        when:
        def strike = new BowlingFrame(10,0)

        then:
        strike.isStrike()
        !strike.isSpare()
    }
}
