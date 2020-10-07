interface Matrix2D {
    public int initialState ();
    public int terminalState ();
    public int nextState ( int currentState, int character );
}
