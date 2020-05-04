package systems.hedgehog.main;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        public void exceptionMethod() {
        Integer[] intArray = new Integer[arraySize];
        try {
            for (int index = 0; ; index++) {
                intArray[index] = MAGIC_NUMBER * index;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) { }
    }
}