package com.example.sbotlevskyi.teams.utils;

import com.example.sbotlevskyi.teams.entity.Player;

import java.util.ArrayList;
import java.util.Stack;

public class TeamUtils {

    public static Stack<Player> toStack(ArrayList<Player> list) {
        Stack<Player> stack = new Stack<>();
        for (Player p : list) {
            stack.push(p);
        }
        return stack;
    }

    public static Stack<Player> toStackRevert(ArrayList<Player> list) {
        Stack<Player> stack = new Stack<>();
        for (int i = list.size()-1; i > -1; i--) {
            stack.push(list.get(i));
        }
        return stack;
    }
}
