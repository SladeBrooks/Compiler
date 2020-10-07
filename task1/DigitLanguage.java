class DigitLanguage implements Language{
    Matrix2D matrix2D;
    public DigitLanguage(Matrix2D matrix2D){
        this.matrix2D = matrix2D;
    }
    public Boolean decide(int[] input){
        int currentState = this.matrix2D.initialState();
        for(int i = 0;  i < input.length; i++){

            currentState = matrix2D.nextState(currentState, input[i]);
        }
        return currentState == this.matrix2D.terminalState();
    }
}
