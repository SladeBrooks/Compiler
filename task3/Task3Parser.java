import java.util.List;
import java.util.ArrayList;

class Task3Parser implements Parser{

    //find the position of a non nested semicolon
    private int findSemiColon(List<Token> list){
        int position = -1;
        int currentPos = 0;
        boolean found = false;
        boolean openCurly = false;

        while(!found && currentPos <= list.size()-1){
            if(list.get(currentPos) instanceof T_LeftCurlyBracket){
                openCurly = true;
                currentPos++;
            }
            else if(list.get(currentPos) instanceof T_RightCurlyBracket){
                openCurly = false;
                currentPos++;
            }
            else if((list.get(currentPos) instanceof T_Semicolon) && (openCurly != true)){
                position = currentPos;
                found = true;
            }
            else{
                currentPos++;
            }
        }
        return position;
    }

    //Parses Block
    private Block parseBlock(List<Token> input) throws SyntaxException, Task3Exception{
        List<Exp> list= new ArrayList<Exp>();
        parseENE(input.subList(1,input.size()-1), list);
        Block block = new Block(list);
        return block;
    }

    //Parses ENE
    private void parseENE(List<Token> input, List<Exp> list) throws SyntaxException, Task3Exception{
        int semiColonPos = findSemiColon(input);
        if(semiColonPos < 0){
            list.add(parseE(input));
        }
        else if(semiColonPos > 0){
            list.add(parseE(input.subList(0,semiColonPos)));
            if(semiColonPos < input.size()-1){
                parseENE(input.subList(semiColonPos+1,input.size()),list);
            }
        }
        else{
                throw new SyntaxException("SyntaxException parsing ENE:");
        }
    }


    //below 2 methods parse E
    private Exp parseE(List<Token> input) throws SyntaxException, Task3Exception{
        //Handles if the E is a integer or a skip
        if(input.size() == 1){
            //Handles Integer
            if(input.get(0) instanceof T_Integer){
                T_Integer tokenInt = (T_Integer)input.get(0);
                return new IntLiteral(tokenInt.n);
            }
            //Handles skip
            else if(input.get(0) instanceof T_Skip){
                return new Skip();
            }
            //Handles expection if neither
            else{
                throw new SyntaxException("syntax error, E with size 1 could not be identified");
            }
        }
        //Handles if the E is a block
        else if((input.get(0) instanceof T_LeftCurlyBracket) && (input.get(input.size() - 1) instanceof T_RightCurlyBracket)){
            return new BlockExp(parseBlock(input.subList(0,input.size())));
        }
        //Throws exception is something went wrong
        else{
            throw new SyntaxException("syntax exception E could not be identified");
        }
    }

    public Block parse ( List<Token> input ) throws SyntaxException, Task3Exception{
        Block block = parseBlock(input);
        return block;
    }
}
