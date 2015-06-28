

/**
 * Created by dylangrald on 6/27/15.
 */

class BowlingFrame {

    private final int firstRoll
    private final int secondRoll

    public BowlingFrame(Integer firstRoll, Integer secondRoll) {
        this.secondRoll = secondRoll
        this.firstRoll = firstRoll
    }

    int firstRoll() {
        return firstRoll
    }

    int secondRoll() {
        return secondRoll
    }

    public boolean isStrike() {
        return firstRoll() == 10
    }
    public boolean isSpare() {
        return !isStrike() && firstRoll() + secondRoll() == 10
    }
}
