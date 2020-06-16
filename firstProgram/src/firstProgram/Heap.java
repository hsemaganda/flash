package firstProgram;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Heap {
	private int [] elements;
	private int size;
	private int k ;
	public Heap(int k) {
		elements = new int [10];
		size=0;
		this.k = k;
	}
	private int parent(int index) {
		return (index-1)/k;
	}
	private boolean hasParent(int index) {
		return index>0;
	}
	private void swap(int [] arr, int one, int two) {
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}
	public int peek() {
		return elements[0];
	}
	public void add(int value) {
		if(size == elements.length-1)
			elements = Arrays.copyOf(elements, elements.length*2);
		elements[size] = value;
		int index = size;
		boolean found = false;
		while(!found && hasParent(index)) {
			int parent = parent(index);
			if(elements[index] < elements[parent]) {
				swap(elements, parent, index);
				index = parent;
			}else 
				found = true;
		}
		size++;
	}
	public int [] heapSort(int [] nums) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i: nums)
			pq.add(i);
		for(int i = 0; i< nums.length; i++)
			nums[i] = pq.remove();
		return nums;
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the number of children you wanna have on the heap");
		int k = keyboard.nextInt();
		Heap pq = new Heap(k);
		for(int n = 10; n>=-6; n--)
		pq.add(n);
		System.out.println(Arrays.toString(pq.elements));

	}

}
