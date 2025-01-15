package DataObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Core.Entities.Mountain;
import Utilities.Constant;

public class MountainDAO {
    private final List<Mountain> mountainList = new ArrayList<>();
    private final FileManager fileManager;

    public MountainDAO(String fileName) throws Exception {
        this.fileManager = new FileManager(fileName);
        loadData();
    }

    public void loadData() throws Exception {
        String mountainCode, mountain, province, description;
        try {
            mountainList.clear();
            List<String> mountainData = fileManager.readDataFromFile();
            for (String e : mountainData) {
                System.out.println("kllk");
                List<String> mountainSplit = Arrays.asList(e.split(","));
                mountainCode = mountainSplit.get(0).trim();
                mountain = mountainSplit.get(1).trim();
                province = mountainSplit.get(2).trim();
                description = mountainSplit.get(3).trim();
                Mountain mountainObject = new Mountain(mountainCode, mountain, province, description);
                mountainList.add(mountainObject);
                Constant.validMountainCode.add(mountainObject.getMountainCode());
                if (mountainList.isEmpty()) {
                    throw new Exception("Mountain list is empty.");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Mountain> getMountainList() {
        return mountainList;
    }
}
