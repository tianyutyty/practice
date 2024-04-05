package com.atguigu.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedLIst circleSingleLinkedLIst = new CircleSingleLinkedLIst();
        circleSingleLinkedLIst.addBoy(10);
        circleSingleLinkedLIst.showBoy();

    }
}

class CircleSingleLinkedLIst {
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("数值不正确");
            return;
        }
        Boy curboy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curboy = first;
            } else {
                curboy.setNext(boy);
                boy.setNext(first);
                curboy = boy;
            }
        }
    }

    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    public void countBoy(int startNo, int countNum, int nums) {
        Boy help = first;
        while (true) {
            if (help.getNext() == first) {
                break;
            }
            first = first.getNext();
        }
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            help = help.getNext();
        }

        while (true) {
            if (help == first) {
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                help = help.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            help.setNext(first);
            System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());

        }


    }


}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
