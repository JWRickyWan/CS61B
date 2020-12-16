package lab14;

public class AcceleratingSawToothGenerator implements Generator {
    private double period;
    private int state;
    private final double factor;
    private boolean ifRest = false;
    public AcceleratingSawToothGenerator(double period, double factor){
        this.period = period;
        state = 0;
        this.factor = factor;
    }
    @Override
    public double next() {
        state+=1;
        if(ifRest){
            state = 0;
            period=Math.round(period*factor);
        }
        return normalize(state);


    }
    private double normalize(int value){
        if(value%period==0&&value>period){
            ifRest = true;
            return -1;
        }
        ifRest = false;
        return ((value%period)*(2/period))-1;
    }
}
