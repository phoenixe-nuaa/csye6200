package edu.neu.csye6200.command;

public class InsertTxtCmd extends Command{
	
	private String inputTxt = "";
	private int insertIndex = 0;
	
	// Constructor
	public InsertTxtCmd(StringBuffer doc, String inputTxt, int insertIndex) {
		super(doc);
		// Hang on to input command parms
		this.inputTxt = inputTxt;
		this.insertIndex = insertIndex;
	}
	
	@Override
	public void execute() {
		doc.insert(insertIndex, inputTxt);
	}
	
	@Override
	public void undo() {
		int endIndex = insertIndex + inputTxt.length();
		doc.delete(insertIndex, endIndex);
	}
}