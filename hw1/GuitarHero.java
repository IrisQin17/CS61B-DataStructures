import es.datastructur.synthesizer.GuitarString;
public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            double frequncy = 440 * Math.pow(2, (i - 24) / 12);
            strings[i] =  new GuitarString(frequncy);
        }
        GuitarString s = strings[0];
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    s = strings[index];
                    s.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = s.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each guitar string by one step */
            s.tic();
        }

    }


}
