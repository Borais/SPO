package Stack_Maschine;

import Collections.MyHashSet;
import Collections.MyLinkedList;
import Lexer.Lexem;
import Lexer.Token;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class PolizCalculation {
    public static Map<String, String> types = new HashMap<>();
    public static Map<String, Object> values  = new HashMap<>();

    public static void calculate(LinkedList<Token> poliz) {
        Stack<Token> stack = new Stack<>();
        for (int i = 0; i < poliz.size(); i++) {
            Token token = poliz.get(i);

            if (token.type == Lexem.VAR || token.type == Lexem.NUM || token.type == Lexem.GOTO_INDEX || token.type == Lexem.TYPE) {
                stack.add(token);
            }

            if (token.type == Lexem.TYPE_W) {
                Token type = stack.pop();
                Token var = stack.pop();
                if (!values.containsKey(var.data)) {
                    types.put(var.data, type.data);
                    if (!type.data.equals("Номер")) {
                        values.put(var.data, null);
                    } else {
                        values.put(var.data, 0);
                    }
                }
            }

            if (token.type == Lexem.FUNC_OP) {
                Token t2 = stack.pop();
                Token t1 = stack.pop();
                if (values.get(t1.data) == null) {
                    if (types.get(t1.data).equals("Лист")) {
                        values.put(t1.data, new MyLinkedList());
                    }
                    else if (types.get(t1.data).equals("Сет")) {
                        values.put(t1.data, new MyHashSet());
                    }
                }
                if (types.get(t1.data).equals("Лист")) {
                    if (t2.type == Lexem.NUM) {
                        if (token.data.equals("добавить")) {
                            ((MyLinkedList) values.get(t1.data)).add(t2.data);
                        } else if (token.data.equals("получить")) {
                            Token tmptoken = stack.peek();
                            stack.add(new Token(Lexem.NUM, (String) ((MyLinkedList) values.get(t1.data)).get(Integer.parseInt(t2.data))));
                       //     values.put(tmptoken.data, Integer.parseInt(stack.peek().data));
                        } else if (token.data.equals("убрать")) {
                            ((MyLinkedList) values.get(t1.data)).remove(Integer.parseInt(t2.data));
                        } else if (token.data.equals("содержит")) {
                            ((MyLinkedList) values.get(t1.data)).contains(t2.data);
                        }
                    }
                    else if (t2.type == Lexem.VAR) {
                        if (token.data.equals("добавить")) {
                            ((MyLinkedList) values.get(t1.data)).add(values.get(t2.data));
                        } else if (token.data.equals("получить")) {
                            stack.add(new Token(Lexem.NUM, (String) ((MyLinkedList) values.get(t1.data)).get((Integer)values.get(t2.data))));
                        } else if (token.data.equals("убрать")) {
                            ((MyLinkedList) values.get(t1.data)).remove((Integer)(values.get(t2.data)));
                        } else if (token.data.equals("содержит")) {
                            stack.add(new Token(Lexem.BOOL, Boolean.toString(((MyLinkedList) values.get(t1.data)).contains(values.get(t2.data)))));
                        }
                    }
                }
                else if (types.get(t1.data).equals("Сет")) {
                    if (t2.type == Lexem.NUM) {
                        if (token.data.equals("добавить")) {
                            ((MyHashSet) values.get(t1.data)).add(t2.data);
                        } else if (token.data.equals("убрать")) {
                            ((MyHashSet) values.get(t1.data)).remove(Integer.parseInt(t2.data));
                        } else if (token.data.equals("содержит")) {
                            ((MyHashSet) values.get(t1.data)).contains(t2.data);
                        }
                    }
                    else if (t2.type == Lexem.VAR) {
                        if (token.data.equals("добавить")) {
                            ((MyHashSet) values.get(t1.data)).add(values.get(t2.data));
                        } else if (token.data.equals("убрать")) {
                            ((MyHashSet) values.get(t1.data)).remove(values.get(t2.data));
                        } else if (token.data.equals("содержит")) {
                            stack.add(new Token(Lexem.BOOL, Boolean.toString(((MyHashSet) values.get(t1.data)).contains(values.get(t2.data)))));
                        }
                    }
                }
            }

            if (token.type == Lexem.BOOL_OP) {
                int a1, a2;
                Token t2 = stack.pop();
                Token t1 = stack.pop();
                if (t1.type == Lexem.VAR) {
                    a1 = (Integer)values.get(t1.data);
                }
                else {
                    a1 = Integer.parseInt(t1.data);
                }
                if (t2.type == Lexem.VAR) {
                    a2 = (Integer)values.get(t2.data);
                }
                else {
                    a2 = Integer.parseInt(t2.data);
                }
                if (token.data.equals("==")) {
                    if (a1 == a2) {
                        stack.add(new Token(Lexem.BOOL, "правда"));
                    }
                    else {
                        stack.add(new Token(Lexem.BOOL, "ложь"));
                    }
                }
                if (token.data.equals("<=")) {
                    if (a1 <= a2) {
                        stack.add(new Token(Lexem.BOOL, "правда"));
                    }
                    else {
                        stack.add(new Token(Lexem.BOOL, "ложь"));
                    }
                }
                if (token.data.equals(">=")) {
                    if (a1 >= a2) {
                        stack.add(new Token(Lexem.BOOL, "правда"));
                    }
                    else {
                        stack.add(new Token(Lexem.BOOL, "ложь"));
                    }
                }
                if (token.data.equals("<")) {
                    if (a1 < a2) {
                        stack.add(new Token(Lexem.BOOL, "правда"));
                    }
                    else {
                        stack.add(new Token(Lexem.BOOL, "ложь"));
                    }
                }
                if (token.data.equals(">")) {
                    if (a1 > a2) {
                        stack.add(new Token(Lexem.BOOL, "правда"));
                    }
                    else {
                        stack.add(new Token(Lexem.BOOL, "ложь"));
                    }
                }
                if (token.data.equals("!=")) {
                    if (a1 != a2) {
                        stack.add(new Token(Lexem.BOOL, "правда"));
                    }
                    else {
                        stack.add(new Token(Lexem.BOOL, "ложь"));
                    }
                }
            }

            if (token.type == Lexem.ASSIGN_OP) {
                int a2;
                Token t1, t2;
                if (!stack.empty())
                    t2 = stack.pop();
                else
                    continue;
                if (!stack.empty())
                    t1 = stack.pop();
                else
                    continue;
                if (t2.type == Lexem.VAR) {
                    a2 = (Integer)values.get(t2.data);
                }
                else {
                    a2 = Integer.parseInt(t2.data);
                }
                values.put(t1.data, a2);
            }

            if (token.type == Lexem.OP) {
                int a1, a2;
                Token t1, t2;
                if (!stack.empty())
                    t2 = stack.pop();
                else
                    continue;
                if (!stack.empty())
                    t1 = stack.pop();
                else
                    continue;
                if (t1.type == Lexem.VAR) {
                    a1 = (Integer)values.get(t1.data);
                }
                else {
                    a1 = Integer.parseInt(t1.data);
                }
                if (t2.type == Lexem.VAR) {
                    a2 = (Integer)values.get(t2.data);
                }
                else {
                    a2 = Integer.parseInt(t2.data);
                }
                if (token.data.equals("*")) {
                    stack.add(new Token(Lexem.NUM, Integer.toString(a1 * a2)));
                }
                if (token.data.equals("/")) {
                    stack.add(new Token(Lexem.NUM, Integer.toString(a1 / a2)));
                }
                if (token.data.equals("+")) {
                    stack.add(new Token(Lexem.NUM, Integer.toString(a1 + a2)));
                }
                if (token.data.equals("-")) {
                    stack.add(new Token(Lexem.NUM, Integer.toString(a1 - a2)));
                }
            }

            if (token.type == Lexem.GOTO) {
                Token index;
                if (!stack.empty()) {
                    index = stack.pop();
                }
                else
                {
                    break;
                }
                if (token.data.equals("!F")) {
                    Token res = stack.pop();
                    if (res.data.equals("правда")) {
                        continue;
                    }
                    else {
                        i = Integer.parseInt(index.data) - 1;
                    }
                }
                else {
                    i = Integer.parseInt(index.data) - 1;
                }
            }
        }
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
