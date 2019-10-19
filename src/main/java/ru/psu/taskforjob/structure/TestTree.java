package ru.psu.taskforjob.structure;

import org.jooq.impl.DSL;
import ru.psu.taskforjob.dto.org;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestTree {
    public static void main(String[] args) {
        List<org> input = new ArrayList<org>() {{
            add(new org(1, "1", 0));
            add(new org(2, "2", 1));
            add(new org(3, "3", 2));
            add(new org(4, "4", 1));
            add(new org(5, "5", 2));
            add(new org(6, "6", 4));
            add(new org(7, "7", 2));
        }};
        TreeWork treeWork = new TreeWork(input);
        Node<org> work = treeWork.work();
    }

}
