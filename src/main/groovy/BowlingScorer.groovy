import groovy.transform.TailRecursive

/**
 * Created by dylangrald on 6/27/15.
 */
class BowlingScorer {

    private final List<Integer> pins = new ArrayList<>();
    private final List<BowlingFrame> frames = new ArrayList<BowlingFrame>()
    private int rollNumber = 1

    public void roll(int pinsKnockedDown) {
        if(frames.size() >= 10){
            frames.add(new BowlingFrame(pinsKnockedDown, 0))
        } else {
            if (rollNumber == 2) {
                frames.add(new BowlingFrame(pins.last(), pinsKnockedDown))
                rollNumber = 1
            } else {
                if (pinsKnockedDown == 10) {
                    frames.add(new BowlingFrame(10, 0))
                    rollNumber = 1
                } else {
                    rollNumber = 2
                }
            }
        }
        pins.add(pinsKnockedDown)
    }

    public int totalScore() {
        return totalScoreTailRecursively(1, frames, 0)
    }

    @TailRecursive
    private int totalScoreTailRecursively(int frameNumber, List<BowlingFrame> framesLeftToScore, int totalScore) {
        if(framesLeftToScore.isEmpty()) {
            return totalScore
        }

        def nextFrame = framesLeftToScore.head()
        def remainingFrames = framesLeftToScore.tail()

        totalScore += scoreNextFrame(nextFrame, remainingFrames, frameNumber)
        totalScoreTailRecursively(frameNumber + 1, remainingFrames, totalScore)
    }

    private static int scoreNextFrame(BowlingFrame nextFrame, List<BowlingFrame> restOfBowlingFrames, int frameNumber) {
        def totalForFrame = nextFrame.firstRoll() + nextFrame.secondRoll()

        if(frameNumber >= 10) {
            return totalForFrame
        } else if(nextFrame.isStrike()) {
            return totalForFrame + totalBonusForStrike(restOfBowlingFrames)
        } else if(nextFrame.isSpare()) {
            return totalForFrame + totalBonusForSpare(restOfBowlingFrames)
        } else {
            return totalForFrame
        }
    }

    private static int totalBonusForStrike(List<BowlingFrame> remainingBowlingFrames) {
        if(remainingBowlingFrames.empty) {
            return 0
        }

        def nextFrame = remainingBowlingFrames.head()

        if(nextFrame.isStrike()) {
            def nextNextFrames = remainingBowlingFrames.tail()
            if(nextNextFrames.empty) {
                return nextFrame.firstRoll()
            } else {
                return nextFrame.firstRoll() + nextNextFrames.head().firstRoll()
            }
        } else {
            return nextFrame.firstRoll() + nextFrame.secondRoll()
        }
    }

    private static int totalBonusForSpare(List<BowlingFrame> remainingBowlingFrames) {
        if(remainingBowlingFrames.empty) {
            return 0
        }
        def nextFrame = remainingBowlingFrames.head()
        return nextFrame.firstRoll()
    }
}
