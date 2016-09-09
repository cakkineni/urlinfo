package com.ca.urlinfo;

import com.ca.utils.Url;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by cakkinen on 9/8/16.
 */
public class UrlCheckerPerfTest {

    @BeforeClass
    public static void runOnceBeforeClass() {
        try {
            Model model = new Model();
            BufferedReader br = new BufferedReader(new FileReader(UrlCheckerTest.class.getClassLoader().getResource("samples").toURI().getRawPath()));
            String line;
            while ((line = br.readLine()) != null) {
                model.setKey(Url.getHost(line), line);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void
    launchBenchmark() throws Exception {

        Options opt = new OptionsBuilder()
                // Specify which benchmarks to run.
                .include(this.getClass().getName() + ".*")
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(2)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(2)
                .threads(2)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-XX:+UnlockDiagnosticVMOptions")
                        //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }

    // The JMH samples are the best documentation for how to use it
    // http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
    @State(Scope.Thread)
    public static class BenchmarkState {
        UrlChecker checker;

        @Setup(Level.Trial)
        public void
        initialize() {
            checker = new UrlChecker();
        }
    }

    @Benchmark
    public void
    whenFound(BenchmarkState state, Blackhole bh) {
        bh.consume(state.checker.isMalware("geil.alon3.tk", "geil.alon3.tk"));
    }

    @Benchmark
    public void
    whenNotFound(BenchmarkState state, Blackhole bh) {
        bh.consume(state.checker.isMalware("google.com:443", "http://www.google.com:443"));

    }

    @Benchmark
    public void
    whenFoundWithService(BenchmarkState state, Blackhole bh) {
        bh.consume(state.checker.isMalware("google.com:443", "http://www.google.com:443"));
    }

}
