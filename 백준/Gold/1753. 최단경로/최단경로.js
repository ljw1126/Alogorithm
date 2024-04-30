class PriorityQueue {
    constructor(comparator) {
        this._items = [];
        this._comparator = comparator;
    }

    enq(item) {
        this._items.push(item);

        let current = this.size() - 1;
        while(current > 0) {
            let parent = Math.floor((current - 1) / 2);
            
            if(this.compareTo(parent, current) <= 0) break;

            this.swap(parent, current);
            current = parent; 
        }
    }

    moveDown(index) {
        let left = (2 * index) + 1;
        let right = (2 * index) + 2;
        
        if(left >= this.size()) {
            return false;
        } else if(right >= this.size()) {
            if(this.compareTo(index, left) >= 0) {
                return true;
            }
        } else {
            if(this.compareTo(left, right) < 0) {
                if (this.compareTo(index, left) >= 0) {
                    return true;
                }
            } else {
                if (this.compareTo(index, right) >= 0) {
                    return true;
                }
            }
        }
        
        return false; 
    }

    deq() {
        let first = this.peek();
        let last = this._items.pop();
        
        if(this.isEmpty()) return first;

        this._items[0] = last;
        let index = 0;
        
        while(this.moveDown(index)){
            let left = (2 * index) + 1;
            let right = (2 * index) + 2;

            if(right >= this.size()) {
                if (this.compareTo(index, left) >= 0) {
                    this.swap(index, left);
                    index = left;
                }
            } else {
                if(this.compareTo(left, right) < 0) {
                    if (this.compareTo(index, left) >= 0) {
                        this.swap(index, left);
                        index = left;
                    }
                } else {
                    if (this.compareTo(index, right) >= 0) {
                        this.swap(index, right);
                        index = right;
                    }
                }
            }

        }
        
        return first;
    }

    isEmpty() {
        return this.size() === 0;
    }

    peek() {
        if(this.isEmpty()) throw new Error('PriorityQueue is Empty');

        return this._items[0];
    }

    size() {
        return this._items.length;
    }

    swap(a, b) {
        const temp = this._items[a];
        this._items[a] = this._items[b];
        this._items[b] = temp;
    }

    compareTo(a, b) {
        return this._comparator(this._items[a], this._items[b]);
    }
}

function dijkstra(start) {
    let pq = new PriorityQueue((a, b) => a[1] - b[1]); 
    
    pq.enq([start, 0]); // 시작노드, 비용
    distance[start] = 0;
    
    while(pq.size() > 0) {
        let [now, cost] = pq.deq();
        
        if(distance[now] < cost) continue;
        
        for(let [nextNode, nextDistance] of graph[now]) {
            let newCost = distance[now] + nextDistance;
            
            if(distance[nextNode] > newCost) {
                distance[nextNode] = newCost;
                pq.enq([nextNode, newCost]);
            }
        }
    }
}



const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [v, e] = input[0].split(' ').map(Number); 
const k = Number(input[1]); 

const graph = [];
for(let i = 0; i <= v; i++) graph.push([]);

const lines = input.slice(2, e + 2).map(line => line.split(' ').map(Number));
for(let [f, t, c] of lines) {
   graph[f].push([t, c]);
}

const INF = 1e9;
const distance = new Array(v + 1).fill(INF);

dijkstra(k);

console.log(distance.map(value => (value === INF ? "INF" : value)).slice(1).join("\n"));


