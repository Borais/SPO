package Lexer;

import java.util.regex.Pattern;

public enum Lexem {

    ASSIGN_OP("="),
    ASSIGN_OP1(":="),
    METHOD("\\."),
    FUNC_OP("добавить|убрать|получить|содержит"),
    TYPE_W("новый"),
    TYPE("Сет|Лист"),
    WHILE("пока"),
    FOR("для"),
    IF("если"),
    ELSE("иначе"),
    PRINT("печать"),
    BOOL_OP("<|>|<=|>=|==|!="),
    BOOL("правда|ложь"),
    VAR("[а-яА-Я][а-яА-Я0-9_]*"),
    INC("\\+\\+"),
    DEC("\\--"),
    OP("[*|/|+|-]"),
    NUM("0|[1-9]{1}[0-9]*"),
    LCB("\\{"),
    RCB("\\}"),
    LB("\\("),
    RB("\\)"),
    C_OP(";"),
    GOTO(""),
    GOTO_INDEX("");

    private final Pattern pattern;

    Lexem(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
    public Pattern getPattern()
    {
        return pattern;
    }
}
