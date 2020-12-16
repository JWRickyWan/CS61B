import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;
public class GuitarHero {
    /**
     * @ClassName GuitarHero
     * @Description TODO
     * @Author Yixiang Zhao
     * @Date 2018/8/1 20:46
     * @Version 1.0
     */
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] frequencyList = new GuitarString[37];
        for(int i =0; i<37;i++){
            frequencyList[i] = new GuitarString(440.0*Math.pow(2.0,((i-24.0)/12.0)));
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    frequencyList[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for(int i =0; i< frequencyList.length;i++) {
                sample += frequencyList[i].sample();
            }
            StdAudio.play(sample);


            /* play the sample on standard audio */
            StdAudio.play(sample);

            for(int i=0;i<frequencyList.length;i++){
                frequencyList[i].tic();
        }
    }
}
}
