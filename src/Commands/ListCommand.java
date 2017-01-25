package Commands;

public abstract class ListCommand {
	
	public abstract Object execute();
	public abstract Object undoExecute();
}
