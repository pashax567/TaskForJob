package ru.psu.taskforjob.structure;

import ru.psu.taskforjob.dto.org;
import ru.psu.taskforjob.dto.org;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TreeWork {
    List<org> input;
    Node<org> output;

    public TreeWork(List<org> input) {
        this.input = input;
        output = new Node<>();
    }

    Node<org> work() {
        List<org> result = input.stream().filter(x -> x.getHeadId()==0).collect(Collectors.toList());

        for (org org : result) {
            CreateTree(org, output);
        }
        return output;
    }

    void CreateTree(org dto, Node<org> tree) {
        if (dto == null)
            return;
        tree.data = dto;
        List<org> result = input.stream().filter(x -> x.getHeadId() == dto.getId()).collect(Collectors.toList());
        if (result.size() == 0)
            return;
        tree.child = new ArrayList<>();
        for (org org : result) {
            Node<org> newTree = new Node<>();
            CreateTree(input.stream().filter(x -> x.getId() == org.getId()).findFirst().get(), newTree);
            tree.child.add(newTree);
        }
    }

}
