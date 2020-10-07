import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

class Task2Lexer implements Lexer{

    public List<Token> lex(String input) throws LexicalException{
        List<Token> tokenList = new ArrayList<Token>();
        //turns all multiple whitespace into single space
        input = input.replaceAll("(\\s)+"," ");
        while(input.length() != 0){

            //removes whitespace at the beginning of the line
            input = input.replaceAll("^(\\s)+","");

            //handles special character: ; ( ) = == < > <= >= , { } := + * - /
            if(Pattern.compile("^[\\>\\<\\=:;\\(\\),\\{\\}\\+\\*\\-/]").matcher(input).find()){
                // handles ==
                if(Pattern.compile("^\\=\\=").matcher(input).find()){
                    input = input.replaceAll("^\\=\\=","");
                    tokenList.add(new T_Equal());
                }
                // handles <=
                else if(Pattern.compile("^\\<\\=").matcher(input).find()){
                    input = input.replaceAll("^\\<\\=","");
                    tokenList.add(new T_LessEq());
                }
                // handles >=
                else if(Pattern.compile("^\\>\\=").matcher(input).find()){
                    input = input.replaceAll("^\\>\\=","");
                    tokenList.add(new T_GreaterEq());
                }
                // handles :=
                else if(Pattern.compile("^:\\=").matcher(input).find()){
                    input = input.replaceAll("^:\\=","");
                    tokenList.add(new T_Assign());
                }
                // handles ;
                else if(Pattern.compile("^;").matcher(input).find()){
                    input = input.replaceAll("^;","");
                    tokenList.add(new T_Semicolon());
                }
                // handles (
                else if(Pattern.compile("^\\(").matcher(input).find()){
                    input = input.replaceAll("^\\(","");
                    tokenList.add(new T_LeftBracket());
                }
                // handles )
                else if(Pattern.compile("^\\)").matcher(input).find()){
                    input = input.replaceAll("^\\)","");
                    tokenList.add(new T_RightBracket());
                }
                // handles =
                else if(Pattern.compile("^\\=").matcher(input).find()){
                    input = input.replaceAll("^\\=","");
                    tokenList.add(new T_EqualDefines());
                }
                // handles <
                else if(Pattern.compile("^\\<").matcher(input).find()){
                    input = input.replaceAll("^\\<","");
                    tokenList.add(new T_LessThan());
                }
                // handles >
                else if(Pattern.compile("^\\>").matcher(input).find()){
                    input = input.replaceAll("^\\>","");
                    tokenList.add(new T_GreaterThan());
                }
                // handles ,
                else if(Pattern.compile("^,").matcher(input).find()){
                    input = input.replaceAll("^,","");
                    tokenList.add(new T_Comma());
                }
                // handles {
                else if(Pattern.compile("^\\{").matcher(input).find()){
                    input = input.replaceAll("^\\{","");
                    tokenList.add(new T_LeftCurlyBracket());
                }
                // handles }
                else if(Pattern.compile("^}").matcher(input).find()){
                    input = input.replaceAll("^}","");
                    tokenList.add(new T_RightCurlyBracket());
                }
                // handles +
                else if(Pattern.compile("^\\+").matcher(input).find()){
                    input = input.replaceAll("^\\+","");
                    tokenList.add(new T_Plus());
                }
                // handles *
                else if(Pattern.compile("^\\*").matcher(input).find()){
                    input = input.replaceAll("^\\*","");
                    tokenList.add(new T_Times());
                }
                // handles -
                else if(Pattern.compile("^\\-").matcher(input).find()){
                    input = input.replaceAll("^\\-","");
                    tokenList.add(new T_Minus());
                }
                // handles /
                else if(Pattern.compile("^/").matcher(input).find()){
                    input = input.replaceAll("^/","");
                    tokenList.add(new T_Div());
                }
                else{
                    throw new LexicalException("LexicalException, unidentified input");
                }
            }

            //Handles Integers
            else if(Pattern.compile("^[0-9]+(\\s|\\z)*").matcher(input).find()){
                Matcher m = Pattern.compile("^\\d+").matcher(input);
                if(m.find()) {
                    tokenList.add(new T_Integer(Integer.parseInt(m.group())));
                }
                input = input.replaceAll("^[0-9]+(\\z|\\s)*","");
            }

            //Handles Key-Words and identifiers
            else if(Pattern.compile("^[a-z]").matcher(input).find()){
                //keywords
                if(Pattern.compile("^(def|if|then|else|skip|while|do|repeat|until|break|continue)([^a-zA-z0-9_]|\\z)").matcher(input).find()){
                    //handles def
                    if(Pattern.compile("^def").matcher(input).find()){
                        input = input.replaceAll("^def","");
                        tokenList.add(new T_Def());
                    }
                    //handles if
                    else if(Pattern.compile("^if").matcher(input).find()){
                        input = input.replaceAll("^if","");
                        tokenList.add(new T_If());
                    }
                    //handles then
                    else if(Pattern.compile("^then").matcher(input).find()){
                        input = input.replaceAll("^then","");
                        tokenList.add(new T_Then());
                    }
                    //handles else
                    else if(Pattern.compile("^else").matcher(input).find()){
                        input = input.replaceAll("^else","");
                        tokenList.add(new T_Else());
                    }
                    //handles skip
                    else if(Pattern.compile("^skip").matcher(input).find()){
                        input = input.replaceAll("^skip","");
                        tokenList.add(new T_Skip());
                    }
                    //handles while
                    else if(Pattern.compile("^while").matcher(input).find()){
                        input = input.replaceAll("^while","");
                        tokenList.add(new T_While());
                    }
                    //handles do
                    else if(Pattern.compile("^do").matcher(input).find()){
                        input = input.replaceAll("^do","");
                        tokenList.add(new T_Do());
                    }
                    //handles reapeat
                    else if(Pattern.compile("^repeat").matcher(input).find()){
                        input = input.replaceAll("^repeat","");
                        tokenList.add(new T_Repeat());
                    }
                    //handles until
                    else if(Pattern.compile("^until").matcher(input).find()){
                        input = input.replaceAll("^until","");
                        tokenList.add(new T_Until());
                    }
                    //handles break
                    else if(Pattern.compile("^break").matcher(input).find()){
                        input = input.replaceAll("^break","");
                        tokenList.add(new T_Break());
                    }
                    //handles continue
                    else if(Pattern.compile("^continue").matcher(input).find()){
                        input = input.replaceAll("^continue","");
                        tokenList.add(new T_Continue());
                    }
                    else{
                        throw new LexicalException("LexicalException, unidentified keyword error");
                    }
                }
                //Identifiers
                else if(Pattern.compile("^[a-z][a-zA-z0-9_]*").matcher(input).find()){

                    Matcher m = Pattern.compile("^[a-z][a-zA-z0-9_]*").matcher(input);
                    if(m.find()) {
                        tokenList.add(new T_Identifier(m.group()));
                    }
                    input = input.replaceAll("^[a-z][a-zA-z0-9_]*","");
                }
                else{
                    throw new LexicalException("LexicalException, unidentified input");
                }
            }
            else{
                input = "";
                throw new LexicalException("LexicalException, unidentified input:"+"'"+input+"'");
            }
            input = input.replaceAll("^(\\s)+","");
        }
        return tokenList;
    }
}
