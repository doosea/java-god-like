package com.dosea.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(5);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加元素");
            System.out.println("g(get):取出元素");
            System.out.println("h(head):显示队列头元素");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    try {
                        System.out.println("请输入一个数字");
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("头部数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}


/**
 * 环形队列
 * <p>
 * front, rear
 * (1) 队列为空的判断条件： front == rear
 * (2) 队列满的判断条件：
 */
class CircleArrayQueue {
    private int maxSize;  // 数组的最大容量
    private int front;  // 队列头
    private int rear;  // 队列尾
    private int[] arr; // 存放数据，模拟队列

    // 构造函数
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public void addQueue(int val) {
        if (isFull()) {
            throw new RuntimeException("队列满了");
        }
        arr[rear] = val;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int res = arr[front];
        front = (front + 1) % maxSize;
        return res;
    }

    /**
     * Queue长度
     *
     * @return
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * 从front 遍历 到rear
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println('空');
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("孔");
        }
        return arr[front];
    }
}
