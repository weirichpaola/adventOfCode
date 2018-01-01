package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var maps map[string]int
var soundMap map[string]int

func main() {
	maps = make(map[string]int)
	soundMap = make(map[string]int)

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
	var rcvValue int
	var jump int
	for i := 0; i < len(list); i++ {
		operation := list[i]

		rcvValue, jump = callOperation(operation)

		if rcvValue != 0 {
			break
		} else if jump != 0 {
			i += jump - 1
		}

	}
	fmt.Println(rcvValue)
}

func callOperation(op string) (int, int) {
	opArray := strings.Split(op, " ")
	rcvValue := 0
	jump := 0
	switch opArray[0] {
	case "snd":
		_ = sound(opArray[1:])
	case "set":
		_ = set(opArray[1:])
	case "add":
		_ = add(opArray[1:])
	case "mul":
		_ = mul(opArray[1:])
	case "mod":
		_ = mod(opArray[1:])
	case "rcv":
		rcvValue = rcv(opArray[1:])
	case "jgz":
		jump = jgz(opArray[1:])
	}
	return rcvValue, jump

}
func sound(op []string) int {
	snd := op[0]
	fmt.Println("snd: " + snd)
	if _, ok := maps[snd]; !ok {
		maps[snd] = 0
	}
	soundMap[snd] = maps[snd]
	return 0
}
func set(op []string) int {
	x := op[0]
	y := op[1]
	fmt.Println("set: " + x + " " + y)
	if res, err := strconv.Atoi(y); err == nil {
		maps[x] = res
	} else {
		if _, ok := maps[y]; !ok {
			maps[x] = 0
			maps[y] = 0
		} else {
			maps[x] = maps[y]
		}
	}
	return 0
}
func add(op []string) int {
	x := op[0]
	y := op[1]
	fmt.Println("add: " + x + " " + y)
	_, okx := maps[x]
	valy, oky := maps[y]
	if res, err := strconv.Atoi(y); err == nil {
		if okx {
			maps[x] += res
		} else {
			maps[x] = res
		}
	} else {

		if oky && okx {
			maps[x] += valy
		} else if oky && !okx {
			maps[x] = valy
		} else if !okx && !oky {
			maps[x] = 0
			maps[y] = 0
		} else {
			maps[y] = 0
		}
	}
	return 0

}
func mul(op []string) int {
	x := op[0]
	y := op[1]
	fmt.Println("mul: " + x + " " + y)

	_, okx := maps[x]
	valy, oky := maps[y]
	if res, err := strconv.Atoi(y); err == nil {
		if okx {
			maps[x] *= res
		} else {
			maps[x] = 0
		}
	} else {
		if oky && okx {
			maps[x] *= valy
		} else if oky && !okx {
			maps[x] = 0
		} else if !okx && !oky {
			maps[x] = 0
			maps[y] = 0
		} else {
			maps[y] = 0
		}

	}
	return 0
}
func mod(op []string) int {
	x := op[0]
	y := op[1]
	fmt.Println("mod: " + x + " " + y)

	_, okx := maps[x]
	valy, oky := maps[y]
	if res, err := strconv.Atoi(y); err == nil {
		if okx {
			maps[x] = maps[x] % res
		} else {
			maps[x] = 0
		}
	} else {
		if oky && okx {
			maps[x] = maps[x] % valy
		} else if oky && !okx {
			maps[x] = 0
		} else if !okx && !oky {
			maps[x] = 0
			maps[y] = 0
		} else {
			maps[y] = 0
		}

	}
	return 0
}
func rcv(op []string) int {
	x := op[0]
	_, ok := maps[x]
	fmt.Println("rcv: " + strconv.Itoa(maps[x]))

	if ok {
		if maps[x] != 0 {
			return soundMap[x]
		}
	}
	return 0

}
func jgz(op []string) int {

	x := op[0]
	y := op[1]
	fmt.Println("jump: " + x + " " + y)

	val, ok := maps[x]
	if ok {
		if val > 0 {
			val, _ := strconv.Atoi(y)
			return val
		}
	}
	return 0
}
