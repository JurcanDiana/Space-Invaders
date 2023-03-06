/*
ActionTimer class:
Can be used to keep track of progress timers.
Will tick on a fixed increment defined in GamePanel when the update() method is called.
The isTriggered() will become true once the timer reaches 0.
reset() can be used to reset back to the default time and setTimer() to clear with a new time.
*/

public class ActionTimer {

    private int startTime;//Time used when resetting the timer.
    private int timeRemaining;//The timer's progress that ticks down continually every update().
    private boolean triggered;//True when the timeRemaining has reached 0 and stays true until reset.

    //Sets the initial timer on the timer and makes it ready to begin ticking on updates.
    public ActionTimer(int startTime) {
        this.startTime = startTime;//The time to start with and is used for each reset.
        reset();
    }

    //Ticks the time remaining down on a fixed interval defined in GamePanel.
    //deltaTime - amount of time to update by.
    public void update(int deltaTime) {

        timeRemaining -= deltaTime;

        //If the timer reaches 0 it will set the triggered to true.
        if(timeRemaining <= 0) {
            triggered = true;
        }
    }

    //Gets the current triggered state of the timer.
    public boolean isTriggered() {
        return triggered;//True when the timer has recently triggered.
    }

    //Sets the timer immediately to the specified time and changes the time that will be used for resets.
    public void setTimer(int time) {
        startTime = time;//The timer to set the new timer interval to.
        reset();
    }

    public void reset() {
        timeRemaining = startTime;//Resets the time remaining back to the full interval again.
        triggered = false; //And changes the triggered state back to the default of false.
    }
}
