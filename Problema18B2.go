package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var mapsA map[string]int64
var mapsB map[string]int64
var queueA []int64
var queueB []int64

func main() {
	resA := 0
	resB := 0
	mapsA = make(map[string]int64)
	mapsA["p"] = 0
	mapsB = make(map[string]int64)
	mapsB["p"] = 1

	reader := bufio.NewReader(os.Stdin)
	var list []string
	var err error
	var line []byte
	for {
		line, _, err = reader.ReadLine()
		if err != nil {
			break
		}
		list = append(list, string(line))
	}
	waitA := false
	waitB := false
	iA := int(0)
	iB := int(0)

	for iB < len(list) || iA < len(list) {
		fmt.Println("-----------------")
		fmt.Println(waitA)
		fmt.Println(waitB)
		fmt.Println(mapsA)
		fmt.Println(mapsB)
		fmt.Println(queueA)
		fmt.Println(queueB)
		fmt.Println(resB)

		if waitA && waitB {
			fmt.Printf("res: %d \n", resB)
			break
		} else if waitA {
			iB, resB, waitB, waitA = execute(iB, list, mapsB, &queueB, &queueA, waitB, waitA, resB)
		} else {
			iA, resA, waitA, waitB = execute(iA, list, mapsA, &queueA, &queueB, waitA, waitB, resA)
		}
	}
}

func execute(cursor int, operations []string, my_map map[string]int64, my_queue *[]int64, other_queue *[]int64, my_wait bool, other_wait bool, res int) (int, int, bool, bool) {
	for cursor < len(operations) {
		fmt.Println(operations[cursor])
		opArray := strings.Split(operations[cursor], " ")
		x := check_map(opArray[1], my_map)
		y := int64(0)

		if len(opArray) == 3 {
			y = check_map(opArray[2], my_map)
		}

		switch opArray[0] {
		case "snd":
			res = snd(x, other_queue, res)
			other_wait = false
		case "set":
			set(opArray[1], y, my_map)
		case "add":
			add(opArray[1], y, my_map)
		case "mul":
			mul(opArray[1], y, my_map)
		case "mod":
			mod(opArray[1], x, y, my_map)
		case "rcv":
			result_rcv := rcv(opArray[1], my_map, my_queue)
			if result_rcv {
				return cursor, res, true, other_wait
			}
		case "jgz":
			jump := jgz(x, y)
			if jump != 0 {
				cursor += jump
				continue
			}
		}
		cursor++
	}
	return cursor, res, false, other_wait
}

func check_map(x string, hash map[string]int64) int64 {
	resp, err := strconv.Atoi(x)
	if err == nil {
		return int64(resp)
	}

	_, ok := hash[x]
	if !ok {
		hash[x] = int64(0)
	}
	return hash[x]
}

func snd(x int64, other_queue *[]int64, value int) int {
	*other_queue = append(*other_queue, x)
	value++
	return value
}

func set(regX string, y int64, hash map[string]int64) {
	hash[regX] = y
}

func add(regX string, y int64, hash map[string]int64) {
	hash[regX] += y
}

func mul(regX string, y int64, hash map[string]int64) {
	hash[regX] *= y
}

func mod(regX string, x int64, y int64, hash map[string]int64) {
	hash[regX] = x % y
}

func rcv(regX string, hash map[string]int64, my_queue *[]int64) bool {
	if len(*my_queue) == 0 {
		return true
	}
	my_queueVal := *my_queue
	value := my_queueVal[0]
	*my_queue = my_queueVal[1:]
	hash[regX] = value
	return false
}

func jgz(x int64, y int64) int {
	if x > 0 {
		return int(y)
	}
	return 0
}
