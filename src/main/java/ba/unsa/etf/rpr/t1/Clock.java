package ba.unsa.etf.rpr.t1;

/**
 * Dummy class that represents the clock
 * @author Dino Keco
 * @version 1.0
 * */
public class Clock {

    private int hours;
    private int minutes;
    private int seconds;

    /**
     * basic constructor
     * */
    public Clock(int hours, int minutes, int seconds){
        this.set(hours, minutes, seconds);
    }

    /**
     * set value of the clock
     * */
    public void set(int hours, int minutes, int seconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    /**
     * moves the clock for 1 second forward
     * */
    public void next(){
        this.seconds++;
        if (this.seconds == 60){
            this.seconds = 0;
            this.minutes++;
        }
        if (this.minutes == 60){
            this.minutes = 0;
            this.hours++;
        }
        if (this.hours == 24){
            this.hours = 0;
        }
    }

    /**
     * moves the clock for 1 second backward
     * */
    public void previous(){
        this.seconds--;
        if (this.seconds == -1){
            this.seconds = 59;
            this.minutes--;
        }
        if (this.minutes == -1){
            this.minutes = 59;
            this.hours--;
        }
        if (this.hours==-1){
            this.hours = 23;
        }
    }

    /**
     * move the clock for specified number of seconds
     * @param seconds number of seconds to move. if negative it moves clock backward
     * */
    public void move(int seconds){
        if (seconds > 0){
            for (int i = 0; i < seconds; i++){
                this.next();
            }
        }else{
            for (int i = 0; i < -seconds; i++){
                this.previous();
            }
        }
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    @Override
    public String toString() {
        //return new StringBuilder().append(this.hours).append(":").append(this.minutes).append(":").append(this.seconds).toString();
        return this.hours+":"+this.minutes+":"+this.seconds;
    }

    public static void main(String[] args) {
        Clock c = new Clock(15,30,45);
        System.out.println(c);
        c.next();
        System.out.println(c);
        c.move(-48);
        System.out.println(c);
        c.set(0,0,0);
        System.out.println(c);
    }
}
