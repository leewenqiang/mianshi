package com.mrl.mianshi;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCodeTest
 * @Description TODO
 * @Author lwq
 * @Date 2021/2/3 17:46
 * @Version 1.0
 */
public class LeetCodeTest {

    public static void main(String[] args) {
        for (int i : twoSum2(new int[]{2, 7, 11, 15}, 9)) {
            System.out.println(i);
        }
        for (int i : twoSum2(new int[]{1,2}, 6)) {
            System.out.println(i);
        }
    }

    /***
     * 1、两数之和 暴力算法  双重循环 遍历数组中 两两组合 暴力破解 算法复杂度 On方
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(i!=j){
                    int sum =  nums[i]+nums[j];
                    if(target==sum){
                        return new int[]{i,j};
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 暴力破解
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
//                int sum =  nums[i]+nums[j];
//                if(target==sum){
//                    return new int[]{i,j};
//                }
                if( target-nums[i]==nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }


    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {

        //存储 值和下标
        Map<Integer,Integer> map = new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            Integer partner = target-nums[i];
            if(map.containsKey(partner)){
                return new int[]{map.get(partner),i};
            }else{
                map.put(nums[i],i);
            }
        }


       return null;
    }



}
