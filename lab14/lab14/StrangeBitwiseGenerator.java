package lab14;

public class StrangeBitwiseGenerator implements Generator{
    private final int period;
    private int state;

    public StrangeBitwiseGenerator(int period){
        this.period = period;
        state = 0;
    }
    @Override
    public double next() {
        state ++;
        int weirdState = state & (state >> 3) & (state >> 8) % period;
        return normalize(weirdState);
    }
    private double normalize(int value){
        if(value%period==0){
            return -1;
        }
        return ((value%period)*(2/ (double)period))-1;
    }
}

