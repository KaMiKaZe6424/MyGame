package mygame;

public enum GameState {
	
	PREINIT(0),
	INIT(1),
	MAIN_MENU(2),
	IN_GAME(3),
	TERMINATING(4);
	
	public int id;
	
	GameState(int id) {
		this.id = id;
	}
	
}
