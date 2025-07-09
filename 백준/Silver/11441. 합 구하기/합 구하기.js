const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const n = Number(input[0]); // 데이터 정보
const num = input[1].split(' ').map(Number);

const m = Number(input[2]); // 구간 정보
const line = input.slice(3, m + 3).map(data => data.split(' ').map(Number));

const prefixSum = [0];
let value = 0;
for(let _n of num) {
    value += _n;
    prefixSum.push(value);
}

let result = "";
for(let [i, j] of line) {
    result += `${prefixSum[j] - prefixSum[i - 1]}\n`;
}
console.log(result);