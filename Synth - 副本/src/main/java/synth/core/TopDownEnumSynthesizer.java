package synth.core;

import synth.cfg.CFG;
import synth.cfg.Production;
import synth.cfg.Symbol;
import synth.cfg.Terminal;

import java.util.ArrayList;
import java.util.List;

public class TopDownEnumSynthesizer implements ISynthesizer {

    /**
     * Synthesize a program f(x, y, z) based on a context-free grammar and examples
     *
     * @param cfg      the context-free grammar
     * @param examples a list of examples
     * @return the program or null to indicate synthesis failure
     */
    @Override
    public Program synthesize(CFG cfg, List<Example> examples) {
        //        // TODO: implement this method
//        throw new RuntimeException("To be implemented");
        List<Production> productionList = cfg.getProductions(cfg.getStartSymbol());
        List<ASTNode> children = new ArrayList<>();
        for (Production p : productionList) {
            List<ASTNode> children2 = new ArrayList<>();
            for (Symbol symbol: p.getArgumentSymbols()) {
                ASTNode astNode=new ASTNode(symbol,new ArrayList<>());
                children2.add(astNode);
            }
            List<ASTNode> children1 = new ArrayList<>();
            children1.add(new ASTNode(p.getOperator(),children2));
            ASTNode astNode=new ASTNode(p.getReturnSymbol(),children1);
            children.add(astNode);
        }
        ASTNode astNode = new ASTNode(cfg.getStartSymbol(), children);
        Program program = new Program(astNode);
        return program;
    }
}
