package cn.howe.study_demo;

import org.junit.Test;

import java.util.Arrays;

public class ListSort {

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 1) {
            return null;
        }
        int[] a = new int[2];
        boolean shoot = false;
        for (int i = 0; i < nums.length && !shoot; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    a[0] = i;
                    a[1] = j;
                    shoot = true;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(0/0);
    }
}
