package lj.huawei;

import java.util.*;

/**
 * @author guohuanjun1
 * @date 2023/8/6 23:10
 */
public class Test2 {
    public static Map<Integer, Index> indexMap = new HashMap<>(16);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int indexNum = scanner.nextInt();
        int searchIndex = scanner.nextInt();
        for (int i = 0; i < indexNum; i++) {
            int index = scanner.nextInt();
            int size = scanner.nextInt();
            String sonList = scanner.next();
            String substring = sonList.substring(1, sonList.length() - 1);
            Index index1 = new Index();
            index1.index = index;
            index1.size = size;
            if(substring.length() > 0){
                String[] splits = substring.split(",");
                for (String split : splits) {
                    index1.sonIndex = new ArrayList<>();
                    index1.sonIndex.add(Integer.valueOf(split));
                }
            }
            indexMap.put(index, index1);
        }
        System.out.println(getSumSize(0, searchIndex));

    }

    public static int getSumSize(int result,  int searchIndex){
        Index index = indexMap.get(searchIndex);
        result += index.size;
        if(index.sonIndex != null){
            for (Integer sonIndex : index.sonIndex) {
                result = getSumSize(result, sonIndex);
            }
        }
        return result;
    }
    public static class Index{
        public int index;
        public int size;
        public List<Integer> sonIndex;
    }
}
