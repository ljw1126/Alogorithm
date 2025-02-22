function dfs(depth, x, y) {
    maxDepth = Math.max(maxDepth, depth);
    for(let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        
        if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
        
        const uniCodeIndex = arr[nx][ny].charCodeAt() - 65;
        if(visited[uniCodeIndex]) continue;
        
        visited[uniCodeIndex] = true;
        dfs(depth + 1, nx, ny);
        visited[uniCodeIndex] = false;
    }
}

const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [r, c] = input[0].split(' ').map(Number); // r : 행, c : 열
const arr = [];
for(let i = 1; i <= r; i++) {
    arr.push(input[i]);
}
// 네 방향
const dx = [-1, 1, 0, 0];
const dy = [0, 0, -1, 1];

const visited = new Array(26).fill(false);
let maxDepth = 0;

// 기준 (0,0) 부터 시작
visited[arr[0][0].charCodeAt() - 65] = true; // Set.has()가 상수 시간이 아니라 시간초과
dfs(1, 0, 0);

console.log(maxDepth);