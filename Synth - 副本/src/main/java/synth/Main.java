package synth;

import synth.cfg.CFG;
import synth.cfg.NonTerminal;
import synth.cfg.Terminal;
import synth.core.Example;
import synth.core.ISynthesizer;
import synth.cfg.Production;
import synth.core.Program;
import synth.core.TopDownEnumSynthesizer;
import synth.util.FileUtils;
import synth.util.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        String examplesFilePath = "examples.txt";
//        //String examplesFilePath = args[0];
//        List<String> lines = FileUtils.readLinesFromFile(examplesFilePath);
//        // parse all examples
//        List<Example> examples = Parser.parseAllExamples(lines);
//        System.out.println(examples.toString());
//        // read the CFG
//        CFG cfg = buildCFG();
//        ISynthesizer synthesizer = new TopDownEnumSynthesizer();
//        Program program = synthesizer.synthesize(cfg, examples);
//        System.out.println(program);
        Main.runBenchmark();
        Main.runBenchmark2();
    }

    public static void runBenchmark() {
        long startTime = System.nanoTime();

        // 在这里实现Benchmark逻辑
        String examplesFilePath = "examples.txt";
        //String examplesFilePath = args[0];
        List<String> lines = FileUtils.readLinesFromFile(examplesFilePath);
        // parse all examples
        List<Example> examples = Parser.parseAllExamples(lines);
        System.out.println(examples.toString());
        // read the CFG
        CFG cfg = buildCFG();
        ISynthesizer synthesizer = new TopDownEnumSynthesizer();
        Program program = synthesizer.synthesize(cfg, examples);
        System.out.println(program);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }

    public static void runBenchmark2() {
        long startTime = System.nanoTime();

        // 在这里实现Benchmark逻辑
        String examplesFilePath = "examples.txt";
        //String examplesFilePath = args[0];
        List<String> lines = FileUtils.readLinesFromFile(examplesFilePath);
        // parse all examples
        List<Example> examples = Parser.parseAllExamples(lines);
        System.out.println(examples.toString());
        // read the CFG
        CFG cfg = buildCFG2();
        ISynthesizer synthesizer = new TopDownEnumSynthesizer();
        Program program = synthesizer.synthesize(cfg, examples);
        System.out.println(program);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Execution time2: " + executionTime + " nanoseconds");
    }

    /**
     * Build the following context-free grammar (CFG):
     * E ::= Ite(B, E, E) | Add(E, E) | Multiply(E, E) | x | y | z | 1 | 2 | 3
     * B ::= Lt(E, E) | Eq(E, E) | And(B, B) | Or(B, B) | Not(B)
     * where x, y, z are variables. 1, 2, 3 are constants. Lt means "less than". Eq means "equals"
     *
     * @return the CFG
     */
    private static CFG buildCFG() {
        NonTerminal startSymbol = new NonTerminal("B");
        Map<NonTerminal, List<Production>> symbolToProductions = new HashMap<>();
        {
            NonTerminal retSymbol = new NonTerminal("E");
            List<Production> prods = new ArrayList<>();
            prods.add(new Production(new NonTerminal("E"), new Terminal("Ite"), List.of(new NonTerminal("B"), new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production(new NonTerminal("E"), new Terminal("Add"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production(new NonTerminal("E"), new Terminal("Multiply"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production(new NonTerminal("E"), new Terminal("x"), Collections.emptyList()));
            prods.add(new Production(new NonTerminal("E"), new Terminal("y"), Collections.emptyList()));
            prods.add(new Production(new NonTerminal("E"), new Terminal("z"), Collections.emptyList()));
            prods.add(new Production(new NonTerminal("E"), new Terminal("1"), Collections.emptyList()));
            prods.add(new Production(new NonTerminal("E"), new Terminal("2"), Collections.emptyList()));
            prods.add(new Production(new NonTerminal("E"), new Terminal("3"), Collections.emptyList()));
            symbolToProductions.put(retSymbol, prods);
        }
        {
            NonTerminal retSymbol = new NonTerminal("B");
            List<Production> prods = new ArrayList<>();
            prods.add(new Production(new NonTerminal("B"), new Terminal("Lt"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production(new NonTerminal("B"), new Terminal("Eq"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production(new NonTerminal("B"), new Terminal("And"), List.of(new NonTerminal("B"), new NonTerminal("B"))));
            prods.add(new Production(new NonTerminal("B"), new Terminal("Or"), List.of(new NonTerminal("B"), new NonTerminal("B"))));
            prods.add(new Production(new NonTerminal("B"), new Terminal("Not"), List.of(new NonTerminal("B"))));
            symbolToProductions.put(retSymbol, prods);
        }
        return new CFG(startSymbol, symbolToProductions);
    }

    /**
     * Build the following context-free grammar (CFG):
     * E ::= Ite(B, E, E) | Add(E, E) | Multiply(E, E) | x | y | z | 1 | 2 | 3
     * B ::= Lt(E, E) | Eq(E, E) | And(B, B) | Or(B, B) | Not(B)
     * where x, y, z are variables. 1, 2, 3 are constants. Lt means "less than". Eq means "equals"
     *
     * @return the CFG
     */
    private static CFG buildCFG2() {
        NonTerminal startSymbol = new NonTerminal("B");
        Map<NonTerminal, List<Production>> symbolToProductions = new HashMap<>();
        {
            NonTerminal retSymbol = new NonTerminal("E");
            List<Production> prods = new ArrayList<>();
            prods.add(new Production("E", new Terminal("Ite"), List.of(new NonTerminal("B"), new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production("E", new Terminal("Add"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production("E", new Terminal("Multiply"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production("E", new Terminal("x"), Collections.emptyList()));
            prods.add(new Production("E", new Terminal("y"), Collections.emptyList()));
            prods.add(new Production("E", new Terminal("z"), Collections.emptyList()));
            prods.add(new Production("E", new Terminal("1"), Collections.emptyList()));
            prods.add(new Production("E", new Terminal("2"), Collections.emptyList()));
            prods.add(new Production("E", new Terminal("3"), Collections.emptyList()));
            symbolToProductions.put(retSymbol, prods);
        }
        {
            NonTerminal retSymbol = new NonTerminal("B");
            List<Production> prods = new ArrayList<>();
            prods.add(new Production("B", new Terminal("Lt"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production("B", new Terminal("Eq"), List.of(new NonTerminal("E"), new NonTerminal("E"))));
            prods.add(new Production("B", new Terminal("And"), List.of(new NonTerminal("B"), new NonTerminal("B"))));
            prods.add(new Production("B", new Terminal("Or"), List.of(new NonTerminal("B"), new NonTerminal("B"))));
            prods.add(new Production("B", new Terminal("Not"), List.of(new NonTerminal("B"))));
            symbolToProductions.put(retSymbol, prods);
        }
        return new CFG(startSymbol, symbolToProductions);
    }
}
