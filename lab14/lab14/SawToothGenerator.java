package lab14;

public class SawToothGenerator implements Generator{
    private final int period;
    private int state;

    public SawToothGenerator(int period){
        this.period = period;
        state = 0;
    }
    @Override
    public double next() {
        state ++;
        return normalize(state);
    }
    private double normalize(int value){
        if(value%period==0){
            return -1;
        }
        return ((value%period)*(2/ (double)period))-1;
    }
}
