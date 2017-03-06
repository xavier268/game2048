/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.game2048;

import com.twiceagain.game2048.board.Board;
import com.twiceagain.game2048.board.BoardImpl;
import com.twiceagain.game2048.util.Evaluator;
import com.twiceagain.game2048.strategy.implement.StrategyParametric;
import com.twiceagain.game2048.util.Serializor;
import java.io.IOException;
import com.twiceagain.game2048.strategy.interfaces.TeacheableStrategy;

/**
 * Implementing a "simulated annealing philosophy" for automatic learning.
 * @author xavier
 * Will typically converge around 3300 with a simple parametric model.
 * 
 Creating empty board
Creating random strategy
Strategy                     		nb	min	med	avg	max	sigma
======================================================================================

Parametric (cool off)    		10000	172	1272	1353	5708	670		0,423 millis/test
Computed score : 1289.24
Computed score : 1678	Temp : 4.85
Computed score : 2207	Temp : 4.7044999999999995
Computed score : 2358	Temp : 4.563364999999999
Computed score : 2495	Temp : 4.426464049999999
Computed score : 2592	Temp : 4.293670128499999
Computed score : 2526	Temp : 4.164860024644999
Computed score : 2787	Temp : 4.039914223905649
Computed score : 2685	Temp : 3.9187167971884795
Computed score : 2737	Temp : 3.801155293272825
Computed score : 2994	Temp : 3.6871206344746406
    ....
Computed score : 3314	Temp : 0.14604650862246865
Computed score : 3251	Temp : 0.1416651133637946
Computed score : 3223	Temp : 0.13741515996288076
Computed score : 3326	Temp : 0.13329270516399433
Computed score : 3421	Temp : 0.1292939240090745
Computed score : 3432	Temp : 0.12541510628880226
Computed score : 3410	Temp : 0.12165265310013819
Computed score : 3461	Temp : 0.11800307350713404
Computed score : 3339	Temp : 0.11446298130192002
Computed score : 3087	Temp : 0.11102909186286242
Computed score : 3297	Temp : 0.10769821910697654
Computed score : 3257	Temp : 0.10446727253376724
Computed score : 3258	Temp : 0.10133325435775421
Computed score : 3422	Temp : 0.09829325672702158
Saved : 'stratParamCooloff'
Parametric improved (cool		10000	268	3512	3340	5516	973		0,803 millis/test
======================================================================================


com.twiceagain.game2048.strategy.implement.StrategyParametric [ size = 4]
 
 * 
 */
public class MainParametricLearnCoolOff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double temperature = 20. ;

        System.out.printf("\nCreating empty board");
        Board b = new BoardImpl(4);

        System.out.printf("\nCreating random strategy");

        TeacheableStrategy strat;
       
        // Do not reload previous attempt ...
        strat = new StrategyParametric(4);
        

        System.out.printf("\nStrategy                     \t%s", Evaluator.statsHeader());
        System.out.printf("\n======================================================================================\n");
        Evaluator.report("Parametric (cool off)", b, strat, 10000);
        System.out.printf("\nComputed score : %s", strat.computeAverageFinalScore(100));
        while(temperature > 0.01) {
            temperature *= 0.99;
            int nb = 20 + (int) (100/temperature);
            //if(nb > 10000) nb = 10000; // cap nb to 1000 for perf reasons.
            strat = strat.improve(nb,  temperature); 
           
            System.out.printf("\nComputed score : %.0f\tNb: %s\tTemp : %.10f", 
                    strat.computeAverageFinalScore(100), 
                    nb,
                    temperature);
        }
        try {
            Serializor.serialize(strat, "stratParamCooloff");
        } catch (IOException ex) {
            System.out.printf("\n%s\n", ex);
        }
        Evaluator.report("Parametric improved (cool off)", b, strat, 10000);
        System.out.printf("\n======================================================================================\n");
        System.out.printf("\n%s", strat);
    }
    
    

}
