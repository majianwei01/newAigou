/**
 * @program: JavaSpecialityDeep
 * @author: Mr.Zerah
 * @create: 2018-10-25 22:52
 * @description: 冒泡排序
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
 * 如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复n 次，
 * 就完成了 n 个数据的排序工作。
 **/
public class BubbleSort {
    public void bubbleSort(Integer[] arr, int n) {
        if (n <= 1) return;       //如果只有一个元素就不用排序了
 
        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {        //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) {        //即这两个相邻的数是逆序的，交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }
 
    public static void main(String[] args) {
        Integer arr[] = {2, 4, 7, 6, 8, 5, 9};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr, arr.length);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
