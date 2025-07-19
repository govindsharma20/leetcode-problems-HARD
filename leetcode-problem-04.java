class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m+n];

        int p1 = 0;
        int p2 = 0;
        int idx = 0;

        while(p1<m && p2<n){

            if(nums1[p1]<=nums2[p2]){
                merged[idx++] = nums1[p1++];
            }else{
                merged[idx++] = nums2[p2++];
            }
        }
        while(p1<m){
            merged[idx++] = nums1[p1++];
        }
        while(p2<n){
            merged[idx++] = nums2[p2++];
        }

        int totalLength = m+n;

        if(totalLength % 2 == 1){
            return (double) merged[totalLength/2];
        }
        else{
            return (double) (merged[totalLength/2 - 1] + merged[totalLength/2])/2.0;
        }
    }
}
