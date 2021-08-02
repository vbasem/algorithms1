import java.util.List;
import java.util.ArrayList;

class Main {
  private static List<List<Integer>> recurisveAllSubSets;

public static void main(String[] args) {
    System.out.println("Hello world!");
    recurisveAllSubSets = new Main().recurisveAllSubSets(new int[] {1,2,3});
    System.out.println(recurisveAllSubSets);
  }
  
  public List<List<Integer>> recurisveAllSubSets(int[] data) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<Integer>());
    if (data.length == 0) {
      return result;
    }

    return recurisveAllSubSets(data, 0, result);
  }

  public List<List<Integer>> recurisveAllSubSets(int[] nums, int index,List<List<Integer>> data) {
    if (index == nums.length) {
      return data;
    }
    List<List<Integer>> temp = new ArrayList<>();
    
    for (List item : data) {
      List<Integer> sb = new ArrayList<Integer>();
      sb.addAll(item);
      sb.add(nums[index]);
      temp.add(sb);
    }
    data.addAll(temp);
    return recurisveAllSubSets(nums, index + 1, data);
  }
}