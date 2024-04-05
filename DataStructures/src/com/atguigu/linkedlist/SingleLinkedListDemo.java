package com.atguigu.linkedlist;

import java.time.temporal.Temporal;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);


//        singleLinkedList.list();
        System.out.println(getLenth(singleLinkedList.getHead()));
    }

    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLenth(head);
        if (index < 0 || index > 0) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    public static int getLenth(HeroNode head) {
        int cnt = 0;

        if (head.next == null) {
            return 0;
        }
        HeroNode cur = head.next;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        return cnt;
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
        }
        temp = temp.next;

        if (flag) {
            System.out.printf("备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表尾空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            } else if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
        }
        temp = temp.next;
        if (flag) {
            temp.no = heroNode.no;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
        }
    }

    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    public void list() {
        HeroNode temp = head.next;
        if (head.next == null) {
            System.out.println("链表尾空");
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
