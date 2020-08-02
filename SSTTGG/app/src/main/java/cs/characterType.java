package cs;

public enum characterType {
	Char(0),
	BackSpace(8),
	Tab(9),
	Enter(13), // 0x0000000D
	Esc(27); // 0x0000001B

	private int value;

	public characterType(int v){
		value=v;
	}

	public int value(){
		return value;
	}
}

