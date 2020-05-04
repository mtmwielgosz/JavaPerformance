package systems.hedgehog.main;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class BenchmarkPlan {

    @Param({ "100000", "1000000", "10000000" })
    public int arraySize;

    private static final int MAGIC_NUMBER = 123;

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @Fork(value = 5)
    @Warmup(iterations = 10)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int[] regularMethod() {
        int[] intArray = new int[arraySize];
        for (int index = 0; index < arraySize; index++) {
            intArray[index] = MAGIC_NUMBER * index;
        }
        return intArray;
    }

    @Benchmark
    @Fork(value = 5)
    @Warmup(iterations = 10)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int[] exceptionMethod() {
        int[] intArray = new int[arraySize];
        int index = 0;
        try {
            while (true) {
                intArray[index] = MAGIC_NUMBER * index;
                index++;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) { }
        return intArray;
    }

    @Benchmark
    @Fork(value = 5)
    @Warmup(iterations = 10)
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int[] steamMethod() {
        return IntStream.range(0, arraySize).map(index -> MAGIC_NUMBER * index).toArray();
    }
}