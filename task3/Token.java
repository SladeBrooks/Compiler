interface Token {}

class T_LeftCurlyBracket implements Token {} // represents {
class T_RightCurlyBracket implements Token {} // represents }
class T_Integer implements Token { // represents non-negative numbers like 0, 1, 2, 3, ...
    public int n;
    public T_Integer ( int _n ) { n = _n; } }
class T_Skip implements Token {} // represents skip
class T_Semicolon implements Token {} // represents ;
