# [Silver II] Sorted Adjacent Differences - 30175 

[문제 링크](https://www.acmicpc.net/problem/30175) 

### 성능 요약

메모리: 50840 KB, 시간: 476 ms

### 분류

해 구성하기, 정렬

### 제출 일자

2025년 10월 16일 14:22:38

### 문제 설명

<p>You have array of $n$ numbers $a_{1}, a_{2}, \ldots, a_{n}$.</p>

<p>Rearrange these numbers to satisfy $|a_{1} - a_{2}| \le |a_{2} - a_{3}| \le \ldots \le |a_{n-1} - a_{n}|$, where $|x|$ denotes absolute value of $x$. It's always possible to find such rearrangement.</p>

<p>Note that all numbers in $a$ are not necessarily different. In other words, some numbers of $a$ may be same.</p>

<p>You have to answer independent $t$ test cases.</p>

### 입력 

 <p>The first line contains a single integer $t$ ($1 \le t \le 10^{4}$) --- the number of test cases.</p>

<p>The first line of each test case contains single integer $n$ ($3 \le n \le 10^{5}$) --- the length of array $a$. It is guaranteed that the sum of values of $n$ over all test cases in the input does not exceed $10^{5}$.</p>

<p>The second line of each test case contains $n$ integers $a_{1}, a_{2}, \ldots, a_{n}$ ($-10^{9} \le a_{i} \le 10^{9}$).</p>

### 출력 

 <p>For each test case, print the rearranged version of array $a$ which satisfies given condition. If there are multiple valid rearrangements, print any of them.</p>

