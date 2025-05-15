#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

long long solve(vector<int>& heights, int start, int end) {
    if (start == end) return heights[start];
    
    int mid = (start + end) / 2;
    long long maxArea = max(solve(heights, start, mid), solve(heights, mid + 1, end));
    
    int left = mid, right = mid + 1;
    int height = min(heights[left], heights[right]);
    maxArea = max(maxArea, (long long)height * 2);
    
    while (start < left || right < end) {
        if (right < end && (left == start || heights[left - 1] < heights[right + 1])) {
            right++;
            height = min(height, heights[right]);
        } else {
            left--;
            height = min(height, heights[left]);
        }
        maxArea = max(maxArea, (long long)height * (right - left + 1));
    }
    
    return maxArea;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    while (true) {
        int n;
        cin >> n;
        if (n == 0) break;
        
        vector<int> heights(n);
        for (int i = 0; i < n; i++) {
            cin >> heights[i];
        }
        
        cout << solve(heights, 0, n - 1) << '\n';
    }
    
    return 0;
}