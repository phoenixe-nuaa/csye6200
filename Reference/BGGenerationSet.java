package edu.neu.csye6200.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.SwingUtilities;


public class BGGenerationSet extends Observable implements Runnable {
	
//	private boolean start = false;
//	private boolean pause = false;
//	private boolean reset = false;
	private boolean done = false;
//	private boolean growing = false;
	private boolean pause = false;
	private long duration = 500L;
	
	
	public int maxGntNum; 
	private double deviation;
	private Color color;
	private ArrayList<Stem> stemList;
	private GrowStep step = null;
	private int stepNum;
	private ArrayList<Stem> ssList;
	public RuleSet ruleset;
//	private Thread appthread = null;
//	private Runnable runUI = null;
	private WolfApp application;
//	public BGCanvas bgPanel;

	
	
//	public HashMap<Integer, ArrayList<Stem>> stepMap;

	// construct the Hash Map to store different generation sets
	// the keys in this Hash Map are rules, and the values are their corresponding stem lists. 
//	public static HashMap<RuleSet, ArrayList<Stem>> generationSetMap  = new HashMap<RuleSet, ArrayList<Stem>>();
	private BGRule bg;

	
	public BGGenerationSet() {
		
	}
	
	 
	// 考虑一下怎样使用命令模式
	
	private void setCurrentRule() {
		switch(this.ruleset) {
		// when you chose the RULE1
			case RULE1:{
				this.deviation = Math.toRadians(25);
				this.color = color.blue;
				break;
			}
		// when you chose the RULE2
			case RULE2:{
				this.deviation = Math.toRadians(20);
				this.color = color.blue;
				break;
			}
		// when you chose the RULE3
			case RULE3:{
				this.deviation = Math.toRadians(30);
				this.color = color.blue;
				break;
			}
		// when you chose nothing
			case NULL:{
				break;
			}
		}
	}
	
	

	public void grow() {
		setCurrentRule();
		bg = new BGRule(this.deviation, this.maxGntNum, this.color);
		stemList = new ArrayList<Stem>();
		System.out.println("stemList exist!");
		stemList = bg.grow();
//		System.out.println(stemList);
	}
	
	
	
	public ArrayList<Stem> createNextGnt(int stepNum) {
//		stepNum = 1;
//		step = new GrowStep(stepNum);
		ssList = new ArrayList<Stem>();
		for(Stem st: stemList) {
			if(st.getLength() >= (maxGntNum - stepNum + 1)) {
				ssList.add(st);
			}
		}	
		return ssList;

	}
	
	private void setStep (int StepNum, ArrayList<Stem> ssList) { 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
				setChanged();
				step = new GrowStep(stepNum, ssList);
				System.out.println("step "+ stepNum +" created");
				notifyObservers(step);
				System.out.println("observing "+ stepNum + " step");
			} 
		}); 
	} 
	

	// duration可以通过速度进度条来设定哦~
	private void delay(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	// 考虑使用泛型 Generics


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("bgset starting run");
		grow();
	
		stepNum = 1;
		while(!done) {
			if (!pause) {
				ssList = createNextGnt(stepNum);
				setStep(stepNum, ssList); 
				delay(duration);
				System.out.println("Thread Grow is working");
				stepNum++;
			} else {
				delay(duration);
				System.out.println("pausing.........");
			}
			if(stepNum > maxGntNum) done = true;
		}
		System.out.println("Growing/ Classifying is done");
	}
	
	
	

//	public void showStem() {
//	for(Stem st: this.stemList) {
//		System.out.println(st.toString());
//	}



// construct the Hash Map and show the content of the Hash Map
//public void buildSet(RuleSet ruleset) {
//
//	generationSetMap.put(ruleset, stemList);
//	printSet(ruleset);
//}



// Print the corresponding stems list when you chose one rule
// public void printSet(RuleSet ruleset) {
//	
//
//	System.out.println("==============================================================================");
//	if(ruleset == RuleSet.RULE1) {
//		System.out.println("                        RULE1 Generation Set");
//	}else if(ruleset == RuleSet.RULE2) {
//		System.out.println("                        RULE2 Generation Set");
//	}else if(ruleset == RuleSet.RULE3) {
//		System.out.println("                        RULE3 Generation Set");
//	}
//	System.out.println("==============================================================================");
//	
//	for(Stem st: generationSetMap.get(ruleset)) {
//		System.out.println(st.toString());
//	}
//
//
//}



	public ArrayList<Stem> getStemList() {
		return stemList;
	}



	public void setStemList(ArrayList<Stem> stemList) {
		this.stemList = stemList;
	}

	public static void main(String[] args) {
		BGGenerationSet bgset = new BGGenerationSet();
		
	}
	
	public void setDone(boolean bl) {
		done = bl;
	}




	public boolean isPause() {
		return pause;
	}


	public void setPause(boolean pause) {
		this.pause = pause;
	}


	public long getDuration() {
		return duration;
	}


	public void setDuration(long duration) {
		this.duration = duration;
	}
}
