package real_time_scheduling_system.flow_generators;

public class ErlangGenerator implements IRandomGenerator {

    private IRandomGenerator lcgGen;

    private double lyambda;

    private double k;

    private double b = 0.0;

    public ErlangGenerator() {
        this.lyambda = 89.0;
        this.k = 2.0;
        this.lcgGen = new LCGenerator();
    }

    public ErlangGenerator(double lyamda, double k) {
        this.lyambda = lyamda;
        this.k = k;
        this.lcgGen = new LCGenerator();
    }

    public ErlangGenerator(double lyamda, double k, IRandomGenerator lcgGen) {
        this.lyambda = lyamda;
        this.k = k;
        this.lcgGen = lcgGen;
    }

    public double nextRandomValue() {
        double sum = 0.0;

        for (int j = 1; j < k + 1; j++) {
            sum += Math.log(lcgGen.nextRandomValue());
        }
        double res = sum / (-lyambda * k);
        if (res > b) {
            b = res;
        }

        return res;
    }

}
