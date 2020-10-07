import java.util.List;

class LexicalException extends Exception {
    public String msg;
    public LexicalException ( String _msg ) {
        msg = _msg;
    }
}

class Task2Exception extends Exception {
    public String msg;
    public Task2Exception ( String _msg ) {
        msg = _msg;
    }
}

interface Lexer {
    public List<Token> lex ( String input ) throws LexicalException, Task2Exception;
}

class Task2 {
    public static Lexer create () {

        return new Task2Lexer();
    }
}
