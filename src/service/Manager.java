package service;

import model.Developer;
import model.Tester;
import model.FileProcess;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    List<Developer> developerList;
    List<Tester> testerList;

    public Manager(List<Developer> developerList, List<Tester> testerList) {
        this.developerList = developerList;
        this.testerList = testerList;
    }

    public void addNew(Object object, int choiceObject) {
        List<Object> objectList = new ArrayList<>();
        switch (choiceObject) {
            case 1:
                developerList.add((Developer) object);
                objectList = new ArrayList<Object>(developerList);
                break;
            case 2:
                testerList.add((Tester) object);
                objectList = new ArrayList<Object>(testerList);
                break;
            default:
                System.out.println("NOT FOUND FUNCTION");
        }
        FileProcess.writeObjectToFile(objectList, choiceObject);
    }

    public void showAll(int choiceObject) {
        switch (choiceObject) {
            case 1:
                for (Developer d : developerList)
                    System.out.println(d);
                break;
            case 2:
                for (Tester t : testerList)
                    System.out.println(t);
                break;
            default:
                System.out.println("NOT FOUND FUNCTION");
        }
    }
}
