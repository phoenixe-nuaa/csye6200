package edu.neu.csye6200.bg;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import edu.neu.csye6200.ui.RuleSet;




public class BGGenerationSet {

	private RuleSet set;
	// construct the Hash Map to store different generation sets
	// the keys in this Hash Map are rules, and the values are their corresponding stem lists. 
	public static HashMap<RuleSet, ArrayList<Stem>> generationSetMap = new HashMap<RuleSet, ArrayList<Stem>>();
	private BGRule bg;

	
	
	// construct the Hash Map and show the content of the Hash Map
	public void buildSet(BGRule bg, RuleSet set) {
		generationSetMap.put(set, bg.getStemList());
		printSet(set);
	}


	
	// Print the corresponding stems list when you chose one rule
	public void printSet(RuleSet set) {

		System.out.println("==============================================================================");
		if(set == RuleSet.RULE1) {
			System.out.println("                        RULE1 Generation Set");
		}else if(set == RuleSet.RULE2) {
			System.out.println("                        RULE2 Generation Set");
		}else if(set == RuleSet.RULE3) {
			System.out.println("                        RULE3 Generation Set");
		}
		System.out.println("==============================================================================");
		
		for(Stem st: generationSetMap.get(set)) {
			
			System.out.println(st.toString());
		}

	}
	
	
}
